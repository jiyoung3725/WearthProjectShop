/**
 * 
 */

$(document).ready(function() {
	$(".info").show();
	$(".review").hide();
	$(".qna").hide();

	$("#btnradio1").click(function() {

		$(".info").show();
		$(".review").hide();
		$(".qna").hide();
	})
	$("#btnradio2").click(function() {
		$(".info").hide();
		$(".review").show();
		$(".qna").hide();
	})

	$("#btnradio3").click(function() {
		$(".info").hide();
		$(".review").hide();
		$(".qna").show();

	})
})




//수량변경
$('.num-in span').click(function() {
	var $input = $(this).parents('.num-block').find('input.in-num');
	if ($(this).hasClass('minus')) {
		var count = parseFloat($input.val()) - 1;
		count = count < 1 ? 1 : count;
		if (count < 2) {
			$(this).addClass('dis');
		}
		else {
			$(this).removeClass('dis');
		}
		$input.val(count);
	}
	else {
		var count = parseFloat($input.val()) + 1
		$input.val(count);
		if (count > 1) {
			$(this).parents('.num-block').find(('.minus')).removeClass('dis');
		}
	}
	$input.change();
	var tr = $input.parents().parents().parents().parents();
	var columns = tr.children();
	goodsNo = columns.eq(0).find("#goodsNo").val();
	console.log(goodsNo);
	//db에 수량 업데이트하기
	/*	$.ajax({
			url : "/updateCartCnt",
			type : "post",
			data : {userNo:userNo, cartCnt:count, goodsNo:goodsNo},
			beforeSend: function (xhr) {xhr.setRequestHeader(header, token);},
			success: function (data) {
				document.location.reload();
			}
		})*/
	return false;
});
//문의하기 작성창

