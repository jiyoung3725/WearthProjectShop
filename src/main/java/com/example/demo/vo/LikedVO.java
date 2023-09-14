package com.example.demo.vo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "liked")
public class LikedVO {

	@Id
	private int likedNo;
	private int userNo;
	private int boardNo;
	private int lecNo;
	private int goodsNo;
	private int eduNo;
	private Date likedDate;
	public int getLikedNo() {
		return likedNo;
	}
	public void setLikedNo(int likedNo) {
		this.likedNo = likedNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getLecNo() {
		return lecNo;
	}
	public void setLecNo(int lecNo) {
		this.lecNo = lecNo;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public int getEduNo() {
		return eduNo;
	}
	public void setEduNo(int eduNo) {
		this.eduNo = eduNo;
	}
	public Date getLikedDate() {
		return likedDate;
	}
	public void setLikedDate(Date likedDate) {
		this.likedDate = likedDate;
	}
	
}
