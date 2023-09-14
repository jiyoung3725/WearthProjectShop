package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.SchoolDBManager;
import com.example.demo.db.ShopDBManager;
import com.example.demo.vo.EducationVO;
import com.example.demo.vo.LectureVO;
import com.example.demo.vo.LikedVO;

@Repository
public class LikedMybatisRepository {
	//상품 좋아요 추가
	public static int insertGoodsLiked(HashMap<String, Object> map) {
		return ShopDBManager.insertGoodsLiked(map);
	}
	//상품 좋아요 취소
	public static int deleteGoodsLiked(HashMap<String, Object> map) {
		return ShopDBManager.deleteGoodsLiked(map);
	}
	//회원별 좋아요 목록 조회
	public static List<LikedVO> findLikedGoodsByUserNo(int userNo){
		return ShopDBManager.findLikedGoodsByUserNo(userNo);
	}
	
		//교육, 강연 좋아요 추가
		public static int insertEducationLiked(HashMap<String, Object> map) {
			return SchoolDBManager.insertEducationLiked(map);
		}
		public static int insertLectureLiked(HashMap<String, Object> map) {
			return SchoolDBManager.insertLectureLiked(map);
		}
		
		//교육, 강연 좋아요 취소
		public static int deleteEducationLiked(HashMap<String, Object> map) {
			return SchoolDBManager.deleteEducationLiked(map);
		}
		public static int deleteLectureLiked(HashMap<String, Object> map) {
			return SchoolDBManager.deleteLectureLiked(map);
		}
		//교육, 강연 좋아요 목록 조회
		public static List<EducationVO> findLikedEducationByUserNo(int userno){
			return SchoolDBManager.findLikedEducationByUserNo(userno);
		}
		public static List<LectureVO> findLikedLectureByUserNo(int userno){
			return SchoolDBManager.findLikedLectureByUserNo(userno);
		}
		
		//교육, 강연 좋아요 버튼 세션유지를 위한 조회
		public List<Integer> findLikedEducationNos(int userno){
			return SchoolDBManager.findLikedEducationNos(userno);
		}
		public List<Integer> findLikedLectureNos(int userno){
			return SchoolDBManager.findLikedLectureNos(userno);
		}
}
		
