<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
      <link rel="icon" href="../../images/logo.png">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
      <style>
          body {
              background-image: url(../../images/6.jpg);
              color: white;
          }
          a{
              text-decoration: none;
              color: white;
          }
          a:visited{
              color: white;
          }
      </style>

  </head>
  
  <body>
<h1 style="text-align: center; margin-top: 30px;"> 神缨 网络图书商城后台管理</h1>
<p style="font-size: 11pt;">管理员：<a href="../list.jsp" target="body.jsp" >${admin.aname}</a></p>
  </body>
</html>
