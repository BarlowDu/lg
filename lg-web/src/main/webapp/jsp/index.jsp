<%--
  Created by IntelliJ IDEA.
  User: dutc
  Date: 2016/11/15
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>
    <script src="<c:url value="/js/jquery-2.1.4.min.js"></c:url>"></script>
    <script src="<c:url value="/bootstrap-3.3.5/js/bootstrap.min.js"></c:url>"></script>
    <link href="<c:url value="/bootstrap-3.3.5/css/bootstrap.min.css"></c:url>" rel="stylesheet" type="text/css" />
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th>MINS</th>
        <th>MAXS</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${data}">
            <tr>
                <td>${item.minSalary }</td>
                <td>${item.maxSalary }</td>
            </tr>
        </c:forEach>
    </tbody>

</table>
</body>
</html>
