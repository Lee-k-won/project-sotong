$(document).ready(function(){
	//게시글 목록 버튼 이벤트 처리 =====================================================
		$('#up-btn').on('click',function() {
			alert("위로 가기 버튼 클릭함");
		});
		
		$('#down-btn').on('click',function() {
			alert("내려가기 버튼 클릭함");
		});
		
		$('#diary-list-ul .diary-list .my-diary-list-title-btn').on('click',function() {
			var index = $('#diary-list-ul .diary-list .my-diary-list-title-btn').index(this);

			$('#form-diary-read' + index).submit();				
		});
		
	//게시글 목록 버튼 이벤트 처리 =====================================================

	
	//게시글 마우스 이베튼 처리 =======================================================
		$('#my-diary-board-okBtn').hover(function(){
			//마우스를 삭제 버튼으로 올리시 이베튼 발생
			$('#my-diary-board-okBtn').css('display', 'none');
			$("#ok-letter").css("display", "block");
		});
		$('#my-diary-board-backBtn').hover(function(){
			//마우스를 수정 버튼으로 올리시 이베튼 발생
			$('#my-diary-board-backBtn').css('display', 'none');
			$("#back-letter").css("display", "block");
		});
		$('#my-diary-board-photoBtn').hover(function(){
			//마우스를 글쓰기 버튼으로 올리시 이베튼 발생
			$('#my-diary-board-photoBtn').css('display', 'none');
			$("#photo-letter").css("display", "block");
		});
		$('#ok-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#ok-letter").css("display", "none");
			$('#my-diary-board-okBtn').css('display', 'block');
			
		});
		$('#back-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#back-letter").css("display", "none");
			$('#my-diary-board-backBtn').css('display', 'block');
		});
		$('#photo-letter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#photo-letter").css("display", "none");
			$('#my-diary-board-photoBtn').css('display', 'block');
		});
	//게시글 마우스 이베튼 처리 =======================================================		
	
		
	//게시물 버튼 이벤트 처리 ========================================================
		$('#back-letter').on('click',modifyBtn);
			//마우스를 수정 버튼으로 콜릭 시 이베튼 발생
			
		$('#ok-letter').on('click',function(){
			
			$("#form-diary-modify").submit();
			
		});
	
	//게시물 버튼 이벤트 처리 ========================================================
});

function deleteBtn(){
	//마우스를 삭제 버튼으로 클릭 시 이베튼 발생
	$("#photo-letter").css("display", "none");
	$('#my-diary-board-photoBtn').css('display', 'block');
	
	var writeDate = $('#my-diary-board-read-write-date').val();
	var title = $('#my-diary-title').val();
	
	
	
	var result = confirm('정말로 삭제 하시겠습니까?' + "\n" + '삭제되면 복구가 불가능합니다.');
	 
     if(result) {
    	 alert("삭제가 완료되었습니다.");
    	 $('#my-diary-title').val("");
    	 $('#my-diary-board-read-write-date').val("");
    	 $('#my-diary-board-read-content-textarea').val("");
     } else {
    	 //no
     }	
}

function modifyBtn(){
	//마우스를 수정 버튼으로 콜릭 시 이베튼 발생
	
		
}