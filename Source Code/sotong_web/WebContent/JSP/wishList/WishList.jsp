<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='css/wishList/wishList.css' rel='stylesheet'>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="js/wishList/wishList.js"></script>
<title>소망상자 진행화면</title>
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
				<!-- 글쓰기 버튼 -->
				<div id="write-btn-border">
					<input type='image' id="write-btn" class='famil-list-button' src='image/image-wirteBtn.png'/>
					<input type='image' id="write-letter" class='famil-list-button' src='image/wirteBtn.png'/>
					<form id='wirte-letter-form' action='move-wish-page'></form>
				</div>
				<div id="wish-list-list-info">
					<input type="image" src="image/upBtn.JPG" id="up-btn"/>
					<div id='wish'>
						<ul id='wish-title-list-border'>
							<c:forEach var='wishTitle' items='${wishTitleList }' varStatus="i">
							
								<li class='wish-title-list'>
									<form action='wish.do' method='post' id='wisth-list-title-form${i.index }'>	<!-- 위에 버튼 아래 버튼 설정 할 수 있게 form 처리 -->
									<!--  여기서 부터 목록을 가져오는 foreach 구문  -->
										<div id="wish-list-summary">	
											<input type='hidden' value=>
											<input type="text" id="wish-list-date" readonly value=${wishTitle[1] } size=9/>
											<br>
											
											<button class="wish-list-title-btn"><a herf='#${wishTitle[0] }${i.index }'>${wishTitle[2] }</a> </button>
										</div>
									<!-- 여기까지 foreach로 묶으면 도니다. -->
									</form>
								</li>
								
							</c:forEach>
						</ul>
					</div>
					<input type="image" src="image/downBtn.JPG" id="down-btn"/>
				</div>
				
			</div>			
			
			<c:forEach var='wishInfo' items='${wishList }' varStatus="i">
					<c:choose>
					    <c:when test="${wishInfo.wishFinish ne '완료'}">
							<!-- 오른쪽 위시 리스트 보다를 제공 해주는 전체 틀이다. -->
							<div class="wish-list-board-border" id='${wishInfo.wishCode }${i.index }'>
								
								<div class="wish-list-board">
									<!-- 작성자 : 작성자 명   작성날짜 : 작성날짜 의 테두리이다. -->
									<div class="wish-list-write-text-border">
										<div id="wish-list-write-text">	
											<span class="write-text" id='write-text-name-span'>작성자 : </span>
											<input type='text' class='write-text' id='write-text-name' value=${wishInfo.memberNickName } readonly/>
											<span class="write-text" id='write-text-date-span'>작성날짜 : </span>
											<input type='text' class='write-text' id='write-text-date' value=${wishInfo.wishDate } readonly/>
										</div>
									</div>
									<!-- 소망상자의 제목 테두리이다. -->
									<div id="wish-list-title-text-border">
										<input type='text' id='wish-list-title-text' value=${wishInfo.wishTitle } readonly/>
									</div>
									<!-- 소망상자의 내용 테두리이다. -->
									<div id="wish-list-content-text-border">
										<input type='textarea' id='wish-list-content-text' value=${wishInfo.contents } readonly/>
									</div>
									<!-- 삭제, 수정, 완료 버튼 테두리 -->
									<div id="wish-list-button-border">
										<!-- 완료 버튼 테두리 -->
										<div id='wish-list-finish-button-border' class='wish-list-button'>
											<input type='image' id='wish-list-finish-button-image' class='famil-list-button' src='image/image-okBtn.png'/>
											<input type='image' id='wish-list-finish-button-letter' class='famil-list-button' src='image/letter-finish-btn.png'/>							
										</div>
										<!-- 수정 버튼 테두리 -->
										<div id='wish-list-modify-button-border' class='wish-list-button'>
											<input type='image' id='wish-list-modify-button-image' class='famil-list-button' src='image/image-modifyBtn.png'/>
											<input type='image' id='wish-list-modify-button-letter' class='famil-list-button' src='image/modifyBtn.png'/>							
										</div>
										<!-- 삭제 버튼 테두리 -->
										<div id='wish-list-delete-button-border' class='wish-list-button'>
											<input type='image' id='wish-list-delete-button-image' class='famil-list-button' src='image/image-deleteBtn.png'/>
											<input type='image' id='wish-list-delete-button-letter' class='famil-list-button' src='image/deleteBtn.png'/>							
										</div>
									</div>
									<!-- 일정날짜 테두리 -->
									<div id='wish-list-schedule-border' class='schedule-date-border'>
										<span id='wish-list-schedule' class='schedule-date'>일정날짜 : </span>
										<input type='text' id='wish-list-schedule-date' class='schedule-date' value=${wishInfo.wishEndDate } readonlys/>
									</div>
									
								</div>
								
							</div>		
						</c:when>
						<c:otherwise>
							<div class="wish-list-board-border" id='${wishInfo.wishCode }${i.index }'>
								
								<div class="wish-list-board" id='finish-wish-list-board'>
									<!-- 작성자 : 작성자 명   작성날짜 : 작성날짜 의 테두리이다. -->
									<div class="wish-list-write-text-border" id='finish-wish-list-write-text-border'>
										<div id="wish-list-write-text">	
											<span class="write-text" id='finish-write-text-name-span'>작성자 : </span>
											<input type='text' class='write-text' id='finish-write-text-name' value=${wishInfo.memberNickName } readonly/>
											<span class="write-text" id='finish-write-text-date-span'>작성날짜 : </span>
											<input type='text' class='write-text' id='finish-write-text-date' value=${wishInfo.wishDate } readonly/>
										</div>
									</div>
									<!-- 소망상자의 제목 테두리이다. -->
									<div id="wish-list-title-text-border">
										<input type='text' id='wish-list-title-text' value=${wishInfo.wishTitle } readonly/>
									</div>
									<!-- 소망상자의 내용 테두리이다. -->
									<div id="wish-list-content-text-border">
										<input type='textarea' id='wish-list-content-text' value=${wishInfo.contents } readonly/>
									</div>
									<!-- 삭제, 수정, 완료 버튼 테두리 -->
									<div id="wish-list-button-border">
										<!-- 삭제 버튼 테두리 -->
										<div id='wish-list-delete-button-border' class='wish-list-button'>
											<input type='image' id='wish-list-delete-button-image' class='famil-list-button' src='image/image-deleteBtn.png'/>
											<input type='image' id='wish-list-delete-button-letter' class='famil-list-button' src='image/deleteBtn.png'/>							
										</div>
									</div>
									<!-- 일정날짜 테두리 -->
									<div id='finish-wish-list-schedule-border' class='schedule-date-border'>
										<span id='finish-wish-list-schedule' class='schedule-date'>완료날짜 : </span>
										<input type='text' id='finish-wish-list-schedule-date' class='schedule-date' value=${wishInfo.wishEndDate } readonly/>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>				
			</c:forEach>
				
		</div>
		
		
		
	</div>
</body>
</html>