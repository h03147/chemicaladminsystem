<template>
  <div>
    <div class="childrendashboard1"><span>入库新设备信息</span></div>
    <HR class="hrline1"></HR>

    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-row class="elrowinlinedemo">
        <el-form-item label="入库时间" prop="storagetime">
          <el-date-picker
            v-model="ruleForm.storagetime"
            align="left"
            type="date"
            placeholder="选择日期"
            format="yyyy年MM月dd日 HH:mm:ss"
            value-format="yyyy-MM-dd HH:mm:ss"
            :picker-options="pickerOptions"
          >
          </el-date-picker>
        </el-form-item>

        <el-form-item label="设备名称" prop="equipment_name">
          <el-input
            class="elinputwidthdemo"
            v-model="ruleForm.equipment_name"
          ></el-input>
        </el-form-item>
      </el-row>

      <el-row class="elrowinlinedemo">
        <el-form-item label="设备柜" prop="equipment_position">
          <el-input
            class="elinputwidthdemo"
            v-model="ruleForm.equipment_position"
          ></el-input>
        </el-form-item>

        <el-form-item label="设备类型" prop="equipment_type">
          <el-input
            class="elinputwidthdemo"
            v-model="ruleForm.equipment_type"
          ></el-input>
        </el-form-item>
      </el-row>

      <el-row class="elrowinlinedemo">
        <el-form-item label="入库人" prop="approver">
          <el-input
            class="elinputwidthdemo"
            v-model="ruleForm.approver"
          ></el-input>
        </el-form-item>
        <el-form-item label="设备数量" prop="equipment_number">
          <el-input
            placeholder="请输入纯数字，不要带文字！"
            class="elinputwidthdemo"
            v-model.number="ruleForm.equipment_number"
            type="number"
          ></el-input>
        </el-form-item>
      </el-row>

      <el-row class="elrowinlinedemo">
        <el-form-item label="审批人员" prop="storageuser">
          <el-input
            class="elinputwidthdemo"
            v-model="ruleForm.storageuser"
          ></el-input>
        </el-form-item>
      </el-row>
      <el-form-item>
        <el-button
          class="answerbutton1"
          type="primary"
          @click="submitForm('ruleForm')"
          >入库</el-button
        >
        <el-button @click="resetForm('ruleForm')">清空</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import api123 from "../../../api/global_variable";
export default {
  name: "adddate2",
  data() {
    return {
      // 日期选择器1里面的配置
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            },
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            },
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            },
          },
        ],
      },
      ruleForm: {
        storagetime: "",
        equipment_name: "",
        equipment_position: "",
        equipment_type: "",
        approver: "",
        equipment_number: "",
        storageuser: "",
      },
      rules: {
        storagetime: [
          { required: true, message: "本行内容不能为空！", trigger: "blur" },
        ],
        equipment_name: [
          { required: true, message: "本行内容不能为空！", trigger: "blur" },
        ],
        equipment_position: [
          { required: true, message: "本行内容不能为空！", trigger: "blur" },
        ],
        equipment_type: [
          { required: true, message: "本行内容不能为空！", trigger: "blur" },
        ],
        approver: [
          { required: true, message: "本行内容不能为空！", trigger: "blur" },
        ],
        equipment_number: [
          { required: true, message: "本行内容不能为空！", trigger: "blur" },
        ],
        storageuser: [
          { required: true, message: "本行内容不能为空！", trigger: "blur" },
        ],
      },
    };
  },

  methods: {
    submitForm(formName) {
      const _this = this;
      this.$refs[formName].validate((valid) => {
        // 从session中抓取当前操作老师的名字
        // console.log(this.ruleForm);
        if (valid) {
          if (this.ruleForm.equipment_number > 0) {
            axios
              .post(
                api123.baseURL +
                  "/Laboratoryequipmentmanagement/savenewlineequipment",
                this.ruleForm
              )
              .then(function (resp) {
                if (resp.data == "success") {
                  _this.$alert("设备添加成功！", "消息", {
                    confirmButtonText: "确定",
                    callback: (action) => {
                      _this.$router.go(-1);
                    },
                  });
                } else {
                  _this.$message.error(resp.data);
                }
              });
          } else {
            // console.log('error submit!!');
            return false;
          }
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      // this.$router.go(-1);
    },
  },
};
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
  border: 1px solid #457ddd;
  margin: 0.4vh 1.2vw;
  opacity: 0.3;
}

/*顶部二级导航文字*/
.childrendashboard1 {
  margin: 1.8vh 0 0 1.3vw;
  color: #457ddd;
}
</style>