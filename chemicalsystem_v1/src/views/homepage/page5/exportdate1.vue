<template>
    <div>
        <div class="childrendashboard1"><span>出库药品信息</span></div>
        <HR class="hrline1"></HR>

        <div class="uploadexcel1">
            <el-upload
                    class="upload-demo"
                    drag
                    :action="globalBaseUrl + '/Exportchemicalmedicine/import'"
                    :before-upload="beforeAvatarUpload"
                    :on-success="afterSuccessUpload"
                    :before-remove="beforeRemove"
                    :limit="1"
                    :on-exceed="handleExceeds"
                    multiple>
                <div class="icondiv1">
                    <i class="el-icon-document"></i>
                </div>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传<span style="color: red">xls/xlsx</span>文件，且大小不超过<span style="color: red">10MB</span></div>
            </el-upload>
        </div>


    </div>
</template>

<script>
    import api123 from "../../../api/global_variable";
    export default {
        name: "exportdate1",

        data() {
            return {
                globalBaseUrl: api123.baseURL,
            }
        },
        methods: {
            handleExceeds(files, fileList) {
                this.$message.warning(`当前限制只能选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            // excel文件上传类型限制
            beforeAvatarUpload(file) {
                console.log(file.type);
                // application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
                // application/vnd.ms-excel
                const isxlsx = file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'; // 限制上传xlsx格式
                const isxls = file.type === 'application/vnd.ms-excel'; // 限制上传xls格式
                const isLt10M = file.size / 1024 / 1024 < 10; // 限制大小10MB以内

                if (!isxlsx && !isxls) {
                    this.$message.error('上传的excel文件 只能是 xlsx 或 xls 格式!');
                }
                if (!isLt10M) {
                    this.$message.error('上传的上传的excel文件大小不能超过 10MB!');
                }

                return isxlsx || isxls && isLt10M;

            },
            beforeRemove(file) {
                return this.$confirm(`确定移除 ${ file.name }？,非法类型文件强制上传会失败`);
            },
            // 文件上传成功后
            afterSuccessUpload(response) {
                console.log(response);
                if (response) {
                    this.$alert("上传成功！", "消息", {
                        confirmButtonText: "确定",
                        callback: (action) => {
                            this.$router.go(-1);
                        },
                    });
                } else {
                    this.$message.error("这是一个未知的错误，请退出后重试！");
                }
            }
        }
    }
</script>

<style scoped>

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

    /*上传文件图标的父div*/
    .icondiv1 {
        font-size: 5vw;
    }

    /*上船组件div*/
    .uploadexcel1 {
        width: 85vw;
        text-align: center;
        padding: 23.7vh;
    }

</style>