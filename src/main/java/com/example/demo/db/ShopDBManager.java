package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.CartVO;
import com.example.demo.vo.DetailCartVO;
import com.example.demo.vo.GoodsCategoryVO;
import com.example.demo.vo.GoodsVO;
import com.example.demo.vo.LikedVO;
import com.example.demo.vo.OpinionVO;

public class ShopDBManager {
	public static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			String resource = "com/example/demo/mapper/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("예외발생 DBManager :"+e.getMessage());
		}
	}

	//goods
		public static List<GoodsVO> findGoods(HashMap<Object, Object> map){
			SqlSession session = sqlSessionFactory.openSession();
			List<GoodsVO> list = session.selectList("goods.findGoods",map);
			session.close();
			return list;
		}
		//카테고리번호별 상품조회
		public static List<GoodsVO> findByCategoryNo(HashMap<Object, Object> map){
			SqlSession session = sqlSessionFactory.openSession();
			List<GoodsVO> list = session.selectList("goods.findByCategoryNo", map);
			session.close();
			return list;
		}
		//상품상세조회
		public static GoodsVO detailGoods(int goodsNo) {
			SqlSession session = sqlSessionFactory.openSession();
			GoodsVO g = session.selectOne("goods.detailGoods", goodsNo);
			session.close();
			return g;
		}
		//category 조회
		public static List<GoodsCategoryVO> findCategory(){
			SqlSession session = sqlSessionFactory.openSession();
			List<GoodsCategoryVO> list = session.selectList("category.findCategory");
			session.close();
			return list;
		}
		
		//전체 레코드수 조회
		public static int getTotalRecord(Integer categoryNo) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession();
			re = session.selectOne("goods.getTotalRecord", categoryNo);
			session.close();
			return re;
		}
		
		//좋아유 버튼 클릭 시 1증가
		public static int insertGoodsLiked(HashMap<String, Object> map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.insert("liked.insertGoodsLiked",map);
			session.close();
			return re;
		}
		
		//좋아요 삭제
		public static int deleteGoodsLiked(HashMap<String, Object> map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.delete("liked.deleteGoodsLiked", map);
			session.close();
			return re;
		}
		
		//회원별 좋아요한 상품 목록 조회
		public static List<LikedVO> findLikedGoodsByUserNo(int userNo){
			SqlSession session = sqlSessionFactory.openSession();
			List<LikedVO> list = session.selectList("liked.findLikedGoodsByUserNo", userNo);
			session.close();
			return list;
		}
		
		//장바구니 추가
		public static int insertCart(HashMap<String, Object> map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re= session.insert("cart.insertCart", map);
			session.close();
			return re;
		}
		
		//유저별 장바구니 조회
		public static List<DetailCartVO> findCartByUserNo(int userNo) {
			SqlSession session = sqlSessionFactory.openSession();
			List<DetailCartVO> list = session.selectList("cart.findCartByUserNo", userNo);
			System.out.println("장바구니 목록!!!!!!!!!!!"+list);
			
			session.close();
			return list;
		}
		
		//장바구니-선택한 품목 삭제
		public static int deleteCartByGoodsNo(HashMap<String, Object> map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.delete("cart.deleteCartByGoodsNo", map);
			session.close();
			return re;
		}
		
		//장바구니 품절상품 삭제
		public static int deleteCartByGoodsStock(int userNo) {
			int re =-1;
			SqlSession session=sqlSessionFactory.openSession(true);
			re = session.delete("cart.deleteCartByGoodsStock", userNo);
			session.close();
			return re;
		}
		
		//장바구니 수량 업데이트
		public static int updateCartCnt(HashMap<String, Object> map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.update("cart.updateCartCnt",map);
			session.close();
			return re;
		}
		
		//쇼핑 문의 조회
		public static List<OpinionVO> selectShopOpinion(int goodsNo){
			SqlSession session = sqlSessionFactory.openSession();
			List<OpinionVO> list = session.selectList("opinion.selectShopOpinion", goodsNo);
			session.close();
			return list;
		}
		
		//쇼핑 리뷰 조회
		public static List<OpinionVO> selectShopReview(int goodsNo){
			SqlSession session = sqlSessionFactory.openSession();
			List<OpinionVO> list = session.selectList("opinion.selectShopReview",goodsNo);
			System.out.println("나나나낭ㄹ나어리나어ㅣㅏㄹㄴ"+list);
			session.close();
			return list;
		}
		
		//쇼핑 리뷰 사진 모아보기
		
		//쇼핑 문의글 작성
		public static int insertShopQNA(HashMap<String, Object>map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.insert("opinion.insertShopQNA",map);
			session.close();
			return re;
		}
		
		//쇼핑 문의글 삭제
		public static int deleteShopQNA(int opinionNo) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.delete("opinion.deleteShopQNA", opinionNo);
			session.close();
			return re;
		}
		
		//쇼핑 문의글 수정
		public static int updateShopQNA(HashMap<String, Object>map) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession(true);
			re = session.update("opinion.updateShopQNA", map);
			session.close();
			return re;
		}
		
		//카테고리 조회
		public static GoodsCategoryVO findCategory(Integer goodsNo) {
			SqlSession session =sqlSessionFactory.openSession();
			GoodsCategoryVO g = session.selectOne("category.findCategory",goodsNo);
			System.out.println("카테고리!!!!!!!!!"+g);
			session.close();
			return g;
		}
		
		//장바구니 모든 상품 수량 조회
		public static CartVO cartTot(int userNo) {
			SqlSession session = sqlSessionFactory.openSession();
			CartVO cart = session.selectOne("cart.cartTot",userNo);
			session.close();
			return cart;
		}
		
		//상품 리뷰 수 조회
		public static int goodsReviewCount(Integer goodsNo) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession();
			re = session.selectOne("goods.goodsReviewCount",goodsNo);
			session.close();
			return re;
		}
		//상품 문의 수 조회
		public static int goodsOpinionCount(Integer goodsNo) {
			int re = -1;
			SqlSession session = sqlSessionFactory.openSession();
			re = session.selectOne("goods.goodsOpinionCount",goodsNo);
			session.close();
			return re;
		}
		
		//문의 수정을 위한 문의글 조회
		public static OpinionVO selectShopByOpinionNo(int opinionNo) {
			SqlSession session = sqlSessionFactory.openSession();
			OpinionVO list = session.selectOne("opinion.selectShopByOpinionNo",opinionNo);
			System.out.println("문의글좀 나와주겟니이이잉"+list);
			session.close();
			return list;
		}
		
		public static int insertGoods() {
			int re = -1;
			SqlSession session  = sqlSessionFactory.openSession();
			re = session.insert("goods.insertGoods");
			session.close();
			return re; 
		}
}
