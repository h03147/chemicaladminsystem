(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0e089f9e"],{"4c41":function(e,r,i){},"4e3e":function(e,r,i){"use strict";var n=i("4c41"),t=i.n(n);t.a},f912:function(e,r,i){"use strict";i.r(r);var n=function(){var e=this,r=e.$createElement,i=e._self._c||r;return i("div",[i("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"100px"}},[i("el-form-item",{attrs:{label:"id",prop:"id"}},[i("el-input",{attrs:{disabled:""},model:{value:e.ruleForm.id,callback:function(r){e.$set(e.ruleForm,"id",r)},expression:"ruleForm.id"}})],1),i("el-form-item",{attrs:{label:"设备名称",prop:"medicinename"}},[i("el-input",{model:{value:e.ruleForm.medicinename,callback:function(r){e.$set(e.ruleForm,"medicinename",r)},expression:"ruleForm.medicinename"}})],1),i("el-form-item",{attrs:{label:"设备类型",prop:"medicinetype"}},[i("el-input",{model:{value:e.ruleForm.medicinetype,callback:function(r){e.$set(e.ruleForm,"medicinetype",r)},expression:"ruleForm.medicinetype"}})],1),i("el-form-item",{attrs:{label:"设备数量",prop:"medicinenumber"}},[i("el-input",{attrs:{placeholder:"请输入纯数字，不要带文字！",type:"number"},model:{value:e.ruleForm.medicinenumber,callback:function(r){e.$set(e.ruleForm,"medicinenumber",e._n(r))},expression:"ruleForm.medicinenumber"}})],1),i("el-form-item",{attrs:{label:"药品柜",prop:"medicinebox"}},[i("el-input",{model:{value:e.ruleForm.medicinebox,callback:function(r){e.$set(e.ruleForm,"medicinebox",r)},expression:"ruleForm.medicinebox"}})],1),i("el-form-item",[i("el-button",{staticClass:"answerbutton1",attrs:{type:"primary"},on:{click:function(r){return e.submitForm("ruleForm")}}},[e._v("确定修改")]),i("el-button",{on:{click:function(r){return e.cancelForm("ruleForm")}}},[e._v("取消")])],1)],1)],1)},t=[],m=i("72ac"),o={name:"update1",data:function(){return{ruleForm:{id:"",medicinename:"",medicinetype:"",medicinenumber:"",medicinebox:""},rules:{medicinename:[{required:!0,message:"本行内容不能为空，请返回重新修改！",trigger:"blur"},{min:1,max:32,message:"长度在 1 到 32 个字符",trigger:"change"}],medicinetype:[{required:!0,message:"本行内容不能为空",trigger:"blur"},{min:1,max:32,message:"长度在 1 到 32 个字符",trigger:"change"}],medicinenumber:[{required:!0,message:"本行内容不能为空",trigger:"blur"}],medicinebox:[{required:!0,message:"本行内容不能为空",trigger:"blur"},{min:1,max:10,message:"长度在 1 到 10 个字符",trigger:"change"}]}}},methods:{submitForm:function(e){var r=this,i=this;this.$refs[e].validate((function(e){if(e){if(!(r.ruleForm.medicinenumber>0))return!1;axios.post(m["a"].baseURL+"/Addnewmedicine/updatemedicine/",r.ruleForm).then((function(e){"修改药品信息成功"==e.data?i.$alert("修改成功！","消息",{confirmButtonText:"确定",callback:function(e){i.$router.go(-1)}}):i.$message.error("这是一个未知的错误，请稍后重试！")}))}}))},cancelForm:function(e){this.$router.go(-1)}},created:function(){var e=this;console.log(this.$route.query.id),axios.get(m["a"].baseURL+"/Addnewmedicine/findById/"+this.$route.query.id).then((function(r){e.ruleForm=r.data.selectByIdResult,console.log(e.ruleForm)}))}},l=o,u=(i("4e3e"),i("2877")),a=Object(u["a"])(l,n,t,!1,null,"75efc468",null);r["default"]=a.exports}}]);
//# sourceMappingURL=chunk-0e089f9e.b35eef36.js.map