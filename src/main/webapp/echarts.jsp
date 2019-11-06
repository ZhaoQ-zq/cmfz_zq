<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/statics/boot/js/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.0.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        url:'${pageContext.request.contextPath}/user/selectUserCount',
        datatype:'json',
        type:'post',
        success:function (result) {
            myChart.setOption({
                    series: [{
                        name: '男',
                        type: 'bar',
                        //柱状图：bar   pie：饼状图 line：线状图
                        data: [result.nan1,result.nan2,result.nan3]
                        // data: [5, 20, 36, 10, 10, 20]
                    },{
                        name:'女',
                        type: 'bar',
                        data: [result.nv1,result.nv2,result.nv3]
                    }]
                }
            );
        }
    })
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'cmfz用户近三周注册统计',
            textStyle:{
                fontStyle:"oblique"
            }
        },
        tooltip: {},
        legend: {
            data:['男','女']
        },
        xAxis: {
            data: ["第一周","第二周","第三周"]
        },
        yAxis: {}
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io',//应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-9f4afe2dcb1146269756b2881da44cd1",
    });
    //接收消息
    goEasy.subscribe({
        channel: "zq",
        onMessage: function (message) {
            $.ajax({
                url:'${pageContext.request.contextPath}/user/selectUserCount',
                datatype:'json',
                type:'post',
                success:function (result) {
                    myChart.setOption({
                            series: [{
                                name: '男',
                                type: 'bar',
                                data: [result.nan1,result.nan2,result.nan3]
                            },{
                                name:'女',
                                type: 'bar',
                                data: [result.nv1,result.nv2,result.nv3]
                            }],title:{
                                     text:message.content
                                }
                        }
                    );
                }
            })
        }
    });
</script>