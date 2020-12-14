<template>
    <div>
        <template>
            <div class="page6maindiv1">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane name="first">
                        <span slot="label">
                        预过期提醒
                        <el-badge :is-dot="All_read1" size="mini" class="item"></el-badge>
                        </span>
                        <el-table :data="expiredTableData"
                                  :show-header="false"
                                  empty-text="预过期通知为空"
                                  style="width: 50%">
<!--                            :row-class-name="tableRowClassName"-->
                            <el-table-column prop="medicine_name">
                                <template slot-scope="scope">
                                    <el-row>
                                        <span class="dian1">●</span>
                                        {{scope.row.medicine_name}}还有
                                        <span class="expired_numberdiv1">{{scope.row.expired_number}}</span>天过期
                                        <span class="timediv3">
                                            <el-button size="mini" icon="el-icon-delete" @click.native="handleDelete(scope.row, 'first')"></el-button>
                                        </span>
                                        <span class="timediv2">{{scope.row.notice_time.substr(0,19)}}</span>
                                        <span @click="clickRead1(scope.row, 'first')" class="timediv1">
                                            <el-badge value="未读" class="el-badge1" v-if="scope.row.notice_status === 0"></el-badge>

                                        </span>
                                    </el-row>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                    <el-tab-pane name="second">
                        <span slot="label">
                        库存不足提醒
                        <el-badge :is-dot="All_read2" size="mini" class="item"></el-badge>
                        </span>
                        <el-table :data="numberTableData"
                                  :show-header="false"
                                  empty-text="库存不足通知为空"
                                  style="width: 50%">
                            <!--                            :row-class-name="tableRowClassName"-->
                            <el-table-column prop="medicine_name">
                                <template slot-scope="scope">
                                    <el-row><span class="dian1">●</span>
                                            {{scope.row.medicine_name}}库存剩余:
                                            <span class="medicine_numberdiv1">{{scope.row.medicine_number}}</span>
                                            <span class="timediv3">
                                                <el-button size="mini" icon="el-icon-delete" @click.native="handleDelete(scope.row, 'second')"></el-button>
                                            </span>
                                            <span class="timediv2">{{scope.row.notice_time.substr(0,19)}}</span>
                                            <span @click="clickRead1(scope.row, 'second')" class="timediv1">
                                                <el-badge value="未读" class="el-badge1" v-if="scope.row.notice_status === 0"></el-badge>
                                            </span>
                                    </el-row>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </template>
    </div>

</template>

