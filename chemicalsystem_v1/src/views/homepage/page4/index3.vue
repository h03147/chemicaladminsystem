<template>
    <div>
        <el-row class="elrow1">
            <el-col>
                <el-input size="small" placeholder="请输入要搜索的内容"></el-input>
            </el-col>
        </el-row>
        <el-table
                v-loading="loading"
                element-loading-text="拼命加载中(▼へ▼メ)"
                ref="multipleTable"
                :data="AddmedicinetableData"
                border
                tooltip-effect="dark"
                class="eltable1"
                :header-cell-style="{ background: '#F2F9FF', 'text-align': 'center' }"
                :cell-style="{ 'text-align': 'center' }"
                @selection-change="handleSelectionChange"
        >
            <el-table-column type="index" width="55"></el-table-column>
            <el-table-column
                    prop="export_time"
                    label="借出时间"
                    :show-overflow-tooltip="true">
                <template slot-scope="scope">
                    <i class="el-icon-date"></i>
                    <span style="margin-left: 0.5vw">{{
                    scope.row.export_time.substr(0, 16)
                }}</span>
                </template>
            </el-table-column>
            <el-table-column prop="equipment_name" label="设备名称">
            </el-table-column>
            <el-table-column prop="equipment_type" label="设备类型">
            </el-table-column>
            <el-table-column prop="equipment_number" label="设备数量">
            </el-table-column>
            <el-table-column prop="equipment_position" label="设备柜">
            </el-table-column>
            <el-table-column prop="export_user" label="外借人">
            </el-table-column>
            <el-table-column prop="export_approver" label="审批人">
            </el-table-column>
            <el-table-column prop="drugname" label="操作">
                <template slot-scope="scope">
                  <el-button @click="handleClick(scope.row)" type="text" size="small">
                      归还设备
                  </el-button>
