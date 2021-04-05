$(document).ready( function() {
	$("#modify-btn").click(function(){
		
		if($("#modify-PW").val() =="")
		{
			window.alert("비밀번호를 입력해주세요.");
			$("#modify-PW").focus();
		}	
		else if($("#mdify-check-PW").val() == "")
		{
			window.alert("비밀번호를 확인해주세요.");
			$("#modify-PW").focus();
		}
		else if($("#modify-PW").val() != $("#mdify-check-PW").val())
		{
			window.alert("입력하신 비밀번호가 일치하지 않습니다.");
			$("#modify-PW").focus();
		}
		else
		{
			$("#family-modify-form").submit();
		}
	});
	
	$('#backBtn').click(function(){
		window.history.back();
	});
	
	function readURL(input) {

	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	            $('#profileImg').attr('src', e.target.result);
	        }

	        reader.readAsDataURL(input.files[0]);
	    }
	}

	$("#profileBtn").change(function(){
	    readURL(this);
	});
});