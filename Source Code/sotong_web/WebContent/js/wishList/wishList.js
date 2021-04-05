$(document).ready(function(){
	$('#checkBtn').on('click',function() {
		window.location.href = "wishList.jsp";
	});
	
	//게시글 마우스 이베튼 처리 =======================================================

	$('#wish-list-finish-button-image').hover(function(){
		//마우스 이모티콘 버튼에 올리 시 이벤트 발생
		$("#wish-list-finish-button-image").css("display", "none");
		$("#wish-list-finish-button-letter").css("display", "block");
	});
	$('#wish-list-finish-button-letter').mouseout(function(){
		//마우스를 요소에서 제거 하면 발생하는 함수
		$("#wish-list-finish-button-letter").css("display", "none");
		$("#wish-list-finish-button-image").css("display", "block");
	});
	
	$('#wish-list-modify-button-image').hover(function(){
		//마우스 이모티콘 버튼에 올리 시 이벤트 발생
		$("#wish-list-modify-button-image").css("display", "none");
		$("#wish-list-modify-button-letter").css("display", "block");
	});
	$('#wish-list-modify-button-letter').mouseout(function(){
		//마우스를 요소에서 제거 하면 발생하는 함수
		$("#wish-list-modify-button-letter").css("display", "none");
		$("#wish-list-modify-button-image").css("display", "block");
	});
	
	$('#wish-list-delete-button-image').hover(function(){
		//마우스 이모티콘 버튼에 올리 시 이벤트 발생
		$("#wish-list-delete-button-image").css("display", "none");
		$("#wish-list-delete-button-letter").css("display", "block");
	});
	$('#wish-list-delete-button-letter').mouseout(function(){
		//마우스를 요소에서 제거 하면 발생하는 함수
		$("#wish-list-delete-button-letter").css("display", "none");
		$("#wish-list-delete-button-image").css("display", "block");
	});
	
	$('#write-btn').hover(function(){
		//마우스 이모티콘 버튼에 올리 시 이벤트 발생
		$("#write-btn").css("display", "none");
		$("#write-letter").css("display", "block");
	});
	$('#write-letter').mouseout(function(){
		//마우스를 요소에서 제거 하면 발생하는 함수
		$("#write-letter").css("display", "none");
		$("#write-btn").css("display", "block");
	});

	//글쓰기 버튼 클릭 시,
	$("#write-letter").click(function(){
		$('#wirte-letter-form').submit();;
	});
	
	//수정 버튼 클릭 시,
	$("#wish-list-modify-button-letter").click(function(){
		window.location.href = "WishListModify.jsp";
	});
	
	//삭제 버튼 클릭 시,
	$("#wish-list-delete-button-letter").click(function(){
		alert("삭제 버튼 클릭");
	});
	
	//확인 버튼 클릭 시,
	$("#wish-list-finish-button-letter").click(function(){
		alert("확인 버튼 클릭");
	});
	
	
	
});
	
	