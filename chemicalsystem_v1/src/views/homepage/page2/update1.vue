<template>
    <div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="id" prop="id">
                <el-input v-model="ruleForm.id" disabled></el-input>
            </el-form-item>
            <el-form-item label="人员姓名" prop="username">
                <el-input v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-form-item label="人员账号" prop="usernumber">
                <el-input v-model="ruleForm.usernumber"></el-input>
            </el-form-item>
            <el-form-item label="人员密码" prop="password">
                <el-input v-model="ruleForm.password"></el-input>
            </el-form-item>
            <el-form-item label="身份类型" prop="userrole">
                <el-select v-model="ruleForm.userrole" placeholder="请选择身份类别">
                    <el-option label="高级管理员" value="高级管理员"></el-option>
                    <el-option label="普通管理员" value="普通管理员"></el-option>
                </el-select>
            </el-form-item>


            <el-form-item>
                <el-button class="answerbutton1" type="primary" @click="submitForm('ruleForm')">确定修改</el-button>
                <el-button @click="cancelForm('ruleForm')">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import api123 from '../../../api/global_variable'
    export default {
        name: "update1",
        data() {
            return {
                ruleForm: {
                    id: '',
                    username: '',
                    usernumber: '',
                    password: '',
                    userrole: ''
                },
                rules: {
                    username: [
                        {required: true, message: '本行内容不能为空，请返回重新修改！', trigger: 'blur'},
                        {min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'change'}
                    ],
                    usernumber: [
                        {required: true, message: '本行内容不能为空', trigger: 'blur'},
                        {min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'change'}
                    ],
                    password: [
                        {required: true, message: '本行内容不能为空', trigger: 'blur'},
                        {min: 6, max: 64, message: '长度在 6 到 64 个字符', trigger: 'change'}
                    ],
                    userrole: [
                        {required: true, message: '本行内容不能为空', trigger: 'blur'},
                        {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'change'}
                    ]
                }
            };
        },

        methods: {
            submitForm(formName) {
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    // 从session中抓取当前操作老师的名字
                    // console.log(this.ruleForm);
                    if (valid) {
                        axios.put(api123.baseURL + '/page2update/', this.ruleForm).then(function (resp) {
                            if(resp.data == 'success')
                            {
                                _this.$alert('修改成功！', '消息', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.go(-1);
                                    }
                                });
                            }
                            else if(resp.data == '抱歉修改后的账号已经有人使用了，请更换后重试')
                            {
                                _this.$message.error(resp.data);
                            }
                            else
                            {
                                _this.$message.error("这是一个未知的错误，请稍后重试！")
                            }

                        })
                    } else {
                        // console.log('error submit!!');
                        return false;
                    }
                });
            },
            cancelForm(formName) {
                // this.$refs[formName].resetFields();
                this.$router.go(-1);
            }
        },
        created() {
            const _this = this;
            //注意发送页面用$router跳转并传参数，接收页用$route接收参数
            // alert(this.$route.query.id);
            axios.get(api123.baseURL + '/findById/' + this.$route.query.id).then(function (resp) {
                _this.ruleForm = resp.data
            })
        }

    }
</script>

<style scoped>
    .demo-ruleForm {
        width: 700px;
        margin: 0 auto;
        margin-top: 10%;
    }

</style>