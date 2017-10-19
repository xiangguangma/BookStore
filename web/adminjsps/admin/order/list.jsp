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
    <link rel="icon" href="../../../images/logo.png">
    <style type="text/css">
        * {
            font-size: 11pt;
        }

        div {
            border: solid 2px rgb(78, 78, 78);
            width: 75px;
            height: 75px;
            text-align: center;
        }

        li {
            margin: 10px;
        }
        a{
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

<body style="background-image: url(../../../images/a.jpg);">
<h1>全部订单</h1>

<c:forEach var="user" items="${users}">
    <c:if test="${user.orders != null}">
        <table border="1" width="100%" cellspacing="0" background="black">
            <caption>${user.username}</caption>
            <c:forEach var="order" items="${user.orders}">
                <tr bgcolor="rgb(78,78,78)" bordercolor="rgb(78,78,78)" style="color: white;">
                    <td colspan="6">
                        订单：${order.oid}　成交时间：${order.ordertime}　金额：<font color="red"><b>${order.total}</b></font>
                        <c:choose>
                        <c:when test="${order.state == 1}">
                        <a href="${pageContext.request.contextPath}/AdminOrderServlet?method=consignment&oid=${order.oid}">发货<a/>
                            <span>已付款等待发货</span>
                            </c:when>
                            <c:when test="${order.state == 2}">
                            等待收货
                            </c:when>
                            <c:when test="${order.state == 3}">
                            已确定收货
                            <a href="${pageContext.request.contextPath}/AdminOrderServlet?method=confirm&oid=${order.oid}">结束订单</a>
                            </c:when>
                            </c:choose>
                    </td>
                </tr>
                <c:forEach var="orderItem" items="${order.orderItemList}">
                    <tr bordercolor="rgb(78,78,78)" align="center">
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
    </c:if>

</c:forEach>
</body>
</html>
