<%--
  Created by IntelliJ IDEA.
  User: zhaol
  Date: 2017/11/3
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加人员</title>
</head>
<body>
    <form action="/person" method="post">
        <label for="idCard">身份证号</label><input type="text" name="idCard" id="idCard"><br>
        <label for="name">姓名</label><input type="text" name="name" id="name"><br>
        <label for="sex">年龄</label><input type="text" name="sex" id="sex"><br>
        <label for="age">性别</label><input type="text" name="age" id="age"><br>
        <label for="address">地址</label><input type="text" name="address" id="address"><br>
        <label for="phone">电话</label><input type="text" name="phone" id="phone"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
