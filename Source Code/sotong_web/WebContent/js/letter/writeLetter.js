$(document).ready(function(){
	
	
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth()+1;
	var date = today.getDate();
	
	if(month<10) {month="0"+month;}
	if(date<10) {date="0"+date;}
	
	var myToday = year+"-"+month+"-"+date;
	
	$("#sendDate").val(myToday);
	
	
	$("#receiverList").change(function(){
		var receiver = $("#receiverList").val();
		$("#receiver").text(receiver);
	});
	
	$("#sendCheck").is(":checked")
	{
		$("#sendDate").val(myToday);
	}
	
	$("#sendCheck").change(function(){
		checkSend($("#sendCheck"));
	});
	
	
	function checkDate(em){
		
		// 사용자가 선택한 날짜를 읽음
		var date = em.val(); 
		
		var afterMonth = today.getMonth()+4;
		var newMonth = afterMonth%12;
		if(newMonth/12 >= 1)
			{
				year +=1;
				month = newMonth%12;
			}
		if(date<myToday) { return -1; }
		else if(date.substring(5,6)>afterMonth) { return -1; }
		else if(date.substring(0,4)>year+1) {return -1;}
		else
		{
			return 1;
		}
	
		
		
		//오늘 날짜
		
		
		/*//사용자가 선택한 연도가 오늘년도보다 이후
		if(userYear>=today.getFullYear())
		{
			//사용자가 선택한 월이 오늘보다 이후
			if(userMonth>=today.getMonth()+1)
				{
					//사용자가 선택한 날이 오늘보다 이후
					if(userDay>=today.getDate())
						{
							return 1;
						}
					else
						{
							return -1;
						}
				}
			else
				{
				return -1;
				}
		}
		else
		{
			return -1;
		}
		*/
	};
	

	function checkSend(em)
	{
		if(em.is(":checked")) //체크된 상태이면
		{
			$("#sendDate").prop("disabled", true); // 발송일 수정 불가
		}
		else //아니면
		{
			alert("예약 발송은 오늘보다 이후 날짜로 설정해주세요. ");
			$("#sendDate").prop("disabled", false); // 발송일 수정 가능
		}
		
	};
	
	$("#checkBtn").click(function(){ // 확인버튼을 클릭했을 경우
		
		if($("#letterTitle").val() == "" )
		{
			alert("제목을 입력해주세요");
		}
		else if($("#letterContent").val() == "" || $("#letterContent").val() == null)
		{
			alert("글 내용을 입력해주세요");
		}
		else if(checkDate($("#sendDate"))==-1)
		{
			alert("발송 날짜를 다시 선택해주세요.")
		}
		else
		{			
			$("#letterForm").submit();
		}
	});
	
	$("#backBtn").click(function(){
		if($("#letterContent").text() != "" || $("#letterContent").text() !=null)
		{
			if(confirm("글 작성을 취소하고 돌아가시겠습니까?")==true)
				{
					window.location.href = "letterbox.jsp";
				}
		}
		else
		{
			window.location.href = "letterbox.jsp";
		}
	});
	
	
		
	
	
});
