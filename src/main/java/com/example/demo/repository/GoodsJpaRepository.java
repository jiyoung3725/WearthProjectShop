package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.GoodsVO;

import jakarta.transaction.Transactional;
@Repository
public interface GoodsJpaRepository extends JpaRepository<GoodsVO, Integer> {

	
}
