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
	
	<!-- 사용자 정의 -->
	
	<link rel="stylesheet" href="css/famSchedule/familySchedule.css">
	<script src="js/famSchedule/familySchedule.js" type="text/javascript" charset="utf-8"></script>
	
	<!-- 메뉴바 -->
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
	
	<!-- 태그라이브러리 -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<!-- 페이지 설정 -->
	<%-- <%@ page contentType="application/json;" pageEncoding="UTF-8"%> --%>
	
	<title>가족 일정</title>
</head>
<body>

	<div id="calendar">
		<input type="button" id="scheduleMatchigBtn" class="fc-prevYear-button fc-button fc-state-default fc-corner-left fc-corner-right" value ="스케쥴매칭">
	</div>


	<!-- 일정 Modal -->
	<div class="modal fade" id="ourSche" role="dialog">
		<div class="modal-dialog">

		<!-- 일정 Modal 내용 -->
			<div class="modal-content">
				
				<!-- 일정 최상단부 -->
				<div class="modal-header" > 
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-lock"></span>
						<label id="menuName" name="menuName">가족 일정 상세보기</label>
					</h4>
				</div>
				
				<!-- 일정 내용 -->
				<div class="modal-body" >
				
					<form method="post" id="form-familySchedule" class="form-horizontal">
						
						<!-- 히든태그 -->
						<input type="hidden" id="scheduleCode" name="scheduleCode" value="${famScheInfo.familyScheduleCode}">
						
						<!-- 제목 -->
						<div class="form-group">
							<label for="txtTel" class="col-sm-2 control-label">제목</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" value="${famScheInfo.familyScheduleTitle}" id="scheduleTitle" placeholder="일정의 제목을 입력하세요." name="scheduleTitle" readonly>
							</div>
						</div>
		
						<!-- 장소 -->       
						<div class="form-group">
							<label for="txtTel" class="col-sm-2 control-label">장소</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" value="${famScheInfo.familySchedulePlace}" id="schedulePlace" placeholder="일정의 장소를 입력하세요." name="schedulePlace" readonly>
							</div>
						</div>
						
						<!-- 일시 -->
						<div class="form-group">
							<label for="txtTel" class="col-sm-2 control-label">시작일시</label>
							<div class="col-sm-5">
								<input type="date" class="form-control" value="${familyInfo.famScheStartDate}" id="startDate" name="startDate" readonly>
							</div>
						
							<div class="col-sm-4">
								<input type="time" class="form-control" value="${familyInfo.famScheStartTime}" id="startTime" name="startTime" readonly>
							</div>
						</div>
						
						<div class="form-group">
							<label for="txtTel" class="col-sm-2 control-label">종료일시</label>             
							<div class="col-sm-5">
								<input type="date" class="form-control" value="${familyInfo.famScheEndDate}" id="endDate" name="endDate" readonly>
							</div>
						  
							<div class="col-sm-4">
								<input type="time" class="form-control" value="${familyInfo.famScheEndTime}" id="endTime" name="endTime" readonly>
							</div>
						</div>
		 
		 
						 <!-- 하루종일 버튼. -->
						<div class="form-group">
							<div id="allDayDiv" >
								<input type="checkbox" name="allDayBtn" id="allDayBtn" readonly>
								<label id="allDayLabel">하루종일</label>
							</div>
						</div>
		
		
						<!-- 반복 및 알림 -->       	
						<div class="form-group">
							<label for="txtTel" class="col-sm-2 control-label">반복</label>
							<div class="col-sm-10">
								<select class="col-sm-4" id="repeatSelec" name="repeatSelec">
									<option selected value="1">일회성 일정</option>
									<option value="2">매주</option>
									<option value="3">매월</option>
								</select>
								
								<script language="javascript">
									document.all["repeatSelec"].disabled=true;
								</script>
								
								<label for="txtTel" class="col-sm-2 control-label">알림</label>
								<select class="col-sm-4" id="alarmSelec" name="alarmSelec">
									<option value="1">없     음</option>	
									<option value="2">15 분전</option>
									<option value="3">30 분전</option>
									<option value="4">1시간 전</option>
									<option value="5">2시간 전</option>
								</select>
								<script language="javascript">
									document.all["alarmSelec"].disabled=true;
								</script>
							</div>
						</div>	
		       
		       
						<!-- 이벤트  -->
						<div class="form-group">
							<label for="txtTel" class="col-sm-3">이벤트 등록 여부</label>
							<input type="checkbox" name="eventSelec" class="col-sm-1" id="eventSelecBtn" name="eventSelecBtn" readonly>
						</div>
		
						<div class="form-group" id="eventDiv">
							<label for="txtTel" class="col-sm-2 control-label">질문</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" value="${familyInfo.famScheEventQ}" id="eventQ" placeholder="질문을 입력하세요" name="eventQ" readonly>
							</div>
						</div>
		
						<!-- 메모 -->
						<div class="form-group">
							<label for="txtTel" class="col-sm-2 control-label">메모</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" value="${familyInfo.famScheMemo}" id="memo" placeholder="메모 입력하세요" name="memo" readonly>
							</div>
						</div>
						
						<!-- 버튼 -->
						<div class="btnDiv">
							<input type="button" class="editScheBtn" id="modifyBtn" alt="수정"/> 
							<input type="button" class="editScheBtn" id="deleteBtn" alt="삭제"/>	
							<input type="button" class="editScheBtn" id="backBtn" alt="돌아가기"/>
							<input type="button" class="addScheBtn" id="okBtn" alt="등록"/>
							<input type="button" class="addScheBtn" id="cancelBtn" alt="취소"/>
						</div>
					</form>
				</div>
			</div> 
		</div>
	</div>
	
	<!-- 일정 Modal -->
	<div class="modal fade" id="mySche" role="dialog">
		<div class="modal-dialog">
	
		<!-- 일정 Modal 내용 -->
			<div class="modal-content">
			<!-- 일정 최상단부 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-lock"></span>
						<label id="menuName">개인 일정</label>
					</h4>
				</div>
				
				<div class="modal-body">
				<form class = "form-horizontal" role="form" name="my-schedule-form" id="schedule-form">
		  
					<!-- 제목 -->
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="myscheduleTitle" name="myscheduleTitle" placeholder="일정의 제목을 입력하세요." readonly>
						</div>
					</div>
		 	
					<!-- 장소 -->
	                
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">장소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="myschedulePlace" name="myschedulePlace" placeholder="일정의 장소를 입력하세요." readonly>
						</div>
					</div>
	
					<!-- 일시 -->
					
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">시작일시</label>
						<div class="col-sm-5">
							<input type="date" class="form-control" id="mystartDate" name="mystartDate" readonly>
						</div>
					   
						<div class="col-sm-4">
							<input type="time" class="form-control" id="mystartTime" name="mystartTime" readonly>
						</div>
					</div>
					
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">종료일시</label>             
						<div class="col-sm-5">
							<input type="date" class="form-control" id="myendDate" name="myendDate" readonly>
						</div>
					   
						<div class="col-sm-4">
							<input type="time" class="form-control" id="myendTime" name="myendTime" readonly>
						</div>
					</div>
	            
	            
					<!-- 하루종일 버튼. -->
					<div class="form-group">
						<div id="allDayDiv" >
							<input type="checkbox" name="myallDayBtn" id="myallDayBtn" readonly>
							<label>하루종일</label>
						</div>
					</div>
	            
	            
					<!-- 반복 -->
					       	
					<div class="form-group">
						<label for="txtTel" class="col-sm-2 control-label">반복</label>
						<div class="col-sm-10">
							<select class="col-sm-4" id="myrepeatSelec" readonly name="myrepeatSelec">
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
							<select class="col-sm-4" id="myalarmSelec" name="myalarmSelec" readonly>
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
							<input type="text" class="form-control" id="mymemo" name="mymemo" placeholder="메모 입력하세요" readonly>
						</div>
					</div>
	
					<!-- 히든 영역 -->
					<div class = "hiddenData">
						<input type="hidden" id="myscheduleCode" name="myscheduleCode">
						<input type="hidden" id="mymemberColor" name="mymemberColor">
					</div>
					
					<div class="btnDiv">
						<!-- 추가 및 상세정보 보기 시 창을 닫는 버튼 -->
						<input type="button" class="addScheBtn detailScheBtn" id="cancelBtn" alt="닫기"/>
					    		              		
					</div>
				</form>
			</div>
		</div> 
	</div>
	</div>
	<div class="modal fade" id="matchingModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					
					<!-- 모달창 제목 -->
					<h4 class="modal-title"><span class="glyphicon glyphicon-ok">가족시간 구하기</span></h4>
					
				</div>
				
				<div class="modal-body" id="matchingModalBody">
					
					<!-- 모달창 내용 -->
					<label class="matchingLabel">우리 가족이 함께 할 수 있는 시간!</label>
					
					<div id="scheduleMatchingDiv">
						<h6><label>평일 : 18, 24, 25, 26, 27, 28, 31 일</label></h6>
						<h6><label>주말 : 22, 23, 30 일</label></h6>
					</div>
					
					<label class="matchingLabel">가족들과 23일 저녁에 외식계획을 </label>
					<label class="matchingLabel">가져보는건 어떠세요?</label>
				
				</div>
				
				<div class="modal-footer" id="myButton">
					<input type="button" id="modalOkBtn" class="buttons" alt="확인"/>
					<input type="button" id="modalCancelBtn" class="buttons" alt="취소"/> 
				</div>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	
	
</body>
</html>