package com.example.demo.vo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "opinion")
@ToString
public class OpinionVO {
	
	@Id
	private int opinionNo;
	private int eduNo;
	private int goodsNo;
	private int lecNo;
	private String id;
	private String opinionName;
	private String opinionContent;
	private Date opinionDate;
	private int opinionSecret;
	private String opinionPwd;
	private String opinionStatus;
	private int reviewScore;
	private int answerNo;
	private String type;
	private int depth;
	public int getOpinionNo() {
		return opinionNo;
	}
	public void setOpinionNo(int opinionNo) {
		this.opinionNo = opinionNo;
	}
	public int getEduNo() {
		return eduNo;
	}
	public void setEduNo(int eduNo) {
		this.eduNo = eduNo;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}

	public int getLecNo() {
		return lecNo;
	}
	public void setLecNo(int lecNo) {
		this.lecNo = lecNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpinionName() {
		return opinionName;
	}
	public void setOpinionName(String opinionName) {
		this.opinionName = opinionName;
	}
	public String getOpinionContent() {
		return opinionContent;
	}
	public void setOpinionContent(String opinionContent) {
		this.opinionContent = opinionContent;
	}
	public Date getOpinionDate() {
		return opinionDate;
	}
	public void setOpinionDate(Date opinionDate) {
		this.opinionDate = opinionDate;
	}
	public int getOpinionSecret() {
		return opinionSecret;
	}
	public void setOpinionSecret(int opinionSecret) {
		this.opinionSecret = opinionSecret;
	}
	public String getOpinionPwd() {
		return opinionPwd;
	}
	public void setOpinionPwd(String opinionPwd) {
		this.opinionPwd = opinionPwd;
	}
	public String getOpinionStatus() {
		return opinionStatus;
	}
	public void setOpinionStatus(String opinionStatus) {
		this.opinionStatus = opinionStatus;
	}
	public int getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	public int getAnswerNo() {
		return answerNo;
	}
	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
}



