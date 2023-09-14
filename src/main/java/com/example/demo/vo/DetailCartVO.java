package com.example.demo.vo;

import org.springframework.stereotype.Component;



@Component
public class DetailCartVO {


	@Override
	public String toString() {
		return "DetailCartVO [userNo="+userNo+",goodsNo="+goodsNo+",goodsName=" + goodsName + ", goodsCompany=" + goodsCompany + ", mainFname=" + mainFname
				+ ", goodsPrice=" + goodsPrice + ", goodsStock="+goodsStock+",shipPrice=" + shipPrice + ", cartCnt=" + cartCnt + "]";
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	private String goodsName;
	private String goodsCompany;
	private String mainFname;
	private int goodsPrice;
	private int shipPrice;
	private int cartCnt;
	private int goodsStock;
	private int goodsNo;
	private int userNo;
	
	public int getGoodsStock() {
		return goodsStock;
	}
	public void setGoodsStock(int goodsStock) {
		this.goodsStock = goodsStock;
	}
	public String getGoodsCompany() {
		return goodsCompany;
	}
	public void setGoodsCompany(String goodsCompany) {
		this.goodsCompany = goodsCompany;
	}
	public String getMainFname() {
		return mainFname;
	}
	public void setMainFname(String mainFname) {
		this.mainFname = mainFname;
	}
	public int getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public int getShipPrice() {
		return shipPrice;
	}
	public void setShipPrice(int shipPrice) {
		this.shipPrice = shipPrice;
	}
	public int getCartCnt() {
		return cartCnt;
	}
	public void setCartCnt(int cartCnt) {
		this.cartCnt = cartCnt;
	}
}
