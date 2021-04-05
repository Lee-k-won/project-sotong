
$(document).ready(
		function() {
		
			
			$("#add-schedule").click(function(){
				$("#mySche").modal();
			});

			// 현재 년,월,일
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
			var h = date.getHours();
			var min = date.getMinutes();
			
			var event;
			var eventId;
			
			 $('#calendar').fullCalendar(
					{
// theme : true,
						header : {
							left : 'today prev,next', // 왼쪽
							center : 'title', // 가운데 타이틀
							right : 'month,agendaWeek,agendaDay' // 오른쪽 버튼 순서
						},
						
						allDaySlot : true,
// events : {
// url : 'http://192.168.0.25:8089/so_tong/schedule.do',
// type : 'POST'
// },
//						 events : function(start, end, timezone, callback) {
//							 $.ajax({
//								  cache: true,
//								  url: 'scheduleList.do',
//								  dataType: "json",
//								  contentType: "application/json; charset=utf-8",
//								  data: { month: start.format(), year: start.format() + 1900 },
//								  success: function(response) {
//									  var myevents = [];
//									  $.each(response, function(i, task) {
//					              myevents.push({
//					                  start: task.start,
//					                  end: task.end,
//					                  allDay: false,
//					                  title: task.title//,
//					                  //color: task.event.color
//					              });
//					              callback(myevents);
//					          });
//								  },
//								  error: function() {
//							            alert('There was an error while fetching events!');
//							      }					
//								 								 
//								});
//							
//						 },
						eventLimit: true,
						/*
						events : [{"title":"아버지 생신♥","start":"2015-08-03T11:30:00+09:00","end":"2015-08-03T01:20:00+09:00"},
						          {"title":"동생 생일♥","start":"2015-08-04T15:10:00+09:00","end":"2015-08-04T04:20:00+09:00"},
						          {"title":"홍춘 생일★","start":"2015-08-02T18:20:00+09:00","end":"2015-08-02T06:20:00+09:00"},
						          {"title":"달봉 생일★","start":"2015-08-17T20:10:00+09:00","end":"2015-08-17T07:25:00+09:00"},
						          {"title":"파이널 프로젝트 발표","start":"2015-08-15T20:10:00+09:00","end":"2015-08-15T07:25:00+09:00"}],
						*/
						
						events: function(start, end, timezone, callback) {
							[{ id : 1, title : '철연이 일시키는 날', start : new Date(y, m, 1), memo : '철연아 자니?', description : '테스트' },
					        $.ajax({
					            url: 'scheduleList.do',					           
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
					        })
					        ];
						},
						
						/*
						 * eventRender: function (event, element) {
						 * element.attr('href', 'javascript:void(0);');
						 * element.click(function() {
						 * $("#startTime").html(moment(event.start).format('MMM
						 * Do h:mm A'));
						 * $("#endTime").html(moment(event.end).format('MMM Do
						 * h:mm A')); $("#eventInfo").html(event.description);
						 * $("#eventLink").attr('href', event.url); //
						 * $("#eventContent").dialog({ modal: true, title:
						 * event.title, width:350}); }); element.qtip({ content :
						 * event.place });
						 * 
						 * element.qtip({ content : event.memo });
						 * 
						 *  },
						 */
						
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
											
						
						// 일정을 클릭했을 경우
						
						/*-- 일정 상세보기 --*/
						eventClick : function(calEvent, jsEvent, view)
						{							
							event = calEvent;
							eventId = calEvent.id;
							changeReadonly(0); // 편집 불가능 상태로 전환
							
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
							
							// 하루종일 설정
							
							if(calEvent.allDay == true) // 하루종일 체크가 되어있으면
							{
								$("#allDayBtn").prop("checked", true);
								$("#allDayBtn").attr("disabled", true);
								$("#startDate").val(startDate); 
								$("#startTime").val(null);
								
								$("#endDate").val(startDate); // 종료 date에 시작
																// 날짜 삽입
								$("#endTime").val(null);
							}
							else
							{
								$("#allDayBtn").prop("checked", false);
								$("#allDayBtn").attr("disalbed", true);
							
							
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
							$("#mySche").modal();
						},
						
						
						// 일자 클릭 시 일정 추가 가능
						dayClick: function (start, end, title, location, memo, allDay, jsEvent, view) {
// var moment = $('#calendar').fullCalendar('getDate');
							changeReadonly(2); // 편집 가능 상태로 전환
							
							$('#scheduleCode').val("");
							$('#scheduleTitle').val("");
						    $("#schedulePlace").val("");
						    $("#startDate").val(start.format('YYYY-MM-DD'));
						    $("#startTime").val(start.format('HH:mm:ss'));
						    $("#endDate").val("");
						    $("#endTime").val("");
						    $("#allDayBtn").attr("checked",false);
						    $("#memo").val("");						    
						    $("#selectedFam").text("없음");
						    $("#mySche").modal();					    
						    
						},
						
						/*
						 * eventRender: function (event, element, view) { var
						 * date = new Date(); //this is your todays date
						 * 
						 * if (event.start >= date)
						 * $(element).css("backgroundColor", "red");
						 *  }
						 */
						
						eventRender: function(event, element, view) {
						}
					});

// if ((event.description).toString() == "테스트")
// {
// element.find(".fc-event-time").after($("<span
// class=\"fc-event-icons\"></span>").html("<img src=\"/img/myhome/profile.jpg\"
// width='20px'/>" ));
// }
// element.qtip({
// content: event.description,
// position: { corner: { tooltip: 'bottomLeft', target: 'topRight'} },
// style: {
// border: {
// width: 1,
// radius: 3,
// color: '#2779AA'
//				 
// },
// padding: 10,
// textAlign: 'center',
// tip: true, // Give it a speech bubble tip with automatic corner detection
// name: 'cream' // Style it according to the preset 'cream' style
// });
							
		
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
				    $("#selectedFam").attr("readonly",true);
				    $("#alarmSelec").attr("readonly",true);
				    
				    $(".addScheBtn").css("display", "none");
				    $(".editScheBtn").css("display","none");
				    $(".detailScheBtn").css("display", "block");				    
				    
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
				    $("#selectedFam").attr("readonly",false);
				    $("#alarmSelec").attr("readonly",false);
				    
				    $(".detailScheBtn").css("display", "none");
				    $(".editScheBtn").css("display", "block");
				    $(".addScheBtn").css("display", "none");
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
				    $("#selectedFam").attr("readonly",false);
				    $("#alarmSelec").attr("readonly",false);
				    
				    $(".detailScheBtn").css("display", "none");
				    $(".editScheBtn").css("display", "none");
				    $(".addScheBtn").css("display", "block");
				}
			};
			
			// 수정 버튼이 클릭되면 수정 가능 상태로 전환 및 수정완료 버튼 등록
			$("#modifyBtn").click(function(){
				
				changeReadonly(1);
				$("#modifyOkBtn").css("display", "block");
				$("#modifyBtn").css("display", "none");
			});
			
			
			
			/*--- 일정 추가 ----*/
			// OK 버튼이 클릭되면 일정등록
			$("#okBtn").click(function(){
				
				var startDate = $("#startDate").val();
				var startTime = $("#startTime").val();
// var $start = new Date(startDate.substring(0,2), startDate.substring(3,5),
// startDate.substring(6,8));
				var startFull = new Date(startDate.substring(0,4),startDate.substring(5,7)-1,startDate.substring(8,10),startTime.substring(0,2),startTime.substring(3,5));
				
// alert(startTime.substring(0,2))
				var endDate = $("#endDate").val();
				var endTime = $("#endTime").val();
				var endFull = new Date(endDate.substring(0,4),endDate.substring(5,7)-1,endDate.substring(8,10),endTime.substring(0,2),endTime.substring(3,5));
				
				var allDayCheck = $("#allDayBtn").is(":checked");
				
				$.ajax({
					type:"POST",
			        url:"addSchedule.do",
			        data:$("#schedule-form").serialize(),
			        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
			        success : function(result){
			        	var newEvent = {
		        			id : result.id,
							title : result.title,
						    place : result.place,
						    start : moment(result.start, "YY-MM-DD HH:mm"),					    	
						    end : moment(result.end, "YY-MM-DD HH:mm"),
						    // $("#endTime").val("");
						    allDay : result.allDay,
						    memo : result.memo,
						    backgroundColor : result.backgroundColor,
			        	};
			        	$('#calendar').fullCalendar( 'renderEvent', newEvent, 'stick' );
			        	$('#scheduleCode').val(result.id);
			        	alert($('#scheduleCode').val());
			        	alert("일정추가가 완료되었습니다.");
						$("#mySche").modal("hide");
						},
					error : function(msg){
						alert(msg);
					}
				});
				
//				var newEvent = {	
//						id : $('#scheduleId').val(),
//						title : $('#scheduleTitle').val(),
//					    place : $("#schedulePlace").val(),
//					    start : startFull,					    	
//					    end : endFull,
//					    // $("#endTime").val("");
//					    allDay : allDayCheck,
//					    memo : $("#memo").val(),
//					    color : $("#memberColor"),
//					    eventBackgroundColor : "red",
////					    repeatSelec : $("#repeatSelec").text(),
////					    selectedFam : $("#selectedFam").text(),
////					    alarmSelec : $("#alarmSelec").text()					    
//					    
//				};
//				$('#calendar').fullCalendar( 'renderEvent', newEvent, 'stick' );
//				alert("일정추가가 완료되었습니다.");
//				$("#mySche").modal("hide");
				
			});
			
			
			// 취소 버튼 클릭
			$("#cancelBtn").click(function(){
				
			$("#mySche").modal("hide");
				
			});
			
			// 닫기 버튼 클릭
			$("#backBtn").click(function(){ 
				
				changeReadonly(0);				
			});
			
			
			
			/*-- 일정 삭제 --*/
			// 삭제 버튼 클릭
			$("#deleteBtn").click(function(){
				
				var check = confirm("삭제하시겠습니까?");
				var scheduleCode = event.id;
				alert("스케쥴 코드 : " + scheduleCode);
				if(check==true)
					{	
					$.ajax({
						type:"GET",
				        url:"deleteSchedule.do",
				        data:"scheduleCode="+scheduleCode,
				        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
				        success : function(result){
				        	
							$('#calendar').fullCalendar('removeEvents', result);			        	
							$("#mySche").modal("hide");
							alert("일정이 삭제되었습니다.");
							},
						error : function(msg){
							alert(msg);
						}
					});
		           
					}
			});
			
			
			/*-- 일정 수정 --*/
			// 수정 버튼 클릭
			$("#modifyOkBtn").click(function(){ // 수정
				alert("수정ㄷ버튼 눌러떵");
				// 현재 선택된 이벤트에 변경
				$.ajax({
					type:"POST",
			        url:"modifySchedule.do",
			        data:$("#schedule-form").serialize(),
			        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
			        success : function(result){
			        	
//	        			event.id = result.id;
						event.title = result.title;
					    event.place = result.place;
					    event.start = moment(result.start, "YY-MM-DD HH:mm");					    	
					    event.end = moment(result.end, "YY-MM-DD HH:mm"); 
					    event.allDay = result.allDay;
					    event.memo = result.memo;
					    event.backgroundColor = result.backgroundColor;
					    event.loaded = true;
					    event.isActive = true; 
					    event.status = 0; // ie in progress
					    
//					    alert("일정코드 : " + event.id + "시작시간 : " + event.start);
			        	alert("타이틀 : " + event.title);
						$('#calendar').fullCalendar('updateEvent', event);						
						$('#calendar').fullCalendar('renderEvent', event);
						$('#calendar').fullCalendar( 'refetchEvents' );
						$("#mySche").modal("hide");									
						
						},
					error : function(msg){
						alert(msg);
					}
				});
				
				/*$.ajax({
					type:"POST",
			        url:"modifySchedule.do",
			        data:$("#schedule-form").serialize(),
			        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
			        success : function(result){
			        	alert(msg);
			        },
					error : function(msg){
						event.id = result.id;
						event.title = result.title;
					    event.place = result.place;
					    event.start = moment(result.start, "YY-MM-DD HH:mm");					    	
					    event.end = moment(result.end, "YY-MM-DD HH:mm");
					    // $("#endTime").val("");
					    event.allDay = result.allDay;
					    event.memo = result.memo;
					    event.backgroundColor = result.backgroundColor;
					    event.loaded = true;
					    alert("일정코드 : " + event.id + "시작시간 : " + event.start);
			        	
						$('#calendar').fullCalendar('updateEvent', event);						
						
						$("#mySche").modal("hide");
						alert("일정수정이 완료되었습니다.");
						
						$('#calendar').fullCalendar('renderEvent', 'scheduleList.do');
					}
					
				});*/
			
			});
			
			// 전체 선택 체크, 해제
			$("#checkAll").change(function(){				
				  if($(this).prop("checked"))
			       {
					  var selectedFam = "";
					  $(".selectFam").each(function(){
						  selectedFam += $(this).text()+"";
					  });
					  $(".selectFam").prop('checked', true);
					  $("#selectedFam").text(selectedFam);
			       }
			        else 
			        	{
			        	 $("#selectedFam").text("없음");
			        	}
			    });
			
			  $('.dropdown-menu li a').click(function(){
				  var selectedFam = "";
				  if($(".selectFam").prop('checked'))
					  {
					  	selectedFam = selectedFam + $(this).siblings("a").text();
					  }
				$("#selectedFam").text(selectedFam);
			});
			
			$('#alarmSelec').change(function(){
				
			});
		});
	