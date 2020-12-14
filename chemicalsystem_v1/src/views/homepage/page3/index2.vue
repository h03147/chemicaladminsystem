<template>
  <div>
    <el-row class="elrow1">
        <span>
          <el-button class="elbtnadd1" size="small" type="primary" @click="exportExcel">导出表格</el-button>
        </span>
      <span class="elsearchdiv1">
          <el-input placeholder="请输入要搜索的内容" v-model="search" size="small" class="elinput1"></el-input>
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
            element-loading-text="拼命加载中(❁´ω`❁)"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column type="index" width="55"></el-table-column>
      <el-table-column prop="equipment_name" label="设备名称">
      </el-table-column>
      <el-table-column prop="equipment_type" label="设备类型">
      </el-table-column>
      <el-table-column prop="equipment_number" label="设备数量">
      </el-table-column>
      <el-table-column prop="equipment_position"
                       label="设备位置">
<!--        <template slot-scope="scope">-->
<!--          <el-button type="text"-->
<!--                     @click="dialogTableVisible = true;openDialog(scope.row.equipment_name, scope.row.equipment_type)">-->
<!--            {{scope.row.equipment_position}}-->
<!--          </el-button>-->

<!--          <el-dialog title="当前设备柜中的设备清单" :visible.sync="dialogTableVisible">-->
<!--            <el-table :data="dialogTableData">-->
<!--              <el-table-column property="equipment_name" label="药品名称"></el-table-column>-->
<!--              <el-table-column property="equipment_number" label="药品数量"></el-table-column>-->
<!--              <el-table-column property="storagetime"-->
<!--                               label="入库日期"-->
<!--                               :show-overflow-tooltip="true">-->
<!--                <template slot-scope="scope">-->
<!--                  <i class="el-icon-date"></i>-->
<!--                  <span style="margin-left: 0.5vw">{{-->
<!--                      scope.row.storagetime.substr(0, 19)-->
<!--                    }}</span>-->
<!--                </template>-->
<!--              </el-table-column>-->
<!--            </el-table>-->
<!--          </el-dialog>-->
<!--        </template>-->
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
    name: "index2",
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
    },
    created() {
      const _this = this;
      // 默认每页十条数据，并且获取第一页的前10条
      axios
              .get(
                      api123.baseURL +
                      "/Materialsequipmentmanagement/findmaterialsequipmentandsortPageable/1/10/ascending/id"
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
                        "/Materialsequipmentmanagement/findmaterialsequipmentandsortPageable/" +
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
                        "/Materialsequipmentmanagement/findmaterialsequipmentandsortPageable/" +
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
                    const tHeader = ['设备名称', '设备类型', '设备数量','设备位置'] //表头
                    const title = ['实验设备物资表', '', '', '']  //标题
                    //表头对应字段
                    const filterVal = ['equipment_name', 'equipment_type', 'equipment_number', 'equipment_position']
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
                    const merges = ['A1:D1'] //合并单元格
                    excel.export_json_to_excel({
                      title: title,
                      header: tHeader,
                      data,
                      merges,
                      filename: '实验设备物资表',
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
                        "/Materialsequipmentmanagement/findmaterialsequipmentandsortPageable/" +
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
