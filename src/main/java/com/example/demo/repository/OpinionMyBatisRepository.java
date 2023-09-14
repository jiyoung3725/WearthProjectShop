package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.db.OpinionDBManager;
import com.example.demo.db.SchoolDBManager;
import com.example.demo.db.ShopDBManager;
import com.example.demo.vo.OpinionVO;

@Repository
public class OpinionMyBatisRepository {
	
	//강의, 교육 전체 목록 반환
	public List<OpinionVO> findAllEducationOpinion(int eduNO){
		return OpinionDBManager.findAllEducationOpinion(eduNO);
	}
	public List<OpinionVO> findAllLectureOpinion(int lecNO){
		return OpinionDBManager.findAllLectureOpinion(lecNO);
	}
	
	//강의, 교육 해당 게시글의 문의 글의 상세 조회
	public OpinionVO findByNoEducationOpinion(HashMap<String, Object> map) {
		return OpinionDBManager.findByNoEducationOpinion(map);
	}
	public OpinionVO findByNoLectureOpinion(HashMap<String, Object> map) {
		return OpinionDBManager.findByNoLectureOpinion(map);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	//=======================쇼핑========================================
	
	//쇼핑 문의 조회
	public static List<OpinionVO> selectShopOpinion(int goodsNo){
		return ShopDBManager.selectShopOpinion(goodsNo);
	}
	
	//쇼핑 리뷰 조회
	public static List<OpinionVO> selectShopReview(int goodsNo){
		return ShopDBManager.selectShopReview(goodsNo);
	}
	
	//쇼핑 문의글 작성
	public static int insertShopQNA(HashMap<String, Object>map) {
		return ShopDBManager.insertShopQNA(map);
	}
	
	//쇼핑 문의글 삭제
	public static int deleteShopQNA(int opinionNo) {
		return ShopDBManager.deleteShopQNA(opinionNo);
	}
	
	//쇼핑 문의글 수정
	public static int updateShopQNA(HashMap<String, Object>map) {
		return ShopDBManager.updateShopQNA(map);
	}
}