$(document).ready(function() {
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content');
	var secretCheckbox = $("#flexSwitchCheckDefault");
	var secret = $("#flexSwitchCheckDefault").val();
	var id = $("#usersID").val();
	secretCheckbox.on("change", function() {
		if (this.checked) {
			$("#pwd-div").css("display", "block");
			secret = 1;
		} else {
			$("#pwd-div").css("display", "none");
		}
	});
	$("#write").click(function() {
		if (id === null || id === "") {
			$(".modal-content").hide();
			
			swal.fire({
				title: "로그인 후 이용가능합니다.",
				text: "로그인 하시겠습니까?",
				icon : "warning",
				showCancelButton: true,
				confirmButtonText: "로그인하러가기",
				cancelButtonText: "취소",
				confirmButtonColor: "#58b483",
  				cancelButtonColor: "lightGray"
			}).then((result)=>{
				if(result.isConfirmed) {
					location.href = "/userinfo/login";
				} else {
					$("#exampleModal").hide();
					$(".modal-backdrop.fade.show").hide();
					 $("body").css("overflow", "auto");
				}
				}) 
			}
		
	})
   
	$("#btn_qna").click(function() {

		var title = $("#recipient-name").val();
		var text = $("#message-text").val();
		var pwd = $("#pwd").val();
		var goodsNo = $("#goodsNo").val();
		console.log(title)
		console.log(text)
		console.log(pwd)
		console.log(goodsNo)


		$.ajax({
			url: "/shop/insertQNA",
			type: "post",
			data: { goodsNo: goodsNo, opinionName: title, opinionContent: text, opinionPwd: pwd },
			beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
			success: function(data) {
				document.location.reload();
			}
		})
		
	})
//리뷰 조회
$(document).ready(function() {
	$(".details").hide();
	$(".clickable").click(function(){
		$(this).next(".details").toggle();
	})
})

	
//문의 비밀글 조회	
$(document).ready(function() {
	$(".clickable-row").click(function() {
		var tr =$(this).children();
		var pwd = $(tr).eq(0).find("#showPwd").val();

		if(pwd===null || pwd===""){
			$(this).next(".details-row").toggle();
		}else{
			swal.fire({
				icon : "warning",
				title : "작성자와 관리자만 열람이 가능합니다",
				text : "비밀번호를 입력해주세요",
				input : "password",
				showCancelButton : true,
				confirmButtonText: "확인",
 				cancelButtonText: "취소",
  				confirmButtonColor: "#58b483",
  				cancelButtonColor: "lightGray"
			}).then((result)=>{
				if(result){
					if(result.value == pwd){
						$(this).next(".details-row").toggle();
					}
				}else{
					console.log("취소")
				}
			})
		}
		
	});
});


$(document).ready(function(){
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content'); 
	var cnt = $("#cnt").val();
	var goodsNo = $("#goodsNo").val();
	var userNo = $("#userNo").val();
//장바구니 담기
$("#cart").click(function(){
	
	if(userNo ===null || userNo===""){
		swal.fire({
				title: "로그인 후 이용가능합니다.",
				text: "로그인 하시겠습니까?",
				icon : "warning",
				showCancelButton: true,
				confirmButtonText: "로그인하러가기",
				cancelButtonText: "취소",
				confirmButtonColor: "#58b483",
  				cancelButtonColor: "lightGray"
			}).then((result)=>{
				if(result.isConfirmed) {
					location.href = "/userinfo/login";
				} else {	}
				}) 
	}

	$.ajax({
		url:"/insertCart",
		type:"post",
		data :{cnt : cnt, goodsNo:goodsNo, userNo:userNo},
		beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
		success : function(data){
			swal.fire({
				icon: "success",
				title: "장바구니에 담겼습니다",
				confirmButtonColor: '#58b483', // confrim 버튼 색깔 지정
				confirmButtonText: '장바구니 보기',
				showCancelButton: true,
				cancelButtonText: '계속 쇼핑하기',
				cancelButtonColor: 'lightGray'
			}).then((success) => {
				if (success.isConfirmed) {
					location.href = "/shop/cart/" + userNo;
				}
			})
		}
	})
})

//구매하기
$("#pay").click(function(){
	if(userNo ===null || userNo===""){
		swal.fire({
				title: "로그인 후 이용가능합니다.",
				text: "로그인 하시겠습니까?",
				icon : "warning",
				showCancelButton: true,
				confirmButtonText: "로그인하러가기",
				cancelButtonText: "취소",
				confirmButtonColor: "#58b483",
  				cancelButtonColor: "lightGray"
			}).then((result)=>{
				if(result.isConfirmed) {
					location.href = "/userinfo/login";
				} else {	}
				}) 
	}else{
		cnt=$("#cnt").val()
		location.href="/shop/order/"+goodsNo+"/"+userNo+"/"+cnt
	}
	
})


});
//캐러셀
$('.owl-carousel').owlCarousel({
  loop: true,
  margin: 10,
  nav: true,
  navText: [
    "<i class='fa fa-caret-left'></i>",
    "<i class='fa fa-caret-right'></i>"
  ],
  autoplay: true,
  autoplayHoverPause: true,
  responsive: {
    0: {
      items: 1
    },
    600: {
      items: 3
    },
    1000: {
      items: 5
    }
  }
})
});

$(".scroll").click(function(){
	$(window).scrollTop(0);
})

$(document).ready(function(){
	var header = $("meta[name='_csrf_header']").attr('content');
	var token = $("meta[name='_csrf']").attr('content'); 
	$("#btn_delete.custom-btn").click(function(){
		alert("삭제")
		var tr=$(this).parents().parents().parents().parents();
		var opinionNo = tr.children().children().eq(0).find("input[name='opinionNo']").val();
		$.ajax({
			url:"/shop/deleteQNA",
			type : "post",
			data : {opinionNo:opinionNo},
			beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
			success:function(){
				document.location.reload();
			}
		})
	})
	$("#btn_qnaUpdate.custom-btn").click(function(){
		var tr=$(this).parents().parents().parents().parents();
		var opinionNo = tr.children().children().eq(0).find("input[name='opinionNo']").val();
		$.ajax({
			url : "/shop/selectQnaByOpinionNo",
			type : "post",
			data : {opinionNo:opinionNo, opinionName:"", opinionContent:""},
			beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
			success:function(data){
				$("#recipient-name").val(data.opinionName);
				$("#message-text").val(data.opinionContent);
				$("#btn_qna").click(function(){
					var Name = $("#recipient-name").val();
					var Content = $("#message-text").val();
					$.ajax({
						url : "/shop/updateQNA",
						data:{opinionNo:opinionNo,opinionName:Name,opinionContent:Content},
						beforeSend: function(xhr) { xhr.setRequestHeader(header, token); },
						success:function(){
								alert("수정성공")
							}
					})
				})
			}
		})
	})
})



