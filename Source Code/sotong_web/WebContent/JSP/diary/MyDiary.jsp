<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/myDiary/myDiary.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="js/myDiary/myDiary.js"></script>
<title>개인 일기장 보다.</title>
</head>
<body>
	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>
	
	<div id="my-diary-all">
		<div id="my-diary-body">
		
			<!-- 왼쪽에 제공되는 개인 일기 목록이다. -->		
			<div id="my-diary-list">
				<span id="my-diary-list-title">내 일기들</span>
				<div id="my-diary-list-info">
					<input type="image" src="image/upBtn.JPG" id="up-btn"/>
					<div id='family'>
						<!-- 위에 버튼 아래 버튼 설정 할 수 있게 form 처리 -->
						<!--  여기서 부터 목록을 가져오는 foreach 구문  -->
						<ul id='diary-list-ul'>
						<c:forEach var="diaryInfo" items="${simpleDiaryList}" varStatus="i">
							<li class='diary-list'>		
								<div id="my-diary-list-summary">	
									<input type="text" class="my-diary-list-date" value="${diaryInfo[2]}" readonly size=9/>
									<br>
									<input type="button" id='title-button' class="my-diary-list-title-btn" value="${diaryInfo[1]}" />
									<form method="get" action="diary.do" id="form-diary-read${i.index }">
										<input type="hidden" class="my-diary-List-code" value="${diaryInfo[0]}" name="diaryCode"/>
									</form>	
								</div>
							</li>
						</c:forEach>	
						</ul>
						<!-- 여기까지 foreach로 묶으면 도니다. -->
						
					</div>
					<input type="image" src="image/downBtn.JPG" id="down-btn"/>
				</div>
			</div>
			
			<div id="my-diary-board">
			
				<div id="my-diary-board-read">
					<form method="post" action="diary_modify.do" id="form-diary-update" >
						<input type="hidden" value="${diaryInfo.diaryCode}" name="diaryCode">
					</form>	
					<input type="text" id="my-diary-board-read-write-date" value="작성날짜: ${diaryInfo.diaryDate}" size=14 readonly/>
					<div id="my-diary-board-read-title-content">
						<input type="text" id="my-diary-title" value="${diaryInfo.diaryTitle}" readonly/>
					</div>
					<div id="my-diary-board-read-content">
						<textarea id="my-diary-board-read-content-textarea" readonly> ${diaryInfo.contents} </textarea>
					</div>
					<div id="my-diary-board-read-button">
					
					
						<a href="<%=request.getContextPath()%>/diaryList.do">
						<div id="write-button-border" class='button-border'>
							<img id="writeLetter" src="image/wirteBtn.png" class="diary-board-button"/>
							<input type="image" id="my-diary-board-writeBtn" src="image/image-wirteBtn.png" class="diary-board-button">						
						</div>
						</a>	
						
						<div id='modify-button-border' class='button-border'>
							<input type="button" id="modifyLetter" class="diary-board-button"/>
							<input type="button" id="my-diary-board-modifyBtn" class="diary-board-button"/>
						</div>					
						
						
						<div id='delete-button-border' class='button-border'>
							<input type="button" id="deleteLetter" class="diary-board-button"/>
							<input type="button" id="my-diary-board-deleteBtn" class="diary-board-button"/>
						</div>				
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>