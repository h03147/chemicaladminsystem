<template>
    <div>
        <el-container class="homepagecontainer">
            <el-header class="elheader1">
                <el-row>
                    <el-col class="elcoldiv1">
                        <span class="headerspan1">化学药品管理系统</span>
                        <span class="headerspan3" @click="quitSignIn">
                            <i class="el-icon-switch-button"></i>
                        </span>
                        <span class="headerspan2" @click="skipToPage6Index1">
<!--                            <i class="el-icon-bell">-->
                            <!--                                <span>-->
                            <!--                                    <el-badge class="el-badge1" value="17" size="mini"></el-badge>-->
                            <!--                                </span>-->
                            <!--                            </i>-->
                        <el-badge :value="AllUnreadNotice_Number" class="item">
                          <i class="el-icon-bell"></i>
                        </el-badge>
                        </span>
                    </el-col>
                </el-row>

            </el-header>
            <el-container>
                <el-aside class="elaside1" v-if="!skipstatus">
                    <!--不收缩其他菜单-->
                    <!--<el-menu class="elmenu1"-->
                    <!--只展开当前菜单列，其他收缩-->
                    <el-menu class="elmenu1" :default-openeds="activeIndex"
                             background-color="#343541"
                             text-color="#CDD0DF"
                             active-text-color="#ffd04b">
                        <el-submenu index="1" @click.native="getData($event,'1')">
                            <template slot="title"><i class="el-icon-message"></i>数据统计</template>
                            <el-menu-item-group>
                                <el-menu-item index="1-1">
                                    <router-link tag="li" to="/homepage/page1index1" @click.native="getData1($event,'0')">数据总览</router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-submenu index="2" @click.native="getData($event,'2')">
                            <template slot="title"><i class="el-icon-menu"></i>人员管理</template>
                            <el-menu-item-group>
                                <el-menu-item index="2-1">
                                    <router-link tag="li" to="/homepage/page2index1" @click.native="getData1($event,'1')">人员操作</router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-submenu index="3" @click.native="getData($event,'3')">
                            <template  slot="title"><i class="el-icon-setting"></i>物资管理</template>
                            <el-menu-item-group>
                                <el-menu-item index="3-1">
                                    <router-link tag="li" to="/homepage/page3index1" @click.native="getData1($event,'2')">化学药品管理</router-link>
                                </el-menu-item>
                                <el-menu-item index="3-2">
                                    <router-link tag="li" to="/homepage/page3index2" @click.native="getData1($event,'3')">实验设备管理</router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-submenu index="4" @click.native="getData($event,'4')">
                            <template slot="title"><i class="el-icon-setting"></i>入库管理</template>
                            <el-menu-item-group>
                                <el-menu-item index="4-1">
                                    <router-link tag="li" to="/homepage/page4index1" @click.native="getData1($event,'4')">化学药品入库</router-link>
                                </el-menu-item>
                                <el-menu-item index="4-2">
                                    <router-link tag="li" to="/homepage/page4index2" @click.native="getData1($event,'5')">实验设备入库</router-link>
                                </el-menu-item>
                                <el-menu-item index="4-3">
                                    <router-link tag="li" to="/homepage/page4index3" @click.native="getData1($event,'6')">实验设备归还</router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-submenu index="5" @click.native="getData($event,'5')">
                            <template slot="title"><i class="el-icon-setting"></i>出库管理</template>
                            <el-menu-item-group>
                                <el-menu-item index="5-1">
                                    <router-link tag="li" to="/homepage/page5index1" @click.native="getData1($event,'7')">化学药品出库</router-link>
                                </el-menu-item>
                                <el-menu-item index="5-2">
                                    <router-link tag="li" to="/homepage/page5index2" @click.native="getData1($event,'8')">实验设备出库</router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-submenu index="6" @click.native="getData($event,'6')">
                            <template slot="title"><i class="el-icon-setting"></i>通知管理</template>
                            <el-menu-item-group>
                                <el-menu-item index="6-1">
                                    <router-link tag="li" to="/homepage/page6index1" @click.native="getData1($event,'9')">通知操作</router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                        <el-submenu index="7" @click.native="getData($event,'7')">
                            <template slot="title"><i class="el-icon-setting"></i>系统设置</template>
                            <el-menu-item-group>
                                <el-menu-item index="7-1">
                                    <router-link tag="li" to="/homepage/page7index1" @click.native="getData1($event,'10')">系统操作</router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                    </el-menu>

                </el-aside>
                <el-main v-if="!skipstatus">
                    <div class="elmaindiv1">
                        <div class="elmiandiv1_1">
                            <span style="font-size: 1.2vw;font-weight: bolder; color: #457DDD;">|</span>
                            <span style="color: #457DDD; font-size: 0.9vw; margin-left: 0.4vw" v-model="dashboardfirst">{{dashboardfirst}}</span>
                            <span style="font-size: 1.0vw; color: #457DDD;">／</span>
                            <span>{{dashboardsecond}}</span>
                        </div>
                    </div>
