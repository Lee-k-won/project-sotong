$(document).ready(function(){
	
	$("#backBtn").click(function(){
		history.back();
	});
	
	$("#personColor").click(function(){
		alert(this.val());
	});
	
	$("#neighbor-profile-a").click(function(){
		$("#neighbor-profile-form").submit();
	});
});
		
