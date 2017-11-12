<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhaol
  Date: 2017/11/12
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/modifyBlessTask" method="post">
        <input type="hidden" name="id" value="${blessTask.id}">
        <input type="hidden" name="jobName" value="${blessTask.jobName}">
        <input type="hidden" name="triggerName" placeholder="最好为英文" value="${blessTask.triggerName}">
    <table>
        <tr>
            <td><span>被祝福人：</span></td>
            <td>
                <select name="personId">
                    <c:forEach var="item" items="${persons}">
                        <c:choose>
                            <c:when test="${item.id == blessTask.personId}">
                                <option value="${item.id}" selected="selected">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.id}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><span>祝福语：</span></td>
            <td><input type="text" name="blessContent" value="${blessTask.blessContent}"></td>
        </tr>
        <tr>
            <td>选择触发时间：</td>
            <td><input type="datetime-local" id="dateInput" ></td>
        </tr>
        <tr>
            <td><span>cron表达式：</span></td>
            <td><input type="text" name="cron" id="cronInput" onfocus="cronInputOnfocus()" value="${blessTask.cron}"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>
    </form>
    <script type="text/javascript">

        function getXMLHttpRequest() {
            var xmlhttp;
            if (window.ActiveXObject) {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else if (window.XMLHttpRequest) {
                xmlhttp = new XMLHttpRequest();
            } else {
                xmlhttp = null;
            }
            return xmlhttp;
        }

        function cronInputOnfocus() {
            var dateInput = document.getElementById("dateInput");
            var dateStr = dateInput.value;
            if (dateStr == "" | dateStr == undefined) {
                /*var message = document.getElementById("message");
                 var newchild = document.createElement("div");
                 newchild.innerHTML = "请选择触发时间";
                 message.append(newchild);
                 console.log("请选择触发时间");*/
                return;
            }

            var xmlhttp = getXMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    console.log(xmlhttp.responseText);
                    var result = eval('('+xmlhttp.responseText+')');
                    if (result.status == "success") {
                        document.getElementById("cronInput").value = result.cron;
                    }
                }
            }
            xmlhttp.open("POST", "/getCronExpression", true);
            xmlhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
            //var data = JSON.stringify(date);
            xmlhttp.send(dateStr);
        }

    </script>
</body>
</html>
