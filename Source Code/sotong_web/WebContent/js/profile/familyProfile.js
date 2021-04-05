$(document).ready(function(){
			
		$("#deleteBtn").click(function(){
			var result = confirm('가족 구성원을 삭제 하시겠습니까?');

		     if(result) {		    	 
		    	 $('#family-profile').submit();
		     } else {
		    	 //no
		     }	
		});
		
		//메소드 수정~!
		$("#managerBtn").click(function(){
			var result = confirm('매니저 권한을 양도 하시겠습니까?' + "\n" + '양도 후 매니저 권한이 사라집니다.');
			 
		     if(result) {
		    	 $.ajax({
		    			type:"POST",
		    	        url:"okManagerRole.do",
		    	        data:$("#family-profile").serialize(),
		    	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
		    	        success : function() {
		    	        	$('#managerImg').css("display", "block");
		    	        	$('#familyImg').css("display", "none");
		    	        	$('#buttons').css("display", "none");
		    			}	        
		    		});
		     } else {
		    	 //no
		     }	
		});
		
		$("#personColor").click(function(){
			alert(this.val());
		});
		
		
		$('#manager').hover(function(){
			//마우스를 수정 버튼으로 올리시 이베튼 발생
			$("#manager").css("display", "none");
			$("#managerBtn").css("display", "block");
		});
		$('#delete').hover(function(){
			//마우스를 글쓰기 버튼으로 올리시 이벤트 발생
			$("#delete").css("display", "none");
			$("#deleteBtn").css("display", "block");
		});
		$('#managerBtn').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#managerBtn").css("display", "none");
			$("#manager").css("display", "block");
		});
		$('#deleteBtn').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#deleteBtn").css("display", "none");
			$("#delete").css("display", "block");		
		});
		

		$('#backBtn').click(function() {
			window.history.back();
		});
	});
