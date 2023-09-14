package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.ShopDBManager;
import com.example.demo.vo.CartVO;
import com.example.demo.vo.DetailCartVO;
import com.example.demo.vo.GoodsVO;

@Repository
public class CartMybatisRepository {
	//장바구니 추가
	public static int insertCart(HashMap<String, Object> map) {
		return ShopDBManager.insertCart(map);
	}
	
	//유저별 장바구니 조회
	public static List<DetailCartVO> findCartByUserNo(int userNo) {
		return ShopDBManager.findCartByUserNo(userNo);
	}
	
	//선택한 상품 삭제
	public static int deleteCartByGoodsNo(HashMap<String, Object> map) {
		return ShopDBManager.deleteCartByGoodsNo(map);
	}
	
	//품절 상품 삭제
	public static int deleteCartByGoodsStock(int userNo) {
		return ShopDBManager.deleteCartByGoodsStock(userNo);
	}
	
	//장바구니 수량 변경
	public static int updateCartCnt(HashMap<String, Object>map) {
		return ShopDBManager.updateCartCnt(map);
	}
	
	//장바구니에 담겨있는 모든 상품수량 조회
	public static CartVO cartTot(int userNo) {
		return ShopDBManager.cartTot(userNo);
	}
	
	
}
