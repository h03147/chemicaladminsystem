<template>
    <div>
        <div>
            <el-row class="elrow1">
                <span>
                    <el-button size="small" type="primary" class="elbtnadd1" @click="addNewMedicine()">+入库新药品信息</el-button>
                </span>
                <span class="elsearchdiv1">
                    <el-input size="small" class="elinput1"></el-input>
                    <el-button size="small" type="primary" class="elbtnsearch1">搜索</el-button>
                </span>
            </el-row>
        </div>

        <div>
            <el-table
                    class="eltable1"
                    v-loading="loading"
                    element-loading-text="拼命加载中"
                    :data="AddmedicinetableData"
                    :header-cell-style="{background:'#F2F9FF', 'text-align':'center'}"
                    :cell-style="{'text-align':'center'}"
                    @sort-change="changeUsertableSort"
                    border>
                <el-table-column
                        prop="id"
                        label="编号"
                        width="100"
                        :sortable="'custom'">
                </el-table-column>
                <el-table-column
                        prop="storagetime"
                        label="入库时间"
                        :show-overflow-tooltip="true"
                        width="120">
                    <template slot-scope="scope">
                        <i class="el-icon-date"></i>
                        <span style="margin-left: 0.5vw">{{ scope.row.storagetime.substr(0, 10) }}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="medicinename"
                        label="药品名称">
                </el-table-column>
                <!-- 设置超长省略用tooltip提示外加substr()方法截断前7个字不显示-->
                <el-table-column
                        prop="medicinetype"
                        label="药品类型">
                </el-table-column>
                <el-table-column
                        prop="medicinenumber"
                        label="药品数量">
                </el-table-column>
                <el-table-column
                        prop="measurement"
                        label="单位">
                </el-table-column>
                <el-table-column
                        prop="medicinebox"
                        label="药品柜">
                </el-table-column>
                <el-table-column
                        prop="expiredtime"
                        :show-overflow-tooltip="true"
                        width="120"
                        label="过期日期">
                    <template slot-scope="scope">
                        <i class="el-icon-date"></i>
                        <span style="margin-left: 0.5vw">{{ scope.row.expiredtime.substr(0, 10) }}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="storageuser"
                        label="入库人">
                </el-table-column>
                <el-table-column
                        prop="approver"
                        label="审批人">
                </el-table-column>

                <el-table-column label="操作" width="150">
                    <template slot-scope="scope">
                        <el-button
                                circle
                                size="mini"
                                type="primary"
                                icon="el-icon-edit"
                                @click="handleEdit(scope.row)"></el-button>
                        <el-button
                                circle
                                size="mini"
                                type="danger"
                                icon="el-icon-delete"
                                @click="handleDelete(scope.row)"></el-button>
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
                    :page-sizes="[8, 16, 32, 64]"
                    :page-size="sortjsontableData.size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
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
                loading:true, // 加载动画
                total: null,
                AddmedicinetableData: [], // 药品表
                currentPage4: 1,
                // 正序逆序get请求提交的字段
                sortjsontableData: {
                    page: 1, // 第几页
                    size: 8, // 每页显示多少条
                    sortType: 'ascending', //正序ascending逆序descending
                    sortableFields: 'id', //需要排序的字段
                }

            }
        },
        created() {
            const _this = this;
            // 默认每页十条数据，并且获取第一页的前10条
            axios.get(api123.baseURL + '/Addnewmedicine/findnewmedicineandsortPageable/1/8/ascending/id').then(response => {
                const res = response.data;
                console.log(res);
                _this.AddmedicinetableData = res.content; //记录的内容
                _this.total = res.totalElements; //总记录数
                _this.sortjsontableData.size = res.size; //每页条数

                //延迟0.8秒来缓存数据
                setTimeout( function(){
                    _this.loading = false;
                }, 1 * 800 );//延迟800毫米
            })
        },
        methods: {
            //改变·每页显示的条数
            handleSizeChange(val) {
                // console.log(`每页 ${val} 条`);
                this.sortjsontableData.size = val; //赋值显示条数给处理分页handleCurrentChange()使用
                const _this = this;
                axios.get(api123.baseURL + '/Addnewmedicine/findnewmedicineandsortPageable/'+this.sortjsontableData.page+'/' +this.sortjsontableData.size+'/'+this.sortjsontableData.sortType+'/'+this.sortjsontableData.sortableFields).then(response => {
                    const res = response.data;
                    _this.AddmedicinetableData = res.content; //记录的内容
                    // _this.total = res.totalElements; //总记录数
                })
            },
            // 处理分页
            handleCurrentChange(val) {
                // console.log(`当前页: ${val}`);
                this.sortjsontableData.page = val; //赋值第几页给处理当前页handleSizeChange()使用
                const _this = this;
                axios.get(api123.baseURL + '/Addnewmedicine/findnewmedicineandsortPageable/'+this.sortjsontableData.page+'/' +this.sortjsontableData.size+'/'+this.sortjsontableData.sortType+'/'+this.sortjsontableData.sortableFields).then(response => {
                    const res = response.data;
                    _this.AddmedicinetableData = res.content; //记录的内容
                    // _this.total = res.totalElements; //总记录数
                })

            },
            //修改操作
            handleEdit(row) {
                // console.log(index, row);
                // this.$router.push('/messageupdate') //只用跳转直接写
                //如果需要传参数请用下面的写法
                this.$router.push({
                    path: '/page2update1',
                    //需要传递的参数
                    query: {
                        id: row.id
                    }
                })
            },
            //删除操作
            handleDelete(row) {
                const _this = this;
                this.$confirm('此操作将永久删除该留言且不可恢复, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.delete(api123.baseURL + '/deleteByUserId/' + row.id).then(function (resp) {

                    });
                    this.$message.success("删除成功！");
                    location.reload();//页面刷新
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
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
                this.$message.info("排序类型为："+sortingType+",排序列字段为:"+columnName);

                axios.get(api123.baseURL + '/Addnewmedicine/findnewmedicineandsortPageable/'+this.sortjsontableData.page+'/' +this.sortjsontableData.size+'/'+this.sortjsontableData.sortType+'/'+this.sortjsontableData.sortableFields).then(response => {
                    // '/userfindAll/' + this.whichpage + '/'+val+''
                    const res = response.data;
                    _this.AddmedicinetableData = res.content; //记录的内容
                    // _this.total = res.totalElements; //总记录数
                })

            },
            // 添加新药跳转
            addNewMedicine() {
                this.$router.push('/page4adddate1')
            }
        }
    }
</script>

<style scoped>
    /*第一行的row样式*/
    .elrow1 {
        /*display: flex;*/
    }

    /*入库新药按钮*/
    .elbtnadd1 {
        width: 7.4vw;
        margin: 2.8vh 0 0 1.2vw;
    }

    /*搜索框加搜索按钮div*/
    .elsearchdiv1{
        float: right;
    }

    /*搜索框*/
    .elinput1 {
        width: 10.4vw;
        margin: 2.8vh 0 0 0;
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