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
    <link rel="icon" href="../images/logo.png">
    <style>
        body{
            text-align: center;
            background-image: url(../images/a.jpg);
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
        a{
            text-underline: none;
            text-decoration: none;
        }
        a:link{
            color: blue;
        }
        a:visited{
            color: blue;
        }
    </style>
</head>
<body>
<br>
<table>
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>密码</th>

    </tr>
    <tr>
        <td>${admin.aid}</td>
        <td>${admin.aname}</td>
        <td>${admin.password}</td>
    </tr>
</table>
</body>
</html>
