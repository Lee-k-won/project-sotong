
$(document).ready(
		function() {		
			
			$("#add-schedule").click(function(){
				addSchedule();
			});

			// 현재 년,월,일
//			var date = new Date();
//			var d = date.getDate();
//			var m = date.getMonth();
//			var y = date.getFullYear();
			
			var event;
			var eventId;
			
			 $('#calendar').fullCalendar(
						{
	// theme : true,
							header : {
								left : 'today prev,next prevYear, nextYear', // 왼쪽
								center : 'title', // 가운데 타이틀
								right : 'month,agendaWeek,agendaDay' // 오른쪽 버튼 순서
							},
							
							allDaySlot : true,
	
							eventLimit : true,
							
							/* 일정 읽어오기 */
							
			                eventSources : [{
			                	
			                	/*가족 일정 읽기*/
			                	events : function(start, end, timezone, callback) {
			                
						        $.ajax({
						            url: 'famScheduleList.do',					           
						            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
						            success: function(result) {
						            	var events = [];
						            	$.each(result, function(index, value){
						            	events.push({
						            			id : result[index].id,
						                        title: result[index].title,
						                        place : result[index].place,
											    start : moment(result[index].start, "YY-MM-DD HH:mm"),					    	
											    end : moment(result[index].end, "YY-MM-DD HH:mm"),
											    allDay : result[index].allDay,
											    alarm : result[index].alarm,
											    memo : result[index].memo,
											    backgroundColor : result[index].backgroundColor,
						                    });
						                });
						                callback(events);
						            }
						        });
							}},
							{
								/*모든 가족 개인 일정 읽기*/
								events: function(start, end, timezone, callback) {
							        $.ajax({
							            url: 'allFamilySchedule.do',					           
							            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
							            success: function(result) {
							            	var events = [];
							            	$.each(result, function(index, value){
							            	events.push({
							            			id : result[index].id,
							                        title: result[index].title,
							                        place : result[index].place,
												    start : moment(result[index].start, "YY-MM-DD HH:mm"),					    	
												    end : moment(result[index].end, "YY-MM-DD HH:mm"),
												    // $("#endTime").val("");
												    allDay : result[index].allDay,
												    memo : result[index].memo,
												    backgroundColor : result[index].backgroundColor,
							                    });
							                });
							                callback(events);
							            }
							        });
								}
							}],
							
							titleFormat : {
								month : 'YYYY MMMM',							
								day : 'YYYY년 MMM d dddd'
							},
							columnFormat : {
	// month : 'ddd',
	// week : 'M/d ddd ',
								day : 'M월d일 dddd '
							},
							timeFormat : { // for event elements
								'' : 'HH:mm', // 월간
								agenda : 'HH:mm{ - HH:mm}' // 주간,일간
							},

							allDayText : '시간', // 주간,월간
	// axisFormat : 'tt hh', // 주간,월간

							monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월',
									'7월', '8월', '9월', '10월', '11월', '12월' ],
							monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
									'7월', '8월', '9월', '10월', '11월', '12월' ],
							dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일',
									'토요일' ],
							dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
							buttonText : {
								prev : '저번달',
								next : '다음달',
								prevYear : '작년',
								nextYear : '내년',
								today : '오늘',
								month : '월간',
								week : '주간',
								day : '일간'
							},
							
							/* 선택했을 때 */
							
							selectable : true,
							selectHelper : true,						
						/*
						 * select : function(start, end, allDay) {
						 * 
						 * var title = prompt('일정을 입력하세요.'); if (title) {
						 * calendar.fullCalendar('renderEvent', { title : title,
						 * start : start, end : end, allDay : allDay }, true // make
						 * the event "stick" ); } calendar.fullCalendar('unselect');
						 *  },
						 */
							editable : true,
							
							  
							/*
							 * { id : 1, title : '철연이 일시키는 날', start : new Date(y,
							 * m, 1), memo : '철연아 자니?', description : '테스트'
							 *  }, { id : 2, title : '태영이 노동시키는 날', start : new
							 * Date(y, m, d - 5), end : new Date(y, m, d - 2),
							 * location : '렉토피아', memo : '노동자는 아닙니다.' }, { id : 999,
							 * title : '경원이 괴롭히는 날', start : new Date(y, m, d - 3,
							 * 16, 0), allDay : true, location : '렉토피아'
							 *  }, { id:3, title : '기분 좋은 날', start : new Date(y, m,
							 * d + 1, 19, 0), end : new Date(y, m, d + 1, 22, 30),
							 * allDay : false, memo : '랄라'
							 *  }, { id:4, title : '치킨 먹기로 한 날', start : new Date(y,
							 * m, 28), end : new Date(y, m, 29), location : '노랑통닭',
							 * description : '테스트'
							 *  }, { id:5, title : '시험', start : new Date(y, m,
							 * d+2), end : new Date(y, m, d+3), allDay : true,
							 * location : '집집집', memo : '메모라능', backGroundColor :
							 * "#ff2233" }, { id:6, title : '집에 가는 날', start : new
							 * Date(y, m, d), end : new Date(y, m, d), allDay :
							 * true, location : '렉토피아 -> 집으로', memo : '집에 갈꾸얌!!',
							 * color : '#00ffff'
							 *  },
							 */
												
							
							// 일정을 클릭했을 경우 (개인일정 모달과 가족일정 모달 확인해야함)
							
							eventClick : function(calEvent, jsEvent, view)
							{							
								event = calEvent;
								eventId = calEvent.id;
								if(eventId.substring(0,2)=='FS') // 가족일정일 때
								{			
	//이거 바깟다								//changeReadonly(0); // 편집 불가능 상태로 전환
																	
									$(".editScheBtn").show();		// 편집 버튼 들을 다시 보이게 한다.
							    	$(".addScheBtn").hide();		// 수정 버튼들은 다시 숨긴다
							    	
							    	$("#menuName").text("가족 일정 상세보기");	// 제목을 일정 상세보기로 수정한다.
							    	$("input").attr("readonly",true);	// 수정이 불가능하도록 고친다.
								
							    	document.all["alarmSelec"].disabled=true;	// select 수정 불가능하게 바꾸는거.
							    	document.all["repeatSelec"].disabled=true;
							    	
	//						    	$("#ourSche").modal();
									
									//일정 코드
									$("#scheduleCode").val(calEvent.id);
									
									// 일정 이름
									$("#scheduleTitle").val(calEvent.title);
									
									// 장소 설정
									$("#schedulePlace").val(calEvent.location); 
									
									// 메모 설정
									$("#memo").val(calEvent.memo);
									
																
									// 일정시작날짜
									var start = calEvent.start; 
									var startDate = start.format().substring(0,10);
									var startTime = start.format().substring(11,19);
									
									// 일정종료날짜
									var end = calEvent.end;
									var endDate = end.format().substring(0,10);
									var endTime = end.format().substring(11,19);
									
									// 하루종일 설정
									
									if(calEvent.allDay == true) // 하루종일 체크가 되어있으면
									{
										$("#allDayBtn").prop("checked", true);
		///								$("#allDayBtn").attr("disabled", true);
										$("#startDate").val(startDate); 										
										$("#endDate").val(startDate); // 종료 date에 시작
										$("#startTime").val(startTime);
										$("#endTime").val(endTime);
																		// 날짜 삽입
										
										$("#startTime").hide();				// 시간 관련 input태그들을 안보이게 하고
							    		$("#endTime").hide();
							    		$("#startDate").css('width','300px');	// 날짜 관련 input 태그들의 길이를 늘린다.
							    		$("#endDate").css('width','300px'); 
									}			
									else
									{
										$("#allDayBtn").prop("checked", false);
		//								$("#allDayBtn").attr("disalbed", true);
									
									
										if(end==null)  // 일정 종료날짜가 없는 경우
										{
											// 일정 종료 세팅
											$("#endDate").val(startDate); // 종료 date에
																			// 시작 날짜 삽입
											$("#endTime").val("23:59:59"); // 종료 time에
																			// 23시 59분
																			// 59초
										}
										else // 일정 종료날짜가 있는 경우
										{
											// 일정 종료 세팅
											
											$("#endDate").val(end.format().substring(0,10)); // 종료
																								// 날짜
																								// 삽입
											$("#endTime").val(end.format().substring(11,19));
										}
									
										
										// 일정 시작 세팅
										$("#startDate").val(startDate); 
										$("#startTime").val(startTime);
									}					
																							
									// 모달창 팝업
									$("#ourSche").modal();
								}
								else // 개인일정일 경우
								{
									$(".detailScheBtn").show();
									
									//일정 코드
									$("#myscheduleCode").val(calEvent.id);
									
									// 일정 이름
									$("#myscheduleTitle").val(calEvent.title);
									
									// 장소 설정
									$("#myschedulePlace").val(calEvent.location); 
									
									// 메모 설정
									$("#mymemo").val(calEvent.memo);							
																
									// 일정시작날짜
									var start = calEvent.start; 
									var startDate = start.format().substring(0,10);
									var startTime = start.format().substring(11,19);
									
									// 일정종료날짜
									var end = calEvent.end;
									
									// 하루종일 설정
									
									if(calEvent.allDay == true) // 하루종일 체크가 되어있으면
									{
										$("#myallDayBtn").prop("checked", true);
										$("#myallDayBtn").attr("disabled", true);
										$("#mystartDate").val(startDate); 
										$("#mystartTime").val(null);
										
										$("#myendDate").val(startDate); // 종료 date에 시작
																		// 날짜 삽입
										$("#myendTime").val(null);
									}
									else
									{
										$("#myallDayBtn").prop("checked", false);
										$("#myallDayBtn").attr("disalbed", true);
									
									
										if(end==null)  // 일정 종료날짜가 없는 경우
										{
											// 일정 종료 세팅
											$("#myendDate").val(startDate); // 종료 date에
																			// 시작 날짜 삽입
											$("#myendTime").val("23:59:59"); // 종료 time에
																			// 23시 59분
																			// 59초
										}
										else // 일정 종료날짜가 있는 경우
										{
											// 일정 종료 세팅
											
											$("#myendDate").val(end.format().substring(0,10)); // 종료
																								// 날짜
																								// 삽입
											$("#myendTime").val(end.format().substring(11,19));
										}
										
										// 일정 시작 세팅
										$("#mystartDate").val(startDate); 
										$("#mystartTime").val(startTime);
										}					
							
										// 모달창 팝업
										$("#mySche").modal();
									}
								
								},
							
							
							// 일자 클릭 시 일정 추가 가능
								dayClick: function (start, end, title, location, memo, allDay, jsEvent, view) {
									var moment = $('#calendar').fullCalendar('getDate');
									$(".editScheBtn").hide();		// 편집 버튼들을 숨긴다.
							    	$(".addScheBtn").show();		// 수정 버튼들을 보이게 한다.
							    	
							    	$("#menuName").text("가족 일정 추가하기");	// 제목을 일정 편집하기로 수정한다.
							    	$("input").attr("readonly",false);	// 수정이 가능하게 한다.
							    	
							    	document.all["alarmSelec"].disabled=false;
							    	document.all["repeatSelec"].disabled=false;
							    	
	//								changeReadonly(2); // 편집 가능 상태로 전환
									
									$('#scheduleCode').val("");
									$('#scheduleTitle').val("");							   
								    $("#startDate").val(start.format('YYYY-MM-DD'));
								    $("#startTime").val(start.format('HH:mm:ss'));
								    $("#endDate").val("");
								    $("#endTime").val("");
								    $("#schedulePlace").val("");
								    $("#allDayBtn").attr("checked",false);
								    $("#repeatSelec").val(1);
								    $("#eventSelecBtn").attr("checked",false);
								    $("#memo").val("");						    
								    $("#eventQ").val("");
								    $("#ourSche").modal();	
									
	//								addSchedule();
								    
								},
							
								/*
								 * eventRender: function (event, element, view) { var
								 * date = new Date(); //this is your todays date
								 * 
								 * if (event.start >= date)
								 * $(element).css("backgroundColor", "red");
								 *  }
								 */
								
	//							eventRender: function(event, element, view) {
	//								element.attr("eventRequest", eventRequest)
	//						}
							});
					 
				 /*------------------------------캘린더 세팅 끝 --------------------*/
			
				// 읽기 상태 전환하기
				function changeReadonly(num){
					
					if(num==0) // 0 : 읽기 / 1 : 수정하기 / 2: 추가하기
					{
						$('#scheduleTitle').attr("readonly",true);
					    $("#schedulePlace").attr("readonly",true);
					    $("#startDate").attr("readonly",true);
					    $("#startTime").attr("readonly",true);
					    $("#endDate").attr("readonly",true);
					    $("#endTime").attr("readonly",true);
					    $("#memo").attr("readonly",true);
					    $("#allDayBtn").attr("disabled",true);
					    $("#repeatSelec").attr("readonly",true);
					    
					    $(".addScheBtn").css("display", "none");
					    $(".editScheBtn").css("display","display");	
					}
					else if(num==1)
					{
						$('#scheduleTitle').attr("readonly",false);
					    $("#schedulePlace").attr("readonly",false);
					    $("#startDate").attr("readonly",false);
					    $("#startTime").attr("readonly",false);
					    $("#endDate").attr("readonly",false);
					    $("#endTime").attr("readonly",false);
					    $("#memo").attr("readonly",false);
					    $("#allDayBtn").attr("disabled",false);
					    $("#repeatSelec").attr("readonly",false);
					    
					    $(".editScheBtn").css("display", "none");
					    $(".addScheBtn").css("display", "block");
					}
					else if(num==2)
					{
						$('#scheduleTitle').attr("readonly",false);
					    $("#schedulePlace").attr("readonly",false);
					    $("#startDate").attr("readonly",false);
					    $("#startTime").attr("readonly",false);
					    $("#endDate").attr("readonly",false);
					    $("#endTime").attr("readonly",false);
					    $("#memo").attr("readonly",false);
					    $("#allDayBtn").attr("disabled",false);
					    $("#repeatSelec").attr("readonly",false);
					    
					    $(".editScheBtn").css("display", "none");
					    $(".addScheBtn").css("display", "block");
					}
				};
				
				// 수정 버튼이 클릭되면 수정 가능 상태로 전환 및 수정완료 버튼 등록