<!--                  <el-progress :stroke-width="5"-->
<!--                               :width="40"-->
<!--                               :height="40"-->
<!--                               type="circle"-->
<!--                               :percentage="processnum"-->
<!--                               :color="colors"-->
<!--                               v-if="!processnumshow">-->
<!--                  </el-progress>-->

                  <el-dialog title="当前设备归还中，请稍后" :visible="!processnumshow">
                    <el-progress :stroke-width="5"
                                 :width="124"
                                 :height="124"
                                 type="circle"
                                 :percentage="processnum"
                                 :color="colors">
                    </el-progress>
                  </el-dialog>


                </template>
            </el-table-column>
        </el-table>
        <div class="block">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[10, 20, 30, 40]"
                    :page-size="sortjsontableData.size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import dayjs from "dayjs";
    // 或者 CommonJS
    // var dayjs = require('dayjs');
    dayjs().format();
    import axios from "axios";
    import api123 from "../../../api/global_variable";

    export default {
        name: "index3",
        data() {
            return {
                loading: true, // 加载动画
                value: "",
                total: null,
                day1: "",
                // 正序逆序get请求提交的字段
                sortjsontableData: {
                    page: 1, // 第几页
                    size: 10, // 每页显示多少条
                    sortType: "ascending", //正序ascending逆序descending
                    sortableFields: "id", //需要排序的字段
                },
                AddmedicinetableData: [], // 化学药品管理表
                currentPage: 1,
                formInline: {
                    user: "",
                },
                multipleSelection: [],
                value3: null,
                // 进度条数字
                processnum: 0,
                processnumshow: true,
                // 进度条颜色
                colors: [
                    {color: '#f56c6c', percentage: 20},
                    {color: '#e6a23c', percentage: 40},
                    {color: '#6f7ad3', percentage: 60},
                    {color: '#1989fa', percentage: 99},
                    {color: '#5cb87a', percentage: 100}
                ],
            };
        },
        created() {
            const _this = this;
            // 默认每页十条数据，并且获取第一页的前10条
            axios
                .get(
                    api123.baseURL +
                    "/Exportequipment/findAllexportequipmentnotreturnedpageableandsort/1/10/ascending/id"
                )
                .then((response) => {
                    const res = response.data.SelectDataResult;
                    _this.AddmedicinetableData = res.content; //记录的内容
                    _this.total = res.totalElements; //总记录数
                    _this.sortjsontableData.size = res.size; //每页条数
                    console.log(res);
                    // const day = dayjs().valueOf();
                    // const days = new Date(
                    //   _this.AddmedicinetableData[0].expiredtime
                    // ).getTime();
                    //
                    // console.log(days);
                    // _this.day1 = days - day; //得出时间戳的差值

                    //延迟0.8秒来缓存数据
                    setTimeout(function () {
                        _this.loading = false;
                    }, 1 * 800); //延迟800毫米
                });
        },
        methods: {
            //改变·每页显示的条数
            handleSizeChange(val) {
                // console.log(`每页 ${val} 条`);
                this.sortjsontableData.size = val; //赋值显示条数给处理分页handleCurrentChange()使用
                const _this = this;
                axios
                    .get(
                        api123.baseURL +
                        "/Addnewmedicine/findnewmedicineandsortPageable/" +
                        this.sortjsontableData.page +
                        "/" +
                        this.sortjsontableData.size +
                        "/" +
                        this.sortjsontableData.sortType +
                        "/" +
                        this.sortjsontableData.sortableFields
                    )
                    .then((response) => {
                        const res = response.data;
                        _this.AddmedicinetableData = res.content; //记录的内容
                        // _this.total = res.totalElements; //总记录数
                    });
            },
            // 处理分页
            handleCurrentChange(val) {
                // console.log(`当前页: ${val}`);
                this.sortjsontableData.page = val; //赋值第几页给处理当前页handleSizeChange()使用
                const _this = this;
                axios
                    .get(
                        api123.baseURL +
                        "/Addnewmedicine/findnewmedicineandsortPageable/" +
                        this.sortjsontableData.page +
                        "/" +
                        this.sortjsontableData.size +
                        "/" +
                        this.sortjsontableData.sortType +
                        "/" +
                        this.sortjsontableData.sortableFields
                    )
                    .then((response) => {
                        const res = response.data;
                        _this.AddmedicinetableData = res.content; //记录的内容
                        // _this.total = res.totalElements; //总记录数
                    });
            },
            onSubmit() {
                console.log("submit!");
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            handleClick(row, val2) {
                // console.log('row1');
                // console.log(val2);
                // console.log("row2");
                const _this = this;

                // for(let i = 0; i < 20; i++)
                // {
                //   setTimeout(function () {
                //     _this.processnum += 1;
                //   }, 2000);
                // }
                // for(let i = 0; i < 20; i++)
                // {
                //   setTimeout(function () {
                //     _this.processnum += 1;
                //   }, 1000);
                // }
                // for(let i = 0; i < 60; i++)
                // {
                //   setTimeout(function () {
                //     _this.processnum += 1;
                //   }, 500);
                // }
                this.$confirm("即将归还该条设备信息外借记录, 是否继续?", "提示", {
                    confirmButtonText: "确定归还",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() => {
                      this.processnumshow = false;
                      axios
                          .post(api123.baseURL + "/Exportequipment/returnEquipByEquip", row)
                          .then(function (resp) {
                            if(resp.data === 'success')
                            {
                              for (let i = 0; i < 100; i++) {
                                setTimeout(function () {
                                  _this.processnum += 1;
                                  if (_this.processnum == 100) {
                                    _this.$message.success('归还成功');
                                    setTimeout(function () {
                                      _this.processnumshow = true;
                                      location.reload();
                                    }, 2000);
                                  }
                                }, i * 20);

                              }
                            }else
                            {
                              _this.$message.error("归还失败");
                              _this.processnumshow = true;
                            }
                          });

                        // location.reload(); //页面刷新
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消归还",
                        });
                    });
            },

        },
    };
</script>

<style scoped lang="scss">

    .table-top {
        color: #000 !important;
    }

    .block {
        text-align: center;
        margin-top: 1.5vh;
        margin-bottom: 4.5vh;
    }

    /* 搜索框外包裹的row样式*/
    .elrow1 {
        width: 10.4vw;
        margin: 2.8vh 1.2vw;
        float: right;
    }

    /* 表的样式*/
    .eltable1 {
        width: 97.3%;
        margin: 0 auto;
    }
</style>

