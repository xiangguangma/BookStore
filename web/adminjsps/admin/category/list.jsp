<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分类列表</title>
	  <link rel="icon" href="../../../images/logo.png">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background-image: url(../../../images/1.png);
	}
	table {font-family: 宋体; font-size: 11pt; border-color: rgb(78,78,78);  width: 60%; text-align: center}
	#th {background: rgb(78,78,78); color: white}
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
  
  <body style="background-image: url(../../../images/1.png);">
    <h2 style="text-align: center;">分类列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    	<tr id="th" bordercolor="rgb(78,78,78)">
    		<th>分类名称</th>
    		<th>操作</th>
    	</tr>

		<c:forEach var="categroy" items="${list}">
			<tr bordercolor="rgb(78,78,78)">
				<td>${categroy.cname}</td>
				<td>
					<a href="<c:url value='/adminjsps/admin/category/mod.jsp?cid=${categroy.cid}&cname=${categroy.cname}'/>">修改</a> |
					<a href="<c:url value='/adminjsps/admin/category/del.jsp?cid=${categroy.cid}&cname=${categroy.cname}'/>">删除</a>
				</td>
			</tr>
		</c:forEach>
   
    </table>
  </body>
</html>
