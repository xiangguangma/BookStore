<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>添加图书</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="icon" href="../../../images/logo.png">
    <script src="../../../js/jquery-3.2.1.js" type="javascript"></script>
    <style type="text/css">
        body {
            background-image: url(../../../images/1.png);
        }
        div{
            margin: auto;
        }
        #in{
            margin-left: 30px;
            width: 200px;
            height: 30px;
            background-color: red;
            color: white;
        }
    </style>
</head>

<body>
<div>
    <h1 style="margin-left: 30px;">添加图书</h1>
    <p style="font-weight: 900; color: red">${msg}</p>
    <form action="<c:url value='AdminAddBookServlet'/> " method="post" enctype="multipart/form-data">
        图书名称：<input id="i1" style="width: 150px; height: 20px;" type="text" name="bname"/><br/><br>
        图书图片：<input id="i2" style="width: 223px; height: 20px;" type="file" name="image" style="width: 150px; height: 150px"/><br/><br>
        图书单价：<input id="i3" style="width: 150px; height: 20px;" type="text" name="price"/><br/><br>
        图书作者：<input id="i4" style="width: 150px; height: 20px;" type="text" name="author"/><br/><br>
        图书数量：<input id="i5" style="width: 150px; height: 20px;" type="text" name="count"/><br/><br>
        图书分类：<select  id="" style="width: 150px; height: 20px;" name="cid"><br><br>
        <c:forEach var="category" items="${categoris}">
            <option value="${category.cid}">${category.cname}</option>
        </c:forEach>
    </select>
        <br/><br>
        <input id="in" type="submit" value="添加图书"/>
    </form>
</div>
<script type="javascript">
    $(document).ready(function () {
        $("#i1").blur(function () {
            if($("#i1").length < 0){
                alert("图书名称不能为空!");
            }
        });
        $("#i2").blur(function () {
            if($("#i2").length < 0){
                alert("图书图片不能为空!")
            }
        });
        $("#i3").blur(function () {
            if($("#i3").length < 0){
                alert("图书单价不能为空!")
            }
        });
        $("#i4").blur(function () {
            if($("#i4").length < 0){
                alert("图书作者不能为空!")
            }
        });
        $("#i5").blur(function () {
            if($("#i5").length < 0){
                alert("图书分类不能为空!")
            }
        });
    })
</script>
</body>
</html>
