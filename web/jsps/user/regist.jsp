<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
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
		  body{
			  text-align: center;

			  background-image: url(../../images/3.jpg);
		  }
		  span {
			  color: red;
		  }
		  #div1{
			  margin: auto;
			  width: 400px;
			  height: 250px;
		  }
		  #b{
			  text-align: center;
			  width: 200px;
			  height: 40px;
			  background-color: red;
			  color: white;

		  }
		  input {
			  width: 270px;
			  height: 40px;
			  background: #2d2d2d; /* browsers that don't support rgba */
			  *background-color:transparent;
			  background: rgba(45,45,45,.15);
			  -moz-border-radius: 6px;
			  -webkit-border-radius: 6px;
			  border-radius: 6px;
			  border: 1px solid #3d3d3d; /* browsers that don't support rgba */
			  border: 1px solid rgba(255,255,255,.15);
			  -moz-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
			  -webkit-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
			  box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
			  color: #fff;
			  text-shadow: 0 1px 2px rgba(0,0,0,.1);
			  -o-transition: all .2s;
			  -moz-transition: all .2s;
			  -webkit-transition: all .2s;
			  -ms-transition: all .2s;
		  }

		  input:-moz-placeholder { color: #fff; }
		  input:-ms-input-placeholder { color: #fff; }
		  input::-webkit-input-placeholder { color: #fff; }

		  input:focus {
			  outline: none;
			  -moz-box-shadow:
					  0 2px 3px 0 rgba(0,0,0,.1) inset,
					  0 2px 7px 0 rgba(0,0,0,.2);
			  -webkit-box-shadow:
					  0 2px 3px 0 rgba(0,0,0,.1) inset,
					  0 2px 7px 0 rgba(0,0,0,.2);
			  box-shadow:
					  0 2px 3px 0 rgba(0,0,0,.1) inset,
					  0 2px 7px 0 rgba(0,0,0,.2);
		  }
		  #div{
			  margin-left: 120px;
		  }

	  </style>

  </head>
  
  <body>
  <div id="div1">
	  <br><br><br>
	  <div id="div">
		  <h1>注册</h1>
		  <p style="color: red; font-weight: 900">${msg}</p>
		  <form action="${pageContext.request.contextPath}/UserServlet" method="post" id="form" target="_parent">
			  <input type="hidden" name="method" value="regist">
			  <input type="text" name="username" placeholder="Username" autocomplete="off"/><span>${map.username}</span>
			  <br/><br>
			  <input type="password" name="password" placeholder="Password" oncontextmenu="return false" onpaste="return false"><span>${map.password}</span>
			  <br/><br>
			  <input type="text" name="email" placeholder="Email" autocomplete="off"><span>${map.email}</span>
			  <br/><br>
			  <input id="b" type="submit" value="rigist"/>
		  </form>
	  </div>
  </div>
  <script>
	  window.onload(function () {
		  var input1 = document.getElementById("name");
		  var input2 = document.getElementById("email");
		  var span1 = document.getElementById("s1");
		  var span2 = document.getElementById("s2");

		  document.getElementById("ip1").onblur = function () {
			  var xmlHttp = createXMLHttpRequest();
//        1. method = post
			  xmlHttp.open("post", "${pageContext.request.contextPath}/UserNameServlet", true);
//        2. 请求头
			  xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        3. 发送请求体
			  xmlHttp.send("username=" + input1.value);
			  xmlHttp.onreadystatechange = function () {
				  // 当 XMLHTTP 的状态改变时会走
				  // 双重判断,xmlHttp 的状态是4(服务器响应结束), 并且服务器的响应状态码是200
				  if (xmlHttp.status == 200 && xmlHttp.readyState == 4) {
					  // 接收服务器发送的数据(responseText, responseXml)
					  var text = xmlHttp.responseText;
					  var user = eval("(" + text + ")");
					  span1.innerText = user.username === input1.value ? "该用户名已被注册" : "可以注册";
				  }
			  }
		  };
		  document.getElementById("ip2").onblur = function () {
			  var xmlHttp = createXMLHttpRequest();
//        1. method = post
			  xmlHttp.open("post", "${pageContext.request.contextPath}/EmailServlet", true);
//        2. 请求头
			  xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//        3. 发送请求体
			  xmlHttp.send("email=" + input2.value);
			  xmlHttp.onreadystatechange = function () {
				  // 当 XMLHTTP 的状态改变时会走
				  // 双重判断,xmlHttp 的状态是4(服务器响应结束), 并且服务器的响应状态码是200
				  if (xmlHttp.status == 200 && xmlHttp.readyState == 4) {
					  // 接收服务器发送的数据(responseText, responseXml)
					  var text = xmlHttp.responseText;
					  var user = eval("(" + text + ")");
					  span2.innerText = user.email === input2.value ? "该邮箱已被注册" : "可以注册";
				  }
			  }
		  };

		  function createXMLHttpRequest() {
			  try {
				  return new XMLHttpRequest();//大多数浏览器
			  } catch (e) {
				  try {
					  return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
				  } catch (e) {
					  try {
						  return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本
					  } catch (e) {
						  alert("哥们儿，您用的是什么浏览器啊？");
						  throw e;
					  }
				  }
			  }
		  }
	  })


  </script>
  </body>
</html>
