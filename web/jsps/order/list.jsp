<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单列表</title>

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
        body{
            background-image: url(../../images/1.png);
            background-size: cover;
            /*background-color: aquamarine;*/
        }
        * {
            font-size: 11pt;
        }

        div {
            border: solid 2px gray;
            width: 75px;
            height: 75px;
            text-align: center;
        }

        li {
            margin: 10px;
        }

        a:link{

            color: blue;
        }
        a:visited {
            color: blue;

        }
        a{
            text-decoration: none;
        }
        tr,td{
            border-color: white;;
        }
    </style>
</head>

<body>
<h1>我的订单</h1>

<table border="1" width="100%" cellspacing="0">

    <c:forEach var="order" items="${orders}">
        <tr border-color="gray" >
            <td colspan="6" bgcolor="#5f9ea0">
                订单编号：${order.oid}　成交时间：${order.ordertime}　金额：<font color="red"><b>${order.total}</b></font>　

                <c:if test="${order.state eq 0}">
                    <a href="<c:url value='/OrderServlet?method=load&oid=${order.oid}'/>">付款</a>
                </c:if>
                <c:choose>
                    <c:when test="${order.state eq 0}">
                        未付款
                    </c:when>
                    <c:when test="${order.state eq 1}">
                        等待发货
                    </c:when>
                    <c:when test="${order.state eq 2}">
                        已发货
                    </c:when>
                    <c:when test="${order.state eq 3}">
                        已收货
                    </c:when>
                    <c:when test="${order.state eq 4}">
                        交易完成
                    </c:when>
                </c:choose>
                <c:if test="${order.state eq 2}">
                    <a href="${pageContext.request.contextPath}/OrderServlet?method=confirm&oid=${order.oid}">确认收货</a>
                </c:if>
                <c:if test="${order.state != 1}">
                    <a href="${pageContext.request.contextPath}/OrderServlet?method=delete&oid=${order.oid}">删除订单</a>
                </c:if>

            </td>
        </tr>
        <c:forEach var="orderItem" items="${order.orderItemList}">
            <tr align="center">
                <td width="15%">
                    <div><img src="<c:url value='${orderItem.book.image}'/>" height="75"/></div>
                </td>
                <td>书名：${orderItem.book.bname}</td>
                <td>单价：${orderItem.book.price}</td>
                <td>作者：${orderItem.book.author}</td>
                <td>数量：${orderItem.count}</td>
                <td>小计：${orderItem.subtotal}</td>
            </tr>
        </c:forEach>

    </c:forEach>


</table>
</body>
</html>
