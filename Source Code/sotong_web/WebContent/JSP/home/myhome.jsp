<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>우리 홈</title>

 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <link href="css/myhome/myhome.css" rel="stylesheet">
  <script src="js/myhome/myhome.js" type="text/javascript" charset="UTF-8"></script>
</head>


<body>

<div>
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
</div>

<div id="homeTitle">

	<div id="neighbor">
		<input type="button" id="neighborBtn" class="btns"/>
		<input type="button" id="neighborBtn2" title="이웃보기" class="overbtns">
	</div>
	<div id="homeInfo">
		<label id="homeName">${familyHome.homeName }</label><%-- EL로 바꿀 곳 --%>
		<c:if test='${userInfo.memberRole eq "1" }'>
			<input type="button" id="editName" alt="가족 홈 이름 수정하기"/>
		</c:if>
	</div>
	
	<c:if test='${userInfo.memberRole eq "1" }'>
	<div id="invite">
		<input type="button" id="inviteBtn" class="btns"/>
		<input type="button" id="inviteBtn2" title="가족초대하기" class="overbtns"/>
	</div>
	</c:if>
</div>

<!-- 가족 구성원 목록 -->

<div class="row" id="familyList" class="text-center">
<ul id="ul-family-list">
	<form action="<%=request.getContextPath()%>/detailMember.do" id="detail-user-form0">
	<a href="#">
	<li class="li-family-list"><div class="col-md-6">
		<div id="profile" class="form-inline">	
		
			<div id="manager_level" class="form-group">
				<img src="img/myhome/manager.png" id="managerIcon" alt="매니저"/>
			</div>
			
			<div id="profile_pic" class="form-group">
				<img src=${familyHome.memberPhoto } id="profileImg" alt="프로필사진" class="img-circle"/>
			</div>
			
			<div id="profile_info" class="form-group">	
				<label>${familyHome.memberName}</label> <%--EL로 바꿀곳 --%>
				<span class="glyphicon glyphicon-gift" aria-hidden="true"  style="color: #FF66CC"></span>생일 : ${familyHome.memberBirth} <%--EL로 바꿀곳 --%>
			</div>
			
			<!-- 멤버코드 히든필드 -->
			<input type="hidden" value="${managerCode}" name="memberInfo"/>	
		</div>
	</div>
	</li>
	</a>
</form>	
	
	<c:forEach var='familyMemberInfo' items="${familyHome.familyMemberList}" varStatus="i">		
		
		<form action="<%=request.getContextPath()%>/detailMember.do" id="detail-user-form${i.index+1 }">	
		<a href="#">
		<li class="li-family-list">
			<div class="col-md-6 famInfo">
		
			<div id="profile" class="form-inline">	
			
				<div id="normal_level" class="form-group">
					<img src="img/myhome/hearts.png" id="familyIcon" alt="가족"/>
				</div>
				
				<div id="profile_pic" class="form-group">
					<img src="${familyMemberInfo[4]}" id="profileImg" alt="프로필사진" class="img-circle"/>
				</div>
				
				<div id="profile_info" class="form-group">	
					<label>${familyMemberInfo[1] }</label> <%--EL로 바꿀곳 --%>
					<span class="glyphicon glyphicon-gift"></span>생일 : ${familyMemberInfo[2]} <%--EL로 바꿀곳 --%>
				</div>
			<!-- 매니저 권한을 가지고 있을 경우에만 출력. -->
			
			<!-- 멤버코드 히든필드 -->
			<input type="hidden" value="${familyMemberInfo[0]}" name="memberInfo"/>
			</div>		
		</div>
		</li>
		</a>	
		</form>
	</c:forEach>
</ul>		
	
</div>

<!-- 가족 초대하기 모달 -->
<div class="container">
	<div class="modal fade" id="inviteModal" role="dialog">
    <div class="modal-dialog" role="document">
    
      <!-- 가족 초대 Modal 내용 -->
     <div class="modal-content">
     	<!-- 가족초대 최상단부 -->
        <div class="modal-header" style="padding:15px 20px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-plus-sign"></span>가족 초대하기</h4>
        </div>
        <!-- 입력부분 -->
        <div class="modal-body" style="padding:20px 30px;">
          <form method="post" action="SendEmail.do" id="form-myhome-invite" class="form-horizontal" role="form">
          
          <!-- 이메일주소 입력부분 -->
            <div class="form-group"  id="textPart">
            	<label>추가할 구성원의 <mark>이메일주소</mark>를 입력하세요. <br></label>
            </div>
            
            <div class="form-group">
                <label for="txtTel" class="col-sm-2 control-label">이메일</label>
            <div class="col-sm-10">
           		<input type="email" class="form-control" id="userEmail" name="recipient" placeholder="초대할 구성원의 이메일 주소를 입력하세요" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" maxlength="50">
        	</div>
        	</div>
    
          <!-- 확인, 취소 버튼 부분 -->
          <div class="modal-body" id="myButton">
              <input type="button" id="inviteOkBtn" class="buttons" alt="확인"/>
       		  <input type="button" id="inviteCancelBtn" class="buttons" alt="취소"/>
          </div>  
          </form>
        </div>
      </div>

</div>
</div>
</div>

<!-- 홈 이름 변경하기 모달 -->

<div class="modal fade" id="changeNameModal" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">홈 이름 변경하기</h4>
      </div>
      <div class="modal-body" id="changeBody">
        <p>변경할 홈 이름을 입력해주세요(최소 2글자 ~ 최대 8글자)</p>
        <form id="rename-form">
        <input type = "text" id="changeName" name="changeName" placeholder="변경할 홈 이름" maxlength="8">
        </form>
      </div>
      <div class="modal-footer" id="myButton">
     	<input type="button" id="changeOkBtn" class="buttons" alt="확인"/>
        <input type="button" id="changeCancelBtn" class="buttons" alt="취소"/> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>