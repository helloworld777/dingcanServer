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

	//Timer timer = new Timer();
	//MyTimer task=new MyTimer(request,response);
	//task.start();
	//timer.schedule(task, 0, 2000);
	//timer.schedule(task, 5000);
	//timer.schedule(task, 1000, 5000);
	//final RequestDispatcher d = request
	//		.getRequestDispatcher("/LuServerServlet?flag=queryOrder");
	//final HttpServletRequest myrequest = request;
	//final HttpServletResponse myrepose = response;
	/**
	OrderBeanManager manager=new OrderBeanManager();
	String flag=request.getParameter("flag");
	boolean refresh=false;
	if(flag.equals("commitOrder")){
		manager.commitOrder(request, response);
		refresh=true;
		//request.getRequestDispatcher("/LuServlet?flag=refresh").forward(request, response);
		
	}
	manager.queryOrder(request, response);
	if(refresh){
	//	response.getWriter().print("<script language=javascript>window.location.reload(true);</script>");
	response.getWriter().print("<script language=javascript>alert("+refresh+");</script>");
		//request.getRequestDispatcher("/order.jsp?flag=").forward(request, response);
		//response.sendRedirect("/dingdan/order.jsp?flag=");
	}
	**/
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">

<title>ordere</title>
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
				
				<th>食品名称</th>
				<th>食品价格</th>
				<th>折扣</th>
				
				<th>售价</th>
				
				
				<th>操作</th>
				<th>备注</th>
			</tr>
			<c:forEach items="${requestScope.foodBeans}" var="foodBean">
				<tr>
				
					<th align="justify"><center>${foodBean.name}</center></th>
					<th>$ ${foodBean.price}</th>
					<th>${foodBean.sale}</th>
					<th>$ ${foodBean.salePrice}</th>
					
					<th><a href="/dingcan/update_food.jsp?id=${foodBean.id}">修改</a>/
					<a href="/dingcan/LuServerServlet?flag=deleteFood&id=${foodBean.id}">删除</a>
					</th>
					<th>${foodBean.note}</th>
				</tr>
			</c:forEach>
		</table>
	</center>
	
</body>
</html>
