<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="UTF-8">
  <title>가족, 그리고 소통</title>
  
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link href="css/main/main.css" rel="stylesheet"></link>
  <script src="js/main/main.js" type="text/javascript" charset="UTF-8"></script> 
  
</head>
<body>
<section>
<div id = "startDiv" class="container">

 
  <!-- 로그인 Modal -->
  <div class="modal fade" id="myLogin" role="dialog">
    <div class="modal-dialog">
    
      <!-- 로그인 Modal 내용 -->
     <div class="modal-content">
     	<!-- 로그인 최상단부 -->
        <div class="modal-header" style="padding:15px 20px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-lock"></span>집으로 들어가기</h4>
        </div>
        <!-- 로그인 입력부분 -->
        <div class="modal-body" style="padding:20px 30px;">
          <form action='login.do' method='post' id='form-login' class = "form-horizontal" role="form">
          <!-- 웹인지를 구분해준다. -->
          <input type='hidden' name='serviceRoute' value='2000'/>
          <!-- 아이디 입력부분 -->
            <div class="form-group">
                <label for="txtId" class="col-sm-2 control-label">아이디</label>
            <div class="col-sm-10">
           		<input type="text" class="form-control" id="userId" name='user-id' placeholder="아이디를 입력하세요">
        	</div>
        	
          <!-- 비밀번호 입력부분 -->
            </div>
            <div class="form-group">
              	<label for="txtPw" class="col-sm-2 control-label">비밀번호</label>
              <div class="col-sm-10">
              	<input type="password" class="form-control" id="userPw" name='user-pw' placeholder="비밀번호를 입력하세요">
              </div>
            </div>
            
          <!-- 들어가는 버튼 부분 -->
          	<div class="buttonDiv">
              <input type="button" id="inBtn" class="buttons">
           	</div> 
          </form>
        </div>
      </div>
      
    </div>
  </div> 
  
  <!-- 회원가입 Modal -->
  <div class="modal fade" id="mySignup" role="dialog">
    <div class="modal-dialog">
    
      <!-- 회원가입 Modal 내용 -->
     <div class="modal-content">
     	<!-- 회원가입 최상단부 -->
        <div class="modal-header" style="padding:15px 20px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-heart"></span>소통 회원 되기</h4>
        </div>
        <div class="modal-body" style="padding:20px 30px;">
          <form action='join.do' method='post' class="form-horizontal" id="form-join" role="form">
          
          <!-- Web 구분해주는 태그 -->
          <input type="hidden" name="joinServiceRoute" value="2000"/>
          
          <!-- 아이디 -->          
            <div class="form-group">
                <label for="txtTel" class="col-sm-2 control-label">아이디</label>
            <div class="col-sm-10">
           		<input type="text" class="form-control" id="Id" name="joinId" placeholder="아이디를 입력하세요" maxlength="16">
        	</div>
        	</div>
        	
          <!-- 비밀번호 -->          
            <div class="form-group">
              	<label for="txtTel" class="col-sm-2 control-label">비밀번호</label>
              <div class="col-sm-10">
              	<input type="password" minlength="6" maxlength="10" class="form-control" id="Pw" name="joinPassword" placeholder="비밀번호를 입력하세요 (6 ~ 10자리)">
              </div>
            </div>
            
          <!-- 이름 -->
          	<div class="form-group">
              	<label for="txtTel" class="col-sm-2 control-label">이름</label>
              <div class="col-sm-10">
              	<input type="text" class="form-control" id="Name" name="joinName" placeholder="이름을 입력하세요" maxlength="5">
              </div>
            </div>
            
           <!-- 휴대폰번호 -->
           
           <div class="form-group">
              <label for="txtTel" class="col-sm-2 control-label">전화번호</label>
              <div class="col-sm-3">
              	<input type="tel" maxlength="3" class="form-control" id="Phone-1" name="joinPhoneNum1">
              </div>
              
              <div class="col-sm-3">
              	<input type="tel" maxlength="4" class="form-control" id="Phone-2" name="joinPhoneNum2">
              </div>
             
              <div class="col-sm-3">
              	<input type="tel" maxlength="4" class="form-control" id="Phone-3" name="joinPhoneNum3">
              </div>
              <!-- js에서 조작 하여 전화 번호를 설정 해준다. servlet에서 사용-->
              <input type='hidden' name='joinPhoneNum' id='join-phone-num'/>
            </div>
            
            <!-- 이메일주소 -->
            
            <div class="form-group">
              	<label for="txtTel" class="col-sm-2 control-label">이메일</label>
              <div class="col-sm-10">
              	<input type="text" class="form-control" id="userEmail" name="joinEmail" placeholder="이메일을 입력하세요" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" maxlength="30">
              </div>
              
            </div> 
            
             <!-- 초대자 식별코드 -->
             <c:choose>
			    <c:when test= "${param.homeCode ne null }" >
			      	<div class="form-group">
		              <div class="col-sm-10">
		              	<input type="hidden" class="form-control-code" id="homeCode" name="joinHomeCode" value="${param.homeCode}">
		              </div>
		            </div>
			    </c:when>    
			    <c:otherwise>
			       <div class="form-group">
		              <div class="col-sm-10">
		              	<input type="hidden" class="form-control-code" id="homeCode" name="joinHomeCode" value="no">
		              </div>
         		   </div>
			    </c:otherwise>
			</c:choose>	
            
            
            <div class = "buttonDiv">  
              <input type="button" id="okBtn" class = "buttons">
              <input type="button" id="cancelBtn" class = "buttons">
             </div>
          </form>
        </div>
      </div>
    </div>
  </div> 
    
  <div class = "inButtons">
  <input type="image" id="loginImg" src="img/main/login.png" alt="로그인하기" width="300" height="300"/>
  <input type="image" id="signupImg" src="img/main/signup.png" alt="회원가입하기" width="300" height="300"/>
</div>
</div>

</section>
<footer>
</footer>
</body>
</html>
