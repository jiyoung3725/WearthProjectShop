### WEARTH Project - Shop
---
<p align="center">
  <img src="https://github.com/jiyoung3725/WearthProjectShop/assets/130877885/45ad8ec1-a873-46f8-85a6-d8af76f36637" width="250" >
</p>

---
#### 목차
1. [프로젝트소개](#프로젝트-소개)
2. [STACK](#stack)
3. [상세화면](#상세화면)
4. [핵심트러블슈팅](#핵심-트러블슈팅)

---
#### 프로젝트 소개 

> 최근 환경 보호에 대한 관심이 증가함에 따라 환경보호에 대한 정보를 자유롭게 공유할 수 있는 **제로웨이스트 실천, 쇼핑, 환경 교육을 위한 통합플랫폼**을 기획, 제작하였습니다.

> 개발기간 : 2023/07/24 - 2023/08/18

> 구현 기능 <br>
상품리뷰, 문의글과 관련된 CRUD기능과 상품조회, 정렬, 좋아요, 페이징기능 및 장바구니 추가, 선택 및 품절상품 삭제 기능

---
#### STACK

> BACKEND <br>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/apachetomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white"> <img src="https://img.shields.io/badge/apachemaven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/mybatis-000000?style=for-the-badge&logo=&logoColor=white"> <img src="https://img.shields.io/badge/ajax-0769AD?style=for-the-badge&logo=&logoColor=white">

  
> FRONTEND <br>
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">


> TOOLS <br>
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">  

> COMUNICATION <br>
> <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white">

---
#### 상세화면
##### [쇼핑 메인 페이지 및 상품 상세페이지]
<img width="45%" height="400px" src="https://github.com/jiyoung3725/WearthProjectShop/assets/130877885/228b2955-ac55-4e2a-92ea-df585579bf4f"> <img width="50%" height="400px" src="https://github.com/jiyoung3725/WearthProjectShop/assets/130877885/9e86e358-8978-47f9-b6ec-ce16d053a42b">


##### [상품 상세페이지 리뷰&문의하기] 
<img  width="45%"  height="400px" src="https://github.com/jiyoung3725/WearthProjectShop/assets/130877885/8e59f2cf-3667-4ad4-ad30-e8599c5e1b5e"> <img width="50%"  width="45%"  height="400px" src="https://github.com/jiyoung3725/WearthProjectShop/assets/130877885/19c97eca-7871-447d-977d-31c70333e0c2">




##### [장바구니 페이지]
<img width="45%"  height="400px" src="https://github.com/jiyoung3725/WearthProjectShop/assets/130877885/dc9118dc-5fc3-4060-bef7-003dc016a2fd">

---
#### 핵심 트러블슈팅

##### [장바구니 선택한 상품의 정보 전달하기]
* 사용자가 선택한 여러 개의 상품을 삭제하거나 주문할 때  상품 정보를 배열에 담아 ajax통신으로 controller에 값을 전달하고자 했습니다.  <br>

* 정보를 찾아보며 ajax으로 배열의 값을 전달하는 방법을 찾아보고 controller에서 배열의값을 받아보고자 수차례 시도했으나 반복되는 400, 504, 500 에러로 다른 방법을 찾아야 했습니다.

* 해결방법으로는 $().each(function(){})를 사용해서 ajax통신을 반복하는 것이었습니다.

```javascript
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
```

#### 이 외의 트러블 슈팅

  <details> resultType, resultMap을 설정하거나 조인을 위한 새로운 VO를 만들어 getter,setter,toString 만들어 해
  <summary> 마이바티스 조인하기 </summary></details> 

   <details> Vo파일을 공유해서 사용하다보니 git에 push/ pull하는 과정에서 팀원이 사전에 프로파일링한 규칙대로 작성하지 않아 오류가 난 것. 
  <summary> parameter mapping 오류[String을 integer로 인식] </summary></details> 

  <details> 수정버튼을 누르면 작성하기와 동일한 모달창이 떠서 ajax통신으로 input태그에 값을 넣어 해결.
  <summary> 문의글 수정 시 사용자가 작성한 값 넣기 </summary></details> 

---
   

