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
<link rel="stylesheet" href="css/profile/familyProfile.css">
<script src="js/profile/familyProfile.js" charset="UTF-8" type="text/javascript"></script>



</head>
<body>

<div>
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
</div>


<div id="profilePage">
	

	<div id="back">
		<input type="button" id="backBtn">
	</div>
	<form id='family-profile' action='deleteMember.do' method='post'>
		<div id="profile">
		
			<div id="homeName">
				<label id="homeNameLabel">${familyHome.familyHomeName }</label>
			</div>
			
			<div id="profileDetail">
				<div id="profilePic">
					<c:choose>
					<c:when test="${memberDetail.memberRole eq '1' }">
						<img src="img/myhome/manager.png" id="managerImg">
					</c:when>
					<c:when test="${memberDetail.memberRole eq '0' }">
						<img src="img/myhome/hearts.png" id="familyImg">
					</c:when>
					</c:choose>
					<img src="${memberDetail.memberPhoto } " id="profileImg" class="img-circle">
					<label class="text-center" id="profileName">${memberDetail.memberName }</label>
				</div>
			
				<div id="profileInfo">
					<div class="profileInfoDiv">
						<label class = "profileInfo">휴대폰번호</label>
						<input class = "proflieContent" type="text" id="phoneNumber" value="${memberDetail.memberPhone }" readonly>
					</div>
					
					<div class="profileInfoDiv">
						<label class = "profileInfo">이메일</label>
						<input class = "proflieContent" type="text" id="email" value="${memberDetail.memberEmail }" readonly>
					</div>
					
					<div class="profileInfoDiv">
						<label class = "profileInfo">생일</label>
						<input class = "proflieContent" type="text" id="birth" name='member-birth' value="${memberBirth }" readonly>
					</div>
					
					<div class="profileInfoDiv">
						<label class = "profileInfo">별명</label>
						<input class = "proflieContent" type="text" id="nickName" value="${memberDetail.memberNickName }" readonly>
					</div>
					
					<div class="profileInfoDiv">
						<label class = "profileInfo">색상</label>
						<input class = "proflieContent" type="color" value="${memberDetail.memberColor }" disabled>
					</div>				
				</div>
			</div>
			<input type='hidden' name='memberCode' value=${memberDetail.memberCode }>
			<input type='hidden' name='homeCode' value=${memberDetail.familyHomecode }>
			<input type='hidden' name='managerId' value=${userInfo.memberId }>
			<input type='hidden' name='managerCode' value=${userInfo.memberCode }>
		</div>
	</form>
	
	<c:if test='${userInfo.memberRole eq "1" }'>
		<c:if test='${userInfo.memberCode ne memberDetail.memberCode }'>
			<div id="buttons">
				<input type="button" id="manager" class="buttons"/>
				<input type="button" id="managerBtn" class="overBtns"/>
				<br>
				<input type="button" id="delete" class="buttons"/>
				<input type="button" id="deleteBtn" class="overBtns"/>
			</div>
		</c:if>
	</c:if>
	
</div>
	
</body>
</html>