<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>top</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!-- 适配各种设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap -->
    <link href="../css/bootstrap.css" rel="stylesheet">
    <title>基本模板</title>
    <!-- 先引jquery -->
    <script src="../js/jquery-3.2.1.js"></script>
    <script src="../js/bootstrap.js"></script>

    <%-- 添加背景音乐 --%>
    <embed src="music/bg.mp3" loop="true" autostar="true" hidden="true"></embed>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="icon" href="../images/logo.png">
    <style type="text/css">
        body {
            background-image: url(../images/top.jpg);
        }

        a {
            text-transform: none;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
        a:link{
            color: blue;
        }
        a:visited{
            color: blue;
        }
        #div1{
            position: fixed;
            float: right;
            top: 0;
            right: 0;
        }

        #div {
            float: right;
            position: fixed;
            bottom: 0px;
            right: 0;
        }
        #i1 {
            width: 170px;
            height: 20px;
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
        *{
            font-size: 20px;
        }
    </style>

</head>

<body>
<h1 style="text-align: center; margin-top: 30px">神缨书店</h1>
<div id="div">
    <form action="${pageContext.request.contextPath}/BookServlet" method="post" target="body">
        <input type="hidden" name="method" value="findLike">
        <input id="i1" type="text" name="bname" placeholder="书名" autocomplete="off">
        <input type="submit" value="🔍" style="height: 30px; width: 40px">
    </form>
</div>
<div id="div1" class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        分享
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
        <li><a id="a1" title="分享到QQ空间" target="view_">QQ空间</a></li>
        <li><a id="a2" title="分享到新浪微博">新浪微博</a></li>
        <li><a id="a3" title="分享到人人网">人人网</a></li>
        <li><a id="a4" title="分享到腾讯微博">腾讯微博</a></li>
    </ul>
</div>
<div style="font-size: 10pt;">
    <c:if test="${formUser != null}">
        您好：<a href="<c:url value="/jsps/user/list.jsp"/>" target="_parent">${formUser.username}</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="<c:url value='/jsps/cart/list.jsp'/>" target="body" class="glyphicon glyphicon-shopping-cart"></a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/UserServlet?method=quit" target="_parent" class="glyphicon glyphicon-off"></a>
    </c:if>
    <br/>
    <c:if test="${formUser == null}">
        <a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a> |&nbsp;
        <a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a>
    </c:if>
</div>
<script>
    var title = encodeURIComponent("红缨书店");
    var link = encodeURIComponent('http:http://localhost:8080/bookstore/index.jsp');
    document.getElementById("a1").onclick = function () {
        window.open("http://v.t.qq.com/share/share.php?url=" + link + "&title=" + title + "&content=utf8");
    };
    document.getElementById("a2").onclick = function () {
        window.open("http://v.t.sina.com.cn/share/share.php?url=" + link + "&title=" + title + "&content=utf8");
    };
    document.getElementById("a3").onclick = function () {
        window.open("http://widget.renren.com/dialog/share?resourceUrl=" + link + "&title=" + title + "&content=utf8");
    };
    document.getElementById("a4").onclick = function () {
        window.open("http://v.t.qq.com/share/share.php?url=" + link + "&title=" + title + "&content=utf8");
    };
</script>
</body>
</html>
