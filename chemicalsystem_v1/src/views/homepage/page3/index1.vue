<template>
    <div>
        <!--    <el-button type="primary" @click="exportExcel">导出表格</el-button>-->
        <!--    <el-form :inline="true" :model="formInline" class="demo-form-inline">-->
        <!--      <el-form-item>-->
        <!--        <el-select v-model="formInline.region" placeholder="全部">-->
        <!--          <el-option label="区域一" value="shanghai"></el-option>-->
        <!--          <el-option label="区域二" value="beijing"></el-option>-->
        <!--        </el-select>-->
        <!--      </el-form-item>-->
        <!--      <el-form-item>-->
        <!--        <el-button type="primary" @click="onSubmit">筛选</el-button>-->
        <!--      </el-form-item>-->
        <!--      <el-form-item>-->
        <!--        <el-input v-model="formInline.user"></el-input>-->
        <!--      </el-form-item>-->
        <!--      <el-form-item>-->
        <!--        <el-button type="primary" @click="onSubmit">搜索</el-button>-->
        <!--      </el-form-item>-->
        <!--    </el-form>-->

        <el-row class="elrow1">
        <span>
          <el-button class="elbtnadd1" size="small" type="primary" @click="exportExcel">导出表格</el-button>
        </span>
            <span class="elsearchdiv1">
          <el-input placeholder="请输入要搜索的内容" v-model="search" size="small" class="elinput1"></el-input>
