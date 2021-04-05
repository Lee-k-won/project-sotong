
$(document).ready(function(){
	/*
	var neighborCheck = 0; // 연결상태 0:연결X, 1:연결O -> 후에 DB에서 받아옴
	
	//연결상태 체크	
	if(neighborCheck == 1) // 연결 상태이면
	{
		$("#connectImg").hide(); // 연결요청 이미지 X
		$("#alreadyConnectImg").show(); // 이미연결됨 이미지 O
		$("#disconnectImg").show(); // 연결끊기 이미지 O
	}
	else // 연결상태가 아니면
	{
		$("#connectImg").show(); // 연결요청 이미지O
		$("#alreadyconnectImg").hide(); // 이미연결됨 이미지 X
		$("#disconnectImg").hide(); // 연결끊기 이미지 X
	}
	*/
	
	//연결요청	
	$("#connectImg").click(function(){
		$("#connectModal").modal();
	});
	
	$("#connectOkBtn").click(function(){
		var familyHomeCode = $("#neighborHomeCode1").val();
		$.ajax({
			type:"GET",
			url:"connect.do",
			data:"neighbor="+ familyHomeCode,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(homeCode) {
				alert("연결요청 하였습니다.");
				$("#disConnectImg").css("display", "block");
				$("#connectImg").css("display", "none");
				$("#connectModal").modal("hide");
				window.location.href = 'neighbor.do';
			}
		})
	});
	
	$("#connectCancelBtn").click(function(){
		$("#connectModal").modal("hide");
	});
	
	
	//연결해제
	
	$("#disconnectOkBtn").click(function(){ // 연결해제가 되면
		
		var familyHomeCode = $("#neighborHomeCode2").val();
		$.ajax({
			type:"GET",
			url:"cutNeighbor.do",
			data:"neighborCode="+ familyHomeCode,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(homeCode) {
				alert("연결해제 되었습니다");
				$("#disConnectImg").css("display", "none");
				$("#connectImg").css("display", "block");
				$("#disconnectModal").modal("hide");
				window.location.href = 'neighbor.do';
				
			}
		})
	});
	
	$("#disconnectCancelBtn").click(function(){
		$("#disconnectModal").modal("hide");
	});
	
	$("#disconnectImg").click(function(){
		$("#disconnectModal").modal();
	});
	
	//이웃프로필 상세보기로 이동
	$("#family-profile-ul li").click(function(){

		var index = $("#family-profile-ul .family-profile-li").index(this);
		$("#neighbor-profile-form"+index).submit();
	});
	
});
