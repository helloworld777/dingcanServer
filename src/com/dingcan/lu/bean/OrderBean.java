package com.dingcan.lu.bean;

public class OrderBean {
	int id;
	public OrderBean(){
		
	}
	public OrderBean(int id, String foodnam, String dingdandate,
			String username, String note, int foodnum, double foodprice,
			double foodtotalprice) {
		super();
		this.id = id;
		this.foodnam = foodnam;
		this.dingdandate = dingdandate;
		this.username = username;
		this.note = note;
		this.foodnum = foodnum;
		this.foodprice = foodprice;
		this.foodtotalprice = foodtotalprice;
	}
	public OrderBean(int id, String dingdandate,
			 String note, int foodnum,
			double foodtotalprice,String finished) {
		super();
		this.finished=finished;
		this.id = id;
		this.dingdandate = dingdandate;
		this.note = note;
		this.foodnum = foodnum;
		this.foodtotalprice = foodtotalprice;
	}
	String foodnam;
	String dingdandate;
	String username;
	String note;
	int foodnum;
	double foodprice;
	double foodtotalprice;
	String finished;
	FoodBean foodBean;
	public FoodBean getFoodBean() {
		return foodBean;
	}
	public void setFoodBean(FoodBean foodBean) {
		this.foodBean = foodBean;
	}
	public String getFinished() {
		return finished;
	}
	public void setFinished(String finished) {
		this.finished = finished;
	}
	UserBean userBean;
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodnam() {
		return foodnam;
	}
	public void setFoodnam(String foodnam) {
		this.foodnam = foodnam;
	}
	public String getDingdandate() {
		return dingdandate;
	}
	public void setDingdandate(String dingdandate) {
		this.dingdandate = dingdandate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getFoodnum() {
		return foodnum;
	}
	public void setFoodnum(int foodnum) {
		this.foodnum = foodnum;
	}
	public double getFoodprice() {
		return foodprice;
	}
	public void setFoodprice(double foodprice) {
		this.foodprice = foodprice;
	}
	public double getFoodtotalprice() {
		return foodtotalprice;
	}
	public void setFoodtotalprice(double foodtotalprice) {
		this.foodtotalprice = foodtotalprice;
	}
}
