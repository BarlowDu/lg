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
            data: <%=request.getAttribute("titles")%>
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
                data: ${data },
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
    var divMain = echarts.init(document.getElementById('divMain'));
    divMain.setOption(option)
</script>
</body>
</html>