<!--                    <div class="elmaindiv2"><router-view></router-view></div>-->

<!--                    套个div和transition做加载动画-->
                    <div class="elmaindiv2">
                        <transition name="slide-fade">
                            <router-view v-if="!skipstatus"></router-view>
                        </transition>
                    </div>


                </el-main>
                <el-alert
                        class="alaltertdiv1"
                        v-if="skipstatus"
                        title="账号强制退出警告"
                        type="warning"
                        :description="'您的账号已在其他地方登录,将在' + count + 's后自动退出'"
                        :closable="false"
                        show-icon>
                </el-alert>
            </el-container>

        </el-container>
    </div>
</template>

<script>
    import axios from "axios";
    import api123 from "../../api/global_variable";
    import Utils from '../../util/util';
    export default {
        name: "index",
        data() {
            return {
                activeIndex:[], // 设置了默认展开菜单是空
                dashboardfirst: "数据统计",  // 显示当前所在菜单的路径的一级菜单名字
                dashboardsecond: "", // 显示当前所在菜单的路径的二级菜单名字
                dashboardhead: ['数据统计','人员管理','物资管理','入库管理','出库管理','通知管理','系统设置'],
                dashboardtail: ['数据总览','人员操作','化学药品管理','实验设备管理','化学药品入库','实验设备入库','实验设备归还','化学药品出库','实验设备出库','通知操作','系统操作',''],
                AllUnreadNotice_Number: null, // 小铃铛未读消息绑定的数据
                // 是否自动退出登录判断
                skipstatus: false,
                // 计数
                count: 5,
                // 验证token的字段封装
                requesttoken: {
                    token: null,
                    user_number: null,
                },
            }
        },
        watch: {
            // 把dashboardfirst 和span用v-model双向绑定 监听把dashboardfirst变化，在仅选中一级菜单时，清除之前点过的二级菜单dashboardsecond的内容
            dashboardfirst(val) {
                this.dashboardsecond = "";
            }
        },
        created() {
            this.getAllUnreadNotice_Result();
            this.tokenExpiredSkip();
        },
        mounted() {
            let _this = this;
            // 接受page6的传参
            Utils.$on('demo', function (msg) {
                console.log(msg);
                _this.getAllUnreadNotice_Result();
            });
        },
        methods: {
            //手动获取index的值展开当前点击选中的菜单关闭其他的菜单
            getData(e,num) {
                console.log("这是抓的getData属性值：" + num);
                this.activeIndex = [num];
                // console.log("变化后的activeIndex" + this.activeIndex);
                this.dashboardfirst = this.dashboardhead[num-1];
                this.tokenExpiredSkip();
            },
            getData1(e,num1) {
                console.log("这是抓的getData1属性值：" + num1);
                this.dashboardsecond = this.dashboardtail[num1];
            },
            // 小铃铛获取未读消息的方法
            getAllUnreadNotice_Result() {
                const _this = this;
                axios
                .get(api123.baseURL + '/Notification/findAllUnreadNotice').then((response) => {
                    _this.AllUnreadNotice_Number = response.data.AllUnreadNotice_Result;
                });
            },
            // 跳转去page6index1查看通知详情
            skipToPage6Index1() {
                // this.$message.success('skipToPage6Index1')
                this.$router.push("/homepage/page6index1");
                // this.$router.push("/homepage/page2index1");
            },
            // token是否过期验证
            tokenExpiredSkip() {
                const _this = this;
                // this.skipstatus = true;
                this.requesttoken.user_number = sessionStorage.getItem("user_number")
                this.requesttoken.token = sessionStorage.getItem("token")
                // this.$message(token);
                axios.post(api123.baseURL + '/requestTokenStatus', this.requesttoken)
                .then((response) => {
                    if(response.data)
                    {
                        // _this.$message.success('token success');
                    }else {
                        _this.skipstatus = true;
                        _this.$message.error('账号在其他地方登录或非法的入口访问，存在风险！')
                        for (let i = 5; i > 0; i--) {
                            setTimeout(function () {
                                _this.count -= 1;
                                let _count = _this.count
                                // _this.$message.warning(_count);
                                if (_this.count == 0) {
                                    setTimeout(function () {
                                        // _this.processnumshow = true;
                                        // location.reload();
                                        _this.$message.success('跳转成功');
                                        _this.$router.push('/');
                                    }, 2000);
                                }
                            }, i * 1000);

                        }
                    }
                });
            },
            // 退出登录
            quitSignIn() {
                this.$confirm("确定要退出?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() => {
                        // this.$message('ok');
                        sessionStorage.clear();
                        this.$router.push('/');
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消退出",
                        });
                    });
            },
        }
    }
