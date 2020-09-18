<template>
    <div>

        <div class="childrendashboard1"><span>入库新药品信息</span></div>
        <HR class="hrline1"></HR>

        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

            <el-row class="elrowinlinedemo">
                <el-form-item label="入库时间" prop="storagetime">
                    <el-date-picker
                            v-model="ruleForm.storagetime"
                            align="left"
                            type="date"
                            placeholder="选择日期"
                            format="yyyy年MM月dd日"
                            value-format="yyyy-MM-dd"
                            :picker-options="pickerOptions">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="药品名称" prop="medicinename">
                    <el-input class="elinputwidthdemo" v-model="ruleForm.medicinename"></el-input>
                </el-form-item>
            </el-row>

            <el-row class="elrowinlinedemo">
                <el-form-item label="药品类型" prop="medicinetype">
                    <el-input class="elinputwidthdemo" v-model="ruleForm.medicinetype"></el-input>
                </el-form-item>

                <el-form-item label="药品数量" prop="medicinenumber">
                    <el-input placeholder="请输入纯数字，不要带文字！" class="elinputwidthdemo" v-model.number="ruleForm.medicinenumber" type="number"></el-input>
                </el-form-item>
            </el-row>

            <el-row class="elrowinlinedemo">
                <el-form-item label="计量单位" prop="measurement">
                    <el-input class="elinputwidthdemo" v-model="ruleForm.measurement"></el-input>
                </el-form-item>
                <el-form-item label="药品柜号" prop="medicinebox">
                    <el-input class="elinputwidthdemo" v-model="ruleForm.medicinebox"></el-input>
                </el-form-item>
            </el-row>

            <el-row class="elrowinlinedemo">
                <el-form-item label="过期日期" prop="expiredtime">
                    <el-date-picker
                            v-model="ruleForm.expiredtime"
                            align="left"
                            type="date"
                            placeholder="选择日期"
                            format="yyyy年MM月dd日"
                            value-format="yyyy-MM-dd"
                            :picker-options="pickerOptions1">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="入库人员" prop="storageuser">
                    <el-input class="elinputwidthdemo" v-model="ruleForm.storageuser"></el-input>
                </el-form-item>
            </el-row>

            <el-row class="elrowinlinedemo">
                <el-form-item label="审批人员" prop="approver">
                    <el-input class="elinputwidthdemo" v-model="ruleForm.approver"></el-input>
                </el-form-item>
            </el-row>

<!--            <el-form-item label="入库日期" prop="value2">-->
<!--                <el-date-picker-->
<!--                        label="入库日期"-->
<!--                        v-model="ruleForm.value2"-->
<!--                        align="left"-->
<!--                        type="date"-->
<!--                        placeholder="选择日期"-->
<!--                        format="yyyy年MM月dd日"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        :picker-options="pickerOptions">-->
<!--                </el-date-picker>-->
<!--            </el-form-item>-->

            <el-form-item>
                <el-button class="answerbutton1" type="primary" @click="submitForm('ruleForm')">入库</el-button>
                <el-button @click="resetForm('ruleForm')">清空</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import api123 from '../../../api/global_variable'
    export default {
        name: "adddate1",
        data() {
            return {
                // 日期选择器1里面的配置
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    },
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                },
                // 日期选择器2里面的配置
                pickerOptions1: {
                    disabledDate(time) {
                        return time.getTime() < Date.now();
                    },
                    shortcuts: [{
                        text: '90天后过期',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() + 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '半年后过期',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() + 3600 * 1000 * 24 * 180);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一年后过期',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() + 3600 * 1000 * 24 * 360);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '两年后过期',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() + 3600 * 1000 * 24 * 720);
                            picker.$emit('pick', date);
                        }
                    }]
                },
                ruleForm: {
                    storagetime: '',
                    medicinename: '',
                    medicinetype: '',
                    medicinenumber: '',
                    measurement: '',
                    medicinebox: '',
                    expiredtime: '',
                    storageuser: '',
                    approver: ''
                },
                rules: {
                    storagetime: [
                        {required: true, message: '本行内容不能为空！', trigger: 'blur'}
                    ],
                    medicinename: [
                        {required: true, message: '本行内容不能为空！', trigger: 'blur'}
                    ],
                    medicinetype: [
                        {required: true, message: '本行内容不能为空！', trigger: 'blur'}
                    ],
                    medicinenumber: [
                        {required: true, message: '本行内容不能为空！', trigger: 'blur'}
                    ],
                    measurement: [
                        {required: true, message: '本行内容不能为空！', trigger: 'blur'},
                    ],
                    medicinebox: [
                        {required: true, message: '本行内容不能为空！', trigger: 'blur'}
                    ],
                    expiredtime: [
                        {required: true, message: '本行内容不能为空！', trigger: 'blur'}
                    ],
                    storageuser: [
                        {required: true, message: '本行内容不能为空！', trigger: 'blur'}
                    ],
                    approver: [
                        {required: true, message: '本行内容不能为空！', trigger: 'blur'}
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
                        axios.post(api123.baseURL + '/Addnewmedicine/savenewlinemedicine', this.ruleForm).then(function (resp) {
                            if(resp.data == '新药添加成功')
                            {
                                _this.$alert('新药添加成功！', '消息', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.go(-1);
                                    }
                                });
                            } else
                            {
                                _this.$message.error(resp.data);
                            }

                        })
                    } else {
                        // console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
                // this.$router.go(-1);
            },

        }

    }
</script>

<style scoped>
    /*表格的样式*/
    .demo-ruleForm {
        width: 700px;
        margin: 3.7vh auto;
    }

    /*两个为一行，行内显示*/
    .elrowinlinedemo {
        display: flex;
    }

    /*input宽度调整到和日期选择器相同*/
    .elinputwidthdemo {
        width: 11.5vw;
    }

    /*分割线*/
    .hrline1 {
        width: 85vw;
        border: 1px solid #457DDD;
        margin: 0.4vh 1.2vw;
        opacity: 0.3;
    }

    /*顶部二级导航文字*/
    .childrendashboard1 {
        margin: 1.8vh 0 0 1.3vw;
        color: #457DDD;
    }



</style>