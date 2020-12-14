<template>
    <div class="page1maindiv1">
        <el-row class="elrowdiv1">
            <el-col>
                <div class="childrendashboard1"><span>药品出库详情</span></div>
            </el-col>
            <el-col>
                <HR class="hrline1"></HR>
            </el-col>
            <el-col>
                <el-form :inline="true" :model="exportCalendarSelectForm1" class="exportCalendarSelectForm1div1">
                    <el-form-item>
                        <el-date-picker
                                size="mini"
                                v-model="exportCalendarSelectForm1.export_time"
                                align="left"
                                type="date"
                                placeholder="选择日期"
                                format="yyyy年MM月dd日"
                                value-format="yyyy-MM-dd"
                                :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary"
                                   size="mini"
                                   @click="onSubmit">搜索</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col>
                <template>
                    <div id="myEchartsBar1" class="myEchartsBar1div1"></div>
                </template>
            </el-col>

        </el-row>
        <el-row class="elrowdiv2">
            <el-col>
                <div class="childrendashboard1"><span>药品入库详情</span></div>
            </el-col>
            <el-col>
                <HR class="hrline1"></HR>
            </el-col>
            <el-col>
                <el-form :inline="true" :model="importCalendarSelectForm1" class="exportCalendarSelectForm1div1">
                    <el-form-item>
                        <el-date-picker
                                size="mini"
                                v-model="importCalendarSelectForm1.storage_time"
                                align="left"
                                type="date"
                                placeholder="选择日期"
                                format="yyyy年MM月dd日"
                                value-format="yyyy-MM-dd"
                                :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary"
                                   size="mini"
                                   @click="storageonSubmit">搜索</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
            <el-col>
                <template>
                    <div id="myEchartsBar2" class="myEchartsBar1div2"></div>
                </template>
            </el-col>
        </el-row>
        <el-row class="elrowdiv3">
            <el-col class="elrowdiv3_col1">
                <div class="elrowdiv3_childrendashboard1"><span>药品总库存</span></div>
                <HR class="elrowdiv3_hrline1"></HR>
                <div class="MaterialsTotalNumberdiv1"><span>{{MaterialsTotalNumber}}</span></div>
            </el-col>
            <el-col class="elrowdiv3_col2">
                <div class="elrowdiv3_childrendashboard2"><span>设备总库存</span></div>
                <HR class="elrowdiv3_hrline2"></HR>
                <div class="MaterialsTotalNumberdiv2"><span>{{EquipmentTotalNumber}}</span></div>
            </el-col>
            <el-col class="elrowdiv3_col3">
                <div class="elrowdiv3_childrendashboard3"><span>库存不足总量</span></div>
                <HR class="elrowdiv3_hrline3"></HR>
                <div class="MaterialsTotalNumberdiv3"><span>{{Total_Inadequate_Numbers}}</span></div>
            </el-col>
            <el-col class="elrowdiv3_col4">
                <div class="elrowdiv3_childrendashboard4"><span>预到期总量</span></div>
                <HR class="elrowdiv3_hrline4"></HR>
                <div class="MaterialsTotalNumberdiv4"><span>{{Total_Pre_expired_number}}</span></div>
            </el-col>
            <el-col class="elrowdiv3_col5">
                <div class="elrowdiv3_childrendashboard5"><span>今日使用人员详情</span></div>
                <HR class="elrowdiv3_hrline5"></HR>
                <el-row class="elrowdiv3_col5_row_div1">
                    <el-row v-for="item in TodayLogin_log_ListResult2" class="elrowdiv3_col5_div1" :key="item.id">
                        <el-col>{{item.login_name}}({{item.user_role}})</el-col>
                        <el-col class="elrowdiv3_col5_row_col2">{{item.login_date.substr(10, 6)}}</el-col>
                    </el-row>
                </el-row>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import axios from "axios";
    import api123 from "../../../api/global_variable";
    import echarts from 'echarts';
    export default {
        name: "index1",
        data() {
            return {
                // 出库日期表单
                exportCalendarSelectForm1: {
                  export_time: new Date(),
                },
                // 入库日期表单
                importCalendarSelectForm1: {
                    storage_time: new Date(),
                },
                // 日期选择器1里面的配置
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    }
                },
                // 接收当前药品库存总量
                MaterialsTotalNumber: null,
                // 接收当前设备库存总量
                EquipmentTotalNumber: null,
                // 接收当前库存不足总量
                Total_Inadequate_Numbers: null,
                // 接收当前预到期总量
                Total_Pre_expired_number: null,
                // 接收今日使用人员记录
                TodayLogin_log_ListResult2: [],
            }
        },
        created() {
            this.getMaterialsTotalNumber();
            this.getTodayLoginlog();
            this.getEquipmentTotalNumber();
        },
        mounted() {
            let date = this.exportCalendarSelectForm1.export_time;
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            let day = date.getDate();
            console.log('叶佳今天出库：' + year + '-' + month + '-' + day);
            this.exportCalendarSelectForm1.export_time = year + '-' + month + '-' + day;
            this.drawing1();

            let date2 = this.importCalendarSelectForm1.storage_time;
            let year2 = date2.getFullYear();
            let month2 = date2.getMonth() + 1;
            let day2 = date2.getDate();
            console.log('叶佳今天入库：' + year2 + '-' + month2 + '-' + day2);
            this.importCalendarSelectForm1.storage_time = year2 + '-' + month2 + '-' + day2;
            this.drawing2();
        },
        methods: {
            // 出库日期搜索提交的方法
            onSubmit(){
                console.log(this.exportCalendarSelectForm1.export_time)
                this.drawing1()
            },
            // 入库日期搜索提交的方法
            storageonSubmit(){
                console.log(this.importCalendarSelectForm1.storage_time)
                this.drawing2()
            },
            // 第一个出库柱状图方法
            drawing1() {
                const _this = this;
                // let date = this.exportCalendarSelectForm1.export_time;
                // let year = date.getFullYear();
                // let month = date.getMonth() + 1;
                // let day = date.getDate();
                // console.log('叶佳：' + year + '-' + month + '-' + day);
                // this.exportCalendarSelectForm1.export_time = year + '-' + month + '-' + day;
                let myEchartsBar1 = echarts.init(document.getElementById('myEchartsBar1'));
                axios
                .get(api123.baseURL + '/DataView/findByExportTime/' +
                    this.exportCalendarSelectForm1.export_time)
                .then(response => {
                    let res = response.data;
                    console.log(res.Export_Medicine_Name_List1.length);
                    // const colorList3 = ['#1F9FFF', '#13CD65']; //交叉的颜色设置
                    const colorList3 = []; //交叉的颜色设置
                    let colorlength = res.Export_Medicine_Name_List1.length;
                    if(colorlength > 0)
                    {
                        for(let i = 0; i < colorlength; i++)
                        {
                            if(i % 2 == 1)
                            {
                                colorList3.push('#1F9FFF');
                            }else
                            {
                                colorList3.push('#13CD65');
                            }
                        }
                    }

                    if(res.Export_Medicine_Name_List1.length === 0)
                    {
                        // 没数据的时候
                        let option = {
                            tooltip: {},
                            grid: {
                                left: 25,
                                right: 0,
                                top: 8,
                                bottom: 0,
                                width: "98%", // 宽度
                                height: "80%"// 高度
                            },
                            xAxis: {
                                data: ["今日暂无数据更新"],
                                // data: ["t2"],
                                axisLabel: {
                                    textStyle: {
                                        color: '#595959',  // 字体颜色
                                        fontStyle: 'normal',
                                        fontFamily: '微软雅黑',
                                        // fontSize: 12,
                                    },
                                },
                            },
                            yAxis: {
                                axisLabel: {
                                    textStyle: {
                                        color: '#595959',  // 字体颜色
                                        fontStyle: 'normal',
                                        fontFamily: '微软雅黑',
                                        // fontSize: 12,
                                    },
                                },
                            },
                            series: [{
                                // name: '当日药品出库详情',
                                type: 'bar',
                                data: [0],
                                // data: [0],
                            }]
                        };
                        myEchartsBar1.setOption(option);
                    }else
                    {
                        // 指定图表的配置项和数据
                        let option = {
                            title: {
                                // text: 'ECharts 入门示例'
                            },
                            tooltip: {},
                            grid: {
                                left:25,
                                right: 0,
                                top: 8,
                                bottom: 0,
                                width: "98%", // 宽度
                                height: "80%"// 高度
                            },
                            legend: {
                                // data:['销量']
                            },
                            xAxis: {
                                data: res.Export_Medicine_Name_List1,
                                // data: ["t2"],
                                axisLabel: {
                                    textStyle: {
                                        color: '#595959',  // 字体颜色
                                        fontStyle: 'normal',
                                        fontFamily: '微软雅黑',
                                        // fontSize: 12,
                                    },
                                },
                                axisTick: {
                                    show: false // 隐藏刻度线
                                },
                                axisLine: {
                                    show: false, // 隐藏轴线
                                },
                            },
                            yAxis: {
                                axisLabel: {
                                    textStyle: {
                                        color: '#595959',  // 字体颜色
                                        fontStyle: 'normal',
                                        fontFamily: '微软雅黑',
                                        // fontSize: 12,
                                    },
                                },
                                axisTick: {
                                    show: false // 隐藏刻度线
                                },
                                axisLine: {
                                    show: false, // 隐藏轴线
                                },
                            },
                            series: [{
                                // name: '当日药品出库详情',
                                type: 'bar',
                                data: res.Export_Medicine_Number_List1,
                                // data: [0],
                                itemStyle: {
                                    normal: {
                                        color: function (params) {
                                            return colorList3[params.dataIndex];
                                        },
                                    }
                                }
                            }]
                        };
                        myEchartsBar1.setOption(option);
                    }
                });

            },
            // 第二个入库柱状图方法
            drawing2() {
                const _this = this;
                // let date = this.exportCalendarSelectForm1.export_time;
                // let year = date.getFullYear();
                // let month = date.getMonth() + 1;
                // let day = date.getDate();
                // console.log('叶佳：' + year + '-' + month + '-' + day);
                // this.exportCalendarSelectForm1.export_time = year + '-' + month + '-' + day;
                let myEchartsBar2 = echarts.init(document.getElementById('myEchartsBar2'));
                axios
                    .get(api123.baseURL + '/DataView/findByImportTime/' +
                        this.importCalendarSelectForm1.storage_time)
                    .then(response => {
                        let res = response.data;
                        console.log(res.Storage_Medicine_Name_List1.length);
                        // const colorList3 = ['#1F9FFF', '#13CD65']; //交叉的颜色设置
                        const colorList3 = []; //交叉的颜色设置
                        let colorlength = res.Storage_Medicine_Name_List1.length;
                        if(colorlength > 0)
                        {
                            for(let i = 0; i < colorlength; i++)
                            {
                                if(i % 2 == 1)
                                {
                                    colorList3.push('#1F9FFF');
                                }else
                                {
                                    colorList3.push('#13CD65');
                                }
                            }
                        }

                        if(res.Storage_Medicine_Name_List1.length === 0)
                        {
                            // 没数据的时候
                            let option = {
                                tooltip: {},
                                grid: {
                                    left: 25,
                                    right: 0,
                                    top: 8,
                                    bottom: 0,
                                    width: "98%", // 宽度
                                    height: "80%"// 高度
                                },
                                xAxis: {
                                    data: ["今日暂无数据更新"],
                                    // data: ["t2"],
                                    axisLabel: {
                                        textStyle: {
                                            color: '#595959',  // 字体颜色
                                            fontStyle: 'normal',
                                            fontFamily: '微软雅黑',
                                            // fontSize: 12,
                                        },
                                    },
                                },
                                yAxis: {
                                    axisLabel: {
                                        textStyle: {
                                            color: '#595959',  // 字体颜色
                                            fontStyle: 'normal',
                                            fontFamily: '微软雅黑',
                                            // fontSize: 12,
                                        },
                                    },
                                },
                                series: [{
                                    // name: '当日药品出库详情',
                                    type: 'bar',
                                    data: [0],
                                    // data: [0],
                                }]
                            };
                            myEchartsBar2.setOption(option);
                        }else
                        {
                            // 指定图表的配置项和数据
                            let option = {
                                title: {
                                    // text: 'ECharts 入门示例'
                                },
                                tooltip: {},
                                grid: {
                                    left: 25,
                                    right: 0,
                                    top: 8,
                                    bottom: 0,
                                    width: "98%", // 宽度
                                    height: "80%"// 高度
                                },
                                legend: {
                                    // data:['销量']
                                },
                                xAxis: {
                                    data: res.Storage_Medicine_Name_List1,
                                    axisLabel: {
                                        textStyle: {
                                            color: '#595959',  // 字体颜色
                                            fontStyle: 'normal',
                                            fontFamily: '微软雅黑',
                                            // fontSize: 12,
                                        },
                                    },
                                    axisTick: {
                                        show: false // 隐藏刻度线
                                    },
                                    axisLine: {
                                        show: false, // 隐藏轴线
                                    },
                                },
                                yAxis: {
                                    axisLabel: {
                                        textStyle: {
                                            color: '#595959',  // 字体颜色
                                            fontStyle: 'normal',
                                            fontFamily: '微软雅黑',
                                            // fontSize: 12,
                                        },
                                    },
                                    axisTick: {
                                        show: false // 隐藏刻度线
                                    },
                                    axisLine: {
                                        show: false, // 隐藏轴线
                                    },
                                },
                                series: [{
                                    // name: '当日药品出库详情',
                                    type: 'bar',
                                    data: res.Storage_Medicine_Number_List1,
                                    itemStyle: {
                                        normal: {
                                            color: function (params) {
                                                return colorList3[params.dataIndex];
                                            },
                                        }
                                    }
                                }]
                            };
                            myEchartsBar2.setOption(option);
                        }
                    });

            },
            // 请求获取药品总库存 库存不足总量 预到期药品总量
            getMaterialsTotalNumber() {
                const _this = this;
                axios
                .get(api123.baseURL + '/Materialsmedicinemanagement/findmaterialsmedicinetonotification/1/100000/ascending/id')
                .then((response) => {
                    let res = response.data;
                    _this.MaterialsTotalNumber = res.Total_materials_medicine_number;
                    _this.Total_Inadequate_Numbers = res.Total_Inadequate_Numbers;
                    _this.Total_Pre_expired_number = res.Total_Pre_expired_number;
                });
            },
            // 请求今日使用人员记录
            getTodayLoginlog() {
                const _this = this;
                axios
                    .get(api123.baseURL + '/Loginlog/findTodayLoginlog')
                    .then((response) => {
                        let res = response.data;
                        _this.TodayLogin_log_ListResult2 = res.TodayLogin_log_ListResult2;
                    });
            },
            // 获取设备总库存
            getEquipmentTotalNumber() {
                const _this = this;
                axios
                    .get(api123.baseURL + '/Materialsequipmentmanagement/findmaterialsequipmentTotalNumber/1/100000/ascending/id')
                    .then((response) => {
                        let res = response.data;
                        _this.EquipmentTotalNumber = res.Total_Equip_Number;
                    });
            },
        },
    }
