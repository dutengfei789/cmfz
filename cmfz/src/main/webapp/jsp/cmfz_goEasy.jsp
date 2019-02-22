<%--
  Created by IntelliJ IDEA.
  User: du
  Date: 2019/1/9
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>goEasyTest</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
</head>
<body>

    <div id="main" style="width: 600px;height:400px;"></div>
    <div id="test1" style="width: 600px;height:400px;"></div>
    <div id="getChinaByMap" style="width: 600px;height:400px;"></div>

    <script type="text/javascript">

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '用户性别统计'
            },
            tooltip: {},
            legend: {
                data:['性别']
            },
            xAxis: {
                data: ["男","女"]
            },
            yAxis: {},

        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);


        // 1.基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init(document.getElementById('test1'));

        // 2.指定图表的配置项和数据
        var option1 = {
            title: {
                text: '过去三周用户注册量变化'
            },
            tooltip: {},
            legend: {
                data:['注册量']
            },
            xAxis: {
                data: ["过去三周","过去两周","过去一周"]
            },
            yAxis: {},

        };

        // 3.使用刚指定的配置项和数据显示图表。
        myChart1.setOption(option1);

        var myChina = echarts.init(document.getElementById('getChinaByMap'));
        var option3 = {
            title : {
                text: '用户地区分布',
                left: 'center'
            },
            tooltip : {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data:['用户人数']
            },
            visualMap: {
                min: 0,
                max: 2500,
                left: 'left',
                top: 'bottom',
                text:['高','低'],           // 文本，默认为数值文本
                calculable : true
            },
            toolbox: {
                show: true,
                orient : 'vertical',
                left: 'right',
                top: 'center',
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            }

        };


        myChina.setOption(option3);



        $.ajax({
            url:"${pageContext.request.contextPath}/user/concurrentGetEcharts",
            type:"get",
            dataType:"json",
            success:function (data) {

                //用户性别统计
                myChart.setOption({
                    series: [{
                        name: '性别',
                        type: 'bar',
                        data: [data.userMap.nan, data.userMap.nv],
                        xAxis: {
                            data: [data.userMap.nan,data.userMap.nv]
                        }
                    }]
                });
                myChart.setOption(option);

                //用户注册时间统计
                myChart1.setOption({
                    series: [{
                        name: '注册量',
                        type: 'line',
                        data: [data.countByWeek[2],data.countByWeek[1],data.countByWeek[0]],
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }]
                });
                myChart1.setOption(option1);

                //用户省份统计
                myChina.setOption({
                    series: [{
                        name: '人数',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:data.chinaByMap
                    }]
                });
            }
        });

        var goEasy = new GoEasy({
            appkey: "BS-630d5f08cb804b06a696d69c2838e30b"
        });

        goEasy.subscribe({
            channel: "cmfzChannel",
            onMessage: function (message) {
                var jsObj=JSON.parse(message.content);
                // alert("Channel:" + message.content.countByWeek + " content:" + message.content);

                //用户性别统计
                myChart.setOption({
                    series: [{
                        name: '性别',
                        type: 'bar',
                        data: [jsObj.userMap.nan, jsObj.userMap.nv]
                    }]
                });
                myChart.setOption(option);


                //用户注册时间统计
                myChart1.setOption({
                    series: [{
                        name: '注册量',
                        type: 'line',
                        data: [jsObj.countByWeek[2],jsObj.countByWeek[1],jsObj.countByWeek[0]],
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }]
                });
                myChart1.setOption(option1);

                //用户省份统计
                myChina.setOption({
                    series: [{
                        name: '人数',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data:jsObj.chinaByMap
                    }]
                });
            }
        });

    </script>
</body>
</html>
