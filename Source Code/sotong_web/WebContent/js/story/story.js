$(document).ready(function(){
		$('#write-btn').on('click',storyWrite);	//가족 이야기 작성 버트 클릭 시 작동.
		
		//가족 이야기 수정 버튼 클릭 시 familyBorderModify 함수 호출
		$('#story-contents-ul .li-story-list .modify').on('click', familyBorderModify);
		$('#comment-btn').on('click',commentWrite);	//댓글 작성 시 실행 되는 창
		
		
		$('#story-contents-ul .li-story-list .delete').on('click',function(){
			var result = confirm('정말로 삭제 하시겠습니까?' + "\n" + '삭제되면 복구가 불가능합니다.');
			 
		     if(result) {
		    	 var index = $("#story-contents-ul .li-story-list .delete").index(this);		    	 
		    	 $("#family-delete-form"+index).submit();
		     } else {
		    	 //no
		     }	
		});
		
		//수정 하여 서버로 전송하는 DB에 저장 메소드 호출
		$('#story-contents-ul .li-story-list .modify-contents-button').on('click', modifyStory);
		
		//하트 클릭 시 
		$('#story-contents-ul .li-story-list .heart-button').on('click', clickHeart);
		
		//이웃 홈 클릭 시
		$('#story-contents-ul .li-story-list .heart-button').on('click', clickHeart);		
});

function storyWrite() {
	//가족 이야기 작성 버튼 클릭 시 작동하는 함수
	var content = $('#family-board-content-write').val();
	if (content == "") {
		alert("내용을 입력하세요");
	} else {
		$('#content').val("");
		$('#family-body-write').submit();
	}
}

function familyBorderModify() {
	 var index = $("#story-contents-ul .li-story-list .modify").index(this);		    	 
	 $("#comment-write" + index).css("display", "none");
	 $("#modify-public-scope-border" + index).css("display", "block");
	 $("#modify-public-scope-border" + index).css("margin-top", "13px");
	 $("#family-board-content-read" + index).attr("readonly", false);
	 $("#read-board-update-button" + index).css("top", "-28px");
	 $("#read-board-update-button" + index).css("right", "-112px");
	 $("#family-board-date" + index).css("right", "-324px");
	 $("#family-board-date" + index).css("top", "-9px");
	 $("#modify-btn" + index).css("display", "none");
}

function modifyStory() {
	var index = $("#story-contents-ul .li-story-list .modify-contents-button").index(this);		    	
	$.ajax({
		type:"POST",
        url:"story-update.do",
        data:$("#family-delete-form" + index).serialize(),
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
        success : function() {
        	$("#modify-public-scope-border" + index).css("display", "none");
        	$("#comment-write" + index).css("display", "block");
        	$("#modify-btn" + index).css("display", "block");
        	$("#family-board-content-read" + index).attr("readonly", true);
        	
        	$("#read-board-update-button" + index).css("top", "-54px");
        	$("#read-board-update-button" + index).css("right", "3px");
        	$("#family-board-date" + index).css("right", "-210px");
        	$("#family-board-date" + index).css("top", "-35px");
		}	        
	});
}

function clickHeart() {
	
	var index = $("#story-contents-ul .li-story-list .heart-button").index(this);
	$.ajax({
		
		type:"POST",
        url:"story-heart.do",
        data:$("#family-delete-form" + index).serialize(),
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
        success : function(heart) {
        	$(".heartNo" + index).text(heart);
		}	        
	});
}

function neighborHome() {
	var index = $("#story-contents-ul .li-story-list .neighbor-story-home-button").index(this);
    $("#family-delete-form" + index).submit();
}

function commentWrite() {
	var comment = $('#comment-content').val();
	if (comment == "") {
		alert("댓글을 작성해주세요");
	} else {
		alert(comment);
	}
}
