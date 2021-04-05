<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='../../css/saveMemory/saveMemory.css' rel='stylesheet'>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<title>추억 쌓기</title>
</head>
<body>

	<div>
	
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>
	
	<div id='save-memory-body'>
		<div id='memory-date-label-border'>
			<label>추억을 쌓을 기간을 선택하세요</label>
		</div>
		<div id='memory-date-border'>
			<input type="date" id='start-date' class='memory-date'/>
			<label>부터</label>
			<input type="date" id='end-date' class='memory-date'/>
			<label>까지</label>
		</div>
		
		<div id='memory-send-email-border'>
			<label id='memory-send-email-label'>추억을 전달받을 이메일 주소</label>
		</div>
		
		<div id='memory-send-email-border'>
			<input type="text" id='memory-send-email'/>
		</div>
		
		<div id='buttons'>
			<input type='button' id='back-btn'/>
			<input type='button' id='check-btn'/>
		</div>
	</div>
	
	
</body>
</html>