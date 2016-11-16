<%--
  Created by IntelliJ IDEA.
  User: dutc
  Date: 2016/11/15
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="<c:url value="/js/jquery-2.1.4.min.js"></c:url>"></script>
    <script src="<c:url value="/js/echarts.min.js"></c:url>"></script>
</head>
<body>
<div style="margin:auto 0;width:800px;" >
    <div style="height: 400px;">
        <div id="divEdu" style="width:500px;height:400px;float:left;"></div>
        <div id="divSalary" style="width:500px;height:400px;float:left;"></div>
    </div>
</div>
<script type="text/javascript">
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{b} <br/>{c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            top: 'middle',
            data: ${titlesEdu}
            //formatter: '{name}'
        },
        series: [
            {
                type: 'pie',
                radius: '70%',
                center: ['55%', '50%'],
                label: {
                    normal: {
                        position: 'outer',
                        formatter: "{b}({d}%)"
                    }
                },
                data: ${dataEdu },
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]

    };
    var divEdu = echarts.init(document.getElementById('divEdu'));
    divEdu.setOption(option)
</script>
<script type="text/javascript">
    var optionSalary = {
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ${titlesSalary},
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '直接访问',
                type: 'bar',
                barWidth: '60%',
                data:${dataSalary}
            }
        ]
    };
    var divSalary = echarts.init(document.getElementById('divSalary'));
    divSalary.setOption(optionSalary)
</script>
</body>
</html>
