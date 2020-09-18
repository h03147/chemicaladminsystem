<template>
    <div class="login-container">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">

            <div class="title-container">
                <h3 class="title">化学药品管理系统</h3>
            </div>

            <el-form-item prop="usernumber">
                <el-input
                        ref="usernumber"
                        v-model="loginForm.usernumber"
                        placeholder="Usernumber"
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
                        placeholder="Password"
                        name="password"
                        tabindex="2"
                        auto-complete="on"
                        @keyup.enter.native="handleLogin"
                >
                    <i slot="prefix" class="el-input__icon el-icon-lock"></i>
                </el-input>
            </el-form-item>

            <el-form-item prop="userrole" class="elformitemrole">
                <el-select v-model="loginForm.userrole" placeholder="请选择登录身份" class="elselectidentity">
                    <el-option label="高级管理员" value="admin"></el-option>
                    <el-option label="普通管理员" value="editor"></el-option>
                    <i slot="prefix" class="el-input__icon el-icon-postcard"></i>
                </el-select>
            </el-form-item>

            <el-button type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录</el-button>
            {{api1}}

        </el-form>
    </div>
</template>

<script>
    import axios from 'axios'
    import api123 from '../../api/global_variable'
    const $http = axios.create({
        baseURL: api123.baseURL,
        timeout: 1000,
        headers: {'X-Custom-Header': 'foobar'}
    });
    export default {
        name: "index",
        data() {
            return {
                loginForm: {
                    usernumber: 'abc001',
                    password: '123456',
                    userrole: ''
                },
                loginRules: {
                    usernumber: [{ required: true, trigger: 'blur', message: '用户名不能为空！' }],
                    password: [{ required: true, message: '密码不能为空！', trigger: 'blur' },
                        { min: 6, max: 50, message: '长度不能低于6位', trigger: 'blur' }]
                },
                passwordType: 'password',
                api1: this.GLOBAL.baseURL
            }
        },
        methods: {
            handleLogin() {
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        this.postLogin()
                    } else {
                        console.log('error submit!!')
                        return false
                    }
                })
            },
            //自定义验证登录方法
            async postLogin() {
                const { data:res } = await $http.post('/login', this.loginForm)
                if(res.state)
                {
                    this.$message.success('登录成功');
                    this.$router.push('/homepage')
                }else
                {
                    this.$message.error(res.msg);
                }
            }
        }
    }
</script>

<style lang="scss">
    /* 修复input 背景不协调 和光标变色 */
    /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

    $bg:#283443;
    $light_gray:#fff;
    $cursor: #fff;

    @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
        .login-container .el-input input {
            color: $cursor;
        }
    }

    /* reset element-ui css */
    .login-container {
        .el-input {
            display: inline-block;
            height: 47px;
            width: 85%;

            input {
                background: transparent;
                border: 0px;
                -webkit-appearance: none;
                border-radius: 0px;
                padding: 12px 5px 12px 32px;
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
    $bg:#2d3a4b;
    $dark_gray:#889aa4;
    $light_gray:#eee;

    .login-container {
        min-height: 100%;
        width: 100%;
        background-color: $bg;
        overflow: hidden;

        .login-form {
            position: relative;
            width: 520px;
            max-width: 100%;
            padding: 160px 35px 0;
            margin: 0 auto;
            overflow: hidden;
        }

        .title-container {
            position: relative;

            .title {
                font-size: 26px;
                color: $light_gray;
                margin: 0px auto 40px auto;
                text-align: center;
                font-weight: bold;
            }
        }

        .el-icon-user, .el-icon-lock, .el-icon-postcard {
            font-size: 20px;
        }

        .elselectidentity {
            width: 100%;
            padding: 0;
        }

        .elformitemrole {
            width: 100%!important;
        }


    }

</style>