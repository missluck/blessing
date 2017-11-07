<%--
  Created by IntelliJ IDEA.
  User: zhaol
  Date: 2017/11/7
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/modifyPerson" method="post">
        <input type="hidden" name="id" value="${person.id}">
        <span>name</span><input type="text" name="name" value="${person.name}"><br>
        <span>sex</span><input type="text" name="sex" value="${person.sex}"><br>
        <span>age</span><input type="text" name="age" value="${person.age}"><br>
        <span>address</span><input type="text" name="address" value="${person.address}"><br>
        <span>phone</span><input type="text" name="phone" value="${person.phone}"><br>
        <span>idCard</span><input type="text" name="idCard" value="${person.idCard}"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
