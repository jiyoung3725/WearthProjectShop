package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.vo.OpinionVO;

import jakarta.transaction.Transactional;

public interface OpinionJpaRepository extends JpaRepository<OpinionVO, Integer> {
// 교육, 강연 문의게시글 등록
	@Query(value ="insert into opinion o (o.opinionno, o.eduNO, o.ID, o.opinionName, o.opinionContent, o.opinionDate, o.opinionPwd, o.opinionStatus, o.type) "
			+ "values (seq_opinion.nextval, :#{#o.eduNO},:#{#o.ID}, :#{#o.opinionName}, :#{#o.opinionContent},sysdate, :#{#o.opinionPwd}, '답변대기', 'opinion')",nativeQuery = true)
	@Modifying
	@Transactional
	public void insertEducationOpinion(OpinionVO o);
	
	@Query(value ="insert into opinion o (o.opinionno, o.lecNO, o.ID, o.opinionName, o.opinionContent, o.opinionDate, o.opinionPwd, o.opinionStatus, o.type) "
			+ "values (seq_opinion.nextval, :#{#o.lecNO},:#{#o.ID}, :#{#o.opinionName}, :#{#o.opinionContent},sysdate, :#{#o.opinionPwd}, '답변대기', 'opinion')",nativeQuery = true)
	@Modifying
	@Transactional
	public void insertLectureOpinion(OpinionVO o);
	

	
}
