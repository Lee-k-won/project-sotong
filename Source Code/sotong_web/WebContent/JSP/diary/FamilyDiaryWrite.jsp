<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='css/familyDiary/familyDiaryWrite.css' rel='stylesheet'>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="js/familyDiary/familyDiaryWrite.js"></script>
<title>가족 일기 작성하다</title>
</head>
<body>

	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>
	
	
	<div id='family-diary-all'>
		<div id="family-diary-body">
			
			<!-- 왼쪽에 제공 되는 목록 틀이다. -->
			<div id="family-diary-list">
				<span id="family-diary-list-title">우리 가족일기</span>
				<div id="family-diary-list-info">
					<input type="image" src="image/upBtn.JPG" id="up-btn"/>	
					<div id="family">
						<ul id='family-diary-list-ul'>
						<c:forEach var="diaryInfo" items="${simpleFamilyDiaryList}" varStatus="i">
							<li class='family-diary-list'>						
								<form id="famly-diary-list-form${i.index }" method='post' action='familyDiary.do'>	
									<div id="family-diary-list-summary">
										<input type='hidden' value=${diaryInfo.familyDiaryCode } name="familyDiaryCode">
										<input type="button" value="${diaryInfo.familyDiaryDate }" class='family-date-list' id="family-diary-list-title-btn"/>
									</div>
								</form>
							</li>		
						</c:forEach>
						</ul>
					</div>
					<input type="image" src="image/downBtn.JPG" id="down-btn"/>
				</div>		
			</div>
			
			<!-- 오른쪽 가족 일기를 제공 해주는 전체 틀이다. -->
			<div id="family-diary-board">
					<!-- 글쓰기 이미지  -->
				<input type="image" id="write-btn" class="write" src="image/image-wirteBtn.png"/>				
				<!-- 가족 이야기 게시물의 전체 테두리 -->
				<div id="family-diary-board-all">
					<!-- 프로필, 가족이야기 한 사람의 게시판의 전체 틀 -->
					<div id="family-diary-board-info">
						<form method="post" action="familyDiary_insert.do" id="form-familyDiary-write">
							<!-- 한 사람이 작성한 일기의 내용 -->
							<div id="family-diary-board-content">
								<div id="family-diary-textarea">
									<textarea id='family-diary-board-textarea' name="familyDiaryContents"></textarea>
								</div>
								<!-- 사진 버튼 -->
								<div id="add-photo-button" class="family-diary-button">
									<input type="image" id="add-photo-letter" class="family-diary-button" src="image/letter-addPhoto.png"/>
									<input type="image" id="add-photo-btn" class="family-diary-button" src="image/image-addPhotoBtn.png"/>
								</div>
								<!-- 확인 버튼 -->
								<div id="ok-button" class="family-diary-button">
									<input type="button" id="ok-letter" class="family-diary-button">
									<input type="button" id="ok-btn" class="family-diary_button">
								</div>
								<!-- 돌아가기 버튼 -->
								<div id="back-button" class="family-diary-button">
									<input type="image" id="back-letter" class="family-diary-button" src="image/letter-back.png"/>
									<input type="image" id="back-btn" class="family-diary-button" src="image/image-backBtn.png"/>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>			
		</div>
	</div>
</body>
</html>