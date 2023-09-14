package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.ShopDBManager;
import com.example.demo.vo.GoodsCategoryVO;
import com.example.demo.vo.GoodsVO;
import com.example.demo.vo.OpinionVO;
@Repository
public class GoodsMybatisRepository {
	
	//전체 상품목록 조회
	public static List<GoodsVO> findGoods(HashMap<Object, Object> map) {
		return ShopDBManager.findGoods(map);
	}
	
	//카테고리별 상품목록 조회
	public static List<GoodsVO> findByCategoryNo(HashMap<Object, Object> map){
		return ShopDBManager.findByCategoryNo(map);
	}
	
	//상품 상세목록 조회
	public static GoodsVO detailGoods(int goodsNo) {
		return ShopDBManager.detailGoods(goodsNo);
	}

	public static int getTotalRecord(Integer categoryNo) {
		return ShopDBManager.getTotalRecord(categoryNo);
	}
	
	//카테고리 조회
	public static GoodsCategoryVO findCategory(Integer goodsNo) {
		return ShopDBManager.findCategory(goodsNo);
	}
	
	//상품 리뷰 수 조회
	public static int goodsReviewCount(Integer goodsNo) {
		return ShopDBManager.goodsReviewCount(goodsNo);
	}
	
	//상품 문의 수 조회
	public static int goodsOpinionCount(Integer goodsNo) {
		return ShopDBManager.goodsOpinionCount(goodsNo);
	}
	
	//문의글 수정을 위한 게시글 조회
	public static OpinionVO selectShopByOpinionNo(int opinionNo) {
		return ShopDBManager.selectShopByOpinionNo(opinionNo);
	}
	
	//상품 등록
	public static int insertGoods() {
		return ShopDBManager.insertGoods();
	}
}
