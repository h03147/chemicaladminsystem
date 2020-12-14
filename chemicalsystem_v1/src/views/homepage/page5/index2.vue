<template>
    <div>

        <div>
            <el-row class="elrow1">
                <span>
                  <el-button
                          size="small"
                          type="primary"
                          class="elbtnadd1"
                          @click="exportEquipment">
                      +出库设备信息
                  </el-button>
                </span>
                <span>
                  <el-button
                          size="small"
                          type="success"
                          class="elbtnadd1"
                          @click="downloadExcel">
                      下载Excel模板
                  </el-button>
                </span>
                <span class="elsearchdiv1">
                  <el-input v-model="search" placeholder="请输入要搜索的内容" size="small" class="elinput1"></el-input>
                </span>
            </el-row>
        </div>

        <div>
            <el-table
                    class="eltable1"
                    v-loading="loading"
                    element-loading-text="拼命加载中ヽ(･ω･´ﾒ)"
                    :data="handleSearch"
                    :header-cell-style="{ background: '#F2F9FF', 'text-align': 'center' }"
                    :cell-style="{ 'text-align': 'center' }"
                    @sort-change="changeUsertableSort"
                    border
            >
                <el-table-column
                        prop="id"
                        label="编号"
                        :sortable="'custom'"
                >
                </el-table-column>
                <el-table-column
                        prop="export_time"
                        label="出库时间"
                        :show-overflow-tooltip="true"
                >
                    <template slot-scope="scope">
                        <i class="el-icon-date"></i>
                        <span style="margin-left: 0.5vw">{{
              scope.row.export_time.substr(0, 16)
            }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="equipment_name" label="设备名称">
                </el-table-column>
                <!-- 设置超长省略用tooltip提示外加substr()方法截断前7个字不显示-->
                <el-table-column prop="equipment_type" label="设备类型">
                </el-table-column>
                <el-table-column prop="equipment_number" label="设备数量">
                </el-table-column>
                <el-table-column prop="equipment_position" label="设备柜"></el-table-column>
                <el-table-column prop="export_user" label="出库人"></el-table-column>
                <el-table-column prop="export_approver" label="审批人"></el-table-column>

                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <!--            <el-button-->
                        <!--              circle-->
                        <!--              size="mini"-->
                        <!--              type="primary"-->
                        <!--              icon="el-icon-edit"-->
                        <!--              @click="update(scope.row)"-->
                        <!--            ></el-button>-->
                        <el-button
                                circle
                                size="mini"
                                type="danger"
                                icon="el-icon-delete"
                                @click="handleDelete(scope.row)"
                        ></el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div class="divpagenation">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage4"
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
    import axios from "axios";
    import api123 from "../../../api/global_variable";

    export default {
        name: "index1",

        data() {
            return {
                loading: true, // 加载动画
                total: null,
                ExportequipmenttableData: [], // 设备表
                currentPage4: 1,
                // 正序逆序get请求提交的字段
                sortjsontableData: {
                    page: 1, // 第几页
                    size: 10, // 每页显示多少条
                    sortType: "ascending", //正序ascending逆序descending
                    sortableFields: "id", //需要排序的字段
                },
                search: '',
            };
        },
        created() {
            const _this = this;
            // 默认每页十条数据，并且获取第一页的前10条
            axios
                .get(
                    api123.baseURL +
                    "/Exportequipment/findexportequipmentandsortPageable/1/10/ascending/id"
                )
                .then((response) => {
                    const res = response.data;
                    console.log(res);
                    _this.ExportequipmenttableData = res.SelectDataResult.content; //记录的内容
                    _this.total = res.SelectDataResult.totalElements; //总记录数
                    _this.sortjsontableData.size = res.SelectDataResult.size; //每页条数

                    //延迟0.8秒来缓存数据
                    setTimeout(function () {
                        _this.loading = false;
                    }, 1 * 800); //延迟800毫米
                });
        },
        computed: {
            handleSearch() {
                let search = this.search;
                if (search) {
                    return this.ExportequipmenttableData.filter(data => {
                        return Object.keys(data).some(key => {
                            return String(data[key]).toLowerCase().indexOf(search) > -1;
                        })
                    });
                }
                return this.ExportequipmenttableData;
            }
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
                        "/Exportequipment/findexportequipmentandsortPageable/" +
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
                        _this.ExportequipmenttableData = res.SelectDataResult.content; //记录的内容
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
                        "/Exportequipment/findexportequipmentandsortPageable/" +
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
                        _this.ExportequipmenttableData = res.SelectDataResult.content; //记录的内容
                        // _this.total = res.totalElements; //总记录数
                    });
            },
            //修改操作
            // update(row) {
            //     console.log(row);
            //     // this.$router.push('/messageupdate') //只用跳转直接写
            //     //如果需要传参数请用下面的写法
            //     this.$router.push({
            //         path: "/page4update1",
            //         //需要传递的参数
            //         query: {
            //             id: row.id,
            //         },
            //     });
            // },
            //删除操作
            handleDelete(row) {
                const _this = this;
                this.$confirm("此操作将永久删除该留言且不可恢复, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() => {
                        axios
                            .get(api123.baseURL + "/Exportequipment/deleteByEquipId/" + row.id)
                            .then(function (resp) {
                                if(resp.data.delete_status === true)
                                {
                                    _this.$message.success("删除成功！");

                                    setTimeout(function () {
                                        location.reload();
                                    }, 1 * 1000); // 延迟1000ms刷新
                                }else
                                {
                                    _this.$message.error("删除失败！请刷新后重试");
                                }

                            });
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消删除",
                        });
                    });
            },
            // 与后端通讯进行逆序排序
            changeUsertableSort(column) {
                // this.$message.info(column);
                // console.log("这是column对象"+column);
                //获取字段名称和排序类型
                const _this = this;
                let columnName = column.prop;
                let sortingType = column.order;
                // this.sortjsontableData.page = this.whichpage;
                // this.sortjsontableData.size = this.currentpagesize;
                this.sortjsontableData.sortType = sortingType;
                this.sortjsontableData.sortableFields = columnName;
                this.$message.info(
                    "排序类型为：" + sortingType + ",排序列字段为:" + columnName
                );

                axios
                    .get(
                        api123.baseURL +
                        "/Exportequipment/findexportequipmentandsortPageable/" +
                        this.sortjsontableData.page +
                        "/" +
                        this.sortjsontableData.size +
                        "/" +
                        this.sortjsontableData.sortType +
                        "/" +
                        this.sortjsontableData.sortableFields
                    )
                    .then((response) => {
                        // '/userfindAll/' + this.whichpage + '/'+val+''
                        const res = response.data;
                        _this.ExportequipmenttableData = res.SelectDataResult.content; //记录的内容
                        // _this.total = res.totalElements; //总记录数
                    });
            },
            // 添加新药跳转
            exportEquipment() {
                this.$router.push("/page5exportdate2");
            },
            // 下载excel模板
            downloadExcel() {
                window.location.href = api123.baseURL + "/chemicalfiles/8A60C905D8F6C958A2C1E7E867E526FA.xlsx";
            },
        },
    };
</script>

<style scoped>
    /*第一行的row样式*/
    .elrow1 {
        /*display: flex;*/
    }

    /*入库新药按钮*/
    .elbtnadd1 {
        /*width: 7.4vw;*/
        margin: 2.8vh 0 0 1.2vw;
    }

    /*搜索框加搜索按钮div*/
    .elsearchdiv1 {
        float: right;
    }

    /*搜索框*/
    .elinput1 {
        width: 10.4vw;
        margin: 2.8vh 1.2vw 0 0;
    }

    /*搜索按钮*/
    .elbtnsearch1 {
        width: 5.2vw;
        margin: 2.8vh 1.2vw 0 0.6vw;
    }

    /*药品表*/
    .eltable1 {
        width: 85vw;
        margin: 1.9vh 1.2vw;
    }

    /*下面的分页标签div的样式*/
    .divpagenation {
        text-align: center;
        margin-top: 1.5vh;
        margin-bottom: 4.5vh;
    }
</style>
