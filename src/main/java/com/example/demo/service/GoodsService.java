package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CartMybatisRepository;
import com.example.demo.repository.GoodsJpaRepository;
import com.example.demo.repository.GoodsMybatisRepository;
import com.example.demo.repository.LikedMybatisRepository;
import com.example.demo.repository.OpinionMyBatisRepository;
import com.example.demo.vo.CartVO;
import com.example.demo.vo.DetailCartVO;
import com.example.demo.vo.GoodsCategoryVO;
import com.example.demo.vo.GoodsVO;
import com.example.demo.vo.LikedVO;
import com.example.demo.vo.OpinionVO;

import lombok.Setter;

@Service
@Setter
public class GoodsService {

	@Autowired
	private GoodsJpaRepository gj;
	@Autowired
	private GoodsMybatisRepository gm;
	@Autowired
	private CartMybatisRepository cm;
	@Autowired
	private LikedMybatisRepository lm;
	@Autowired OpinionMyBatisRepository om;
	
	//전체 상품 목록 조회
	public List<GoodsVO> findGoods(HashMap<Object, Object> map){
		return gm.findGoods(map);
	}
	
	//카테고리별 상품목록 조회
	public List<GoodsVO> findByCategoryNo(HashMap<Object, Object> map){
		return gm.findByCategoryNo(map);
	}
	
	//상품상세목록 조회
	public GoodsVO detailGoods(int goodsNo) {
		return gm.detailGoods(goodsNo);

	}
	
	//전체 레코드 수 조회
	public int getTotalRecord(Integer categoryNo) {
		return gm.getTotalRecord(categoryNo);
	}
	
	//좋아요 수 추가
	public int insertGoodsLiked(HashMap<String, Object> map) {
		return lm.insertGoodsLiked(map);
	}
	
	//좋아요 취소 
	public int deleteGoodsLiked(HashMap<String, Object> map) {
		return lm.deleteGoodsLiked(map);
	}
	
	//회원별 상품 좋아요목록
	public List<LikedVO> findLikedGoodsByUserNo(int userNo){
		return lm.findLikedGoodsByUserNo(userNo);
	}
	//장바구니 추가
	public int insertCart(HashMap<String, Object> map) {
		return cm.insertCart(map);
	}
	//유저별 장바구니 조회
	public List<DetailCartVO> findCartByUserNo(int userNo) {
		return cm.findCartByUserNo(userNo);
	}
	
	//선택한 장바구니 품목 삭제
	public int deleteCartByGoodsNo(HashMap<String, Object> map) {
		return cm.deleteCartByGoodsNo(map);
	}
	
	//품절된 장바구니 품목 삭제
	public int deleteCartByGoodsStock(int userNo) {
		return cm.deleteCartByGoodsStock(userNo);
	}
	
	//장바구니 수량 변경
	public int updateCartCnt(HashMap<String, Object>map) {
		return cm.updateCartCnt(map);
	}
	
	//쇼핑 문의 조회
	public List<OpinionVO> selectShopOpinion(int goodsNo){
		return om.selectShopOpinion(goodsNo);
	}
	
	//쇼핑 리뷰 조회
	public List<OpinionVO> selectShopReview(int goodsNo){
		return om.selectShopReview(goodsNo);
	}
	
	//쇼핑 문의글 작성
	public int insertShopQNA(HashMap<String, Object>map) {
		return om.insertShopQNA(map);
	}
	
	//쇼핑 문의글 삭제
	public int deleteShopQNA(int opinionNo) {
		return om.deleteShopQNA(opinionNo);
	}
	
	//쇼핑 문의글 수정
	public int updateShopQNA(HashMap<String, Object> map) {
		return om.updateShopQNA(map);
	}
	
	//상품 등록
	public int insertGoods() {
		return gm.insertGoods();
	}
	
	
	//카테고리 조회
	public GoodsCategoryVO findCategory(Integer goodsNo) {
		return gm.findCategory(goodsNo);
	}
	
	//장바구니 상품 수량 조회
	public CartVO cartTot(int userNo) {
		return cm.cartTot(userNo);
	}
	
	//상품 리뷰 수 조회
	public int goodsReviewCount(Integer goodsNo) {
		return gm.goodsReviewCount(goodsNo);
	}
	
	//상품 문의 수 조회
	public int goodsOpinionCount(Integer goodsNo) {
		return gm.goodsOpinionCount(goodsNo);
	}
	
	//문의글 수정을 위한 작성글 조회
	public OpinionVO selectShopByOpinionNo(int opinionNo) {
		return gm.selectShopByOpinionNo(opinionNo);
	}
}
