<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/story/FamilyStory.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="js/story/story.js"></script>
	<title>가족 이야기</title>
</head>
<body>
	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>
	
	<div id="all">
		<ul id="story-contents-ul">
		<header>	
		
		</header>
		<li>
		 <div id="family-body">
		 	<div class="family-board">
		 		<div id="family-board-write" class="famil-board">
					<form action='story-write.do' method='post' id='family-body-write' class="form-horizontal" role="form">
						<div class="profile-border">
			    			<div id="write-profile-photo" class="family-board-image">
			    				<image src=${userInfo.memberPhoto } class='profile-image'>
					   		</div>
					   		<input type="text" id="write-profile-name" class="family-board-name" value=${userInfo.memberNickName } readonly>
	    				</div>
				   		<div class="family-board-content">
					   		<div id="write-content" class="famil-board-text">
				     			<textarea id="family-board-content-write" class="form-control" name='family-board-content-write' placeholder="오늘은 어떤 가족 이야기가 있나요?" maxlength="2000"></textarea>
							</div>
				  			<div id="family-board-write-down" class="family-board-down">
						  		<div id="write-board-button">
						  			<button id="write-btn" type="button">작성</button>
						  			<ul class="list-inline">
						  				<li><input type="button" id="emoticon-btn" class="emoticon-button"/></li>
						  				<li>
						  					<div class="image-upload">
											    <label for="file-input">
											        <img src="image/camera.png"/>
											    </label>
											    <input id="file-input" type="file"/>
											</div>	
						  				</li>
						  			</ul>
						  		</div>
					  			<span id="public-scope-letter">공개 범위</span>
					  			<select id="public-scope" name="public-scope">
					  				<option value="가족">가족 공개</option>
					  				<option value="이웃">이웃 공개</option>
					  			</select>
					  			
				  			</div>
				  		</div>
				  	</form>
				  </div>
		 		</div>
		 	
		 	<div class='family-board '>
		 	</li>
		 		<c:forEach var='storyInfo' items="${storyList }" varStatus="i">
				
			 	 <li class="li-story-list">
			 	  <form action='story-delete.do' method='post' id="family-delete-form${i.index}">
			 	 <div id="family-board-read" class="famil-board">
		 			<div >
		 				<div class='profile-border'>
			    			<div id="read-profile-photo" class="family-board-image">
			    				<image src=${storyInfo.memberPhoto } class='profile-image'/>
					   		</div>
					   		<input type="text" id="read-profile-name" class="family-board-name" value=${storyInfo.memberNickname } readonly>
	    				</div>
	    				<div class="family-board-content" style="border-color:${storyInfo.memberColor }">
	    				
					   		<div id="read-content" class="famil-board-text">
				     			<textarea id="family-board-content-read${i.index }" name='mdofiyContents' class="form-control" readonly>${storyInfo.contents }</textarea>
							</div>
							
			 				<div id="comment-write${i.index }" class="family-board-down">
		 						<div id="comment-profile">
		 							<image src=${userInfo.memberPhoto }>
		 						</div>
		 						<div id="comment-content-all">
		 							<input type="text" id="comment-content"/>
		 							<input type="button" id="comment-btn" value="댓글"/>
		 						</div>
			 				</div>
			 				
			 				<div id="modify-public-scope-border${i.index}" class="modify-family-board-down" >
			 					<div id="write-board-button">
						  			<button id="write-btn${i.index }" type="button" class="modify-contents-button">수정</button>
						  			<ul class="list-inline">
						  				<li><input type="button" id="modify-emoticon-btn" class="emoticon-button"/></li>
						  				<li>
						  					<div class="image-upload">
											    <label for="file-input">
											        <img src="image/camera.png"/>
											    </label>
											    <input id="file-input" type="file"/>
											</div>	
						  				</li>
						  			</ul>
						  		</div>
		 						<span id="public-scope-letter">공개 범위</span>
					  			<select id="modify-public-scope" name="public-scope">
					  				<option value="가족">가족 공개</option>
					  				<option value="이웃">이웃 공개</option>
					  			</select>
			 				</div>
			 				
			 				<div id="read-board-update-button${i.index }" class="read-board-update-button">
				 				<c:choose>
								    <c:when test="${storyInfo.memberCode eq userInfo.memberCode}">
								      	<input type="button" class='modify' id="modify-btn${i.index }" value="수정"/>
				 						<input type="button" class='delete' id="delete-btn" value="삭제"/>
								    </c:when>    
								    <c:otherwise>
								       	<input type="hidden" class='modify'/>
					 					<input type="hidden" class='delete'/>
								    </c:otherwise>
								</c:choose>	
								
			 				</div>
			 				<div class="family-board-date" id="family-board-date${i.index }">
					  			${storyInfo.storyDate } 
					  		</div>
			 			</div>
			 		</div>
			 		
			 		<div id="heart-image" class='heart'>
	 					<label id="heartNo" class="heartNo${i.index }">${storyInfo.stroyHeart } </label>
	 					<input type='hidden' name="heartNo" class='heartNo-hidden${i.index }' value=${storyInfo.stroyHeart }>
	 					<input type='hidden' name='story-code' value=${storyInfo.storyCode }>
	 					<input type="button" class='heart-button'/>
	 				</div>
	 				
			 	</div>
			 		
			 	 </form>
			 	</li>
			 	</c:forEach>
			 	</ul>	
			 </div>
			
		</div>
		<footer>
		
		</footer>
	</div>
</body>
</html>