//				$("#modifyBtn").click(function(){
//					
//					changeReadonly(1);
//					$("#modifyOkBtn").css("display", "block");
//					$("#modifyBtn").css("display", "none");
//				});
//				
				// OK 버튼이 클릭되면 일정등록
//				$("#okBtn").click(function(){
//					
//					$.ajax({
//						type:"POST",
//				        url:"famScheduleInsert.do",
//				        data:$("#form-familySchedule").serialize(),
//				        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
//				        success : function(result){
//				        	var newEvent = {
//			        			id : result.id,
//								title : result.title,							   
//							    start : moment(result.start, "YY-MM-DD HH:mm"),					    	
//							    end : moment(result.end, "YY-MM-DD HH:mm"),
//							    place : result.place,
//							    // $("#endTime").val("");
//							    allDay : result.allDay,
//							    backgroundColor : result.backgroundColor,
//							    eventRequest : event.eventRequest,
//							    alarm : result.alarm,
//							    memo : result.memo,							    
//				        	};
//				        	$('#calendar').fullCalendar( 'renderEvent', newEvent, 'stick' );
//				        	$('#scheduleCode').val(result.id);
//				        	alert($('#scheduleCode').val());
//				        	alert("일정추가가 완료되었습니다.");
//							$("#ourSche").modal("hide");
//							},
//						error : function(msg){
//							alert(msg);
//						}
//					});
//				});
				
				// 버튼 처리 시작
			    $("#editFamScheBtn").click(function(){	// 일정추가 버튼 클릭 시, 모달창 나옴.
			    	$(".editScheBtn").show();		// 편집 버튼 들을 다시 보이게 한다.
			    	$(".addScheBtn").hide();		// 수정 버튼들은 다시 숨긴다
			    	
			    	$("#menuName").text("가족 일정 상세보기");	// 제목을 일정 상세보기로 수정한다.
			    	$("input").attr("readonly",true);	// 수정이 불가능하도록 고친다.
				
			    	document.all["alarmSelec"].disabled=true;	// select 수정 불가능하게 바꾸는거.
			    	document.all["repeatSelec"].disabled=true;
			    	
			    	$("#ourSche").modal();
			    });
			    
			    
			    /*---------------------- 일정 삭제 ---------------------------*/
								
				$("#deleteBtn").click(function(){
					
					var check = confirm("삭제하시겠습니까?");
					var scheduleCode = event.id;
					//alert("스케쥴 코드 : " + scheduleCode);
					if(check==true)
					{	
						$.ajax({
							type:"GET",
					        url:"famScheduleDelete.do",
					        data:"scheduleCode="+scheduleCode,
					        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
					        success : function(result){
					        	//alert(result);
								$('#calendar').fullCalendar('removeEvents', result);			        	
								$("#ourSche").modal("hide");
								alert("일정이 삭제되었습니다.");
								},
							error : function(msg){
								alert(msg);
							}
						});
					}
				});
				
				
				$("#modifyOkBtn").click(function(){ // 수정
					// 현재 선택된 이벤트에 변경
//					$.ajax({
//						type:"POST",
//				        url:"famScheduleUpdate.do",
//				        data:$("#schedule-form").serialize(),
//				        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
//				        success : function(result){
//				        	
////			        			event.id = result.id;
//							event.title = result.title;
//						    event.place = result.place;
//						    event.start = moment(result.start, "YY-MM-DD HH:mm");					    	
//						    event.end = moment(result.end, "YY-MM-DD HH:mm"); 
//						    event.allDay = result.allDay;
//						    event.memo = result.memo;
//						    event.backgroundColor = result.backgroundColor;
//						    event.eventRequest = result.eventRequest;
//						    event.alarm = result.alarm;
//						    event.loaded = true;
//						    event.isActive = true; 
//						    event.status = 0; // ie in progress
//						    
//				        	alert("타이틀 : " + event.title);
//							$('#calendar').fullCalendar('updateEvent', event);						
//							$('#calendar').fullCalendar('renderEvent', event);
//							$('#calendar').fullCalendar( 'refetchEvents' );
//							$("#ourSche").modal("hide");									
//							
//							},
//						error : function(msg){
//							alert(msg);
//						}
//					});
					
				});
				
				 /* 확인버튼, 제목에 따라 호출하는 메소드가 달라지도록 설정 */
			    $("#okBtn").click(function() {		// 등록버튼.
			    	if($("#scheduleTitle").val() == "")		// 제목을 입력하지 않았을 경우.
			    	{
			    		window.alert("제목을 입력해주세요.");	
			    	}
			    	else if($("#eventSelecBtn").prop("checked"))
			    	{
			    		if($("#eventQ").val() == "")
			    		{
			    			window.alert("요청할 질문을 입력해주세요.");
			    		}
			    	}
			    	else
			    	{
			    		if($("#allDayBtn").prop("checked"))		//체크가 되어 있다면
			        	{
			        		$("#startTime").val("00:01");	// 하루종일 클릭시 일정 시간을 00:00~23:59 로 설정. (임시)
			        		$("#endTime").val("23:59");   		
			        	}
			    		if($("#menuName").text() == "가족 일정 추가하기")
			    		{
			    			$.ajax({
								type:"POST",
						        url:"famScheduleInsert.do",
						        data:$("#form-familySchedule").serialize(),
						        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
						        success : function(result){
						        	var newEvent = {
					        			id : result.id,
										title : result.title,							   
									    start : moment(result.start, "YY-MM-DD HH:mm"),					    	
									    end : moment(result.end, "YY-MM-DD HH:mm"),
									    place : result.place,
									    // $("#endTime").val("");
									    allDay : result.allDay,
									    backgroundColor : result.backgroundColor,
//									    eventRequest : event.eventRequest,
									    alarm : result.alarm,
									    memo : result.memo,							    
						        	};
						        	$('#calendar').fullCalendar( 'renderEvent', newEvent, 'stick' );
						        	$('#scheduleCode').val(result.id);
						        	//alert("일정 코드 :" + $('#scheduleCode').val());
						        	alert("일정추가가 완료되었습니다.");
									$("#ourSche").modal("hide");
									},
								error : function(msg){
								//	alert(msg);
								}
							});
			    			//window.alert($("#startDate"));
			    			
			    			//$("#form-familySchedule").attr("action","famSchedule_insert.do");
			    			//$("#form-familySchedule").submit();
			    		}
			    		if($("#menuName").text() == "가족 일정 편집하기")
			    		{
			    			$.ajax({
								type:"POST",
						        url:"famScheduleUpdate.do",
						        data:$("#form-familySchedule").serialize(),
						        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
						        success : function(result){
						        	
					        		event.id = result.id;
									event.title = result.title;
								    event.place = result.place;
								    event.start = moment(result.start, "YY-MM-DD HH:mm");					    	
								    event.end = moment(result.end, "YY-MM-DD HH:mm"); 
								    event.allDay = result.allDay;
								    event.memo = result.memo;
								    event.backgroundColor = result.backgroundColor;
//								    event.eventRequest = result.eventRequest;
								    event.alarm = result.alarm;
								    event.loaded = true;
								    event.isActive = true; 
								    event.status = 0; // ie in progress
								    
									$('#calendar').fullCalendar('updateEvent', event);						
									$('#calendar').fullCalendar('renderEvent', event);
									$('#calendar').fullCalendar( 'refetchEvents' );
									$("#ourSche").modal("hide");									
									
									},
								error : function(msg){
								//	alert(msg);
								}
			    			});
			    		}	
			    	}
			    });
			
			    $("#cancelBtn").click(function(){	//취소 버튼 클릭 시,
			    	if($("#menuName").text() == "가족 일정 편집하기")
			    	{
			    		$(".editScheBtn").show();		// 편집 버튼 들을 다시 보이게 한다.
			        	$(".addScheBtn").hide();		// 수정 버튼들은 다시 숨긴다
			        	
			        	$("#menuName").text("가족 일정 상세보기");	// 제목을 일정 상세보기로 수정한다.
			        	$("input").attr("readonly",true);	// 수정이 불가능하도록 고친다.
			    	
			        	document.all["alarmSelec"].disabled=true;	// select 수정 불가능하게 바꾸는거.
			        	document.all["repeatSelec"].disabled=true;
			        	
			        	//alert("가족 일정 수정하기에서 취소 버튼 클릭");
			    	}
			    	else if($("#menuName").text() == "가족 일정 추가하기")
			    	{
			    		 $("#ourSche").modal("hide");
			    		// alert("가족 일정 추가하기에서 취소 버튼 클릭");
			    	}  
			    	else if ($("#menuName").text() == "가족 일정 상세보기")
			    	{
			    		 $("#ourSche").modal("hide");
			    		// alert("가족 일정 상세보기에서 취소 버튼 클릭");
			    	}	
			    	else
			    	{
			    		$("#mySche").modal("hide");
			    	}
			    
			    });
			    
				
			    $("#modifyBtn").click(function(){	// 수정 버튼 클릭 시,
			    	
			    	$(".editScheBtn").hide();		// 편집 버튼들을 숨긴다.
			    	$(".addScheBtn").show();		// 수정 버튼들을 보이게 한다.
			    	
			    	$("#menuName").text("가족 일정 편집하기");	// 제목을 일정 편집하기로 수정한다.
			    	$("input").attr("readonly",false);	// 수정이 가능하게 한다.
			    	
			    	document.all["alarmSelec"].disabled=false;
			    	document.all["repeatSelec"].disabled=false;
			    });
				
				$("#allDayBtn").click(function(){		//하루종일 버튼 클릭 시 동작.
			    	
			    	if($("#menuName").text()=="가족 일정 상세보기")		//상세보기 모드일 경우에는 편집이 되지 않도록.
					{
						return false;	
					}
			    	
			    	if($("#allDayBtn").prop("checked"))		//체크가 되어 있다면
			    	{
				  		$("#startTime").hide();				// 시간 관련 input태그들을 안보이게 하고
			    		$("#endTime").hide();
			    		$("#startDate").css('width','300px');	// 날짜 관련 input 태그들의 길이를 늘린다.
			    		$("#endDate").css('width','300px'); 		
			    	}
			    	else
			    	{
			    		$("#startTime").show();					//아니라면 원래대로 복구.
			    		$("#endTime").show();
			    		$("#startDate").css('width','205px');
			    		$("#endDate").css('width','205px');
			    	}
			    });
				
				function addSchedule()
				{
					$(".editScheBtn").hide();		// 편집 버튼들을 숨긴다.
			    	$(".addScheBtn").show();		// 수정 버튼들을 보이게 한다.
			    	
			    	$("#menuName").text("가족 일정 추가하기");	// 제목을 일정 편집하기로 수정한다.
			    	$("input").attr("readonly",false);	// 수정이 가능하게 한다.
			    	
			    	document.all["alarmSelec"].disabled=false;
			    	document.all["repeatSelec"].disabled=false;
			    	
					var date = new Date();
			    	var d = date.getDate();
			        var m = date.getMonth()+1;
			        var y = date.getFullYear();
			        
					if(d < 10){
			            d = "0"+d;
			        };
			        if(m < 10){
			            m = "0"+m;
			        };
			        
					var dateChar = y + '-' + m + '-' + d;
					
					var hour = date.getHours();
					var min = date.getMinutes();
					
					if(hour < 10){
						hour = "0"+hour;
					};
					if(min < 10){
						min = "0"+hour;
					};
			        
					var time = hour + ":" + min;
			        
					

					$('#scheduleCode').val("");
					$('#scheduleTitle').val("");							   
				    $("#schedulePlace").val("");
				    $("#allDayBtn").attr("checked",false);
				    $("#repeatSelec").val(1);
				    $("#eventSelecBtn").attr("checked",false);
				    $("#memo").val("");						    
				    $("#eventQ").val("");
				
					
					$("#startDate").val(dateChar);
					$("#endDate").val(dateChar);
			    	
					$("#startTime").val(time);
					$("#endTime").val("23:59");
					
					$("#ourSche").modal();
				}
				
				
				$("#eventSelecBtn").change(function(){
					if($("#eventSelecBtn").prop("checked"))
					{
						$("#eventDiv").css("display","block");
					}
					else
					{
						$("#eventDiv").css("display","none");
					}
				});
				
				$("#scheduleMatchigBtn").click(function(){
					$("#matchingModal").modal();
				});
	});
										
	
