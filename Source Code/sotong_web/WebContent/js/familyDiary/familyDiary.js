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
		$('#write-btn').hover(function(){
			//마우스를 삭제 버튼으로 올리시 이베튼 발생
			$("#write-btn").css("display", "none");
			$("#write-letter").css("display", "block");
		});
		$('#modify-btn').hover(function(){
			//마우스를 수정 버튼으로 올리시 이베튼 발생
			$("#modify-btn").css("display", "none");
			$("#modify-letter").css("display", "block");
		});
		$('#delete-btn').hover(function(){
			//마우스를 글쓰기 버튼으로 올리시 이베튼 발생
			$("#delete-btn").css("display", "none");
			$("#delete-letter").css("display", "block");
		});
		$('#write-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#write-letter").css("display", "none");
			$("#write-btn").css("display", "block");
		});
		$('#modify-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#modify-letter").css("display", "none");
			$("#modify-btn").css("display", "block");		
		});
		$('#delete-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#delete-letter").css("display", "none");
			$("#delete-btn").css("display", "block");
		});
	//게시글 마우스 이베튼 처리 =======================================================
		
		
	//게시글 클릭 이베튼 처리 ========================================================
		
		//글쓰기 버튼 클릭시 호출 되는 함수 
		$('#write-letter').on('click', function(){

		});
		
		//수정 버튼 클릭 시 호출 되는 함수 
		$('#modify-letter').on('click', modifyBtn);
			
		//삭제 버튼 클릭 시 호출 되는 함수
		$('#delete-letter').on('click', deleteBtn);
		
	//게시글 클릭 이베튼 처리 ========================================================
		
	//댓글, 이모티콘 클릭 이벤트 처리 =========================================================
		//댓글 버튼 클릭 시 호출 되는 함수
		$('#comment-btn').on('click', commentBtn);
		
		//이모티콘 버튼 클릭 시 호출 되는 함수
		$('#comment-emoticon').on('click', function(){
			alert("이모티콘이 클릭 되었습니다.");
		});
	//댓글 클릭 이벤트 처리 =========================================================
});

//수정 버튼 클릭 시 호출 되는 함수 
function modifyBtn() {
	$("#form-familyDiary-update").submit();
}

//삭제 버튼 클릭 시 호출되는 함수
function deleteBtn() {
	$("#delete-letter").css("display", "none");
	$("#delete-btn").css("display", "block");
	var name = $('#profile-name').val();
	var content = $('#family-diary-board-textarea').val();
	alert(name + '\n' + content);
	$('#family-diary-board-textarea').val("");
	
	$("#form-familyDiary-update").attr("action","familyDiary_delete.do");
    $("#form-familyDiary-update").submit();
}

//댓글 버튼 클릭 시 호출 되는 함수
function commentBtn() {
	var commentConntent = $('#comment-content').val();
	var name = $('#profile-name').val();
	var content = $('#family-diary-board-textarea').val();
	alert(name + '\n' + content + '\n' + commentConntent);
	
	$('#comment-content').val("");
}