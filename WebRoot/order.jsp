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

<title>order</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="table.css" type="text/css" />
	<meta http-equiv="refresh" content="10">
</head>

<body>
	<center>
		<table>
			<tr>
				<th>订单号</th>
				<th>食品</th>
				<th>数量</th>
				<th>单价</th>
				<th>总价</th>
				<th>订单时间</th>
				<th>用户名</th>
				<th>地址</th>
				<th>联系电话</th>
				<th>配送情况</th>
				<th>操作</th>
				<th>备注</th>
			</tr>
			<c:forEach items="${requestScope.orderBeans}" var="orderBean">
				<tr>
					<th>${orderBean.id}</th>
					<th>${orderBean.foodBean.name}</th>
					<th>${orderBean.foodnum}</th>
					<th>${orderBean.foodBean.price}</th>
					<th>${orderBean.foodtotalprice}</th>
					<th>${orderBean.dingdandate}</th>
					<th>${orderBean.userBean.username}</th>
					<th>${orderBean.userBean.address}</th>
					<th>${orderBean.userBean.phone}</th>
					
					
					<th>${orderBean.finished}</th>
					<th><c:if test="${orderBean.finished=='未配送'}">
						<a href="/dingcan/LuServerServlet?flag=updateOrder&id=${orderBean.id}">配送</a>
					</c:if>
					</th>
					<th>${orderBean.note}</th>
				</tr>
			</c:forEach>
		</table>
	</center>
	
</body>
</html>
