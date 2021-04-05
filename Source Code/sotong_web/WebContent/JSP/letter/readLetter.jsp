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
  
  <link rel="stylesheet" href="css/letter/readLetter.css">
<script src="js/letter/readLetter.js" charset="UTF-8" type="text/javascript"></script>
<title>편지읽기</title>
</head>
<body>

	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>

<div id="letter">

<form id="letterForm" action="<%=request.getContextPath()%>/letter_delete.do">
	
		<!-- 제목 -->
		<div class="input-group" id="letterTitleDiv">
		<input type="text" class = "form-control" value="${letter.letterTitle }" size="100" id="letterTitle" readonly/>
		</div>
		
		<!-- 발송시각 -->
		<div class="input-group" id="letterTimeDiv">
		<input type="text" class = "form-control" value="발송시각 : ${letterDate }" size="100" id="letterDate" readonly/>
		</div>
				
		<!-- 내용 -->
		<div class="input-group" id="letterContentDiv">
    	<textarea class="form-control custom-control" id="letterContent" readonly>${letter.contents }</textarea>     
		</div>

		<!-- 발신인 -->
		<div id="letterWriter">
			
			<div id="writer">
				<label id="from">From.</label>		
				<img src="img/profile/sotong_20150813_2046552.png" class="img-circle" id="profileImg"/>			
				<label class="text-center" id="sender">${letter.sender }</label>	
			</div>
		</div>
		
		<!-- 히든 -->
		<input type="hidden" id="letterCode" name="letterCode" value="${letter.letterCode }">
		
</form>	
	
	<div id="letter-read-buttons">	
		<!-- 편지 삭제 버튼 -->
		<input type="button" id="deleteBtn" class="buttons">	
		<!-- 글 쓰기 버튼 -->
		<input type="button" id="writeBtn" class="buttons">
	</div>

</div>

</body>
</html>