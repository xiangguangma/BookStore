<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>用户登录</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="icon" href="../../images/logo.png">
    <style type="text/css">
        body {
            text-align: center;
            background-image: url(../../images/a.jpg);
            background-size: cover;
        }
        span {
            color: red;
        }
        #div1{
            margin: auto;
            width: 400px;
            height: 250px;
        }
        #b{
            text-align: center;
            width: 200px;
            height: 40px;
            background-color: red;
            color: white;

        }
        input {
            width: 270px;
            height: 40px;
            background: #2d2d2d; /* browsers that don't support rgba */
            *background-color:transparent;
            background: rgba(45,45,45,.15);
            -moz-border-radius: 6px;
            -webkit-border-radius: 6px;
            border-radius: 6px;
            border: 1px solid #3d3d3d; /* browsers that don't support rgba */
            border: 1px solid rgba(255,255,255,.15);
            -moz-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
            -webkit-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
            box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
            color: #fff;
            text-shadow: 0 1px 2px rgba(0,0,0,.1);
            -o-transition: all .2s;
            -moz-transition: all .2s;
            -webkit-transition: all .2s;
            -ms-transition: all .2s;
        }

        input:-moz-placeholder { color: #fff; }
        input:-ms-input-placeholder { color: #fff; }
        input::-webkit-input-placeholder { color: #fff; }

        input:focus {
            outline: none;
            -moz-box-shadow:
                    0 2px 3px 0 rgba(0,0,0,.1) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            -webkit-box-shadow:
                    0 2px 3px 0 rgba(0,0,0,.1) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
            box-shadow:
                    0 2px 3px 0 rgba(0,0,0,.1) inset,
                    0 2px 7px 0 rgba(0,0,0,.2);
        }
        #div{
           margin-left: 120px;
        }


    </style>
</head>

<body>
<div id="div1">
    <br><br><br>
    <div id="div">
        <h1>Login</h1>
        <p style="color: red; font-weight: 900">${msg}</p>
        <form action="${pageContext.request.contextPath}/UserServlet" method="post" id="form" target="_parent">
            <input type="hidden" name="method" value="login">
            <input type="text" name="username" placeholder="Username" autocomplete="off"/><span>${map.username}</span>
            <br/><br>
            <input type="password" name="password" placeholder="Password" oncontextmenu="return false" onpaste="return false"><span>${map.password}</span>
            <br/><br>
            <input id="b" type="submit" value="sign in"/>
        </form>
    </div>
</div>
</body>
</html>
