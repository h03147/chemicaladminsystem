<template>
    <div>
        <el-card class="elcard1" shadow="hover">
            <div class="childrendashboard1"><span>预到期提醒</span></div>
            <HR class="hrline1"></HR>
            <el-form inline :model="systemsettingTableData1" :rules="rules" ref="systemsettingTableData1">
                <el-form-item label="药品预到期提醒的天数设置：" prop="medicine_expired_warning">
                    <el-input type="number" v-model="systemsettingTableData1.medicine_expired_warning" size="small" placeholder="请输入数字"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="primary" @click="submitssForm('systemsettingTableData1')">确定</el-button>
                </el-form-item>
            </el-form>
        </el-card>

        <el-card class="elcard2" shadow="hover">
            <div class="childrendashboard2"><span>库存提醒</span></div>
            <HR class="hrline2"></HR>
            <el-form inline :model="systemsettingTableData2"
                     :rules="rules"
                     ref="systemsettingTableData2">
                <el-form-item label="药品库存不足预警数量设置：" prop="medicine_number_warning">
                    <el-input type="number" v-model="systemsettingTableData2.medicine_number_warning" size="small" placeholder="请输入数字"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="primary" @click="submitssForm('systemsettingTableData2')">确定</el-button>
                </el-form-item>
            </el-form>
<!--            <el-form inline-->
<!--                     :model="systemsettingTableData3"-->
<!--                     :rules="rules"-->
<!--                     ref="systemsettingTableData3">-->
<!--                <el-form-item label="设备库存不足预警数量设置：" prop="equipment_number_warning">-->
<!--                    <el-input type="number" v-model="systemsettingTableData3.equipment_number_warning" size="small" placeholder="请输入数字"></el-input>-->
<!--                </el-form-item>-->
<!--                <el-form-item>-->
<!--                    <el-button size="small" type="primary" @click="submitssForm('systemsettingTableData3')">确定</el-button>-->
<!--                </el-form-item>-->
<!--            </el-form>-->
        </el-card>

    </div>
</template>

<script>
    import axios from "axios"
    import api123 from "../../../api/global_variable"
    export default {
        name: "index1",
        data() {
            return {
                // 系统设置表回调接受表
                systemsettingTableData1: {},
                systemsettingTableData2: {},
                // systemsettingTableData3: {},
                // 校验规则
                rules: {
                    medicine_expired_warning: [
                        {required: true, message: '输入不能为空!', trigger: 'blur'}
                    ],
                    medicine_number_warning: [
                        {required: true, message: '输入不能为空!', trigger: 'blur'}
                    ],
                    // equipment_number_warning: [
                    //     {required: true, message: '输入不能为空!', trigger: 'blur'}
                    // ]
                },
            }
        },
        created() {
            const _this = this;
            axios
            .get(api123.baseURL + '/systemsetting/lovestory').then((response) => {
                let res = response.data;
                console.log(res);
                _this.systemsettingTableData1 = res;
                _this.systemsettingTableData2 = res;
                // _this.systemsettingTableData3 = res;
            });
        },
        methods: {
            // 提交修改的方法
            submitssForm(formName){
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // this.systemsettingTableData1.id = null;
                        axios
                        .post(api123.baseURL + '/systemsetting/cardigan', this.systemsettingTableData1).then((response) => {
                           let res = response.data;
                           console.log(res);
                           if(res.state === 'ok')
                           {
                               _this.$alert('修改成功');
                           }
                           else
                           {
                               _this.$alert(res);
                           }
                        });

                    } else {
                        this.$alert('修改失败，请遵循校验规则！');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>

    /*分割线*/
    .hrline1, .hrline2 {
        width: 83vw;
        border: 1px solid #457DDD;
        /*margin: 0 0.2vw 0 0;*/
        /*padding-right: 0.2vw;*/
        opacity: 0.3;
    }

    /*顶部二级导航文字*/
    .childrendashboard1, .childrendashboard2 {
        /*margin: 1.8vh 0 0 1.3vw;*/
        color: #457DDD;
    }

    /*卡片样式*/
    .elcard1 {
        margin: 1.8vh 1.3vw 1.8vh 1.3vw;
    }
    .elcard2 {
        margin: 1.8vh 1.3vw 1.8vh 1.3vw;
    }

</style>