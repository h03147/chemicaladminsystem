(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6ff127f8"],{2879:function(e,t,r){"use strict";var i=r("aa91"),o=r.n(i);o.a},9637:function(e,t,r){"use strict";r.r(t);var i=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[e._m(0),r("HR",{staticClass:"hrline1"}),r("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"100px"}},[r("el-row",{staticClass:"elrowinlinedemo"},[r("el-form-item",{attrs:{label:"入库时间",prop:"storagetime"}},[r("el-date-picker",{attrs:{align:"left",type:"date",placeholder:"选择日期",format:"yyyy年MM月dd日 HH:mm:ss","value-format":"yyyy-MM-dd HH:mm:ss","picker-options":e.pickerOptions},model:{value:e.ruleForm.storagetime,callback:function(t){e.$set(e.ruleForm,"storagetime",t)},expression:"ruleForm.storagetime"}})],1),r("el-form-item",{attrs:{label:"设备名称",prop:"equipment_name"}},[r("el-input",{staticClass:"elinputwidthdemo",model:{value:e.ruleForm.equipment_name,callback:function(t){e.$set(e.ruleForm,"equipment_name",t)},expression:"ruleForm.equipment_name"}})],1)],1),r("el-row",{staticClass:"elrowinlinedemo"},[r("el-form-item",{attrs:{label:"设备柜",prop:"equipment_position"}},[r("el-input",{staticClass:"elinputwidthdemo",model:{value:e.ruleForm.equipment_position,callback:function(t){e.$set(e.ruleForm,"equipment_position",t)},expression:"ruleForm.equipment_position"}})],1),r("el-form-item",{attrs:{label:"设备类型",prop:"equipment_type"}},[r("el-input",{staticClass:"elinputwidthdemo",model:{value:e.ruleForm.equipment_type,callback:function(t){e.$set(e.ruleForm,"equipment_type",t)},expression:"ruleForm.equipment_type"}})],1)],1),r("el-row",{staticClass:"elrowinlinedemo"},[r("el-form-item",{attrs:{label:"入库人",prop:"approver"}},[r("el-input",{staticClass:"elinputwidthdemo",model:{value:e.ruleForm.approver,callback:function(t){e.$set(e.ruleForm,"approver",t)},expression:"ruleForm.approver"}})],1),r("el-form-item",{attrs:{label:"设备数量",prop:"equipment_number"}},[r("el-input",{staticClass:"elinputwidthdemo",attrs:{placeholder:"请输入纯数字，不要带文字！",type:"number"},model:{value:e.ruleForm.equipment_number,callback:function(t){e.$set(e.ruleForm,"equipment_number",e._n(t))},expression:"ruleForm.equipment_number"}})],1)],1),r("el-row",{staticClass:"elrowinlinedemo"},[r("el-form-item",{attrs:{label:"审批人员",prop:"storageuser"}},[r("el-input",{staticClass:"elinputwidthdemo",model:{value:e.ruleForm.storageuser,callback:function(t){e.$set(e.ruleForm,"storageuser",t)},expression:"ruleForm.storageuser"}})],1)],1),r("el-form-item",[r("el-button",{staticClass:"answerbutton1",attrs:{type:"primary"},on:{click:function(t){return e.submitForm("ruleForm")}}},[e._v("入库")]),r("el-button",{on:{click:function(t){return e.resetForm("ruleForm")}}},[e._v("清空")])],1)],1)],1)},o=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"childrendashboard1"},[r("span",[e._v("入库新设备信息")])])}],n=r("72ac"),l={name:"adddate2",data:function(){return{pickerOptions:{disabledDate:function(e){return e.getTime()>Date.now()},shortcuts:[{text:"今天",onClick:function(e){e.$emit("pick",new Date)}},{text:"昨天",onClick:function(e){var t=new Date;t.setTime(t.getTime()-864e5),e.$emit("pick",t)}},{text:"一周前",onClick:function(e){var t=new Date;t.setTime(t.getTime()-6048e5),e.$emit("pick",t)}}]},ruleForm:{storagetime:"",equipment_name:"",equipment_position:"",equipment_type:"",approver:"",equipment_number:"",storageuser:""},rules:{storagetime:[{required:!0,message:"本行内容不能为空！",trigger:"blur"}],equipment_name:[{required:!0,message:"本行内容不能为空！",trigger:"blur"}],equipment_position:[{required:!0,message:"本行内容不能为空！",trigger:"blur"}],equipment_type:[{required:!0,message:"本行内容不能为空！",trigger:"blur"}],approver:[{required:!0,message:"本行内容不能为空！",trigger:"blur"}],equipment_number:[{required:!0,message:"本行内容不能为空！",trigger:"blur"}],storageuser:[{required:!0,message:"本行内容不能为空！",trigger:"blur"}]}}},methods:{submitForm:function(e){var t=this,r=this;this.$refs[e].validate((function(e){if(e){if(!(t.ruleForm.equipment_number>0))return!1;axios.post(n["a"].baseURL+"/Laboratoryequipmentmanagement/savenewlineequipment",t.ruleForm).then((function(e){"success"==e.data?r.$alert("设备添加成功！","消息",{confirmButtonText:"确定",callback:function(e){r.$router.go(-1)}}):r.$message.error(e.data)}))}}))},resetForm:function(e){this.$refs[e].resetFields()}}},s=l,a=(r("2879"),r("2877")),u=Object(a["a"])(s,i,o,!1,null,"fd6e4cb2",null);t["default"]=u.exports},aa91:function(e,t,r){}}]);
//# sourceMappingURL=chunk-6ff127f8.bb9f036f.js.map