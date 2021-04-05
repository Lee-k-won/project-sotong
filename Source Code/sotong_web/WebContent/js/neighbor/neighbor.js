$(document).ready(function(){
	
	
	//탭 설정
	
	$('#neighborTab a[href="#showNeighbor"]').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});
	
	$('#neighborTab a[href="#searchNeighbor"]').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});	
	

	$("ul.nav-tabs > li > a").on("shown.bs.tab", function (e) {
	        var id = $(e.target).attr("href").substr(1);
	        window.location.hash = id;
	 });
	    // on load of the page: switch to the currently selected tab
	    var hash = window.location.hash;
	$('#neighborTab a[href="' + hash + '"]').tab('show');
	
	
	
	//검색 버튼 클릭

	$('#searchBtn').click(function(){
//		alert($('#search-category :selected').val());
		var searchCategory = $("#search-category :selected").val();
		var searchWord2 = $("#searchWord").val();
		var searchWord =escape(encodeURIComponent(searchWord2));
//		$.ajax({
//			type:"GET",
//	        url:"searchNeighbor.do",
//	        data:{searchCategory:searchCategory, searchWord: searchWord},
//	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
//	        success : function(result){
//				alert("검색됨" + result);
//				}	        
//		});
		$("#search-result-list").empty();
		
		$.ajax({
			type:"POST",
	        url:"searchNeighbor.do",
	        data:$("#neighbor-search").serialize(),
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        dataType: "json",
	        success : function(result){	        	
	        	$.each(result, function(index, value){
	        		var html ='';
	        		
	        		html+= '<li class="search-result-list-li">';
	        		html+= '<div id="neighbor-home-info">';
	        		html+= '<form action=neighborHome.do id="search-neighbor-form'+index+'">';
	        		html+= '<a href="#">';
	        		html+= '<div id="neighbor-home"> ';
	        		html+= '<img src="img/neighbor/home.png" id="homeImg" width="47px" height="47px"/>';
	        		html+= '<label>'+result[index].familyHomeName+'</label>';
	        		html+= '<input type="hidden" id="homeCode" name="homeCode" value="'+result[index].familyHomeCode+'">';
	        		html+= '</div>';
	        		
	        		if(result[index].memberRole=='1')
	        		{
	        			html+= '<div id="neighbor-family-info"><div id="manager_level" class="form-group"><img src="img/myhome/manager.png" id="managerIcon" alt="매니저"/></div>';
	        		}
	        		else
	        		{
	        			html+= '<div id="neighbor-family-info"><div id="manager_level" class="form-group"><img src="img/myhome/hearts.png" id="familyIcon" alt="가족"/></div>';
	        		}
	        		
	        		html+= '<div id="profile_pic" class="form-group"><img src="'+result[index].memberPhoto+'" id="profileImg" alt="프로필사진" class="img-circle"/></div>';
	        		html+= '<div id="profile_info" class="form-group"><label>'+result[index].memberNickName+'</label><span class="glyphicon glyphicon-gift" aria-hidden="true"  style="color: #FF66CC"></span><span class="search-member-birth">생일 : '+result[index].memberBirth+'</span></div></div></a></form></div></li>';
	        		$("#search-result-list").append(html);
	        	})
	        },

	});
	});


	//클릭 시 이웃 보기
	$("#neighbor-list-ul li").click(function(){
		var index = $("#neighbor-list-ul .neighbor-list-li").index(this);
		$("#neighbor-form"+index).submit();
	});
	
	$('#search-result-list .search-result-list-li').click(function(){
		alert("클릭");
	});
	
	// 클릭 시 검색된 이웃 목록 선택
	
	$(document).on("click",".search-result-list-li",function(e) {
	      var index = $('.search-result-list-li').index(this); 
	      $("#search-neighbor-form"+index).submit();
	   });

	// 엔터 입력 시 버튼클릭
	
	 $("#searchWord").keydown(function(e){
	        if(e.keyCode == 13){
	            e.cancelBubble = true;
	            $("#searchBtn").click();
	            return false;
	        }
	    });
	
	 //날짜 변경 함수
	 
	function newDate(date){
		var date = new Date(date);
		
		var year = date.getYear();
		var month = date.getMonth();
		if(month<10)
		{
			month = "0"+month;
		}
		var day = date.getDate();
		if(day<10)
		{
			day = "0"+day;
		}
		var yymmdd = year+"/"+month+"/"+day;
		
		return yymmdd;
	}
	
});
