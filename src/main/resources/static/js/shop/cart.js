/**
 * 
 */

$(function() {

	$(document).ready(function() {	
		var header = $("meta[name='_csrf_header']").attr('content');
		var token = $("meta[name='_csrf']").attr('content');
		var cnt = 0;
		var price = 0;
		var goodsPrice=0;
		var shipPrice =0;
		var userNo = $("#userNo").val();
		var goodsNo = 0;
		var delete_arr = [];
		var totalCnt=0;
		var chk =$("input[name=check]");
		//맨 위에 체크박스 선택시 모든 체크박스 선택,선택해제
		$('.checked_All').click(function(){	
			AllClick(event);		
			if($(this).is(":checked")){chk.prop("checked",true);	
			}else{chk.prop("checked",false)}
		})
		//개별 상품 체크박스 선택 시 체크, 체크해제
		$(".td_check").click(function(){
			clickEvent(event);
			
			
			if($(this).is(":checked")){
				$(this).prop("checked",true);	
				
			}else{$(this).prop("checked",false)}
			
			
		})
		
		
		//clickEvent에서 해당 Row값 가져오고 상품가격 합산해서 html에 값 넣어주기
		function clickEvent(event){		
			var row = $(event.target).parent().parent().parent().parent().parent();
			var columns = row.children();		
			 cnt = parseInt(columns.eq(2).find('#cnt').val());
			 price = parseInt(columns.eq(3).find('#price').html());
			 console.log(cnt)
			 console.log(price)
			
					//선택상품 삭제 누르면 삭제
					$("#delete-check").click(function(){
						 $(row).each(function(){
						goodsNo = parseInt(columns.eq(0).find("#goodsNo").val());
						$.ajax({
							url : "/deleteCartByGoodsNo",
							type : "post",
							data: {goodsNo:goodsNo, userNo:userNo},
							beforeSend: function (xhr) {xhr.setRequestHeader(header, token);},
							success: function (data) {
								document.location.reload();
							}
						})
					})
			 })			
			
			if($(event.target).is(":checked")){
				goodsPrice += price;
				totalCnt+=cnt;
			}else{ 
				goodsPrice-=price;
				totalCnt-=cnt;
			}
			console.log(goodsPrice)
			$("#totalCnt").html(totalCnt+"개");
			if(goodsPrice < 30000){shipPrice=3000;
			}else if(goodsPrice==0){shipPrice=0;
			}else{shipPrice=0;}
			
			 var tot=goodsPrice+shipPrice;
			$(".goodsPrice").html(goodsPrice);
			$(".shipPrice").html(shipPrice);		
			$(".totalPrice").html(tot);
			
		
		}
		//checked_All 클릭 시 장바구니에 담긴 모든 상품의 가격, 수량 가져오고 합산해서 html에 값 넣어주기
		function AllClick(event){
			$(".tr").each(function(index,item){
				var columns=$(item).children();
				cnt = parseInt(columns.eq(2).find('#cnt').val());
			 	price = parseInt(columns.eq(3).find('#price').html());
			
			if($(event.target).is(":checked")){
				goodsPrice += price;
			}else{ 
				 goodsPrice-=price;
			}
			if(goodsPrice < 30000){shipPrice=3000;
			}else{shipPrice=0;}
			
			 var tot=goodsPrice+shipPrice;
			$(".goodsPrice").html(goodsPrice);
			$(".shipPrice").html(shipPrice);		
			$(".totalPrice").html(tot);
			})
			
		}
		
		//품절상품 삭제
		$("#delete-stock").click(function(){
			$.ajax({
				url : "/deleteCartByGoodsStock",
				type : "post",
				data : {userNo : userNo},
				beforeSend: function (xhr) {xhr.setRequestHeader(header, token);}, 
				success: function (data) {
					
					document.location.reload();
				}
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
			console.log(userNo)
			$.ajax({
				url : "/updateCartCnt",
				type : "post",
				data : {userNo:userNo, cartCnt:count, goodsNo:goodsNo},
				beforeSend: function (xhr) {xhr.setRequestHeader(header, token);},
				success: function (data) {
				
					document.location.reload();
				}
			})
			return false;
		});
		
		//
		$(".pay-btn-small").click(function(){
			var userNo = $("#userNo").val();
			var tr = $(this).parents().parents();
			var goodsNo = parseInt(tr.children().eq(0).find("#goodsNo").val());
			cnt = parseInt(tr.children().eq(2).find("#cnt").val());
			location.href = "/shop/order/"+goodsNo+"/"+userNo+"/"+cnt;
		})
		
		$("#totPay").click(function(){
			var userNo = $("#userNo").val();
			location.href="/shop/orders/"+userNo		
	});
	})	
	// product +/-
})