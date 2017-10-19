<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <link rel="icon" href="../images/logo.png">
	<style type="text/css">
		body{
			font-size:10pt;
			text-align: center;
			background: rgb(218,218,217);
		}
		div {
			background: #87CEFA;
			margin: 3px; 
			padding: 3px;
		}
		a {
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
  
  <body style="">
<div>
	<a href="<c:url value='/BookServlet?method=findAll'/>">全部分类</a>
</div>
<c:forEach var="category" items="${list}">
	<div>
		<a href="${pageContext.request.contextPath}/BookServlet?method=findByCategory&cid=${category.cid}">${category.cname}类</a>
	</div>
</c:forEach>
  <div>
	  <a href="${pageContext.request.contextPath}/OrderServlet?method=findCount">销量排行</a>
  </div>
<c:forEach var="count" items="${counts}">
	<div>
		<a href="${pageContext.request.contextPath}/BookServlet?method=findByCategory&cid=${category.cid}">${category.cname}类</a>
	</div>
</c:forEach>
  </body>
</html>
