<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>소통-가족 프로필</title>

<!-- 외부 링크 -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- 내꺼 -->
<link href="css/profile/familyProfile.css" rel="stylesheet">
<script src="js/neighbor/neighborProfile.js" type="text/javascript" charset="utf-8"></script>

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
			<label id="homeNameLabel">${homeName}</label>
		</div>
		
		<div id="profileDetail">
			<div id="profilePic">
				<c:choose>
				<c:when test="${memberInfo.memberRole eq '1' }">
					<img src="img/myhome/manager.png" id="managerImg">
				</c:when>
				<c:when test="${memberInfo.memberRole eq '0' }">
					<img src="img/myhome/hearts.png" id="managerImg">
				</c:when>
				</c:choose>
				<img src="${memberInfo.memberPhoto } " id="profileImg" class="img-circle">
				<label class="text-center" id="profileName">${memberInfo.memberName }</label>
			</div>
		
			<div id="profileInfo">
				
				<div class="profileInfoDiv">
					<label class = "profileInfo">이메일</label>
					<input class = "proflieContent" type="text" id="email" value="${memberInfo.memberEmail }" readonly>
				</div>
				
				<div class="profileInfoDiv">
					<label class = "profileInfo">생일</label>
					<input class = "proflieContent" type="text" id="birth" value="${birth }" readonly>
				</div>
				
				<div class="profileInfoDiv">
					<label class = "profileInfo">별명</label>
					<input class = "proflieContent" type="text" id="nickName" value="${memberInfo.memberNickName }" readonly>
				</div>
				
				<div class="profileInfoDiv">
					<label class = "profileInfo">색상</label>
					<input class = "proflieContent" type="color" value="${memberInfo.memberColor }" disabled>
				</div>				
			</div>
		</div>
	</div>
</div>
</body>
</html>