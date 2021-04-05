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
		$('#my-diary-board-deleteBtn').hover(function(){
			//마우스를 삭제 버튼으로 올리시 이베튼 발생
			$('#my-diary-board-deleteBtn').css('display', 'none');
			$("#deleteLetter").css("display", "block");
		});
		$('#my-diary-board-modifyBtn').hover(function(){
			//마우스를 수정 버튼으로 올리시 이베튼 발생
			$('#my-diary-board-modifyBtn').css('display', 'none');
			$("#modifyLetter").css("display", "block");
		});
		$('#my-diary-board-writeBtn').hover(function(){
			//마우스를 글쓰기 버튼으로 올리시 이베튼 발생
			$('#my-diary-board-writeBtn').css('display', 'none');
			$("#writeLetter").css("display", "block");
		});
		$('#deleteLetter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#deleteLetter").css("display", "none");
			$('#my-diary-board-deleteBtn').css('display', 'block');
			
		});
		$('#modifyLetter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#modifyLetter").css("display", "none");
			$('#my-diary-board-modifyBtn').css('display', 'block');
		});
		$('#writeLetter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#writeLetter").css("display", "none");
			$('#my-diary-board-writeBtn').css('display', 'block');
		});
	//게시글 마우스 이베튼 처리 =======================================================		
	
		
	//게시물 버튼 이벤트 처리 ========================================================
		$('#deleteLetter').on('click',deleteBtn);
			//마우스를 삭제 버튼으로 클릭 시 이베튼 발생
		
		$('#modifyLetter').on('click',modifyBtn);
			//마우스를 수정 버튼으로 콜릭 시 이베튼 발생
			
		$('#writeLetter').on('click',function(){
			//마우스를 글쓰기 버튼으로 클릭 시 이베튼 발생
			$("#writeLetter").css("display", "none");
			$('#my-diary-board-writeBtn').css('display', 'block');

		});
	
		
		$('.my-diary-list-title-btn').click(function(){
			$("#form-diary-read").submit();				
		})
	//게시물 버튼 이벤트 처리 ========================================================
});

function deleteBtn(){
	//마우스를 삭제 버튼으로 클릭 시 이베튼 발생
	$("#deleteLetter").css("display", "none");
	$('#my-diary-board-writeBtn').css('display', 'block');
	
	var writeDate = $('#my-diary-board-read-write-date').val();
	var title = $('#my-diary-title').val();
	

	if(confirm("정말로 삭제하시겠습니까?")==true)
	{
		$("#form-diary-update").attr("action","diary_delete.do");
	    $("#form-diary-update").submit();
	}
	else
	{
		//window.location.href = "letterbox.jsp";
	}   
}

function modifyBtn(){
	//마우스를 수정 버튼으로 콜릭 시 이베튼 발생
	$("#modifyLetter").css("display", "none");
	$('#my-diary-board-modifyBtn').css('display', 'block');
	
	$("#form-diary-update").submit();
}