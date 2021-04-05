<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우체통</title>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 <link href="css/letter/letterbox.css" rel="stylesheet"></link>
 <script src="js/letter/letterbox.js" type="text/javascript" charset="UTF-8"></script>

</head>
<body>	
	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>

	<div id="letter-head-list-border">
		<input type="text" id="letter-head-list" value="편지목록"/>
	</div>
	<div id="letterView">
		<div id="letterHead" class="letter-border">
			<ol class="letters">
				<li class="letterInfo">
					<span class="check"><input type="checkbox" id="checkAll" class="checkbox-danger" ></span>
					<span class="title"><label id="letter-title-sample" class="letterTitle">편지제목</label></span>
					<span class="writer"><label id="letter-writer-sample" class="letterWriter">보낸이</label></span>
					<span class="date"><label id="letter-date-sample" class="letterDate">날짜</label></span>
				</li>
			</ol>
		</div>
		<div id="letterList" class="letter-border">
			<!-- 편지 목록 -->
			<ol id="letters">
			<c:forEach var='letter' items="${letterInfo}" varStatus="i">
				<!-- 편지 한개의 정보 -->				
				<form id="letter-form${i.index }" method="get" action="<%=request.getContextPath()%>/letter_detail.do">
			
					
					<span class="check"><input type="checkbox" id="check" class="checkbox-danger">
					</span>
					<li class="letterInfo">	
					<a href="#">			
					<span class="title"><label class="letterTitle" id="letterTitle">${letter[0]}</label>
					</span>			
					<span class="writer"><label class="letterWriter" id="letterWriter">${letter[2]}</label>
					</span>				
					<span class="date"><label class="letterDate" id="letterDate">${letter[1]}</label>
					</span>
					</a>
					</li>					
				<input type="hidden" value="${letter[3]}" name="letterCode">		
				</form>
				
			</c:forEach>
			</ol>
		</div>
		
	</div>

	<div id="button-side">
		<input type="button" id="backBtn" class="buttons">
		<input type="button" id="deleteBtn" class="buttons">
		<input type="button" id="writeBtn" class="buttons">
	</div>
</body>
</html>