</script>

<style scoped>
    /* 所有子div的样式*/
    .elrowdiv1, .elrowdiv2, .elrowdiv3 {
        /*border: 1px solid #e5e5e5;*/
        background-color: #ffffff;
    }

    /*分割线*/
    .hrline1 {
        width: 85vw;
        border: 1px solid #457DDD;
        margin: 0.4vh 1.2vw;
        opacity: 0.3;
    }

    /*第三个div子二级导航文字分割线样式1*/
    .elrowdiv3_hrline1, .elrowdiv3_hrline2, .elrowdiv3_hrline3, .elrowdiv3_hrline4 {
        width: 11.8vw;
        border: 1px solid #457DDD;
        margin: 0.4vh auto;
        opacity: 0.3;
    }

    /*第三个div子二级导航文字分割线样式2*/
    .elrowdiv3_hrline5 {
        width: 22.4vw;
        border: 1px solid #457DDD;
        margin: 0.4vh 1.2vw;
        opacity: 0.3;
    }

    /*顶部二级导航文字*/
    .childrendashboard1 {
        margin: 1.8vh 0 0 1.3vw;
        color: #457DDD;
    }

    /*第三个div子二级导航文字样式1*/
    .elrowdiv3_childrendashboard1, .elrowdiv3_childrendashboard2, .elrowdiv3_childrendashboard3, .elrowdiv3_childrendashboard4 {
        margin: 1.8vh 0 0 1.6vw;
        color: #457DDD;
    }
    /*第三个div子二级导航文字样式2*/
    .elrowdiv3_childrendashboard5 {
        margin: 1.8vh 0 0 1.3vw;
        color: #457DDD;
    }

    /* elrowdiv2 单独样式*/
    .elrowdiv2 {
        margin-top: 1.5vh;
    }

    /* elrowdiv3 单独样式*/
    .elrowdiv3 {
        margin-top: 1.5vh;
        background-color: #f4f4f5;
    }

    /*row div 3 单独的样式*/
    .elrowdiv3 {
        display: flex;
    }

    /*elrow3的col通用样式1*/
    .elrowdiv3_col1, .elrowdiv3_col2, .elrowdiv3_col3, .elrowdiv3_col4, .elrowdiv3_col5 {
        /*border: 1px solid #e5e5e5;*/
        background-color: #ffffff;
    }
    /*elrow3的col通用样式2*/
    .elrowdiv3_col2, .elrowdiv3_col3, .elrowdiv3_col4, .elrowdiv3_col5 {
        margin: 0 0 0 0.8vw;
    }


    /* elrow1日历提交表单form的样式*/
    .exportCalendarSelectForm1div1 {
        margin: 0 0 0 1.3vw;
    }

    /* 出库表1的样式*/
    .myEchartsBar1div1, .myEchartsBar1div2{
        /*style="width: 85vw;height:400px;*/
        width: 85vw;
        height: 21.2vh;
        margin: 0 0 0 1.3vw;
    }

    /* 最下面各个总量通用样式div*/
    .MaterialsTotalNumberdiv1, .MaterialsTotalNumberdiv2, .MaterialsTotalNumberdiv3, .MaterialsTotalNumberdiv4 {
        margin: 2.6vh 0 2.6vh 0;
        text-align: center;
        font-size: 2.5vw;
        font-family: Arial;
        font-weight: bold;
        color: #20A0FF;
    }
    /* 今日使用人员详情每列数据的样式*/
    .elrowdiv3_col5_div1 {
        display: flex;
        line-height: 2.3vh;
    }
    /* 今日使用人员局部滑动条*/
    .elrowdiv3_col5_row_div1 {
        margin: 0 0 0 1.3vw;
        width: 90%;
        height: 9.7vh;
        overflow: auto;
    }

    .elrowdiv3_col5_row_div1::-webkit-scrollbar {
        display: none;
    }
    /* 今日使用人员局部第二列 时间右对齐*/
    .elrowdiv3_col5_row_col2 {
        text-align: right;
        margin: 0 0.2vw 0 0;
    }

    /* page1的全局样式 给底色*/
    .page1maindiv1 {
        background-color: #f4f4f5;
    }

</style>