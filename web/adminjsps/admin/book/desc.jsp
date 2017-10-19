<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
	  <link rel="icon" href="../../../images/logo.png">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
		background-image: url(../../../images/1.png);
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
  </head>
  
  <body>
  <div>
    <img src="<c:url value='/${book.image}'/>" border="0"/>
  </div>
  <form style="margin:20px;" id="form" action="${pageContext.request.contextPath}/AdminBookServlet?bid=${book.bid}&image=${book.image}" method="post">
  	图书名称：<input type="text" name="bname" value="${book.bname}"/><br/>
  	图书单价：<input type="text" name="price" value="${book.price}"/><br/>
  	图书作者：<input type="text" name="author" value="${book.author}"/><br/>
  	图书分类：<select style="width: 150px; height: 20px;" name="cid">
	  <c:forEach var="category" items="${categoris}">
		  <option value="${category.cid}" <c:if test="${book.cid eq category.cid}">selected="selected"</c:if>>${category.cname}</option>
	  </c:forEach>
    	</select><br/>
	  <c:choose>
		  <c:when test="${book.del == false}">
			  <input type="submit" name="method" value="del" onclick="return confirm('是否真要删除该图书？');"/>
			  <input type="submit" name="method" value="mod"/>
		  </c:when>
		  <c:when test="${book.del == true}">
			  <input type="submit" name="method" value="del" onclick="return confirm('确定恢复该图书?');"/>
		  </c:when>
	  </c:choose>
  </form>
  </body>
</html>
