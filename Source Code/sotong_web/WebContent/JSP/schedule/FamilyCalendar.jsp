<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<!-- 풀 캘린더 -->
	<script src='../../fullcalendar/lib/moment.min.js'></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 	<script src='../../fullcalendar/fullcalendar.min.js'></script>
	<link rel='stylesheet' href='../../fullcalendar/lib/cupertino/jquery-ui.min.css' />
	<script src='../../fullcalendar/lib/jquery-ui.custom.min.js'></script>
	<link href='../../fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
	<link rel='stylesheet' href='../../fullcalendar/fullcalendar.css' />
	<script src='../../fullcalendar/gcal.js'></script>
	<script src='../../fullcalendar/lang-all.js'></script>
	
	<!-- 부트스트랩 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<!-- 사용자 정의 css/js -->
	<script src='../../js/schedule/familyCalendar.js'></script>
	<link rel='stylesheet' href='../../css/schedule/familyCalendar.css'>
		
	<title>가족일정</title>
</head>
<body>
	<!-- 메인메뉴 -->
	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>
	
	<div id="calendar">
		<input type = "button" id = "add-schedule" class = "fc-prevYear-button fc-button fc-state-default fc-corner-left fc-corner-right" value ="스케쥴매칭">
	</div>
 	
	<!-- 일정추가 Modal -->
	<div class="modal fade" id="ourSche" role="dialog">
		<div class="modal-dialog">
	  
	    <!-- 일정추가 Modal 내용 -->
			<div class="modal-content">
				<!-- 일정추가 최상단부 -->
				<div class="modal-header" > 
					<button type="button" class="close" data-dismiss="modal">&times;</button>
	  				<h4><span class="glyphicon glyphicon-lock"></span>가족 일정 추가</h4>
				</div>
				
				<div class="modal-body" >
	  				<form class = "form-horizontal" role="form">
	  							
	 					<!-- 제목 -->
	   					<div class="form-group">
	       					<label for="txtTel" class="col-sm-2 control-label">제목</label>
	   						
	   						<div class="col-sm-10">
	  							<input type="text" class="form-control" id="scheduleTitle" placeholder="일정의 제목을 입력하세요.">
							</div>
						</div>
	
						<!-- 장소 -->
						<div class="form-group">
	  						<label for="txtTel" class="col-sm-2 control-label">장소</label>
	  						
	  						<div class="col-sm-10">
	  							<input type="text" class="form-control" id="schedulePlace" placeholder="일정의 장소를 입력하세요.">
	  						</div>
						</div>
	
						<!-- 일시 -->
						<div class="form-group">
	   						<label for="txtTel" class="col-sm-2 control-label">시작일시</label>
	   						
	   						<div class="col-sm-5">
	   							<input type="date" class="form-control" id="startDate">
	   						</div>
	   
	   						<div class="col-sm-4">
	   							<input type="time" class="form-control" id="startTime">
	   						</div>
						</div>
	
						<div class="form-group">
	   						<label for="txtTel" class="col-sm-2 control-label">종료일시</label>             
	   						<div class="col-sm-5">
	   							<input type="date" class="form-control" id="endDate">
	   						</div>
	   
	   						<div class="col-sm-4">
	   							<input type="time" class="form-control" id="endTime">
	   						</div>
						</div>
 
	 					<!-- 하루종일 버튼. -->
						<div class="form-group">
							<div id="allDayDiv" >
								<input type="checkbox" name="allDayBtn" id="allDayBtn">하루종일
							</div>
						</div>

						<!-- 반복 -->      	
	       				<div class="form-group">
	       					<label for="txtTel" class="col-sm-2 control-label">반복</label>
	       					<div class="col-sm-10">
	       						<select class="col-sm-4">
	       							<option>일회성 일정</option>
					       			<option>매주</option>
					       			<option>매월</option>
								</select>
	
								<label for="txtTel" class="col-sm-2 control-label">알림</label>
								<select class="col-sm-4">
					       			<option>없     음</option>	
					       			<option>15 분전</option>
					       			<option>30 분전</option>
					       			<option>1시간 전</option>
					       			<option>2시간 전</option>
								</select>
	       					</div>
	       				</div>	

	       				<!-- 이벤트  -->
						<div class="form-group">
							<label for="txtTel" class="col-sm-3">이벤트 등록 여부</label>
							<input type="checkbox" name="eventSelec" class="col-sm-1" id="eventSelecBtn">
							<label for="txtTel" id="famSelec">응답 요청 가족</label>
						</div>
	
						<div class="form-group" id="eventDiv">
							<label for="txtTel" class="col-sm-2 control-label">질문</label>
							<div class="col-sm-10">
								<input class="col-sm-7" type="text" id="eventQ">
			
								<div class="col-sm-2" id="famCheckBox">
									아빠♡<input type="checkbox" checked="checked"><br>
									엄마♡<input type="checkbox" checked="checked"><br>
									동생♡<input type="checkbox" checked="checked"><br>
								</div>
							</div>	
						</div>
		
						<!-- 메모 -->
	          			<div class="form-group">
				        	<label for="txtTel" class="col-sm-2 control-label">메모</label>
	            			<div class="col-sm-10">
	            				<input type="text" class="form-control" id="userEmail" placeholder="메모를 입력하세요">
	            			</div>
	          			</div>
	            		<div class="btnDiv">          
		            		<input type="button" class="addScheBtn" id="okBtn" alt="등록"/>
		            		<input type="button" class="addScheBtn" id="cancelBtn" alt="취소"/>
		        		</div>
	        		</form>
				</div>
			</div> 
		</div>
	</div> 
	
</body>
</html>












