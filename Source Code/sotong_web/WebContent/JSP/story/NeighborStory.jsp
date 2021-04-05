<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/story/NeighborStroy.css" rel="stylesheet">
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
		<header>
		
		</header>
		<ul id="story-contents-ul">
		 <div id="family-body">
		 	<div class="family-board">
		 		<!-- 여기 부터 foreach 구문 돌려라 -->
		 		<c:forEach var='storyInfo' items="${neighborList }"  varStatus="i">
				 	<c:choose>
					 	<c:when test='${userInfo.familyHomecode eq storyInfo.familyHomeCode }'>
					 		<li class="li-story-list">
						 	<div id="family-board-read" class="famil-board">
						 	
						 		<form action='neighborHome.do' method='post' id="family-delete-form${i.index }">
						 			<div class="famil-board-text">
							 			<div class="profile-border">
							    			<div id="read-profile-photo" class="family-board-image">
							    				<image src=${storyInfo.memberPhoto } class='profile-image'/>
							    			</div>
							    			<input type="text" id="read-profile-name" class="family-board-name" value=${storyInfo.memberNickname } readonly>
					    				</div>
						 				
					    				<div class="family-board-content" style="border-color:#00FF00">
						    				<div id="family-board-top">
						    					<button class='neighbor-story-home-button'>
										 			<span id="neighbor-story">${storyInfo.familyHomeName }</span>
										 			<image id="negihbor-story-btn" src="image/home.png"/>
										 			<input type='hidden' name='homeCode' value=${storyInfo.familyHomeCode }>
									 			</button>
									 			<div id="heart-image">
								 					<label id="heartNo" class="heartNo${i.index }">${storyInfo.stroyHeart } </label>
				 									<input type='hidden' name="heartNo" class='heartNo-hidden${i.index }' value=${storyInfo.stroyHeart }>
								 					<input type="button" class='heart-button' >
								 				</div>
							 				</div>
									   		<div id="read-content" class="famil-board-text">
								     			<textarea id="family-board-content-read" name='ab' class="form-control" readonly>${storyInfo.contents }</textarea>
											</div>
							 				<div id="comment-write" class="family-board-down">
							 					<div id="comment-photo">
							 						<div id="comment-profile">
							 							<image src=${userInfo.memberPhoto }>
							 						</div>
							 						<div id="comment-content-all">
							 							<input type="text" id="comment-content"/>
							 							<input type="button" id="comment-btn" value="댓글"/>
							 						</div>
							 					</div>
							 				</div>
							 			</div>
							 			
							 		</div>
							 		<div class="family-board-date">
							  			${storyInfo.storyModifyDate }
							  		</div>
							  		<input type='hidden' name='story-code' value=${storyInfo.storyCode }>
						 		</form>
						 		
					 		</div>
					 		</li>
					 	</c:when>
					 	<c:otherwise>
					 		<li class="li-story-list">
						 	<div id="family-board-read" class="famil-board">
						 	
						 		<form action='neighborHome.do' method='post' id="family-delete-form${i.index }">
						 			<div class="famil-board-text">
							 			<div class="profile-border">
							    			<div id="read-profile-photo" class="family-board-image">
							    				<image src=${storyInfo.memberPhoto } class='profile-image'/>
							    			</div>
							    			<input type="text" id="read-profile-name" class="family-board-name" value=${storyInfo.memberNickname } readonly>
					    				</div>
						 				
					    				<div class="family-board-content" style="border-color:#FF9900">
						    				<div id="family-board-top">
						    					<button class='neighbor-story-home-button'>
										 			<span id="neighbor-story">${storyInfo.familyHomeName }</span>
										 			<image id="negihbor-story-btn" src="image/home.png"/>
										 			<input type='hidden' name='homeCode' value=${storyInfo.familyHomeCode }>
									 			</button>
									 			<div id="heart-image">
								 					<label id="heartNo" class="heartNo${i.index }">${storyInfo.stroyHeart } </label>
				 									<input type='hidden' name="heartNo" class='heartNo-hidden${i.index }' value=${storyInfo.stroyHeart }>
								 					<input type="button" class='heart-button' >
								 				</div>
							 				</div>
									   		<div id="read-content" class="famil-board-text">
								     			<textarea id="family-board-content-read" name='ab' class="form-control" readonly>${storyInfo.contents }</textarea>
											</div>
							 				<div id="comment-write" class="family-board-down">
							 					<div id="comment-photo">
							 						<div id="comment-profile">
							 							<image src=${userInfo.memberPhoto }>
							 						</div>
							 						<div id="comment-content-all">
							 							<input type="text" id="comment-content"/>
							 							<input type="button" id="comment-btn" value="댓글"/>
							 						</div>
							 					</div>
							 				</div>
							 			</div>
							 			
							 		</div>
							 		<div class="family-board-date">
							  			${storyInfo.storyModifyDate }
							  		</div>
							  		<input type='hidden' name='story-code' value=${storyInfo.storyCode }>
						 		</form>
						 		
					 		</div>
					 		</li>
					 	</c:otherwise>
					 </c:choose>				 		
		 		</c:forEach>
		 		<!-- 여기 까지 foreach 구문 돌려라 -->
		 	</div>
		</div>
		
		</ul>
		<footer>
		 
		</footer>
	</div>
</body>
</html>