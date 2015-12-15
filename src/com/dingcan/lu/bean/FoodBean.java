package com.dingcan.lu.bean;


public class FoodBean 	{
	private String name;
	private double price;
	private int res;
	private double sale;
	private boolean isOrdered;
	
	private int number;
	private String picture;
	private String note;
	private int id;
	private double salePrice;
	
	public double getSalePrice(){
		return salePrice;
	}
	public void setSalePrice(){
		salePrice=Double.parseDouble(FormatUti.formatNumber(price*sale));
	}
	public String getSalePriceText(){
		return FormatUti.formatNumber(price*sale);
	}
	public String getName() {
		return name;
	}
	public FoodBean(){
		
	}
	public FoodBean(String name, double price, double sale) {
		super();
		this.name = name;
		this.price = price;
		this.sale = sale;
	}
	public FoodBean(String name, double price, double sale,String picture,String note) {
		this(name,price,sale);
		this.picture=picture;
		this.note=note;
	}
	
	public FoodBean(String name, double price, double salePrice, int res) {
		super();
		this.name = name;
		this.price = price;
//		this.setSalePrice(salePrice);
		this.setRes(res);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	
	public boolean isOrdered() {
		return isOrdered;
	}
	public void setOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getSale() {
		return sale;
	}
	public void setSale(double sale) {
		this.sale = sale;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
