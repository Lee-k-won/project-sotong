$(document).ready(function(){
		
	//현재 날짜 불러오는 코드========================================================
		var now = new Date();
	    var year= now.getFullYear();
	    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	            
	    var chan_val = year + '-' + mon + '-' + day;
	    $('#schedule-date').val(chan_val);
	//현재 날짜 불러오는 코드========================================================
	//목록 이벤트 처리 ============================================================
	
		// ^버튼 클릭 시 호출되는 이벤트 함수이다.
		$('#up-btn').on('click',function() {
			alert("위로 가기 버튼 클릭함");
		});
		
		// v버튼 클릭 시 호출되는 이벤트 함수이다.
		$('#down-btn').on('click',function() {
			alert("내려가기 버튼 클릭함");
		});
		
		// 날짜를 클릭시 호출되는 이벤트 함수이다.
		$('#wish-list-list-title-btn').on('click',function(){
			var modifyDate = $('#wish-list-list-title-btn').val();
			alert(modifyDate);
		});
		
	//목록 이벤트 처리 ============================================================
	//게시글 마우스 이베튼 처리 =======================================================
		$('#add-photo-btn').hover(function(){
			//마우스를 삭제 버튼으로 올리시 이베튼 발생
			$("#add-photo-btn").css("display", "none");
			$("#add-photo-letter").css("display", "block");
		});
		$('#back-btn').hover(function(){
			//마우스를 수정 버튼으로 올리시 이베튼 발생
			$("#back-btn").css("display", "none");
			$("#back-letter").css("display", "block");
		});
		$('#ok-btn').hover(function(){
			//마우스를 글쓰기 버튼으로 올리시 이베튼 발생
			$("#ok-btn").css("display", "none");
			$("#ok-letter").css("display", "block");
		});
		$('#emoticon-btn').hover(function(){
			//마우스 이모티콘 버튼에 올리 시 이벤트 발생
			$("#emoticon-btn").css("display", "none");
			$("#emoticon-letter").css("display", "block");
		});
		$('#add-photo-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#add-photo-letter").css("display", "none");
			$("#add-photo-btn").css("display", "block");
		});
		$('#back-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#back-letter").css("display", "none");
			$("#back-btn").css("display", "block");		
		});
		$('#ok-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#ok-letter").css("display", "none");
			$("#ok-btn").css("display", "block");
		});
		$('#emoticon-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#emoticon-letter").css("display", "none");
			$("#emoticon-btn").css("display", "block");
		});		
	//게시글 마우스 이베튼 처리 =======================================================	
	//게시글 클릭 이베튼 처리 ========================================================
		
		//사진 추가 버튼 클릭시 호출 되는 함수 
		$('#add-photo-letter').on('click', function(){
			$("#add-photo-letter").css("display", "none");
			$("#add-photo-btn").css("display", "block")
			alert("사진 버튼이 클릭 되었습니다.");
		});
		
		//돌아가기 버튼 클릭 시 호출 되는 함수 
		$('#back-letter').on('click', function(){
			window.location.href = "WishList.jsp";
		});
		
		//확인 버튼 클릭 시 호출 되는 함수
		$('#ok-letter').on('click',function(){
			okBtn(chan_val);
		});
		
		//이모티콘 클릭 시 호출 되는 함수
		$('#emoticon-letter').on('click',function(){
			alert('이모티콘이 클릭 되었습니다.');
		});	
	//게시글 클릭 이베튼 처리 ========================================================
});

//확인 버튼 클릭 시 호출되는 함수
function okBtn(chan_val) {
	$("#ok-letter").css("display", "none");
	$("#ok-btn").css("display", "block");
	
	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
    var month1 =  now.getMonth()+4; //2015 - 14 - 13
    
    alert(month1);
    
    var monaa = year + '-' + month1 + '-' + day;
    if(month1 > 12) {
    	alert('넘어온다.');
    	year = now.getFullYear() + 1;
		switch(month1) {
			case 13:
				mon = '0' + 1;
				break;
			case 14:
				mon = '0' + 2;
				break;
			case 15:
				mon = '0' + 3;
				break;
		}
		monaa = year + '-' + mon + '-' +day
		alert(monaa);
	}   
	var scheduleDate = $('#schedule-date').val();
	
	var subDate = scheduleDate.substring(5,7);
	var mooooo = parseInt(subDate) + 3;
	
	alert(scheduleDate);
	alert(writeDate1);
	
	if (chan_val > scheduleDate) {
		alert("작성 날짜 보다 이후여야 합니다.");
	} else if (monaa < scheduleDate) {
		alert("3개월 이내 밖에 저장이 안됩니다.");
	} else if (scheduleDate > writeDate1) {
	alert('수정');
		if(mooooo > 12) {
			switch(mooooo) {
				case 13:
					alert('여기를 1년증가 1월');
					break;
				case 14:
					alert('여기를 1년 증가 2월');
					break;
				case 15:
					alert('여기를 1년 증가 3월');
					break;
				default:
					var name = $('#modify-name').val();
					var modifyDate = $('#modify-date').val();
					var wishListTitle = $('#modify-title').val();
					var wishListContent = $('#wish-list-board-textarea').val();
					
					//alert("확인 버튼이 클리 되었습니다.");
					alert("작성자 : " + name + "\n" + "작성 날짜 : " + modifyDate + "\n" + 
							"제목 : " + wishListTitle + "\n" + "내용 : " +wishListContent +
							"\n" + "일정 등록 : " + scheduleDate);
			}
		}
	} else {
		alert('else');
		var name = $('#modify-name').val();
		var modifyDate = $('#modify-date').val();
		var wishListTitle = $('#modify-title').val();
		var wishListContent = $('#wish-list-board-textarea').val();
		
		//alert("확인 버튼이 클리 되었습니다.");
		alert("작성자 : " + name + "\n" + "작성 날짜 : " + modifyDate + "\n" + 
				"제목 : " + wishListTitle + "\n" + "내용 : " +wishListContent +
				"\n" + "일정 등록 : " + scheduleDate);
	}
	
	$('#modify-title').val('');
	$('#wish-list-board-textarea').val('');
	$('#schedule-date').val('');
	
}