</script>

<style scoped>

    .homepagecontainer {
        width: 100vw;
        height: 100vh;
    }

    .el-header {
        background-color: #B3C0D1;
        color: #333;
        text-align: left;
        /*line-height: 60px;*/
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        /*line-height: 200px;*/
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        padding: 0;
        /*line-height: 160px;*/
    }

    .elmaindiv1 {
        border: 1px solid #FFFFFF;
        width: 89.6vw;
        height: 5.2vh;
        background-color: #FFFFFF;
    }

    .elmaindiv2 {
        border: 1px solid #FFFFFF;
        /*margin-left: 1vw;*/
        /*margin-top: 1.5vh;*/
        width: 87.5vw;
        /*height: 85.4vh;*/
        margin: 1.5vh 1vw 1.2vh 1vw; /*两个参数就是上下 左右 四个就是上右下左*/
        background-color: #FFFFFF;
    }

    /*header的样式*/
    .elheader1 {
        background-color: #457EDD;
    }

    /*header里面左边字的样式*/
    .headerspan1 {
        width: 7.6vw;
        height: 1.7vh;
        font-size: 1.1vw;
        font-family: Source Han Sans SC;
        font-weight: 400;
        color: #FFFFFF;
        line-height: 29px;
    }

    /*aside的navmenu菜单的样式*/
    .elmenu1 {
        width: 10.45vw;
        height: 93.6vh;
        /*min-width: 7.3vw;*/
        text-align: left;
    }

    /*aside的样式*/
    .elaside1 {
        width: 10.4vw!important;
        /*min-width: 7.3vw;*/
        background-color: #343541;
        overflow-x: hidden;
    }

    /*顶部导航树路径div*/
    .elmiandiv1_1 {
        margin: 1.2vh 0 0 1vw;
    }


    /*切换动画1 给transform上一层div加class名字index-content节课*/
    .index-content{
        /*width: 100%;*/
        /*//解决出现横向滚动条问题*/
        overflow-x: hidden;

        border: 1px solid #FFFFFF;
        /*margin-left: 1vw;*/
        /*margin-top: 1.5vh;*/
        width: 87.5vw;
        /*height: 85.4vh;*/
        margin: 1.5vh 1vw 1.2vh 1vw; /*两个参数就是上下 左右 四个就是上右下左*/
        background-color: #FFFFFF;

    }
    .v-enter{
        opacity: 0.2;
        transform: translateX(0%);
    }
    .v-leave-to{
        opacity: 0.2;
        transform: translateX(100%);
        /*// 解决页面从上往下位移问题*/
        position: absolute;
    }
    .v-enter-active,.v-leave-active{
        transition: all 1.5s ease;
    }
    /*切换动画1结束 给transform上一层div加class名字index-content节课*/



    /*切换动画2 直接给transform加name=slide-fade即可，包住routervoiew*/
    .slide-fade{
        position: absolute;left:0;right: 0;
    }
    .slide-fade-enter-active {
        transition: all 1.2s ease;
    }
    .slide-fade-leave-active {

        transition: all .1s cubic-bezier(2.0, 0.5, 0.8, 1.0);
    }
    .slide-fade-enter, .slide-fade-leave-to
    {
        left:0;right: 0;
        transform: translateX(50px);
        opacity: 0;
    }
    /*切换动画2结尾 直接给transform加name=slide-fade即可，包住routervoiew*/

    /*消息提示铃铛的位置*/
    .headerspan2 , .headerspan3{
        color: #FFFFFF;
        font-size: 1.2vw;
        margin-left: 1vw;
        float: right;
    }
    /*.el-badge1 {*/
    /*    font-size: 1.9vw;*/
    /*    margin-left: -0.5vw;*/
    /*    margin-top: -0.5vh;*/
    /*    vertical-align: baseline;*/
    /*}*/
    /*消息气泡和通知铃铛的样式*/
    .item {
        margin-bottom: 0.4vh;
    }

    /*header里面子div的公用样式*/
    .elcoldiv1 {
        margin-top: 2vh;
    }
    /* 强制退出警告框样式*/
    .alaltertdiv1 {
        /*margin: 0 auto;*/
        /*text-align: center;*/
        display: flow-root;
    }


</style>
