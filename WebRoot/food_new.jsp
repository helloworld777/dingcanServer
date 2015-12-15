<%@page import="java.io.PrintWriter"%>
<%@page import="com.dingcan.lu.bean.manager.OrderBeanManager"%>
<%@ page language="java"
	import="java.util.*,java.sql.*,com.dingcan.lu.bean.*,java.util.*,com.dingcan.lu.servlet.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>


<title>食品</title>
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


	


		<%
			PrintWriter pw = response.getWriter();
			pw.print(" <center> <a href='/dingcan/add_food.jsp?'><h1 style='font-family:verdana;font-size:150%; margin:1cm 4cm 0cm 4cm;'>商品添加</h1></a>");
			List<FoodBean> foodBeans = (List<FoodBean>) request
					.getAttribute("foodBeans");

			pw.print("<table>");
			int oldRow = -1;
			boolean isSameRow = true;
			int row = 0;
			int line = 4;
			int yu = 0;
			for (int i = 0; i < foodBeans.size(); i++) {

				FoodBean food = foodBeans.get(i);

				row = i / line;
				yu = i % line;
				if (oldRow != row) {

					isSameRow = false;

				} else {
					isSameRow = true;
				}
				if (!isSameRow) {
					pw.print("<tr>");
				}

				pw.print("<th>");
				pw.print("<a href='/dingcan/update_food.jsp?id=" + food.getId()
						+ "'>");
				pw.print("<img src="
						+ food.getPicture()
						+ " height=200pix width=200pix style='margin:10pix;padding:10pix;border-width:10pix'/>");
				pw.print("</a>");
				pw.print("</br>");
				pw.print(food.getName() + " ");

				pw.print("$" + food.getSalePrice());
				if (food.getSale() < 1) {
					pw.print(" " + food.getSale() * 10 + "折");
				}

				pw.print("</th>");

				if (yu == (line - 1)) {
					pw.print("</tr>");

				}
				oldRow = row;
			}
			pw.print("</table>");
			pw.print("</center>");
		%>








	</center>

</body>
</html>
