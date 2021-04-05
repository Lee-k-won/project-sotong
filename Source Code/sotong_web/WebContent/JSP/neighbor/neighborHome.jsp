<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<title>이웃 홈 보기</title>

<!-- 부트스트랩 -->
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  
<!-- 내꺼 -->
<link rel="stylesheet" href="css/neighbor/neighborHome.css">
<script src="js/neighbor/neighborHome.js" charset="UTF-8" type="text/javascript"></script>
</head>


<body>

<div>
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
</div>

<div id="homeTitle">
	<!-- 이웃 홈 연결 요청 페이지 -->
	
	<c:if test='${userInfo.memberRole eq "1" }'>
		<c:choose>
		    <c:when test="${neighborCheck eq '1' }">
				<input type="image" id="disconnectImg" src="img/neighbor/disconnect.png" alt="이웃과 연결끊기" title="이웃과 연결끊기"/>
			</c:when>
			<c:otherwise>
				<input type="image" id="connectImg" src="img/neighbor/connect.png" alt="이웃과 연결하기" title="이웃과 연결하기"/>
			</c:otherwise>
		</c:choose>
	</c:if>
	
	<!-- 홈 이름 -->
	<label id="homeName"><span>${familyHome.homeName }</span></label> <%-- EL로 바꿀 곳 --%>
	
</div>

<!-- 이웃 가족 구성원 목록 -->

<div class="row" id="familyList" class="text-center">

<!-- 매니저 프로필 -->
<ul id="family-profile-ul">
<li class="family-profile-li">	
	<div class="col-md-6" class="famInfo">
	<form action="<%=request.getContextPath()%>/neighborProfile.do" method="post" id="neighbor-profile-form0" class="neighbor-form-class">
		<a href="#" class="neighbor-profile-class">
		<div id="profile" class="form-inline">	
		
			<div id="manager_level" class="form-group">
				<img src="img/myhome/manager.png" id="managerIcon" alt="매니저"/>
			</div>
			
			<div id="profile_pic" class="form-group">
				<img src="${homeManager.memberPhoto }" id="profileImg" alt="프로필사진" class="img-circle"/>
				
			</div>
			
			<div id="profile_info" class="form-group">
				<label>${homeManager.memberName }</label> <%--EL로 바꿀곳 --%>	
				<span class="glyphicon glyphicon-gift" aria-hidden="true"  style="color: #FF66CC"></span>생일 : ${managerBirth} <%--EL로 바꿀곳 --%>
			</div>
		
		<input type="hidden" value="${managerCode}" name="memberCode"/>	
		</div>
		</a>
	</form>
	</div>
	
<!-- 가족 구성원 프로필 -->

	<c:forEach var='familyMemberInfo' items="${familyHome.familyMemberList }" varStatus="i" >
<li class="family-profile-li">	
	<div class="col-md-6" class="famInfo">
	<form action="<%=request.getContextPath()%>/neighborProfile.do" method="post" id="neighbor-profile-form${i.index+1}" class="neighbor-form-class">
		<a href="#" class="neighbor-profile-class">
		<div id="profile" class="form-inline">	
			<div id="manager_level" class="form-group">
				<img src="img/myhome/hearts.png" id="familyIcon" alt="가족"/>
			</div>
			
			<div id="profile_pic" class="form-group">
				<img src="${familyMemberInfo[4] }" id="profileImg" alt="프로필사진" class="img-circle"/>
				
			</div>
			
			<div id="profile_info" class="form-group">
				<label>${familyMemberInfo[1] }</label> <%--EL로 바꿀곳 --%>	
				<span class="glyphicon glyphicon-gift" aria-hidden="true"  style="color: #FF66CC"></span>생일 : ${familyMemberInfo[2]} <%--EL로 바꿀곳 --%>
			</div>
			
			<!-- 멤버코드 히든필드 -->
			<input type="hidden" value="${familyMemberInfo[0]}" name="memberCode"/>
			

		</div>
		</a>
		</form>
	</div>
</li>	
	</c:forEach>
</ul>	
</div>

<!-- 연결 요청 모달 -->

<div class="modal fade" id="connectModal" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><span class="glyphicon glyphicon-ok">  연결 요청하기</span></h4>
      </div>
      <div class="modal-body" id="connectBody">
     	<input type="hidden" value="${homeManager.familyHomecode}" name="homeCode" id="neighborHomeCode1"/>
        <p><strong>${familyHome.homeName }</strong>에 연결요청을 하시겠습니까?</p>
      </div>
      <div class="modal-footer" id="myButton">
     	<input type="button" id="connectOkBtn" class="buttons" alt="확인"/>
        <input type="button" id="connectCancelBtn" class="buttons" alt="취소"/> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 연결 끊기 모달 -->

<div class="modal fade" id="disconnectModal" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><span class="glyphicon glyphicon-remove">연결 해제하기</span></h4>
      </div>
      <div class="modal-body" id="connectBody">
      			<!-- 홈 코드 히든필드 -->
		<input type="hidden" value="${homeManager.familyHomecode}" name="homeCode" id="neighborHomeCode2"/>
        <p><strong>${familyHome.homeName }</strong>과 연결을 끊으시겠습니까?</p>
      </div>
      <div class="modal-footer" id="myButton">
     	<input type="button" id="disconnectOkBtn" class="buttons" alt="확인"/>
        <input type="button" id="disconnectCancelBtn" class="buttons" alt="취소"/> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>