<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>图书列表</title>

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
            background-image: url(../../images/1.png);
            font-size: 10pt;
        }

        .icon {
            margin: 10px;
            border: solid 2px gray;
            width: 160px;
            height: 200px;
            text-align: center;
            float: left;
        }

        a {
            text-underline: none;
            text-decoration: none;
        }

        a:link {
            color: blue;
        }

        a:visited {
            color: blue;
        }

        .icon_pop {
            position: absolute;
            top: 5px;
            right: 5px;
        }

        .product_tags {
            width: 56px !important;
            height: 56px !important;;
            position: absolute;
            right: -5px;
            top: -5px;
            color: #fff;
            font: bold 12px/18px Arial;
            overflow: hidden;
            text-align: center;
            text-decoration: none;
        }
    </style>
</head>

<body>
<embed src="http://media2.ddimg.cn/files/17/23/12001787.swf" quality="high" wmode="transparent"
       pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash"
       type="application/x-shockwave-flash" width="100" height="100">

<c:forEach var="book" items="${list}">

    <div class="icon">
        <a href="<c:url value='/BookServlet?method=load&bid=${book.bid}'/>"><img
                src="<c:url value='${book.image}'/>" border="0"/></a>
        <br/>
        <a href="<c:url value='/BookServlet?method=load&bid=${book.bid}'/>">${book.bname}</a>
    </div>



</c:forEach>


</body>

</html>