<!--          <el-button size="small"-->
<!--                     type="primary"-->
<!--                     class="elbtnsearch1"-->
<!--                     @click="handleSearch"-->
<!--          >搜索</el-button-->
<!--          >-->
        </span>
        </el-row>


        <el-table
                class="eltable1"
                ref="multipleTable"
                :data="handleSearch"
                :header-cell-style="{ background: '#F2F9FF', 'text-align': 'center' }"
                :cell-style="{ 'text-align': 'center' }"
                @selection-change="handleSelectionChange"
                v-loading="loading"
                element-loading-text="拼命加载中(✪ω✪)"
        >
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column type="index" width="55"></el-table-column>
            <el-table-column prop="medicine_name" label="药品名称">
            </el-table-column>
            <el-table-column prop="medicine_type" label="药品类型">
            </el-table-column>
            <el-table-column prop="medicine_number" label="药品数量">
                <!--        <template slot-scope="scope">-->
                <!--          <el-button class="btnnumber1" type="text" @click="toLoad(scope.row.medicine_name, scope.row.medicine_box)">{{scope.row.medicine_number}}</el-button>-->
                <!--        </template>-->
            </el-table-column>
            <el-table-column prop="measurement" label="单位">
            </el-table-column>
            <el-table-column prop="medicine_box"
                             label="药柜位置"
            >
                <template slot-scope="scope">
                    <el-button type="text"
                               @click="dialogTableVisible = true;openDialog(scope.row.medicine_name, scope.row.medicine_box)">
                        {{scope.row.medicine_box}}
                    </el-button>

                    <el-dialog title="当前药品在药柜中的数量详情" :visible.sync="dialogTableVisible">
                        <el-table :data="dialogTableData">
                            <el-table-column property="medicinename" label="药品名称"></el-table-column>
                            <el-table-column property="medicinenumber" label="药品数量"></el-table-column>
                            <el-table-column property="storagetime"
                                             label="入库日期"
                                             :show-overflow-tooltip="true">
                                <template slot-scope="scope">
                                    <i class="el-icon-date"></i>
                                    <span style="margin-left: 0.5vw">{{
                      scope.row.storagetime.substr(0, 19)
                    }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column property="expiredtime"
                                             label="过期日期"
                                             :show-overflow-tooltip="true">
                                <template slot-scope="scope">
                                    <i class="el-icon-date"></i>
                                    <span style="margin-left: 0.5vw">{{
                      scope.row.expiredtime.substr(0, 10)
                    }}</span>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-dialog>
                </template>
            </el-table-column>
            <el-table-column prop="expired_time"
                             label="过期日期"
                             :show-overflow-tooltip="true">
                <template slot-scope="scope">
                    <i class="el-icon-date"></i>
                    <span style="margin-left: 0.5vw">{{
                      scope.row.expired_time
                    }}</span>
                </template>
            </el-table-column>
            <el-table-column prop="storage_number_status" label="库存状态">
                <template slot-scope="scope">
                    <el-tag type="success" v-if="scope.row.storage_number_status === '库存正常'">{{scope.row.storage_number_status}}
                    </el-tag>
                    <el-tag type="danger" v-else-if="scope.row.storage_number_status === '库存不足'">
                        {{scope.row.storage_number_status}}
                    </el-tag>
                    <el-tag type="danger" v-else>{{scope.row.storage_number_status}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="expired_time_status" label="日期状态">
                <template slot-scope="scope">
                    <el-tag type="success" v-if="scope.row.expired_time_status === '日期正常'">
                        {{scope.row.expired_time_status}}
                    </el-tag>
                    <el-tag type="primary" v-else-if="scope.row.expired_time_status === '预过期'">
                        {{scope.row.expired_time_status}}
                    </el-tag>
                    <el-tag type="danger" v-else>{{scope.row.expired_time_status}}</el-tag>
                </template>
            </el-table-column>
            <!--      <el-table-column prop="medicinenumber" width="100" label="药品保质状态">-->
            <!--        <template slot-scope="scope">-->
            <!--            <el-tag v-if="scope.row.medicinenumber > 99">正常</el-tag>-->
            <!--            <el-tag type="danger" v-else>库存不足</el-tag></template>-->
            <!--      </el-table-column>-->
            <!--      <el-table-column prop="day1" width="100%" label="药品保质状态">-->
            <!--        <template slot-scope="scope">{{-->
            <!--          scope.row.day1 >= 432000000 ? "正常" : "过期"-->
            <!--        }}</template>-->
            <!--      </el-table-column>-->
            <!--      <el-table-column prop="drugname" width="100%" label="操作">-->
            <!--        <template slot-scope="scope">-->
            <!--          <el-button @click="handleClick(scope.row)" type="text" size="small"-->
            <!--            >修改</el-button-->
            <!--          >-->
            <!--        </template>-->
            <!--      </el-table-column>-->
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
    // 调用封装好的导出excel js类
    // import { exportExcel } from '../../../exportexcel/xlsxUtils.js'
    // 自定义选中excel 引用封装类
    import Blob from '@/excel/Blob'
    import Export2Excel from '@/excel/Export2Excel.js'

    const $http = axios.create({
        baseURL: api123.baseURL,
        timeout: 1000,
        headers: {'X-Custom-Header': 'foobar'}
    });


    export default {
        name: "index1",
        data() {
            return {
                loading: true, // 加载动画
                status: true,
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
                    region: "",
                },
                multipleSelection: [], // 多选选中的数据1
                selectList: [], // 多选选中的数据2
                value3: null,
                // 自定义表格数据
                approver1: {},
                // 药柜位置的点击模态框状态判断
                dialogTableVisible: false,
                // 药品柜请求回调接受数据
                dialogTableData: [],
                // 搜索框的内容
                search: '',


            };
        },
        mounted() {
            // this.$refs.dialog.openDialog();
            // this.toLoad();
            //   let btns = document.getElementsByClassName("btnnumber1")
            //   btns.forEach(element => {
            //       element.click()
            //   });
            //   this.toLoad();
        },
        created() {
            const _this = this;
            // 默认每页十条数据，并且获取第一页的前10条
            axios
                .get(
                    api123.baseURL +
                    "/Materialsmedicinemanagement/findmaterialsmedicineandsortPageable/1/10/ascending/id"
                )
                .then((response) => {
                    const res = response.data;
                    console.log(res);
                    _this.AddmedicinetableData = res.SelectDataResult.content; //记录的内容
                    _this.total = res.SelectDataResult.totalElements; //总记录数
                    _this.sortjsontableData.size = res.SelectDataResult.size; //每页条数
                    console.log(res);

                    //延迟0.8秒来缓存数据
                    setTimeout(function () {
                        _this.loading = false;
                    }, 1 * 2000); //延迟1000毫米
                    // const day = dayjs().valueOf();
                    // const days = new Date(
                    //   _this.AddmedicinetableData[0].expiredtime
                    // ).getTime();
                    //
                    // console.log(days);
                    // _this.day1 = days - day; //得出时间戳的差值
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
                        "/Materialsmedicinemanagement/findmaterialsmedicineandsortPageable/" +
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
                        _this.AddmedicinetableData = res.SelectDataResult.content; //记录的内容
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
                        "/Materialsmedicinemanagement/findmaterialsmedicineandsortPageable/" +
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
                        _this.AddmedicinetableData = res.SelectDataResult.content; //记录的内容
                        // _this.total = res.totalElements; //总记录数
                    });
            },
            onSubmit() {
                console.log("submit!");
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                this.selectList = val;
            },
            handleClick(row) {
                console.log(row);
            },
            // 导出成excel表格
            // async page3index1exportExcel() {
            //   const _this = this;
            //   // const params = {
            //   //   pageNum: this.pageNum,
            //   //   pageSize: this.pageSize
            //   // }
            //   // params[this.queryForm.searchType] = this.queryForm.searchInput
            //   console.log('导出excel1');
            //   this.exportLoading = true
            //   try {
            //     console.log(api123.baseURL);
            //     const  res = await axios.get(api123.baseURL + "/Addnewmedicine/findnewmedicineandsortPageable/1/10/ascending/id")
            //     console.log(res.data.content);
            //     // res.data.content.forEach(item => {
            //     //   // item.approver = item.approver.map(o => o.name).join(',')
            //     //   console.log('导出approver' + item.approver);
            //     //   _this.approver1 = item.approver;
            //     //
            //     // })
            //     // -----------------------------------------------------
            //     // await exportExcel(res.data, {
            //     //   name: '姓名',
            //     //   sex: '性别',
            //     //   idCard: {
            //     //     name: '身份证号',
            //     //     wch: 18
            //     //   },
            //     //   birthday: {
            //     //     name: '出生日期',
            //     //     wch: 14
            //     //   },
            //     //   primaryContactPhone: {
            //     //     name: '联系人号码',
            //     //     wch: 11
            //     //   },
            //     //   primaryContactName: '联系人姓名',
            //     //   relation: '联系人关系',
            //     //   nickName: '昵称',
            //     //   school: '就读学校',
            //     //   grade: '年级',
            //     //   address: {
            //     //     name: '地址',
            //     //     wpx: 160,
            //     //     alignment: { wrapText: true }
            //     //   },
            //     //   remark: {
            //     //     name: '备注',
            //     //     wpx: 240,
            //     //     alignment: { wrapText: true }
            //     //   },
            //     //   courseStr: {
            //     //     name: '报读课程',
            //     //     wpx: 120,
            //     //     font: { color: { rgb: 'ffff0000' } },
            //     //     alignment: { wrapText: true }
            //     //   },
            //     //   classStr: {
            //     //     name: '班级',
            //     //     wpx: 120,
            //     //     font: { color: { rgb: 'ffff0000' } },
            //     //     alignment: { wrapText: true }
            //     //   }
            //     // }, '学员信息表.xlsx', {
            //     //   'E4': {
            //     //     fill: { fgColor: { rgb: 'FFFF0000' } }
            //     //   }
            //     // })
            //     // -----------------------------------------------------
            //     await exportExcel(res.data.content, {
            //
            //       expiredtime: {
            //         name: 'expiredtime',
            //         wch: 14
            //       },
            //       approver: {
            //         name: 'approver',
            //         wch: 14
            //       }
            //     }, '学员信息表.xlsx', {
            //       'E4': {
            //         fill: { fgColor: { rgb: 'FFFF0000' } }
            //       }
            //     })
            //   } catch (error) {
            //     console.log('后端数据没执行');
            //   }
            //   this.exportLoading = false
            // },

            // 导出excel方法二 带选中行
            // handleExport() {
            //   if (this.selectList.length === 0) {
            //     this.$message({
            //       message: '请至少选择一条需要导出的数据',
            //       type: 'warning'
            //     });
            //     return;
            //   }
            //   this.$confirm('该操作将导出选中的数据，是否继续', '提示', {
            //     confirmButtonText: '确定',
            //     cancelButtonText: '取消',
            //     type: 'warning'
            //   }).then(() => {
            //     //导出
            //     require.ensure([], () => {
            //       const { export_json_to_excel } = require('@/excel/Export2Excel');   //注意这个Export2Excel路径
            //       const tableHeader = ['药品名称', '药品类型'];   // 设置Excel表格的表头
            //       const filterVal = ['medicinename', 'medicinetype'];  // 跟表格表头对应的绑定的prop
            //       const merges = ["A1:C1"];
            //       let list = this.filterTableData(JSON.parse(JSON.stringify(this.selectList)));   // this.selectList为选中的要导出的数据，用filterTableData方法先处理一下数据格式(要进行深度拷贝，以免过滤的时候，影响页面上展示的数据)，再存到list
            //       let data = this.formatJson(filterVal, list);
            //       export_json_to_excel(tableHeader, data, '简历');    //最后一个是导出表格的名字
            //     })
            //   }).catch(() => { });
            // },
            // formatJson(filterVal, Data) {
            //   return Data.map(v => filterVal.map(j => v[j]))
            // },
            // 导出数据前对数据处理
            // filterTableData(data) {
            //   data.forEach(item => {
            //     // item.applyDate = shortTime(item.applyDate)   // 将导出的时间转换成'YYYY-MM-DD'格式
            //     // item.workAge = item.workAge + '年'
            //   })
            //   return data;
            // },

            // 导出excel方法三 带选中行 带表头样式
            exportExcel() {
                if (this.selectList.length === 0) {
                    this.$message({
                        message: '请至少选择一条需要导出的数据',
                        type: 'warning'
                    });
                    return;
                }

                this.$confirm("该操作将导出选中的数据，是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() => {
                        import('@/excel/excelOut').then(excel => {
                            const tHeader = ['药品名称', '药瓶类型', '药品数量', '单位', '药柜位置', '过期日期', '库存状态', '日期状态'] //表头
                            const title = ['化学药品物资表', '', '', '', '', '', '', '']  //标题
                            //表头对应字段
                            const filterVal = ['medicine_name', 'medicine_type', 'medicine_number', 'measurement', 'medicine_box', 'expired_time', 'storage_number_status', 'expired_time_status']
                            let list = this.filterTableData(JSON.parse(JSON.stringify(this.selectList)));
                            let data = this.formatJson(filterVal, list);
                            data.map(item => {
                                // console.log(item)
                                item.map((i, index) => {
                                    if (!i) {
                                        item[index] = ''
                                    }
                                })
                            })
                            const merges = ['A1:H1'] //合并单元格
                            excel.export_json_to_excel({
                                title: title,
                                header: tHeader,
                                data,
                                merges,
                                filename: '化学药品物资表',
                                autoWidth: true,
                                bookType: 'xlsx',
                                myRowFont: '2'
                            })
                        })
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消导出表格操作",
                        });
                    });
            },
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]))
            },
            // 导出数据前对数据处理
            filterTableData(data) {
                data.forEach(item => {
                    // item.applyDate = shortTime(item.applyDate)   // 将导出的时间转换成'YYYY-MM-DD'格式
                    // item.workAge = item.workAge + '年'
                })
                return data;
            },

            // dialog模态框的open回调方法
            openDialog(val1, val2) {
                console.log('调用了opendialog()' + val1 + val2);
                const _this = this;
                axios
                    .get(
                        api123.baseURL +
                        "/Materialsmedicinemanagement/findmaterialsmedicinebycondition/" +
                        val1 +
                        "/" +
                        val2
                    )
                    .then((response) => {
                        const res = response.data;
                        // _this.AddmedicinetableData = res.SelectDataResult.content; //记录的内容
                        // _this.total = res.SelectDataResult.totalElements; //总记录数
                        // _this.sortjsontableData.size = res.SelectDataResult.size; //每页条数
                        _this.dialogTableData = res.findByConditiona_Resultexport123;
                        console.log(res);
                    });

            },
            // 当每行加载时的方法
            toLoad(val1, val2) {
                console.log('我刷新了' + val1 + val2);
            },


        },
        computed: {
            // 搜索筛选的方法
            handleSearch() {
                console.log('search')
                let search = this.search;
                if(search) {
                    return this.AddmedicinetableData.filter(data => {
                        return Object.keys(data).some(key => {
                            return String(data[key]).toLowerCase().indexOf(search) > -1
                        })
                    });
                }
                return this.AddmedicinetableData;
            }
        },

    };
</script>

<style scoped lang="scss">
    .demo-form-inline {
        float: right;
    }

    .table-top {
        color: #000 !important;
    }

    .block {
        text-align: center;
        margin-top: 1.5vh;
        margin-bottom: 4.5vh;
    }

    /*入库新药按钮*/
    .elbtnadd1 {
        width: 7.4vw;
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

</style>
