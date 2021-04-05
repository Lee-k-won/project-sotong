$(document).ready(function(){
	
	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
            
    var chan_val = '작성날짜: ' + year + '-' + mon + '-' + day;
    $('#my-diary-board-read-write-date').val(chan_val);
    
    
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
		$('#addPhotoBtn').hover(function(){
			//마우스를 삭제 버튼으로 올리시 이베튼 발생
			$("#addPhotoBtn").css("display", "none");
			$("#addPhotoLetter").css("display", "block");
		});
		$('#backBtn').hover(function(){
			//마우스를 수정 버튼으로 올리시 이베튼 발생
			$("#backBtn").css("display", "none");
			$("#backLetter").css("display", "block");
		});
		$('#okBtn').hover(function(){
			//마우스를 글쓰기 버튼으로 올리시 이베튼 발생
			$("#okBtn").css("display","none");
			$("#okLetter").css("display", "block");
		});
		$('#addPhotoLetter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#addPhotoLetter").css("display", "none");
			$("#addPhotoBtn").css("display", "block");
		});
		$('#backLetter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#backLetter").css("display", "none");
			$("#backBtn").css("display", "block");
		});
		$('#okLetter').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#okLetter").css("display","none");
			$("#okBtn").css("display", "block");
		});
	//게시글 마우스 이베튼 처리 =======================================================		
	
		
	//게시물 버튼 이벤트 처리 ========================================================
		$('#addPhotoBtn').on('click',addPhotoBtn);
			//마우스를 삭제 버튼으로 클릭 시 이베튼 발생
		
		$('#backLetter').on('click',backBtn);
			//마우스를 수정 버튼으로 콜릭 시 이베튼 발생
			
		$('#okLetter').click(function(){
			
			var writeDate = year + '-' + mon + '-' + day;
			var title = $('#my-diary-title').val();
			var content = $('#my-diary-board-read-content-textarea').val();
			
			
			if (title == "" || title==null) {
				alert('제목을 입력하세요');
			} else if (content == "") {
				alert("내용을 입력하세요");
			} else {

				//다이어리 데이트 리스트의 데이트의 div의 input 값이 겹치면
				//노드를 추가하지 않고 타이틀 추가로 바로 간다.
				
				$(".list-date").each(function()
						{
							if($(this).attr("value")!=writeDate)// 날짜가 없을 경우 
								{
									alert($(this).attr("value") + "작성날짜 : " +writeDate);
									appendDateAndDiary($("#ul-diary-date-list"));
									return false;										
								}
							else
								{
									appendDiary($(".list-date .ul-diary-title").first());
									return false;
								}
						}
					);
				
				$("#form-diary-write").submit();
				
			}
			
			
			//작성 확인 클릭 시 okBtn 함수를 호출한다.
	//게시물 버튼 이벤트 처리 ========================================================
});


function appendDateAndDiary(em) {
	var writeDate = year + '-' + mon + '-' + day;
	var title = $('#my-diary-title').val();
	
	var t1='<li class="li-diary-date">';
	var t2='<div id="my-diary-date-div">';
	var t3='<input type="text" id="my-diary-list-date" class="list-date" readonly value="'+writeDate+'"size=9 />';
	var t4='</div>';
	var t5='<div id="my-diary-title-div">';
	var t6='<ul class="ul-diary-title">';
	var t7='<li class="li-diary-title"><input type="button"	value="'+title+'" class="my-diary-list-title-btn" /></li>';
	var t8='</ul>';
	var t9='</div>';
	var t10='</li>';
	
	em.prepend(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10);
	
};

function appendDiary(em) {
	var title = $('#my-diary-title').val();
	
	var t1 = '<li class="li-diary-title"><input type="button" value="'+title+'" class="my-diary-list-title-btn" /></li>';
	
	em.prepend(t1);
}

function addPhotoBtn(){
	//사진 추가
	
	$("#addPhotoLetter").css("display", "none");
	
	alert('사진 추가 버튼 클릭');
	
}

function backBtn() {
	//돌아 가는 버튼 되돌아가기
	
	$("#backLetter").css("display", "none");
	
	alert('되돌아가자');
}

function okBtn() {
	//작성 누르면 서버로 가서 등록 한다.
	
	var writeDate = $('#my-diary-board-read-write-date').val();
	var title = $('#my-diary-title').val();
	var content = $('#my-diary-board-read-content-textarea').text();
	alert(writeDate);
	
	if (title == "" || title==null) {
		alert('제목을 입력하세요');
	} else if (content == "") {
		alert("내용을 입력하세요");
	} else {

		//다이어리 데이트 리스트의 데이트의 div의 input 값이 겹치면
		//노드를 추가하지 않고 타이틀 추가로 바로 간다.
		
		alert($("#ul-diary-date-list .li-diary-date #my-diary-date #my-diary-list-date").val());
	
		
		
	}
}
}); 