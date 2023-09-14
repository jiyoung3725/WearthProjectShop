package com.example.demo.db;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.example.demo.vo.OpinionVO;

public class OpinionDBManager extends DBManager {

	
	//강의, 교육 전체 목록 반환
	public static List<OpinionVO> findAllEducationOpinion (int eduNO){
		SqlSession session = sqlSessionFactory.openSession();
		List<OpinionVO> list = session.selectList("opinion.findAllEducationOpinion",eduNO);
		session.close();
		return list;
	}
	public static List<OpinionVO> findAllLectureOpinion (int lecNO){
		SqlSession session = sqlSessionFactory.openSession();
		List<OpinionVO> list = session.selectList("opinion.findAllLectureOpinion",lecNO);
		session.close();
		return list;
	}
	
	//강의, 교육 해당 게시글의 문의 글의 상세 조회
	public static OpinionVO findByNoEducationOpinion (HashMap<String, Object> map) {
		OpinionVO o = null;
		SqlSession session = sqlSessionFactory.openSession();
		o = session.selectOne("opinion.findByNoEducationOpinion",map);	
		session.close();
		return o;
	}
	public static OpinionVO findByNoLectureOpinion (HashMap<String, Object> map) {
		OpinionVO o = null;
		SqlSession session = sqlSessionFactory.openSession();
		o = session.selectOne("opinion.findByNoLectureOpinion",map);	
		session.close();
		return o;
	}

}
