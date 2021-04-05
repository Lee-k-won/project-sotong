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
			var writeDate = $('#wish-list-list-title-btn').val();
			alert(writeDate);
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
			$("#emoticon-letter").css("display", "none");
			$("#emoticon-btn").css("display", "block");
			alert('이모티콘이 클릭 되었습니다.');
		});	
	//게시글 클릭 이베튼 처리 ========================================================
});

//확인 버튼 클릭 시 호출되는 함수
function okBtn(chan_val) {
	$("#ok-letter").css("display", "none");
	$("#ok-btn").css("display", "block");
	
	var scheduleDate = $('#schedule-date').val();
	if (chan_val > scheduleDate) {
		alert("작성 날짜 보다 이후여야 합니다.");
	} else {
		var name = $('#write-name').val();
		var writeDate = $('#write-date').val();
		var wishListTitle = $('#write-title').val();
		var wishListContent = $('#wish-list-board-textarea').val();
		
		//alert("확인 버튼이 클리 되었습니다.");
		alert("작성자 : " + name + "\n" + "작성 날짜 : " + writeDate + "\n" + 
				"제목 : " + wishListTitle + "\n" + "내용 : " +wishListContent +
				"\n" + "일정 등록 : " + scheduleDate);
	}
	
	$('#write-title').val('');
	$('#wish-list-board-textarea').val('');
	$('#schedule-date').val('');
}
