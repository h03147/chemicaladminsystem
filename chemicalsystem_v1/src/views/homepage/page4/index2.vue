<template>
    <div>
        <!--    <el-row class="elrow1">-->
        <!--        <span>-->
        <!--          <el-button-->
        <!--                  size="small"-->
        <!--                  type="primary"-->
        <!--                  class="elbtnadd1"-->
        <!--                  @click="addNewMedicine()"-->
        <!--          >+入库新药品信息</el-button-->
        <!--          >-->
        <!--        </span>-->
        <!--      <span class="elsearchdiv1">-->
        <!--          <el-input v-model="search" placeholder="请输入要搜索的内容" size="small" class="elinput1"></el-input>-->
        <!--        &lt;!&ndash;          <el-button size="small" type="primary" class="elbtnsearch1"&ndash;&gt;-->
        <!--        &lt;!&ndash;            >搜索</el-button&ndash;&gt;-->
        <!--        &lt;!&ndash;          >&ndash;&gt;-->
        <!--        </span>-->
        <!--    </el-row>-->

        <el-row class="elrow1">
            <el-button
                    class="elbtnadd1"
                    size="small"
                    type="primary"
                    icon="el-icon-plus"
                    @click="handleEdit">
                入库新设备信息
            </el-button>
            <el-input
                    v-model="search"
                    placeholder="请输入要搜索的内容"
                    class="elinput1"
                    size="small"
            >
            </el-input>
        </el-row>

        <template>
            <el-table
                    v-loading="loading"
                    element-loading-text="拼命加载中(๑＞ڡ＜)✿ "
                    :data="handleSearch"
                    border
                    class="eltable1"
                    :header-cell-style="{ background: '#F2F9FF', 'text-align': 'center' }"
                    :cell-style="{ 'text-align': 'center' }"
            >
                <el-table-column fixed type="index" width="50"></el-table-column>
                <el-table-column
                        prop="storagetime"
                        label="入库时间"
                        :show-overflow-tooltip="true">
                    <template slot-scope="scope">
                        <i class="el-icon-date"></i>
                        <span style="margin-left: 0.5vw">{{
                    scope.row.storagetime.substr(0, 16)
                }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="equipment_name" label="设备名称">
                </el-table-column>
                <el-table-column prop="equipment_type" label="设备类型">
                </el-table-column>
                <el-table-column prop="equipment_number" label="设备数量" width="120">
                </el-table-column>
                <el-table-column prop="equipment_position" label="设备柜">
                </el-table-column>
                <el-table-column prop="approver" label="入库人" width="120">
                </el-table-column>
                <el-table-column prop="storageuser" label="审批人" width="120">
                </el-table-column>
                <el-table-column fixed="right" label="操作" width="100">
                    <template slot-scope="scope">
                        <!--            <el-button-->
                        <!--              circle-->
                        <!--              size="mini"-->
                        <!--              type="primary"-->
                        <!--              icon="el-icon-edit"-->
                        <!--              @click="updata(scope.row)"-->
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
        </template>
        <div class="block">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-sizes="[10, 20, 30, 40]"
                    :page-size="equipmentData.size"
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
        name: "index2",

        data() {
            return {
                loading: true, // 加载动画
                result: {},
                total: null,
                equipmentData: {
                    page: 1, // 第几页
                    size: 10, // 每页显示多少条
                    sortType: "ascending", //正序ascending逆序descending
                    sortableFields: "id", //需要排序的字段
                },
                experimentData: [], //设备信息表
                currentPage: 1,
                formInline: {
                    user: "",
                },
                // 搜索框内容
                search: '',
            };
        },
        created() {
            const _this = this;
            // 默认每页十条数据，并且获取第一页的前10条
            axios
                .get(
                    api123.baseURL +
                    "/Laboratoryequipmentmanagement/findlaboratoryequipmentmanagementandsortPageable/1/10/ascending/id"
                )
                .then((response) => {
                    const res = response.data;
                    _this.experimentData = res.content; //记录的内容
                    console.log(res);
                    _this.total = res.totalElements; //总记录数
                    _this.equipmentData.size = res.size; //每页条数

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
                    return this.experimentData.filter(data => {
                        return Object.keys(data).some(key => {
                            return String(data[key]).toLowerCase().indexOf(search) > -1;
                        })
                    });
                }
                return this.experimentData;
            }
        },
        methods: {
            //改变·每页显示的条数
            handleSizeChange(val) {
                // console.log(`每页 ${val} 条`);
                this.equipmentData.size = val; //赋值显示条数给处理分页handleCurrentChange()使用
                const _this = this;
                axios
                    .get(
                        api123.baseURL +
                        "/Laboratoryequipmentmanagement/findlaboratoryequipmentmanagementandsortPageable/" +
                        this.equipmentData.page +
                        "/" +
                        this.equipmentData.size +
                        "/" +
                        this.equipmentData.sortType +
                        "/" +
                        this.equipmentData.sortableFields
                    )
                    .then((response) => {
                        const res = response.data;
                        _this.experimentData = res.content; //记录的内容
                        _this.total = res.totalElements; //总记录数

                        console.log(_this.experimentData);
                    });
            },

            // 处理分页
            handleCurrentChange(val) {
                // console.log(`当前页: ${val}`);
                this.equipmentData.page = val; //赋值第几页给处理当前页handleSizeChange()使用
                const _this = this;
                axios
                    .get(
                        api123.baseURL +
                        "/Laboratoryequipmentmanagement/findlaboratoryequipmentmanagementandsortPageable/" +
                        this.equipmentData.page +
                        "/" +
                        this.equipmentData.size +
                        "/" +
                        this.equipmentData.sortType +
                        "/" +
                        this.equipmentData.sortableFields
                    )
                    .then((response) => {
                        const res = response.data;
                        _this.experimentData = res.content; //记录的内容
                        // _this.total = res.totalElements; //总记录数

                        console.log(_this.experimentData);
                    });
            },
            //添加操作
            handleEdit(row) {
                // console.log(index, row);
                // this.$router.push('/messageupdate') //只用跳转直接写
                //如果需要传参数请用下面的写法
                this.$router.push({
                    path: "/page4adddate2",
                });
            },

            //修改操作
            // updata(row) {
            //   console.log( row);
            //   // this.$router.push('/messageupdate') //只用跳转直接写
            //   //如果需要传参数请用下面的写法
            //   this.$router.push({
            //     path: "/page4update2",
            //     //需要传递的参数
            //     query: {
            //       id: row.id,
            //     },
            //   });
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
                            .get(api123.baseURL + "/Laboratoryequipmentmanagement/deleteEquipmentById/" + row.id)
                            .then(function (resp) {
                            });
                        this.$message.success("删除成功！");
                        location.reload(); //页面刷新
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消删除",
                        });
                    });
            },
            onSubmit() {
                console.log(this.formInline.user);
            },
            handleClick(row) {
                console.log(row);
            },
        },
    };
</script>

<style scoped lang="scss">
    .demo-form-inline {
        float: right;
    }

    .block {
        text-align: center;
        margin-top: 1.5vh;
        margin-bottom: 4.5vh;
    }

    /* 最上面的第一行入库以及搜索框的行样式*/
    .elrow1 {
        width: 85vw;
        display: flex;
        margin: 2.8vh 1.2vw 2.8vh 1.2vw;
    }

    /* 输入框样式*/
    .elinput1 {
        margin: 0 0 0 68vw;
        width: 10.4vw;
    }

    /* 表的样式*/
    .eltable1 {
        width: 97.3%;
        margin: 0 auto;
    }
</style>