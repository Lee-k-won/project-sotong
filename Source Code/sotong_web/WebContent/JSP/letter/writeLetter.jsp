<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/letter/writeLetter.css">
<script src="js/letter/writeLetter.js" charset="UTF-8" type="text/javascript"></script>

<title>편지쓰기</title>
</head>
<body>
	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>

<div id="letter">
	<form id="letterForm" action="<%=request.getContextPath()%>/letter_write.do" method="post">
	
		<!-- 제목 -->
		<div class="input-group" id="letter-writer-title">
			<input type="text" class = "form-control" placeholder="제목을 입력하세요" id="letterTitle" name="title" />
		</div>
		
		<!-- 받는사람 -->
		<div class="form-inline" id="letter-writer-receiverForm">
			<div class="form-group" id="letter-writer-receiverDiv">
		
				<label id="letter-writer-receiver">받는 사람</label>
		
		<!-- 받는 사람 선택 목록 -->
				<div class="receiverList" id="letter-writer-list">
					<select id="receiverList" name="receiverList">
						<c:forEach var='member' items="${familyMemberList}">
						<option>${member.memberNickName}</option>
				  		</c:forEach>
				  	</select>
				</div>		

			</div>
		</div>
		<!-- 내용 -->
		<div class="input-group" id="letter-writer-contentsDiv">
  		  	<textarea class="form-control custom-control" id="letterContent" rows="25" cols="60" name="contents"></textarea>     
		</div>
		
		<!-- 발송일자 선택 -->
		<div class="input-group" id="letter-write-sendInfo">
			<div id="letter-write-sendDate">
				<label>발송일자</label>
				<input type="checkbox" class="checkbox-info" id="sendCheck" checked/> 즉시발송
				<label>예약일 설정</label>
				<input type="date" id="sendDate" name="sendDate" readonly/>
			</div>			
			
			<div id="letter-write-buttons">		
				<input type="button" id="backBtn" class="buttons">
				<input type="button" id="checkBtn" class="buttons">
			</div>
		</div>
		
	</form>
</div>

</body>
</html>