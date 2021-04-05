$(document).ready(function(){
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
		$('#family-diary-list-ul .family-diary-list .family-date-list').on('click',function() {
			var index = $('#family-diary-list-ul .family-diary-list .family-date-list').index(this);
			
			$('#famly-diary-list-form' + index).submit();				
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
			$("#back-letter").css("display", "none");
			$("#back-btn").css("display", "block");	
			alert("돌아 가기 버튼이 클리 되었습니다.");
		});
		
		//확인 버튼 클릭 시 호출 되는 함수
		$('#ok-letter').on('click',okBtn);
		
	//게시글 클릭 이베튼 처리 ========================================================
});

//확인 버튼 클릭 시 호출되는 함수
function okBtn() {
	$("#form-familyDiary-modify").submit();
}
