<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑</title>
    
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

	  <style>
		  span{
			  color: red;
		  }
		  body{
			  text-align: center;

			  background-image: url(../../images/3.jpg);
		  }
	  </style>

  </head>
  
  <body>
  <h1>编辑</h1>
  <hr>
  <br>
<p style="color: red; font-weight: 900">${msg}</p>
<form action="${pageContext.request.contextPath}/UserServlet" method="post">
	<input type="hidden" name="method" value="edit"/>
	用户名：<input type="text" name="username" value="${formUser.username}"/>
	<br/><br>
	密　码：<input type="password" name="password" value="${formUser.password}"/>
	<br/><br>
	<input type="submit" value="修改"/>


</form>
  </body>
</html>
