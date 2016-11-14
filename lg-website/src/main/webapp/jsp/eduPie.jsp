<%--
  Created by IntelliJ IDEA.
  User: dutc
  Date: 2016/11/14
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>EduPie</title>
    <script src='../js/jquery-2.1.4.min.js'></script>
    <script src='../js/echarts.min.js'></script>
</head>
<body>
<div style="width:800px;height: 600px;" id="divMain">

</div>
<script type="text/javascript">
    $("#divMain").text("123");
    var divRemindTypePie = echarts.init(document.getElementById('divMain'));
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{b} <br/>{c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            top: 'middle',
            data: ${titles }
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
                        formatter: "{c}({d}%)"
                    }
                },
                data: ${data},
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
    divRemindTypePie.setOption(option);
</script>
</body>
</html>
