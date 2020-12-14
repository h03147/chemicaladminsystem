<template>
  <div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="id" prop="id">
        <el-input v-model="ruleForm.id" disabled></el-input>
      </el-form-item>
      <el-form-item label="设备名称" prop="medicinename">
        <el-input v-model="ruleForm.medicinename"></el-input>
      </el-form-item>
      <el-form-item label="设备类型" prop="medicinetype">
        <el-input v-model="ruleForm.medicinetype"></el-input>
      </el-form-item>
      <el-form-item label="设备数量" prop="medicinenumber">
        <el-input
          placeholder="请输入纯数字，不要带文字！"
          v-model.number="ruleForm.medicinenumber"
          type="number"
        ></el-input>
      </el-form-item>
      <el-form-item label="药品柜" prop="medicinebox">
        <el-input v-model="ruleForm.medicinebox"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button
          class="answerbutton1"
          type="primary"
          @click="submitForm('ruleForm')"
          >确定修改</el-button
        >
        <el-button @click="cancelForm('ruleForm')">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import api123 from "../../../api/global_variable";
export default {
  name: "update1",
  data() {
    return {
      ruleForm: {
        id: "",
        medicinename: "",
        medicinetype: "",
        medicinenumber: "",
        medicinebox: "",
      },
      rules: {
        medicinename: [
          {
            required: true,
            message: "本行内容不能为空，请返回重新修改！",
            trigger: "blur",
          },
          {
            min: 1,
            max: 32,
            message: "长度在 1 到 32 个字符",
            trigger: "change",
          },
        ],
        medicinetype: [
          { required: true, message: "本行内容不能为空", trigger: "blur" },
          {
            min: 1,
            max: 32,
            message: "长度在 1 到 32 个字符",
            trigger: "change",
          },
        ],
        medicinenumber: [
          { required: true, message: "本行内容不能为空", trigger: "blur" },
        ],
        medicinebox: [
          { required: true, message: "本行内容不能为空", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "长度在 1 到 10 个字符",
            trigger: "change",
          },
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
          if (this.ruleForm.medicinenumber > 0) {
            axios
              .post(api123.baseURL + "/Addnewmedicine/updatemedicine/", this.ruleForm)
              .then(function (resp) {
                if (resp.data == "修改药品信息成功") {
                  _this.$alert("修改成功！", "消息", {
                    confirmButtonText: "确定",
                    callback: (action) => {
                      _this.$router.go(-1);
                    },
                  });
                } else {
                  _this.$message.error("这是一个未知的错误，请稍后重试！");
                }
              });
          } else {
            // console.log('error submit!!');
            return false;
          }
        }
      });
    },
    cancelForm(formName) {
      // this.$refs[formName].resetFields();
      this.$router.go(-1);
    },
  },
  created() {
    const _this = this;
    //注意发送页面用$router跳转并传参数，接收页用$route接收参数
    console.log(this.$route.query.id);
    axios
      .get(api123.baseURL + "/Addnewmedicine/findById/" + this.$route.query.id)
      .then(function (resp) {
        _this.ruleForm = resp.data.selectByIdResult;
        console.log(_this.ruleForm);
      });
  },
};
</script>

<style scoped>
.demo-ruleForm {
  width: 700px;
  margin: 0 auto;
  margin-top: 10%;
}
</style>