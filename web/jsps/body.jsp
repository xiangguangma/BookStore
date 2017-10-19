<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="">
    
    <title>body</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
      <!-- 适配各种设备 -->
      <meta name="viewport" content="width=device-width, initial-scale=1">

      <!-- Bootstrap -->
      <link href="../css/bootstrap.css" rel="stylesheet">
      <title>基本模板</title>
      <!-- 先引jquery -->
      <script src="../js/jquery-3.2.1.js"></script>
      <script src="../js/bootstrap.js"></script>


      <style>
          h1{
              margin-top: 250px;
          }
      </style>

  </head>

  <body style="background-image: url(../images/1.png); text-align: center;">

  <%-- 让文字 从右向左移动 --%>
    <marquee direction=left ><h1>Welcome&nbsp;To&nbsp;GodRibbon&nbsp;BookStore</h1></marquee>
  </body>
</html>
