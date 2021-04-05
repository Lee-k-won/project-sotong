<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>소통-가족 프로필</title>



<!-- 외부 링크 -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/profile/modifyFamilyProfile.css">
<script src="js/profile/modifyProfile.js" charset="UTF-8" type="text/javascript"></script>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>

<div>
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
</div>


<div id="profilePage">
	
	<div id="back">
		<input type="button" id="backBtn">
	</div>

	<div id="profile">
	
		<div id="homeName">
			<label id="homeNameLabel">${homeInfo.familyHomeName }</label>
		</div>
		
		<div id="profileDetail">
			<form action="modifyMemberOk.do" method="post" id="family-modify-form" enctype="multipart/form-data">
				<div id="profilePic">
					<c:choose>
						<c:when test="${userInfo.memberRole eq '1' }">
							<img src="img/myhome/manager.png" id="managerImg" name="role">
						</c:when>
						<c:when test="${userInfo.memberRole eq '0' }">
							<img src="img/myhome/hearts.png" id="managerImg" name="role">
						</c:when>
					</c:choose>
					<label for="profileBtn">
						<img src="${userInfo.memberPhoto}" id="profileImg" class="img-circle"/>
					</label>
					<input type="file" id="profileBtn" name="updateImg" accept="image/jpg, image/png, image/JPG, image/PNG">
					
					<input type="hidden" name="photo" value="${userInfo.memberPhoto }">
					<label class="text-center" id="profileName">${userInfo.memberName }</label>
					<input type="hidden" name="name" value="${userInfo.memberName }">
				</div>
			
				<div id="profileInfo">
					<div class="profileInfoDiv">
						<label class = "profileInfo">아이디</label>
						<input class = "proflieContent" type="text" id="sotong-ID" name="id" value="${userInfo.memberId }" maxlength="16" readonly>
					</div>
					
					<div class="profileInfoDiv">
						<label class = "profileInfo">비밀번호</label>
						<input class = "proflieContent" type="password" id="modify-PW" name="pw" minlength="6" maxlength="10">
					</div>
					<div class="profileInfoDiv">
						<label class = "profileInfo">비빌번호확인</label>
						<input class = "proflieContent" type="password" id="mdify-check-PW" minlength="6" maxlength="10">
					</div>
					<div class="profileInfoDiv">
						<label class = "profileInfo">휴대폰번호</label>
						<input class = "proflieContent" type="text" id="phoneNumber" name="phone" value="${userInfo.memberPhone }">
					</div>
					<div class="profileInfoDiv">
						<label class = "profileInfo">이메일주소</label>
						<input class = "proflieContent" type="text" id="email" name="email" value="${userInfo.memberEmail }">
					</div>
					<div class="profileInfoDiv">
						<label class = "profileInfo">생년월일</label>
						<input class = "proflieContent" type="text" id="birth" name="birth" value="${birth}">
					</div>
					<div class="profileInfoDiv">
						<label class = "profileInfo">별명</label>
						<input class = "proflieContent" type="text" id="nickName" name="nickName" value="${userInfo.memberNickName }">
					</div>
					<div class="profileInfoDiv">
						<label class = "profileInfo">색상</label>
						<input class = "proflieContent" type="color" value="${userInfo.memberColor }" name="color">
					</div>
					<input type="hidden" value="${userInfo.memberCode }" name="code">			
				</div>
			</form>
		</div>
	</div>
	
	<div id='button'>
		<input type='button' id='modify-btn' value="수정"/>
	</div>
</div>
	
</body>
</html>