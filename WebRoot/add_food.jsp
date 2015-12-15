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
	OrderBeanManager manager = OrderBeanManager.getInstance();
	String sId=request.getParameter("id");
	FoodBean foodBean=new FoodBean();
	String flag=null;
	if(sId!=null){
	int id = Integer.parseInt(sId);
		foodBean = manager.queryFood(id);
		flag="修改食品";
	}else{
		flag="添加食品";
		foodBean.setId(-1);
	}
	request.setAttribute("foodBean", foodBean);
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">

<title><%=flag%></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />

</head>

<body>
	<div class="login-aside">
  <div id="o-box-up"></div>
  <div id="o-box-down"  >
  
   
   <form class="registerform"action="/dingcan/LuServerServlet?flag=addFood"  method="post" enctype="multipart/form-data"
   >
   <div class="fm-item">
	   <label for="logonId" class="form-label">食品名称</label>
	   <input type="text" value="" name="foodname" maxlength="100" id="username" class="i-text"  >    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label">食品价格：</label>
	   <input type="text" value="" name="foodprice" maxlength="100" id="password" class="i-text" >    
       <div class="ui-form-explain"></div>
  </div>
  <div class="fm-item">
	   <label for="logonId" class="form-label">折扣：</label>
	   <input type="text" value="" maxlength="100"  name="foodsale" id="password" class="i-text" >    
       <div class="ui-form-explain"></div>
  </div>
  
 
 <div class="fm-item">
	   <label for="logonId" class="form-label">图片：</label>

		<input type="file" name="picture"/>
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item">
 		 <input type="hidden" value="addFood" name="flag"/>
 		  <input type="hidden" value="${requestScope.foodBean.id}" name="id"/>
	   <label for="logonId" class="form-label"></label>
	   <input type="submit" value="" tabindex="4" id="send-btn" class="btn-login"> 
       <div class="ui-form-explain"></div>
  </div>
 
  </form>
  
  </div>

</div>

</body>
</html>
