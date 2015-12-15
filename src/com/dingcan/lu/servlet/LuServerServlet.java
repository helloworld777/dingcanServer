package com.dingcan.lu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dingcan.lu.bean.FoodBean;
import com.dingcan.lu.bean.OrderBean;
import com.dingcan.lu.bean.UserBean;
import com.dingcan.lu.bean.manager.FileManager;
import com.dingcan.lu.bean.manager.OrderBeanManager;

public class LuServerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static  String webPath;
	OrderBeanManager orderBeanManager;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
//		PrintWriter out = response.getWriter(); // 再拿到输出对象
		response.setContentType("text/html;charset=UTF-8"); 
		
		String flag = request.getParameter("flag");
		
		if(flag==null){
//			flag=request.getAttribute("flag").toString();
		}
		System.out.println("flag:" + flag);
		if (("queryOrder").equals(flag)) {
			List<OrderBean> beans = orderBeanManager.queryOrder(request,
					response);
			System.out.println("order.size:"+beans.size());
			request.setAttribute("orderBeans", beans);
			request.getRequestDispatcher("/order.jsp").forward(request,
					response);
			return;
		} else if ("queryFood".equals(flag)) {
			List<FoodBean> foodBeans = orderBeanManager.queryFood(request,
					response);
			request.setAttribute("foodBeans", foodBeans);
			request.getRequestDispatcher("/food_new.jsp")
					.forward(request, response);
			return;

		} else if ("updateFood".equals(flag)) {
			System.out.println(request.getParameter("picture"));
			FileManager f=new FileManager();
			f.upload(request, response);
			;
		} else if ("updateOrder".equals(flag)) {
			int id = Integer.parseInt(request.getParameter("id"));
			orderBeanManager.updateOrder(id);
			List<OrderBean> beans = orderBeanManager.queryOrder(request,
					response);
			request.setAttribute("orderBeans", beans);
			request.getRequestDispatcher("/order.jsp").forward(request,
					response);
			return;
		}else if("queryUser".equals(flag)){
			List<UserBean> userBeans=orderBeanManager.queryUser();
			request.setAttribute("userBeans", userBeans);
			request.getRequestDispatcher("/user.jsp").forward(request,
					response);
			
			
		}else{
			FileManager f=new FileManager();
			f.upload(request, response);
			
			List<FoodBean> foodBeans = orderBeanManager.queryFood(request,
					response);
			request.setAttribute("foodBeans", foodBeans);
			request.getRequestDispatcher("/food_new.jsp")
					.forward(request, response);
			return;
		}

	}

	public void init() throws ServletException {

		orderBeanManager = OrderBeanManager.getInstance();
		webPath=getServletContext().getRealPath("/");
	}
	

}
