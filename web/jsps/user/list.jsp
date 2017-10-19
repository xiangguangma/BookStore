<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/9/26
  Time: 下午8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的资料</title>
    <link rel="icon" href="../../images/logo.png">
    <style>
        body{
            text-align: center;
            background-image: url(../../images/a.jpg);
            background-size: cover;
        }
        table,th,tr,td{
            border: 1px solid blue;
            border-collapse: collapse;
        }
        table{
            width: 500px;
            text-align: center;
            vertical-align: baseline;
            line-height: 30px;
            margin: auto;
        }
        th,tr,td{
            height: 30px;
        }

    </style>
</head>
<body>
<br>
<%-- 防止直接输入地址, 进行判断 --%>
<c:if test="${formUser == null}">
    <c:redirect url="/jsps/user/login.jsp"/>
</c:if>
<table>
    <tr>
        <th>姓名</th>
        <th>密码</th>
        <th>邮箱</th>
        <th>编辑</th>

    </tr>
    <tr>
        <td>${formUser.username}</td>
        <td>${formUser.password}</td>
        <td>${formUser.email}</td>
        <td><a href="<c:url value="/jsps/user/edit.jsp"/>">修改</a></td>
    </tr>
</table>
</body>
</html>
