<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='css/myhome/makeHome.css' rel='stylesheet'>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="js/main/main.js"></script>
<title>홈 생성</title>
</head>
<body>

	<div>
		<jsp:include page="MakeHomeMainMenu.jsp"></jsp:include>
	</div>
	
	<div id='make-home-body'>
	
	</div>
	<div>
		<textarea id='home-text' readonly>새 홈을 생성할 경우 더 이상
		
초대받을 수 없습니다.</textarea>
	</div>
		<input type="button" value="홈 생성" class='btn' id='make-home-button'>	
		<form action='makeHome.do' id='form-make-home'></form>
</body>
</html>