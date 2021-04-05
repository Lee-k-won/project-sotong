<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='css/wishList/wishListWrite.css' rel='stylesheet'>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="js/wishList/wishListWrite.js"></script>
<title>소망상자 등록하다</title>
</head>
<body>
	
	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>
	
	<div id='wish-list-all'>
		<div id="wish-list-body">
			
			<!-- 왼쪽에 제공되는 개인 일기 목록이다. -->
			<div id="wish-list-list">
				<span id="wish-list-list-title">소망 상자</span>
				<div id="wish-list-list-info">
					<input type="image" src="image/upBtn.JPG" id="up-btn"/>
					<div id='wish'>
						<form>	<!-- 위에 버튼 아래 버튼 설정 할 수 있게 form 처리 -->
						<!--  여기서 부터 목록을 가져오는 foreach 구문  -->
							<div id="wish-list-summary">	
								<input type="text" id="wish-list-date" readonly value="2015-07-21" size=9/>
								<br>
								<input type="button" value="제목1" id="wish-list-title-btn"/>
							</div>
						<!-- 여기까지 foreach로 묶으면 도니다. -->
						</form>
					</div>
					<input type="image" src="image/downBtn.JPG" id="down-btn"/>
				</div>
			</div>			
			
			<!-- 오른쪽 위시 리스트 등록하다를 제공 해주는 전체 틀이다. -->
			<div id="wish-list-board">
				<!-- 글쓰기 이미지  -->
							
				<!-- 위시리스트 등록하다 게시물의 전체 테두리 -->
				<div id="wish-list-board-all">
					<!-- 위시 리스트 작성 틀-->
					<div id="wish-list-board-info">
						<form id="wish-list-form">
							<!-- 위시 리스트  -->
							<div id="wish-list-board-content">
								<!-- 작성자 이름, 작성 날짜 -->
								<div id="wish-list-write-name-date">
									<input type='text' id="write-name" class='wish-list-write-text' value="김철연" readonly/>
									<span id="write-date-name">작성날짜 : </span>
									<input type='text' id="write-date" class="wish-list-write-text" value="2015-07-24" readonly/>
								</div>
								<!-- 소망상자의 제목  -->
								<div id='wish-list-write-title'>
									<input type='text' id='write-title'/>
								</div>
								<div id="wish-list-textarea">
									<textarea id='wish-list-board-textarea'></textarea>
								</div>
								<!-- 확인 버튼 -->
								<div id="ok-button" class="wish-list-button">
									<input type="image" id="ok-letter" class="famil-list-button" src="image/letter-ok.png"/>
									<input type="image" id="ok-btn" class="famil-list-button" src="image/image-okBtn.png"/>
								</div>
								<!-- 사진 버튼 -->
								<div id="add-photo-button" class="wish-list-button">
									<input type="image" id="add-photo-letter" class="famil-list-button" src="image/letter-addPhoto.png"/>
									<input type="image" id="add-photo-btn" class="famil-list-button" src="image/image-addPhotoBtn.png"/>
								</div>
								<!-- 이모티콘 버튼 -->
								<div id="emoticon-button" class="wish-list-button">
									<input type="image" id="emoticon-letter" class="famil-list-button" src="image/profile.png"/>
									<input type="image" id="emoticon-btn" class="famil-list-button" src="image/emoticon-cool.png"/>
								</div>
								<!-- 돌아가기 버튼 -->
								<div id="back-button" class="wish-list-button">
									<input type="image" id="back-letter" class="famil-list-button" src="image/letter-back.png"/>
									<input type="image" id="back-btn" class="famil-list-button" src="image/image-backBtn.png"/>
								</div>
								<div id="wish-list-schedule-date">
									<span id="schedule-date-button-name">일정날짜 : </span>
									<input type='date' id='schedule-date'/>
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