package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.UserJpaRepository;
import com.example.demo.repository.UserMyBatisRepository;
import com.example.demo.service.GoodsService;
import com.example.demo.vo.GoodsCategoryVO;
import com.example.demo.vo.GoodsVO;
import com.example.demo.vo.LikedVO;
import com.example.demo.vo.OpinionVO;
import com.example.demo.vo.UsersVO;


import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class ShopController {

	@Autowired
	private GoodsService gs;


	// 전체상품 조회 및 카테고리별 상품 조회
	@GetMapping(value = { "/shop/shopMain/{categoryNo}", "/shop/shopMain/{categoryNo}/{value}", "/shop/shopMain",
			"/shop/shopMain/{value}","/shop/shopMain/{categoryNo}/{value}/{pageNum}"})
	public ModelAndView findGoods(@PathVariable(required = false) Integer categoryNo,@PathVariable(required = false) String value, @PathVariable(value ="pageNum" ,required = false) Integer pageNum,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/shop/shopMain");
		HttpSession session = (HttpSession) request.getSession();
		session.setAttribute("categoryNo", categoryNo);
		if(value==null ) {
			value="liked";
			session.setAttribute("value", value);
		}
		if(pageNum==null) {
			pageNum=1;
		}
		session.setAttribute("value", value);
		//페이징기능
		int totalRecord=gs.getTotalRecord(categoryNo);
		int pageSize=12;
		int totalPage = (int)Math.ceil(totalRecord/(double)pageSize);
		int start = (pageNum-1)*pageSize+1;
		int end = start+pageSize-1;
		mav.addObject("totalPage", totalPage);
		System.out.println("시작번호 : "+start);
		System.out.println("마지막번호 : "+end);
		if(totalRecord<end) {
			end = totalRecord;
		}
		
		//select 정렬기능
		if (categoryNo == 1 ) {
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("categoryNo", categoryNo);
			map.put("value", value);
			map.put("start", start);
			map.put("end", end);
			mav.addObject("list", gs.findGoods(map));
			
		} else if (categoryNo != 1 ) {
			session.setAttribute("categoryNo", categoryNo);
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			map.put("categoryNo", categoryNo);
			map.put("value", value);
			map.put("start", start);
			map.put("end", end);
			mav.addObject("list", gs.findByCategoryNo(map));
		}
		UsersVO user = (UsersVO)session.getAttribute("u");
		if(user!=null) {
		int userNo = user.getUserno();
		session.setAttribute("userNo", userNo);
		}
		return mav;
	}

	// 상품 상세조회 / 리뷰&문의 조회 / 리뷰 수, 문의 수 조회
	@GetMapping("/shop/detail")
	public void detailGoods(@RequestParam Integer goodsNo, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UsersVO user = (UsersVO)session.getAttribute("u");
		if(user !=null) {
		model.addAttribute("id",user.getId());
		model.addAttribute("userNo",user.getUserno());
		}else {
			model.addAttribute("id", null);
		}
		
		model.addAttribute("g", gs.detailGoods(goodsNo));
		model.addAttribute("review", gs.selectShopReview(goodsNo));
		model.addAttribute("qna", gs.selectShopOpinion(goodsNo));
		model.addAttribute("category", gs.findCategory(goodsNo));
		model.addAttribute("r_count", gs.goodsReviewCount(goodsNo));
		model.addAttribute("q_count", gs.goodsOpinionCount(goodsNo));
		//model.addAttribute("detailQna", gs.selectShopByOpinionNo(opinionNo));
	}

	
	//좋아요 버튼 클릭 시 db 추가
	@PostMapping("/insertGoodsLiked")
	@ResponseBody
	public void insertGoodsLiked(HttpServletRequest request,Model model) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
		map.put("userNo", userNo);
		map.put("goodsNo", goodsNo);
		model.addAttribute(gs.insertGoodsLiked(map));
	}
	
	//좋아요 취소
	@PostMapping("/deleteGoodsLiked")
	@ResponseBody
	public void deleteGoodsLiked(Model model, HttpServletRequest request) {
		HashMap< String, Object> map = new HashMap<String, Object>();
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
		map.put("userNo", userNo);
		map.put("goodsNo", goodsNo);
		model.addAttribute(gs.deleteGoodsLiked(map));
	}
	
	//유저의 좋아요목록 조회
	@PostMapping("/findLikedGoodsByUserNo")
	@ResponseBody
	public List<LikedVO> findLikedGoodsByUserNo(HttpServletRequest request) {
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		List<LikedVO> list = gs.findLikedGoodsByUserNo(userNo);
		return list;
	}
	
	
	//장바구니 추가
	@RequestMapping("/insertCart")
	@ResponseBody
	public void insertCart(Model model,HttpServletRequest request) {
	int cartCnt = Integer.parseInt(request.getParameter("cnt"));
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	int userNo = Integer.parseInt(request.getParameter("userNo")) ;
	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("cartCnt", cartCnt);
	map.put("goodsNo", goodsNo);
	map.put("userNo", userNo);
	System.out.println("맵!!!"+map);
	model.addAttribute(gs.insertCart(map));
	}
	
	//유저별 장바구니 조회
	@GetMapping("/shop/cart/{userNo}")
	public ModelAndView detailCart(@PathVariable int userNo) {
		ModelAndView mav = new ModelAndView("shop/cart");
		mav.addObject("cart",gs.findCartByUserNo(userNo));
		return mav;
	}
	//품절된 장바구니 품목 삭제
	@PostMapping("/deleteCartByGoodsStock")
	@ResponseBody
	public void deleteCartByGoodsStock(Model model, HttpServletRequest request) {
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		model.addAttribute(gs.deleteCartByGoodsStock(userNo));
		System.out.println("품절 삭제 : "+userNo);
		
	}
	
	//선택한 장바구니 품목 삭제
	@PostMapping("/deleteCartByGoodsNo")
	@ResponseBody
	public void deleteCartByGoodsNo(HttpServletRequest request, Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		map.put("goodsNo", goodsNo);
		map.put("userNo", userNo);
		model.addAttribute(gs.deleteCartByGoodsNo(map));
		}
	
	
	
	//장바구니 수량변경
	@PostMapping("/updateCartCnt")
	@ResponseBody
	public void updateCartCnt(HttpServletRequest request, Model model) {
		System.out.println("수량변경 컨트롤러 동작함!");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", Integer.parseInt(request.getParameter("userNo")));
		map.put("goodsNo", Integer.parseInt(request.getParameter("goodsNo")));
		map.put("cartCnt", Integer.parseInt(request.getParameter("cartCnt")));
		System.out.println("map:"+map);
		model.addAttribute(gs.updateCartCnt(map));
	}
	
	
	//문의글 등록
	@PostMapping("/shop/insertQNA")
	@ResponseBody
	public void insertShopQNA(HttpServletRequest request) {
		System.out.println("문의 컨트롤러 동작!!!!!!!!!!!!");
		int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
		HttpSession session = request.getSession();
		UsersVO user = (UsersVO)session.getAttribute("u");
		String id = user.getId();
		String opinionName = request.getParameter("opinionName");
		String opinionContent = request.getParameter("opinionContent");
		String opinionPwd = request.getParameter("opinionPwd");
		System.out.println("아이디"+id);
		System.out.println("상품번호"+goodsNo);
		System.out.println("상품번호"+opinionName);
		System.out.println("상품번호"+opinionContent);
		System.out.println("상품번호"+opinionPwd);
		HashMap<String, Object>map =new HashMap<String,Object>();
		map.put("id", id);
		map.put("goodsNo",goodsNo);
		map.put("opinionName",opinionName);
		map.put("opinionContent",opinionContent);
		map.put("opinionPwd", opinionPwd) ;
		gs.insertShopQNA(map);
		
	}
	
	//문의글 삭제
	@PostMapping("/shop/deleteQNA")
	@ResponseBody
	public void deleteShopQNA(Model model, HttpServletRequest request) {
		int opinionNo = Integer.parseInt(request.getParameter("opinionNo"));
		model.addAttribute(gs.deleteShopQNA(opinionNo));
	}
	
	//문의글 수정 시 내용 조회
	@PostMapping("/shop/selectQnaByOpinionNo")
	@ResponseBody
	public HashMap<String, Object> selectQnaByOpinionNo(Model model, HttpServletRequest request) {
		int opinionNo = Integer.parseInt(request.getParameter("opinionNo"));
		HashMap<String, Object>map = new HashMap<>();
		model.addAttribute("detailQna",gs.selectShopByOpinionNo(opinionNo));
		String opinionName = gs.selectShopByOpinionNo(opinionNo).getOpinionName();
		String opinionContent = gs.selectShopByOpinionNo(opinionNo).getOpinionContent();
		System.out.println(opinionName);
		map.put("opinionName",opinionName);
		map.put("opinionContent",opinionContent);
		return map;
	}
	
	//문의글 수정
	@PostMapping("/shop/updateQNA")
	@ResponseBody
	public void updateQNA(HttpServletRequest request) {
		String opinionName = request.getParameter("opinionName");
		String opinionContent = request.getParameter("opinionContent");
		HashMap<String, Object> map = new HashMap<>();
		map.put("opinionName", opinionName);
		map.put("opinionContent", opinionContent);
		map.put("opinionNo", Integer.parseInt(request.getParameter("opinionNo")));
		gs.updateShopQNA(map);
	}
	
	//하나만 구매하기 
	@RequestMapping("/shop/order/{goodsNo}/{userNo}/{cnt}")
	public String order(Model model,@PathVariable int goodsNo,@PathVariable int userNo,@PathVariable int cnt) {
		model.addAttribute("cnt",cnt);
		model.addAttribute("buy",gs.detailGoods(goodsNo));
		model.addAttribute("count",null);
		return "shop/order";
	}
	//여러개 구매하기
	@RequestMapping("/shop/orders/{userNo}")
	public String orders(@PathVariable int userNo,Model model) {
		model.addAttribute("products", gs.findCartByUserNo(userNo));
		model.addAttribute("count", 1);
		return "shop/order";
	}
	
	
	
	//상품등록화면
	@GetMapping("/shop/insertGoods")
	public String insertGoodsForm() {
		return "shop/insertGoods";
	}
	

