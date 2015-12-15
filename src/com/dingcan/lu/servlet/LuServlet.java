package com.dingcan.lu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dingcan.lu.bean.manager.OrderBeanManager;

public class LuServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderBeanManager manager;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String flag = request.getParameter("flag");
		System.out.println("flag:" + flag);
		if ("login".equals(flag)) {
			manager.login(request, response);
		} else if ("commitOrder".equals(flag)) {
			manager.commitOrder(request, response);
		} else if ("refresh".equals(flag)) {
			request.getRequestDispatcher("/order.jsp?flag=queryOrder").forward(
					request, response);
		} else if ("queryOrder".equals(flag)) {
			// queryOrder(request,response);
			manager.queryOrderFromPhone(request, response);
		} else if ("queryFood".equals(flag)) {
			manager.queryFoodFromPhone(request, response);
		}
	}
	public void init() throws ServletException {
		manager = OrderBeanManager.getInstance();
	}

}
