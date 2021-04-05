$(document).ready(function(){
	$("#deleteBtn").click(function(){
		if(confirm("삭제하시겠습니까?")==true)
			{
				//삭제작업
				$("#letterForm").submit();
				alert("삭제되었습니다.");
				
			}
	});
	
	$("#writeBtn").click(function(){
		window.location.href = "letter_writing.do"; // 글쓰기로 이동
		alert("글쓰기가 완료되었습니다.");
	});

});