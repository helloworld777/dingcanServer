package com.dingcan.lu.bean.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dingcan.lu.bean.FoodBean;
import com.dingcan.lu.bean.OrderBean;
import com.dingcan.lu.bean.UserBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class OrderBeanManager {
	String driver = "com.mysql.jdbc.Driver";

	String url = "jdbc:mysql://127.0.0.1:3306/lu";

	String user = "root";

	String password = "root";

	java.sql.Connection conn;
	Statement statement;
	int maxOrderId;
	private static OrderBeanManager manager;

	public static OrderBeanManager getInstance() {

		if (manager == null) {
			manager = new OrderBeanManager();
		}
		return manager;
	}

	public OrderBeanManager() {
		init();
	}

	public List<UserBean> queryUser() {

		String sql = "select * from info";
		List<UserBean> userbeans = new ArrayList<UserBean>();
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				UserBean user = getUserFromResultSet(rs);
				userbeans.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userbeans;

	}

	public void login(HttpServletRequest request, HttpServletResponse response) {

		// manager.
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// usernamedevice.getBytes("ISO-8859-1"),"UTF-8"
		System.out.println(username + "," + password);
		// username=new String(username.getBytes("ISO-8859-1"),"GBK");
		System.out.println(username);
		String sql = "select * from info where username=" + "'" + username
				+ "' and password='" + password + "'";

		System.out.println(sql);
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql);
			PrintWriter printWriter = response.getWriter();
			JsonObject result = new JsonObject();
			if (rs.next()) {
				result.addProperty("message", "success");
				int id = rs.getInt("Id");
				String date = rs.getString("date");
				String address = rs.getString("address");
				String phonenumber = rs.getString("phonenumber");
				JsonObject json = new JsonObject();
				json.addProperty("id", id);
				json.addProperty("username", username);
				json.addProperty("password", password);
				json.addProperty("phone", phonenumber);
				json.addProperty("address", address);
				json.addProperty("date", date);
				result.add("userBean", json);

			} else {
				result.addProperty("message", "faild");
			}
			printWriter.print(result.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<FoodBean> queryFood(HttpServletRequest request,
			HttpServletResponse response) {

		String indexStr = request.getParameter("index");
		String sql = "select * from food where 1=1 ";
		if (indexStr != null) {
			int index = Integer.parseInt(indexStr);
			int count = Integer.parseInt(request.getParameter("count"));
			sql += "and id>=" + index + " and id<" + (index + count);
		}
		sql += " order by id asc";
		System.out.println("sql:" + sql);
		List<FoodBean> foodbeans = new ArrayList<FoodBean>();
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				FoodBean food = getFoodFromResultSet(rs);
				foodbeans.add(food);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foodbeans;
	}

	private String pictureUrl(String picture) {

		if (picture.contains(".jpg")) {
			return "http://10.10.3.81:8080/dingcan/images/" + picture;
		}
		return "http://10.10.3.81:8080/dingcan/images/" + picture + ".jpg";
	}

	public void queryOrderFromPhone(HttpServletRequest request,
			HttpServletResponse response) {
		List<OrderBean> beans = queryOrder(request, response);
		Gson g = new Gson();
		String json = g.toJson(beans);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void queryFoodFromPhone(HttpServletRequest request,
			HttpServletResponse response) {
		List<FoodBean> foodbeans = queryFood(request, response);
		Gson g = new Gson();
		try {
			response.getWriter().write(g.toJson(foodbeans));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void commitOrder(HttpServletRequest request,
			HttpServletResponse response) {

		String foodname = isoToUtf8(request.getParameter("foodname"));
		int foodnum = Integer.parseInt((request.getParameter("foodnum")));
		double foodprice = Double
				.parseDouble(request.getParameter("foodprice"));
		double foodtotalprice = Double.parseDouble(request
				.getParameter("foodtotalprice"));
		String dingdandate = isoToUtf8(request.getParameter("dingdandate"));
		String finished = isoToUtf8(request.getParameter("finished"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		String note = isoToUtf8(request.getParameter("note"));
		// int _id = Integer.parseInt(request.getParameter("id"));

		try {
			statement = conn.createStatement();

			String sql = "insert into dingdan(_id,foodname,foodnum,foodprice,foodtotalprice,dingdandate,userid,note,finished)value("
					+ (++maxOrderId)
					+ ",'"
					+ foodname
					+ "'"
					+ ","
					+ foodnum
					+ ","
					+ foodprice
					+ ","
					+ foodtotalprice
					+ ",'"
					+ dingdandate
					+ "',"
					+ userid
					+ ",'"
					+ note
					+ "','"
					+ finished + "')";
			System.out.println(sql);
			System.out.println(statement.executeUpdate(sql));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<OrderBean> queryOrder(HttpServletRequest request,
			final HttpServletResponse response) {

		String uidString = request.getParameter("uid");
		String sql = "select * from dingdan d, food f,info i where d.fid=f.id and d.userid=i.Id";
		if (uidString != null) {
			int uid = Integer.parseInt(request.getParameter("uid"));
			sql += "and d.userid=" + uid;
		}
		// DESC
		sql += " order by dingdandate DESC";
		System.out.println("sql:" + sql);
		ArrayList<OrderBean> beans = new ArrayList<OrderBean>();
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				OrderBean order = getOrderFromResultSet(rs);
				FoodBean food = getFoodFromResultSet(rs);
				UserBean user = getUserFromResultSet(rs);
				order.setFoodBean(food);
				order.setUserBean(user);
				beans.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return beans;

	}

	private OrderBean getOrderFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("_id");
		String date = rs.getString("dingdandate");
		String note = rs.getString("note");
		String finished = rs.getString("finished");
		int foodnum = rs.getInt("foodnum");
		double foodtotalprice = rs.getDouble("foodtotalprice");
		OrderBean order = new OrderBean(id, date, note, foodnum,
				foodtotalprice, finished);
		return order;
	}

	private UserBean getUserFromResultSet(ResultSet rs) throws SQLException {

		int id = rs.getInt("Id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String date = rs.getString("date");
		String address = rs.getString("address");
		int number = rs.getInt("phonenumber");
		UserBean user = new UserBean(username, password, number + "", address,
				date, id);
		return user;
	}

	public void addFoodBean(FoodBean food) {
		String sql = "insert into food (foodname,foodprice,sale,picture)value('"
				+ food.getName()
				+ "',"
				+ food.getPrice()
				+ ","
				+ food.getSale() + ",'" + food.getPicture() + "')";
		System.out.println("sql:" + sql);
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private FoodBean getFoodFromResultSet(ResultSet rs) throws SQLException {
		// FoodBean food=new FoodBean();
		double foodprice = rs.getDouble("foodprice");
		double sale = rs.getDouble("sale");
		String picture = pictureUrl(rs.getString("picture"));
		String foodname = rs.getString("foodname");
		int id = rs.getInt("id");
		FoodBean foodBean = new FoodBean();
		foodBean.setId(id);
		foodBean.setName(foodname);
		foodBean.setPicture(picture);
		// System.out.println(picture);
		foodBean.setPrice(foodprice);
		foodBean.setSale(sale);
		foodBean.setSalePrice();
		foodBean.setPicture(picture);
		return foodBean;
	}

	private String isoToUtf8(String string) {
		try {
			return new String(string.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FoodBean queryFood(int id) {
		FoodBean food = null;
		String sql = "select * from food where id=" + id;
		ResultSet rs;
		try {
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				food = getFoodFromResultSet(rs);
				System.out.println("queryFood:" + food.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return food;

	}

	public void init() {
		// Put your code here
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();
			String sql = "select max(_id) id from dingdan";
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				maxOrderId = rs.getInt("id");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateOrder(int id) {
		String sql = "update dingdan set finished='已派送' where _id=" + id;
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateFoodBean(FoodBean foodBean) {
		String sql = "update food set foodname='" + foodBean.getName()
				+ "',foodprice=" + foodBean.getPrice() + ",sale="
				+ foodBean.getSale() + "" + " where id=" + foodBean.getId();
		;
		if (foodBean.getPicture() != null && !foodBean.getPicture().equals("")) {
			sql = "update food set foodname='" + foodBean.getName()
					+ "',foodprice=" + foodBean.getPrice() + ",sale="
					+ foodBean.getSale() + ",picture='" + foodBean.getPicture()
					+ "' where id=" + foodBean.getId();

		}

		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
