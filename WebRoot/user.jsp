<%@page import="com.dingcan.lu.bean.manager.OrderBeanManager"%>
<%@ page language="java"
	import="java.util.*,java.sql.*,com.dingcan.lu.bean.*,java.util.*,com.dingcan.lu.servlet.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">

<title>user</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="table.css" type="text/css" />

</head>

<body>
	<center>
		<table>
			<tr>

				<th>用户名</th>
				<th>联系方式</th>
				<th>地址</th>

				<th>注册日期</th>



			</tr>
			<c:forEach items="${requestScope.userBeans}" var="userBean">
				<tr>

					<th>${userBean.username}</th>

					<th>${userBean.phone}</th>
					<th>${userBean.address}</th>
					<th>${userBean.date}</th>


				</tr>
			</c:forEach>
		</table>
	</center>

</body>
</html>
