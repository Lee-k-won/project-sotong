$(document).ready(function(){
	
	var count = letterCheck(this);
	
	$("#checkAll").change(function() {
		$("input:checkbox").prop('checked', $(this).prop("checked"));
	});
	
	
	$("#deleteBtn").click(function(){ // 삭제버튼을 클릭했을 때
		var count = letterCheck();
		/*var count = $("input:checkbox:not('checked')").length;*/
		if(count==0) // 체크한 편지가 없으면
		{
			alert("삭제할 편지를 선택하세요");
		}
		else
		{
			if(confirm("선택한 편지를 정말 삭제하시겠습니까?")==true)
			{
					//삭제작업
			}
			else
			{
				//취소
			};
		}
		
	});
	
	$("#writeBtn").click(function(){
		window.location.href = "letter_writing.do"; // 글쓰기로 이동
	});
	
	$("#backBtn").click(function(){
		window.history.back(); // 뒤로 이동
	});
	
	
	function letterCheck(em) // 선택된 체크박스가 몇 번인지 찾기
	{
		var numberOfChecked = $("input:checkbox:checked").length; // 선택된 버튼의 개수
		/*var allCheckbox = $("input:checkbox").length-1; // 전체선택 버튼의 갯수 뺌
		
		if($("#checkAll").is(':checked')==true) // 전체선택 버튼이 선택되었다면
		{
			count = 0; // 선택안된건 0개
		}
		else
		{
			var count = allCheckbox - numberOfChecked;
		}*/
		return numberOfChecked;
		
		
		/*if(count==0)
		{
			alert("삭제할 편지를 선택하세요");
		}
		else
		{
			confirm("정말 삭제하시겠습니까?");
		}*/
		
	}
	
	$("#letters li").click(function(){
		var index = $("#letters .letterInfo").index(this);
		$("#letter-form"+index).submit();
	});

});