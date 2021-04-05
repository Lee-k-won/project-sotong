$(document).ready(function(){
    $("#loginImg").click(function(){
        $("#myLogin").modal();
    });
    
    $("#signupImg").click(function(){
    	$('.form-control').val("");
        $("#mySignup").modal();
    });  
   
    $("#inBtn").click(function() {
    	if($("#userId").val()=="" || $("#userPw").val()=="") {
    			alert("ID 또는 PW를 입력 하시오");
    	} else {
    		//Web 은 앞에 2000으로 넣어 구분해준다.		
    		$('#form-login').submit();
    		//window.location.href = "JSP/home/myhome.jsp";
    	}
    });
    
    $("#okBtn").click(function() {   	
    	
    	if($("#Id").val() == "")		// 제목을 입력하지 않았을 경우.
    	{
    		window.alert("아이디를 입력해주세요.");	
    		$("#Id").focus();
    	}
    	else if($("#Pw").val() == "")		// 제목을 입력하지 않았을 경우.
    	{
    		window.alert("비밀번호를 입력해주세요.");
    		$("#Pw").focus();
    	}
    	else if($("#Name").val() == "")		// 제목을 입력하지 않았을 경우.
    	{
    		window.alert("이름을 입력해주세요.");	
    		$("#Name").focus();
    	}
    	else if($("#Phone-1").val() == "" || $("#Phone-2").val() == "" || $("#Phone-3").val() == "" )		// 제목을 입력하지 않았을 경우.
    	{
    		window.alert("전화번호를 입력해주세요.");
    		$("#Phone-1").focus();
    	}
    	else if($.isNumeric($("#Phone-1").val()) == false || $.isNumeric($("#Phone-2").val()) == false || $.isNumeric($("#Phone-3").val()) == false)
    	{
    		window.alert("전화번호를 정확히 입력해주세요.");
    		$("#Phone-1").focus();
    	}
    	else if($("#userEmail").val() == "")		// 제목을 입력하지 않았을 경우.
    	{
    		window.alert("이메일을 입력해주세요.");	
    		$("#userEmail").focus();
    	}
    	else
    	{
    		var phoneNum = $('#Phone-1').val() + '-' + $('#Phone-2').val() + '-' + $('#Phone-3').val();
        	$('#join-phone-num').val(phoneNum);
        	
        	$("#mySignup").modal("hide");
          	
        	$('#form-join').submit();
    	}
    });
    
    $("#cancelBtn").click(function(){
    	$("#mySignup").modal("hide");
    });
    
    $('#make-home-button').click(function(){
    	var result = confirm('정말로 홈 생성 하시겠습니까?' + "\n" + '홈 생성 되면 홈 초대를 받을 수 없습니다.');
   	 
        if(result) {
        	$('#form-make-home').submit();
        } else {
       	 //no
        }	
    });
});