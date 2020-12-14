<template>
  <div>
<!--    <div>-->
<!--      <el-row>-->
<!--        <span class="elrow1bigtitlediv1">-->
<!--          {{Bigtitle}}-->
<!--        </span>-->
<!--      </el-row>-->
<!--    </div>-->
    <div class="login-container">
      <el-row class="elrow1bigtitlediv1">
        <span>
          {{Bigtitle}}
        </span>
      </el-row>
      <el-form
              ref="loginForm"
              :model="loginForm"
              :rules="loginRules"
              class="login-form"
              auto-complete="on"
              label-position="left"
      >
        <div class="title-container">
          <h3 class="title">欢迎登录</h3>
        </div>

        <el-form-item prop="usernumber">
          <el-input
                  ref="usernumber"
                  v-model="loginForm.usernumber"
                  placeholder="请输入账号"
                  name="usernumber"
                  type="text"
                  tabindex="1"
                  auto-complete="on"
          >
            <i slot="prefix" class="el-input__icon el-icon-user"></i>
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
                  :key="passwordType"
                  ref="password"
                  v-model="loginForm.password"
                  :type="passwordType"
                  placeholder="请输入密码"
                  name="password"
                  tabindex="2"
                  auto-complete="on"
                  @keyup.enter.native="handleLogin"
          >
            <i slot="prefix" class="el-input__icon el-icon-lock"></i>
          </el-input>
        </el-form-item>

        <el-form-item prop="userrole" class="elformitemrole">
          <el-select
                  v-model="loginForm.userrole"
                  placeholder="权限选择"
                  class="elselectidentity"
          >
            <el-option label="高级管理员" value="高级管理员"></el-option>
            <el-option label="普通管理员" value="普通管理员"></el-option>
            <i slot="prefix" class="el-input__icon el-icon-postcard"></i>
          </el-select>
        </el-form-item>

        <el-button
                type="primary"
                style="width: 100%; margin-bottom: 30px"
                @click.native.prevent="handleLogin"
        >登录</el-button
        >
        <!-- {{api1}} -->
      </el-form>
    </div>
  </div>

</template>

<script>
import axios from "axios";
import api123 from "../../api/global_variable";
const $http = axios.create({
  baseURL: api123.baseURL,
  timeout: 1000,
  headers: { "X-Custom-Header": "foobar" },
});
export default {
  name: "index",
  data() {
    return {
      loginForm: {
        usernumber: "",
        password: "",
        userrole: "",
      },
      loginRules: {
        usernumber: [
          { required: true, trigger: "blur", message: "用户名不能为空！" },
        ],
        password: [
          { required: true, message: "密码不能为空！", trigger: "blur" },
          { min: 6, max: 50, message: "长度不能低于6位", trigger: "blur" },
        ],
        userrole: [
          { required: true, trigger: "blur", message: "请选择权限！" },
        ],
      },
      passwordType: "password",
      api1: this.GLOBAL.baseURL,
      // 主标题
      Bigtitle: '化学品物资管理系统',
    };
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.postLogin();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //自定义验证登录方法
    async postLogin() {
      const { data: res } = await $http.post("/login", this.loginForm);
      if (res.state) {
        $http.post("/Loginlog/saveOneHistory", this.loginForm)
        sessionStorage.setItem("token", res.token);
        sessionStorage.setItem("user_number", this.loginForm.usernumber);
        if (this.loginForm.userrole == "高级管理员") {
          this.$message.success("高级管理员登录成功");
          this.$router.push("/homepage/page1index1");
        } else {
          this.$message.success("普通管理员登录成功");
          this.$router.push("/homepage/page1index1");
        }
      } else {
        this.$message.error(res.msg);
      }
    },
  },
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
#app {
  display: flex;
  align-items: center;
  width: 100%;
  /*height: 1vh;*/
  background: url("../../assets/login.png") center center no-repeat;
  background-size: cover;
}
/* reset element-ui css */
.login-container {
  background-color: rgba(0, 0, 0, 0) !important;
  .el-input {
    display: inline-block;
    /*height: 47px;*/
    width: 100%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #E5EBFF;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;
  /*display: flex;*/
  align-items: center;
  .login-form {
    position: relative;
    width: 27.1vw;
    /*height: 550px;*/
    max-width: 100%;
    margin: 0 auto;
    overflow: hidden;
    background: url("../../assets/login1.png") center center no-repeat;
    padding: 4.6vh 2.6vw;
    background-size: cover;
  }

  .title-container {
    position: relative;
    margin: 40px 0 40px 0;
    .title {
      font-size: 1.7vw;
      letter-spacing: 0.5vw;
      color: $light_gray;
      margin: 0px auto 0 auto;
      text-align: center;
      font-family: Source Han Sans SC;
      font-weight: 500;
      opacity: 0.66;
    }
  }

  .el-icon-user,
  .el-icon-lock,
  .el-icon-postcard {
    font-size: 20px;
  }

  .elselectidentity {
    width: 100%;
    padding: 0;
  }

  .elformitemrole {
    width: 100% !important;
  }

  /* 大标题的字样式*/
  .elrow1bigtitlediv1 {
    position: relative;
    font-size: 2.2vw;
    font-family: Source Han Sans SC;
    font-weight: bold;
    letter-spacing: 0.5vw;
    color: #E5EBFF;
    text-align: center;
    margin: 12vh 0 3.8vh 0;
  }
}
</style>