$(document).ready(function(){   
	
	messageAlarm();
	
    $("#showSidebar").click(function(){	
        
        if ($("#sidebar").prop("display","none"))
        {
        	$("#sidebar").toggle();
        }

    });

    $("#alarm-button").click(function(){
    	$("#jewelBox").toggle();
    });
    
    $('#dropdown-menu .connecting-list .connection-btn-group').click(function() {
    	var index = $('#dropdown-menu .connecting-list .connection-btn-group').index(this);
    	alert(index);
    });
    
    $('#dropdown-menu .connecting-list .reject-btn-group').click(function() {
    	var index = $('#dropdown-menu .connecting-list .reject-btn-group').index(this);
    	alert(index);
    });
    
    $('#logout-button-menu').click(function() {
    	alert("로그아웃");
    	$('#logout-form').submit();
    });
    
    $("#alarm-button").click(loadList);
    
    //수락 
    
    $(document).on("click",".connection-btn-group",function(event){
        console.log("You clicked on: ", event.target.id);
        var target =$(event.target).closest('.connecting-form').attr('id');
        var senderHomeCode = $("#senderHomeCode").val();
        alert(senderHomeCode);
        $.ajax({
			type:"GET",
			url:"connectOk.do",
			data:"senderHomeCode="+	 senderHomeCode,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(result) {
				loadList();
			}
		})
    });
    
    //거절
    
    $(document).on("click",".reject-btn-group",function(event){
        console.log("You clicked on: ", event.target.id);
        var target =$(event.target).closest('.connecting-form').attr('id');
        var waitingCode = $("#waitingCode").val();
        $.ajax({
			type:"GET",
			url:"rejectNeighbor.do",
			data:"waitingCode=" + waitingCode,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(result) {
				loadList();
			}
		})
    });
    
    function loadList(){
    	var familyHomeCode = $("#familyHomeCode").val();
        $("#jewelBox .connecting-list").empty();
        
        $.ajax({
          type:"GET",
          url:"waitingNeighbor.do",
          data:"neighbor="+ familyHomeCode,
          contentType: "application/x-www-form-urlencoded; charset=UTF-8",
          dataType: "json",
          success : function(result) {
             
             $.each(result, function(index, value){      
                         
               var html = '<li class="connecting-list">';
               html+= '<form id="form-connecting-list'+index+'"method="post" action="connecting.do" class="connecting-form">';
               html+= '<input type="hidden" value='+result[index].senderHomeCode+' name="senderHomeCode" id="senderHomeCode">';
               html+= '<input type="hidden" value='+result[index].waitingCode+' name="waitingCode" id="waitingCode">';
               html+= '<label class="jewelLabel">'+result[index].senderHomeName+'</label>';
               html+= '<label class="jewelLabel">'+result[index].senderHomeManagerName+'</label>';
               html+= '<div class="jewel-btn">';
               html+= '<input type="button" value="수락" name="connectingBtn" class="connection-btn-group" id="connectingBtn'+index+'">';
               html+= '<input type="button" value="거절" name="rejectBtn" class="reject-btn-group" id="rejectBtn">';
               html+= '</div>';
               html+= '</form>';
               html+= '</li>';
            
               $("#jewelBox").append(html);
                })
            },
    });
    };
    
    /*
    $("#storyBoard").hover(function(){
    	$("#story-sub-menu").css("display","block");  
    });
    $("#storyBoard").mouseout(function(){
    	$("#story-sub-menu").css("display","none");  
    });
    
    $("#diaryBoard").hover(function(){
    	$("#diary-sub-menu").css("display","block");  
    });
    $("#diaryBoard").mouseout(function(){
    	$("#diary-sub-menu").css("display","none");  
    });
    
    $("#scheduleBoard").hover(function(){
    	$("#schedule-sub-menu").css("display","block");  
    });
    $("#scheduleBoard").mouseout(function(){
    	$("#schedule-sub-menu").css("display","none");  
    });
    */
    
    // 알람 변경될 때마다 개수 가져옴
    
//    $("#alarmNum").change(function(){
////    	alarm();
//    });
//   
   
});

function messageAlarm() {
	$("#alarmNum").load(
	
	$.ajax({
		type : "GET",
		url : "countAlarm.do",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(count) {
        	if(count == 0)
        	{
        		$("#alarmNum").css("display","none");
        	}
        	else
        	{
        		$("#alarmNum").val(count);
        		$("#alarmNum").css("display","block");
        		
        	}
        }
	})
	);
setInterval("messageAlarm()", 1000000);
	
	
};