<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单详细</title>

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
    <script src="jquery-3.2.1.js"></script>
    <style type="text/css">
        body {
            background-image: url(../../images/1.png);
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

        #pay {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -412px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }

        #pay:HOVER {
            background: url(<c:url value='/images/all.png'/>) no-repeat;
            display: inline-block;

            background-position: 0 -448px;
            margin-left: 30px;
            height: 36px;
            width: 146px;
        }
    </style>
</head>

<body>
<h1>当前订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">
    <tr bgcolor="gray" bordercolor="gray">
        <td colspan="6">
            订单编号：${order.oid}　成交时间：${order.ordertime}　金额：<font color="red"><b>${order.total}</b></font>
        </td>
    </tr>

    <c:forEach var="orderItem" items="${order.orderItemList}">
        <tr bordercolor="white" align="center">
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

</table>
<br/>
<form method="post" action="<c:url value='/OrderServlet?oid=${order.oid}'/>" id="form" target="_parent">
    <input type="hidden" name="method" value="submit">
    收货地址：
            <select id="DropProvince" name="paddress" style="width:100px;">
                <option>请选择</option>
            </select>
            <select id="City" name="caddress" style="width:100px;">
                <option>请选择相应市</option>
            </select>
            <input type="text" name="daddress" size="50" value="" placeholder="具体地址" autocomplete="off"/>
<span>${msg}</span>
    <br/>

    选择银行：<br/>
    <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
    <img src="../../bank_img/icbc.bmp" align="middle"/>
    <input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
    <img src="../../bank_img/bc.bmp" align="middle"/><br/><br/>
    <input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
    <img src="../../bank_img/abc.bmp" align="middle"/>
    <input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
    <img src="../../bank_img/ccb.bmp" align="middle"/><br/><br/>
    <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
    <img src="../../bank_img/bcc.bmp" align="middle"/><br/>
</form>
<a id="pay" href="javascript:document.getElementById('form').submit();"></a>
<script type="text/javascript">
    $(document).ready(function () {
        //省
        $.ajax({
            url: "china.xml",
            success: function (xml) {
                $(xml).find("province").each(function () {
                    var t = $(this).attr("name");//this->
                    $("#DropProvince").append("<option>" + t + "</option>");
                });
            }
        });
        //市
        $("#DropProvince").change(function () {
            $("#City>option").remove();
            var pname = $("#DropProvince").val();
            $.ajax({
                url: "china.xml",
                success: function (xml) {
                    ///查找<province>下的所有第一级子元素(即城市)
                    $(xml).find("province[name='" + pname + "']>city").each(function () {
                        var city = $(this).html();//this->
                        $("#City").append("<option>" + city + "</option>");
                    });
                }
            });
        });
    })
</script>
</body>
</html>

