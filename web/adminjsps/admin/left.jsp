<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <link rel="icon" href="../../images/logo.png">
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
var bar1 = new Q6MenuBar("bar1", "神缨网络图书商城");
function load() {
	bar1.colorStyle = 2;
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";
	bar1.config.radioButton=false;
	bar1.add("分类管理", "查看分类", "<c:url value='/AdminCategoryServlet?method=findAll'/>", "body");
	bar1.add("分类管理", "添加分类", "<c:url value='/adminjsps/admin/category/add.jsp'/>", "body");

	bar1.add("图书管理", "查看图书", "<c:url value='/AdminBookServlet?method=findAll'/>", "body");
	bar1.add("图书管理", "添加图书", "<c:url value='/AdminBookServlet?method=addPre'/>", "body");

	bar1.add("订单管理", "所有订单", "<c:url value='/AdminOrderServlet?method=findAll'/>", "body");
	bar1.add("订单管理", "未付款订单", "<c:url value='/AdminOrderServlet?method=noPay'/>", "body");
	bar1.add("订单管理", "已付款订单", "<c:url value='/AdminOrderServlet?method=payed'/>", "body");
	bar1.add("订单管理", "未收货订单", "<c:url value='/AdminOrderServlet?method=noTake'/>", "body");
	bar1.add("订单管理", "已完成订单", "<c:url value='/AdminOrderServlet?method=finish'/>", "body");

	bar1.add("回收站", "已删图书", "<c:url value='/AdminBookServlet?method=findDel'/>", "body");
	var d = document.getElementById("menu");
	d.innerHTML = bar1.toString();
}
</script>
	  <style>
		  body {
			  background-image: url(../../images/7.jpg);
		  }
	  </style>

</head>

<body onload="load()" style="margin: 0px;">
<div id="menu"></div>

</body>
</html>
