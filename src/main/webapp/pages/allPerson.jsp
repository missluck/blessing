<%--
  Created by IntelliJ IDEA.
  User: zhaol
  Date: 2017/11/7
  Time: 20:30
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
        <c:forEach var="item" items="${persons}">
        <tr>
            <td>
                <span>${item.toString()}</span>
            </td>
            <td>
                <a href="/modifyPersonBefore/${item.id}">修改信息</a>
            </td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
