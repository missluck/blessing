<%--
  Created by IntelliJ IDEA.
  User: zhaol
  Date: 2017/11/7
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <c:forEach var="item" items="${blessTasks}">
        <tr>
            <td>
                <span>${item}</span>
            </td>
            <td>
                <a href="/modifyBlessTaskBefore/${item.id}">修改任务</a>
            </td>
            <td>
                <a href="/removeBlessTask/${item.id}">删除任务</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
