<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!-- 풀 캘린더 -->
	<script src='fullcalendar/lib/moment.min.js'></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src='fullcalendar/fullcalendar.min.js'></script>
	
	<link rel='stylesheet' href='fullcalendar/lib/cupertino/jquery-ui.min.css' />
	<script src='fullcalendar/lib/jquery-ui.custom.min.js'></script>
	
	<link href='fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
	<link rel='stylesheet' href='fullcalendar/fullcalendar.css' />
	
	<script src='fullcalendar/gcal.js'></script>
	<script src='fullcalendar/lang-all.js'></script>
	
	<!-- 부트스트랩 -->
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<!-- 사용자 정의 css/js -->
	<script src='js/schedule/selfCalendar.js'></script>
	<link rel='stylesheet' href='css/schedule/selfCalendar.css'>

	<!-- 메뉴바 -->
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
	
	<!-- 태그라이브러리 -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<!-- 페이지 설정 -->
	<%-- <%@ page contentType="application/json;" pageEncoding="UTF-8"%> --%>
	
	<title>일정</title>	
</head>
<body>

	<div id="calendar">
		<input type = "button" id = "add-schedule" class = "ui-top-person" value ="일정추가">
	</div>
	  
	<!-- 일정수정 Modal -->
	<div class="modal fade" id="mySche" role="dialog">
		<div class="modal-dialog">
	
		<!-- 일정 수정 Modal 내용 -->
			<div class="modal-content">
			<!-- 일정 수정 최상단부 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-lock"></span>
						<label id="menuName">개인 일정</label>
					</h4>
				</div>
				
				<div class="modal-body">
				<form class = "form-horizontal" role="form" name="schedule-form" id="schedule-form">
		  
					<!-- 제목 -->
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="scheduleTitle" name="scheduleTitle" placeholder="일정의 제목을 입력하세요." readonly>
						</div>
					</div>
		 	
					<!-- 장소 -->
	                
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">장소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="schedulePlace" name="schedulePlace" placeholder="일정의 장소를 입력하세요." readonly>
						</div>
					</div>
	
					<!-- 일시 -->
					
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">시작일시</label>
						<div class="col-sm-5">
							<input type="date" class="form-control" id="startDate" name="startDate" readonly>
						</div>
					   
						<div class="col-sm-4">
							<input type="time" class="form-control" id="startTime" name="startTime" readonly>
						</div>
					</div>
					
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">종료일시</label>             
						<div class="col-sm-5">
							<input type="date" class="form-control" id="endDate" name="endDate" readonly>
						</div>
					   
						<div class="col-sm-4">
							<input type="time" class="form-control" id="endTime" name="endTime" readonly>
						</div>
					</div>
	            
	            
					<!-- 하루종일 버튼. -->
					<div class="form-group">
						<div id="allDayDiv" >
							<input type="checkbox" name="allDayBtn" id="allDayBtn" readonly>하루종일
						</div>
					</div>
	            
	            
					<!-- 반복 -->
					       	
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">반복</label>
						<div class="col-sm-10">
							<select class="col-sm-4" id="repeatSelec" readonly name="repeatSelec">
								<option selected value="1">일회성 일정</option>
								<option value="2">매주</option>
								<option value="3">매월</option>
							</select>
					
							<label class ="col-sm-4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;알림 가족 설정</label>
					
							<!--  <div class="button-group">
								<button tabindex="-1" class="btn btn-default" type="button">가족 선택하기<span class="caret"></span></button>
								<ul role="menu" class="dropdown-menu">
									<li><a href="#" class="family" data-value="option1"><input type="checkbox"/>엄마</a></li>
									<li><a href="#" class="family" data-value="option2"><input type="checkbox"/>아빠</a></li>
									<li><a href="#" class="family" data-value="option3"><input type="checkbox"/>동생</a></li>
								</ul>
							</div> -->
							
							<!-- <select class="col-sm-4" id="familyAlarmSelec">					
								<option>엄마♡</option>
								<option>아빠♡</option>
								<option>동생♡</option>
							</select>  -->
					
							<div class="input-group">
								<div class="input-group-btn">
									<button tabindex="-1" class="btn btn-default" type="button" data-toggle="dropdown" readonly>우리가족 보기<span class="caret"></span></button>
									<ul role="menu" class="dropdown-menu">
										<li><a href="#"><input type="checkbox" id="checkAll"><span class="lbl"> 모두 선택</span>
										</a></li><li class="divider"></li>
										<li><a href="#"><input type="checkbox" class="selectFam"><span class="lbl selectList"> 엄마</span></a></li>
										<li><a href="#"><input type="checkbox" class="selectFam"><span class="lbl selectList"> 아빠</span></a></li>
										<li><a href="#"><input type="checkbox" class="selectFam"><span class="lbl selectList"> 동생</span></a></li>				
									</ul>
								</div>
							</div>
						</div>	
					</div>
					
					<!-- 알림 -->	
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">알람</label>
						<div class="col-sm-10">
							<select class="col-sm-4" id="alarmSelec" name="alarmSelec" readonly>
								<option selected>없     음</option>
								<option>15 분전</option>
								<option>30 분전</option>
								<option>1시간 전</option>
								<option>2시간 전</option>
							</select>	
							<label class ="col-sm-4" id="selectedFam"></label>
						</div>
					</div>	
	     	
					<!-- 메모 -->	     
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">메모</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="memo" name="memo" placeholder="메모 입력하세요" readonly>
						</div>
					</div>
	
					<!-- 히든 영역 -->
					<div class = "hiddenData">
						<input type="hidden" id="scheduleCode" name="scheduleCode">
						<input type="hidden" id="memberColor" name="memberColor">
					</div>
					
					<div class="btnDiv">
					
						<!-- 상세정보 보기 시 보이는 버튼 -->          
						<input type="button" class="detailScheBtn" id="modifyBtn" alt="수정"/> 
						<input type="button" class="detailScheBtn" id="deleteBtn" alt="삭제"/>	
		
						<!-- 수정 시 보이는 버튼 -->              		
						<input type="button" class="editScheBtn" id="backBtn" alt="취소"/>
						<input type="button" class="editScheBtn" id="modifyOkBtn" alt="수정 완료"/>
						
						<!-- 일정 추가시 보이는 버튼 -->
						<input type="button" class="addScheBtn" id="okBtn" alt="새 일정 등록"/>
						              		
						<!-- 추가 및 상세정보 보기 시 창을 닫는 버튼 -->
						<input type="button" class="addScheBtn detailScheBtn" id="cancelBtn" alt="닫기"/>
					    		              		
					</div>
				</form>
			</div>
		</div> 
	</div>
</body>
</html>