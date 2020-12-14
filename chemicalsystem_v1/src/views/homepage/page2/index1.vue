<template>
  <div>
    <!--        上面的添加操作-->
    <div>
      <div class="divinputinsert">
        <el-form
          :inline="true"
          :model="insertUsertableData"
          :rules="rules"
          ref="insertUsertableData"
          class="demo-form-inline"
        >
          <el-form-item label="人员姓名" prop="username">
            <el-input
              size="small"
              v-model="insertUsertableData.username"
              placeholder="请输入姓名"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="人员账号"
            style="margin-left: 3.2vw"
            prop="usernumber"
          >
            <el-input
              size="small"
              v-model="insertUsertableData.usernumber"
              placeholder="请输入账号"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="人员密码"
            style="margin-left: 3.2vw"
            prop="password"
          >
            <el-input
              size="small"
              type="password"
              v-model="insertUsertableData.password"
              placeholder="请输入密码"
            ></el-input>
          </el-form-item>
          <el-form-item
            label="身份类型"
            style="margin-left: 3.2vw"
            prop="userrole"
          >
            <el-select
              size="small"
              v-model="insertUsertableData.userrole"
              placeholder="请选择身份类别"
            >
              <el-option label="高级管理员" value="高级管理员"></el-option>
              <el-option label="普通管理员" value="普通管理员"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
      <div class="divinsertbutton">
        <el-button
          size="small"
          type="primary"
          @click="submitForm('insertUsertableData')"
          style="width: 5.2vw"
          >添加</el-button
        >
        <el-button
          size="small"
          @click="resetForm('insertUsertableData')"
          style="width: 5.2vw; margin-left: 3vw"
          >清空</el-button
        >
      </div>
    </div>
    <HR class="hrline"></HR>
    <!--        下面的表格-->
    <div class="elshowtablediv">
      <div>
        <el-table
          class="eltable1"
          ref="filterTable"
          v-loading="loading"
          element-loading-text="拼命加载中"
          :data="UsertableData"
          :header-cell-style="{ background: '#F2F9FF', 'text-align': 'center' }"
          :cell-style="{ 'text-align': 'center' }"
          @sort-change="changeUsertableSort"
          style="width: 85vw"
          border
        >
          <el-table-column
            prop="id"
            label="编号"
            width="100"
            :sortable="'custom'"
          >
          </el-table-column>
          <el-table-column prop="username" label="姓名"> </el-table-column>
          <el-table-column prop="usernumber" label="账号"> </el-table-column>
          <!-- 设置超长省略用tooltip提示外加substr()方法截断前7个字不显示-->
          <el-table-column
            prop="password"
            :show-overflow-tooltip="true"
            label="密码"
          >
            <template slot-scope="scope">
              <span>{{ scope.row.password.substr(7) }}</span>
            </template>
          </el-table-column>
          <!--                    <el-table-column-->
          <!--                            prop="userrole"-->
          <!--                            label="身份类型">-->
          <!--                    </el-table-column>-->

          <el-table-column
            prop="userrole"
            label="身份类型"
            width="110"
            column-key="date"
            :filters="filterTablecolumn"
            :filter-method="filterHandler"
          >
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.userrole === '高级管理员' ? 'warning' : ''"
                disable-transitions
                >{{ scope.row.userrole }}</el-tag
              >
            </template>
          </el-table-column>

          <el-table-column label="操作" width="200">
            <template slot-scope="scope">
              <el-button
                circle
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="handleEdit(scope.row)"
              ></el-button>
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
          :page-sizes="[8, 16, 32, 64]"
          :page-size="sortjsontableData.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </div>
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
      // currentpagesize: null, // 每页显示多少条
      // whichpage: 1, // 第几页
      UsertableData: [], // 查询表的数据载体
      filterTablecolumn: [
        { text: "高级管理员", value: "高级管理员" },
        { text: "普通管理员", value: "普通管理员" },
      ], // 某一列做固定选择内容筛选
      currentPage4: 1,
      insertUsertableData: {
        username: null,
        usernumber: null,
        password: null,
        userrole: null,
      }, // 插入操作的数据载体
      rules: {
        username: [
          { required: true, message: "姓名不能为空！", trigger: "blur" },
          {
            min: 1,
            max: 32,
            message: "长度在 1 到 32 个字符",
            trigger: "change",
          },
        ],
        usernumber: [
          { required: true, message: "账号不能为空！", trigger: "blur" },
          {
            min: 1,
            max: 32,
            message: "长度在 1 到 32 个字符",
            trigger: "change",
          },
        ],
        password: [
          { required: true, message: "密码不能为空！", trigger: "blur" },
          {
            min: 6,
            max: 50,
            message: "长度在 6 到 50 个字符",
            trigger: "change",
          },
        ],
        userrole: [
          { required: true, message: "身份类型不能为空!", trigger: "change" },
        ],
      }, //校验
      // 正序逆序get请求提交的字段
      sortjsontableData: {
        page: 1, // 第几页
        size: 8, // 每页显示多少条
        sortType: "ascending", //正序ascending逆序descending
        sortableFields: "id", //需要排序的字段
      },
    };
  },

  created() {
    const _this = this;
    // 默认每页十条数据，并且获取第一页的前10条
    axios.get(api123.baseURL + "/userfindAll/1/8").then((response) => {
      const res = response.data;
      console.log(res);
      _this.UsertableData = res.content; //记录的内容
      _this.total = res.totalElements; //总记录数
      _this.sortjsontableData.size = res.size; //每页条数

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
            "/findandsortPageable/" +
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
          _this.UsertableData = res.content; //记录的内容
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
            "/findandsortPageable/" +
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
          _this.UsertableData = res.content; //记录的内容
          // _this.total = res.totalElements; //总记录数
        });
    },
    // 添加人员信息按钮事件
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // this.$message.success("校验成功！")
          const _this = this;
          axios
            .post(api123.baseURL + "/addsystemuser", this.insertUsertableData)
            .then(function (resp) {
              if (resp.data == "添加人员信息成功") {
                _this.$message.success(resp.data);
              } else {
                _this.$message.error(resp.data);
              }
            });
        } else {
          this.$message.error("Please input correct data!");
          return false;
        }
      });
    },
    //重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //修改操作
    handleEdit(row) {
      // console.log(index, row);
      // this.$router.push('/messageupdate') //只用跳转直接写
      //如果需要传参数请用下面的写法
      this.$router.push({
        path: "/page2update1",
        //需要传递的参数
        query: {
          id: row.id,
        },
      });
    },
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
            .delete(api123.baseURL + "/deleteByUserId/" + row.id)
            .then(function (resp) {});
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
            "/findandsortPageable/" +
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
          _this.UsertableData = res.content; //记录的内容
          // _this.total = res.totalElements; //总记录数
        });
    },
    // 身份类型列筛选
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
  },
};
</script>

<style scoped>
/*插入输入框div的样式*/
.divinputinsert {
  /*margin-left: 1.2vw;*/
  /*margin-top: 2.8vh;*/
  margin: 2.8vh 0 0 1.2vw; /*写两个值 就是 上下 和 左右 四个值 上 右 下 左*/
}

/*插入按钮div的样式*/
.divinsertbutton {
  /*width: 87.5vw;*/
  /*margin: 0 auto;*/
  text-align: center; /*让按钮居中*/
}

/*分割线*/
.hrline {
  width: 85vw;
  color: #cccccc;
  margin: 2.8vh 1.2vw;
  opacity: 0.3;
}

/*显示的表格样式*/
.elshowtablediv {
  margin-left: 1.2vw;
}

/*下面的分页标签div的样式*/
.divpagenation {
  text-align: center;
  margin-top: 1.5vh;
  margin-bottom: 4.5vh;
}

/*表格的外加样式*/
.eltable1 {
  /*max-height: 57vh;*/
}
</style>