//	//상품등록
//	@PostMapping("/shop/insertGoods")
//	public ModelAndView insertGoods(GoodsVO g, MultipartHttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("redirect:/shop/insertGoods");
//		int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
//		String goodsName = request.getParameter("goodsName");
//		String goodsCompany = request.getParameter("goodsCompany");
//		String goodsOrigin = request.getParameter("goodsOrigin");
//		int goodsPrice = Integer.parseInt(request.getParameter("goodsPrice"));
//		int goodsStock = Integer.parseInt(request.getParameter("goodsStock"));
//		int shipPrice = Integer.parseInt(request.getParameter("shipPrice"));
//		int goodsDC = Integer.parseInt(request.getParameter("goodsDC"));
//		int addPoint = Integer.parseInt(request.getParameter("addPoint"));
//		int re = gs.insertGoods();
//		String path = request.getServletContext().getRealPath("images");
//		System.out.println("경로오오오오오오!"+path);
//		MultipartFile files1 = g.getFiles1();
//		MultipartFile files2 = g.getFiles2();
//		MultipartFile files3 = g.getFiles3();
//		String mainFname = files1.getOriginalFilename();
//		String addFname = files2.getOriginalFilename();
//		String infoFname = files3.getOriginalFilename();
//		System.out.println("사진1"+mainFname);
//		System.out.println("사진2"+addFname);
//		System.out.println("사진3"+infoFname);
//
//		if(re==1) {
//			if(!mainFname.isEmpty() && !addFname.isEmpty() && !infoFname.isEmpty() ) {
//		//		long n=System.currentTimeMillis();
//			
//				try {
//					byte []main = files1.getBytes();
//					byte []add = files2.getBytes();
//					byte []info = files3.getBytes();
//					FileOutputStream mainFos = new FileOutputStream(path+"/"+mainFname);
//					mainFos.write(main);
//					mainFos.close();
//					FileOutputStream addFos = new FileOutputStream(path+"/"+addFname);
//					addFos.write(add);
//					addFos.close();
//					FileOutputStream infoFos = new FileOutputStream(path+"/"+infoFname);
//					infoFos.write(info);
//					infoFos.close();
//					System.out.println("상품 등록 완료!");
//					
//				}catch(Exception e) {
//					System.out.println("예외발생"+e.getMessage());
//				}
//			}
//		}
//		
//		return mav;
//		
//	}
	
	
	
}

