<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>购物车列表</title>

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
        }

        * {
            font-size: 11pt;
        }

        div {
            margin: 20px;
            border: solid 2px gray;
            width: 150px;
            height: 150px;
            text-align: center;
        }

        li {
            margin: 10px;
        }

        #buy {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -902px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }

        #buy:HOVER {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -938px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }

        a:link {
            color: blue;
        }

        a:visited {
            color: blue;
        }

        a {
            text-decoration: none;
        }

        #tbody {
            text-align: center;
        }
    </style>

</head>

<body>
<h1>购物车</h1>

<table border="1" width="100%" cellspacing="0" background="black">
    <thead>
    <tr>
        <td colspan="8" align="right" style="font-size: 15pt; font-weight: 900">
            <a href="${pageContext.request.contextPath}/CartServlet?method=clear&cart=${cart}">清空购物车</a>
        </td>
    </tr>
    <tr>
        <td><input id="all" type="checkbox">全选</td>
        <th>图片</th>
        <th>书名</th>
        <th>作者</th>
        <th>单价</th>
        <th>数量</th>
        <th>小计</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody id="tbody">
    <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}" varStatus="vs">
        <tr>
            <td><input type="checkbox"></td>
            <td>
                <div><img src="<c:url value='/${cartItem.value.book.image}'/>"/></div>
            </td>
            <td>${cartItem.value.book.bname}</td>
            <td>${cartItem.value.book.author}</td>
            <td>${cartItem.value.book.price}</td>
            <td>${cartItem.value.count}</td>
            <td>${cartItem.value.book.price * cartItem.value.count}</td>
            <td>
                <a href="${pageContext.request.contextPath}/CartServlet?method=delete&bid=${cartItem.value.book.bid}">删除</a>
            </td>
        </tr>
        <c:set var="size" value="${vs.count}"/>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="8" align="right" style="font-size: 15pt; font-weight: 900">
            <c:set var="sum" value="0"/>
            <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                <c:set var="sum" value="${sum = cartItem.value.book.price * cartItem.value.count + sum}"/>
            </c:forEach>
            <c:choose>
                <c:when test="${sum >= 59 && sum < 100}">
                    折前：
                    <del>${sum}</del>&nbsp;
                    合计：
                    <span>${sum - 20}</span>
                    <c:set var="sum" value="${sum -20}"/>
                </c:when>
                <c:when test="${sum >= 100 && sum < 300 }">
                    折前：
                    <del>${sum}</del>&nbsp;
                    合计：
                    <span>${sum - 30}</span>
                    <c:set var="sum" value="${sum - 30}"/>
                </c:when>
                <c:when test="${sum >= 300 && sum < 500 }">
                    折前：
                    <del>${sum}</del>&nbsp;
                    合计：
                    <span>${sum - 50}</span>
                    <c:set var="sum" value="${sum - 50}"/>
                </c:when>
                <c:when test="${sum >= 500 && sum < 1000 }">
                    折前：
                    <del>${sum}</del>&nbsp;
                    合计：
                    <span>${sum * 0.75}</span>
                    <c:set var="sum" value="${sum * 0.75}"/>
                </c:when>
                <c:otherwise>
                    合计：
                    <span>${sum}</span>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td colspan="8" align="right" style="font-size: 15pt; font-weight: 900">
            <a id="buy" href="<c:url value='/OrderServlet?method=add&count=${sum}&size=${size}'/>"></a>
        </td>
    </tr>
    </tfoot>
</table>

<script>
    window.onload = function () {

        var topInp = document.getElementById("all");
        var tbody = document.getElementById("tbody");
        var botInpArr = tbody.getElementsByTagName("input");

        //全选事件
        topInp.onclick = function () {
            //被点击的input按钮的checked属性值，应该直接作为下面的所有的input按钮的checked属性值
            for (var i = 0; i < botInpArr.length; i++) {
                botInpArr[i].checked = this.checked;
            }
        };
        // 反选
        for (var i = 0; i < botInpArr.length; i++) {
            botInpArr[i].onclick = function () {
                //定义一个标识是true的变量
                var bool = true;
                //检测每一个input的checked属性值。
                for (var j = 0; j < botInpArr.length; j++) {
                    if (botInpArr[j].checked === false) {
                        bool = false;
                    }
                }
                topInp.checked = bool;
            }
        }
    }
</script>
</body>
</html>