<script>
    import axios from "axios";
    import api123 from "../../../api/global_variable";
    import Utils from '../../../util/util';

    export default {
        name: "index1",
        data() {
            return {
                // 默认停留到哪一个tab上
                activeName: 'first',
                // 预过期回调表
                expiredTableData: [],
                // 库存不足回调表
                numberTableData: [],
                // 今天的时间
                todaytime: new Date().getTime(),
                // 后端回调的预提醒时间设置
                warningexpiredtime: null,
                // 后端回调的预提醒库存数量设置
                warningnumber: null,
                // 预过期提醒消息是否有未读消息
                All_read1: null,
                // 库存不足提醒消息是否有未读消息
                All_read2: null,
                // 消息通知表
                // notificationTable: {
                //     medicine_name: 'test1',
                //     expired_number: 1,
                //     medicine_number: 2,
                //     notice_time: new Date(),
                //     notice_status: 1,
                // },
                // testjsonList1: [],
            }
        },
        created() {
            // const _this = this;
            // // 获取系统设置参数
            // axios
            //     .get(api123.baseURL + '/systemsetting/lovestory').then((response) => {
            //     let res = response.data;
            //     console.log(res);
            //     _this.warningexpiredtime = res.medicine_expired_warning;
            //     _this.warningnumber = res.medicine_number_warning;
            //     console.log(_this.warningexpiredtime);
            //     console.log(_this.warningnumber);
            //     // _this.systemsettingTableData3 = res;
            // });
            this.handleClick('first');
            // for(let i = 0 ; i < 2; i++)
            // {
            //     console.log('循环i' + i)
            //     this.testjsonList1.push(this.notificationTable)
            //
            // }
            // console.log(this.testjsonList1);
            //
            // axios
            //     .post(api123.baseURL + '/Notification/savenotice', this.testjsonList1).then((response) => {
            //         console.log(response.data);
            // })
        },
        methods: {
            handleClick(tab, event) {
                // console.log(tab, event);
                // console.log(tab.name, event);
                console.log('正义的光辉：' + this.expiredTableData)

                const _this = this;
                // 默认每页十条数据，并且获取第一页的前10条
                axios
                    .get(
                        api123.baseURL +
                        "/Notification/findAllNotices"
                    )
                    .then((response) => {
                        const res = response.data;
                        console.log(res);
                        _this.expiredTableData = res.findNotices_ListResult1; //记录的内容
                        _this.numberTableData = res.findNotices_ListResult2; //记录的内容
                        _this.All_read1 = res.All_read1;
                        _this.All_read2 = res.All_read2;
                        // console.log(new Date(_this.expiredTableData[1].expired_time).getTime())
                        // console.log(_this.todaytime.getTime())

                    });

            },
            // 点击未读小气泡
            clickRead1(row, clicktabpanelname) {
                // this.$message.info(clicktabpanelname)
                const _this = this;
                axios
                    .post(api123.baseURL + "/Notification/isreadNotice", row)
                    .then(function (resp) {
                        // _this.$message.success(resp.data.read_notice_status);
                        //延迟0.8秒来缓存数据
                        setTimeout(function () {
                            _this.handleClick(clicktabpanelname); //页面panel刷新
                            _this.RefreshBell(); // 去上级目录的header中刷新小铃铛的消息
                        }, 1 * 800); //延迟800毫米

                    });
            },
            // 删除操作
            handleDelete(row, clicktabpanelname) {
                const _this = this;
                this.$confirm("此操作将永久删除该条通知且不可恢复, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() => {
                        axios
                            .post(api123.baseURL + "/Notification/deleteNotice", row)
                            .then(function (resp) {
                                _this.$message.success(resp.data.delete_notice_status);
                                //延迟0.8秒来缓存数据
                                setTimeout(function () {
                                    _this.handleClick(clicktabpanelname); //页面panel刷新
                                    _this.RefreshBell(); // 去上级目录的header中刷新小铃铛的消息
                                }, 1 * 1150); //延迟1150毫米

                            });
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消删除",
                        });
                    });
            },
            // 跨页面刷新铃铛上的消息按钮
            RefreshBell() {
                Utils.$emit('demo', 'msg：page6的index1请求了跨页面调用函数方法');
            }
            // 隐藏某行不符合条件的数据 !!!!注意当需要修改css样式时需要删除<style scoped>里面的scoped
            /**
             * 隐藏某行不符合条件的数据
             * !!!!!!!!注意!!!!!!!!
             * !!!!!!!!注意!!!!!!!!
             * !!!!!!!!注意!!!!!!!!
             * !!!!!!!!注意!!!!!!!!
             * 当需要修改table的行css样式时
             * 需要把最下面全局css样式标签里面的 scoped 删除掉
             * 既 <style scoped> 替换为 <style> 即可！！！
             * 否则不生效！
             */
            // tables的每行数据过滤加入需要的样式
            // tableRowClassName ({row, rowIndex}) {
            //     console.log('我拿到的配置：' + this.warningexpiredtime + ', ' + this.warningnumber)
            //     console.log(((new Date(row.expired_time).getTime() - this.todaytime) / 1000 / 3600 / 24).toFixed(0));
            //     if (((new Date(row.expired_time).getTime() - this.todaytime) / 1000 / 3600 / 24).toFixed(0) > this.warningexpiredtime) {
            //         return 'hidden-row'
            //     }
            //     return '';
            // },
            // async相当于await 等待异步完成后再执行同步 获取后端需要的数据
            // async getSystemSetting() {
            //     const _this = this;
            //     axios
            //         .get(api123.baseURL + '/systemsetting/lovestory').then((response) => {
            //         let res = response.data;
            //         console.log(res);
            //         _this.warningexpiredtime = res.medicine_expired_warning;
            //         _this.warningnumber = res.medicine_number_warning;
            //         console.log(_this.warningexpiredtime);
            //         console.log(_this.warningnumber);
            //         // _this.systemsettingTableData3 = res;
            //     });
            // }
        }
    }
</script>

<style>
    /* page6页面el-tabs div样式*/
    .page6maindiv1 {
        width: 85vw;
        margin: 1.9vh 1.2vw;
    }

    /* 需要隐藏行的样式*/
    .el-table .hidden-row {
        display: none;
    }

    /* 点的样式*/
    .dian1 {
        margin-right: 0.3vw;
    }
    /* 通知时间的样式*/
    .timediv1, .timediv2, .timediv3 {
        color: #999999;
        font-size: 0.7vw;
        float: right;
        margin-left: 0.5vw;
    }
    /*数字加粗*/
    .expired_numberdiv1, .medicine_numberdiv1{
        font-weight: bolder;
    }
    /* new气泡的样式*/
    .el-badge1 .el-badge__content {
        height: 2.4vh!important;
    }

</style>