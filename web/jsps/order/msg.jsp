<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'msg.jsp' starting page</title>
      <link rel="icon" href="../../images/logo.png">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
      <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}/jsps/main.jsp">
      <style>
          body{
              background-image: url(../../images/1.png);
          }
          #spanId{
              color: blue;
          }
      </style>
  </head>
  
  <body onload="run()"style="text-align: center">
  <div style="margin: auto">
      <br><br>
      <h1>${msg}</h1>
      <br>
      ҳ�潫��<span id="spanId">5</span>�����ת����

  </div>
  <script type="text/javascript">
      // ҳ��һ������ɣ��÷����ͻ�ִ��
      // ���룬һ�������ָı�һ��
      var x = 5;
      function run(){
          // ��ȡ������span��ǩ�Ķ���
          var span = document.getElementById("spanId");
          // ��ȡspan��ǩ�м���ı�
          span.innerHTML = x;
          x--;
          // ����run����ִ���أ�һ����ִ��һ��
          window.setTimeout("run()", 1000);
      }

  </script>
  </body>

</html>
