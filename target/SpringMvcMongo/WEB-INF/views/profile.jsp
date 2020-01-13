<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SocialNet</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="static/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
  <div class="main_resize">
    <div class="header">
      <div class="logo">
        <h1><a href="#"><span>Social</span>Net<small>Enjoy your life</small></a></h1>
      </div>
      <div class="search">
        <form method="get" id="search" action="#">
          <span>
          <input type="text" value="Search..." name="s" id="s" />
          <input name="searchsubmit" type="image" src="static/img/images/search.gif" value="Go" id="searchsubmit" class="btn"  />
          </span>
        </form>
        <!--/searchform -->
        <div class="clr"></div>
      </div>
      <div class="clr"></div>
      <div class="menu_nav">
        <ul>
          <li><a href="createMessage">Write Message</a></li>
          <li><a href="createGroup">Create Group</a></li>
          <li><a href="createPost">Write Post</a></li>
        </ul>
        <div class="clr"></div>
      </div>
      <div class="hbg"><img src="static/img/images/header_images.jpg" width="923" height="291" alt="" /></div>
    </div>
    <div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <h2><span>Template License</span></h2>
            <div class="clr"></div>
            <p class="post-data"><span class="date">November 16, 2019</span> &nbsp;|&nbsp; Posted by <a href="#">Owner</a> &nbsp;|&nbsp; Filed under <a href="#">templates</a>, <a href="#">internet</a></p>
            <img src="static/img/images/images_1.jpg" width="613" height="193" alt="" />
            <p></p>
            <p class="spec"><a href="#" class="com fr">Comments</a> <a href="#" class="rm fl">Read more</a></p>
            <div class="clr"></div>
          </div>
          <div class="article">
            <h2><span>Social advertisement</span></h2>
            <div class="clr"></div>
            <p class="post-data"><span class="date">December 15, 2019</span> &nbsp;|&nbsp; Posted by <a href="#">Owner</a> &nbsp;|&nbsp; Filed under <a href="#">templates</a>, <a href="#">internet</a></p>
            <img src="static/img/images/images_2.jpg" width="613" height="193" alt="" />
            <p></p>
            <p class="spec"><a href="#" class="com fr">Comments</a> <a href="#" class="rm fl">Read more</a></p>
            <div class="clr"></div>
        <div class="sidebar">
          <div class="gadget">
            <h2 class="star"><span>Sidebar</span> Menu</h2>
            <div class="clr"></div>
            <ul class="sb_menu">
              <li><a href="chat">Chats</a></li>
              <li><a href="post">Posts</a></li>
              <li><a href="groupsList">Groups</a></li>
            </ul>
          </div>
          <div class="gadget">
            <h2 class="star"><span>Sponsors</span></h2>
            <div class="clr"></div>
            <ul class="ex_menu">
              <li>Groups
              <table id="users_table" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Name</th><th>Description</th><th colspan="2"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${groups}" var="group">
	                	<tr align="center">
	                    	<td><c:out value="${group.name}" /></td>
	                    		<td><c:out value="${group.description}" /></td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
              </li>
              <li>Chats
              <table id="users_table" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Name</th><th colspan="1"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${chats}" var="chat">
	                	<tr align="center">
	                    	<td><c:out value="${chat.name}" /></td>                   	
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>           
              </li>
              <li>Posts
              <table id="users_table" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Date</th><th colspan="1"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${posts}" var="post">
	                	<tr align="center">
	                    	<td><c:out value="${post.timestamp}" /></td>
                    		
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>       
              </li>
            </ul>
          </div>
          <div class="gadget">
            <h2 class="star"><span>Wise Words</span></h2>
            <div class="clr"></div>
            <div class="testi">
              <p><span class="q"><img src="static/img/images/qoute_1.gif" width="20" height="15" alt="" /></span> We can let circumstances rule us, or we can take charge and rule our lives from within. <span class="q"><img src="static/img/images/qoute_2.gif" width="20" height="15" alt="" /></span></p>
              <p class="title"><strong>Earl Nightingale</strong></p>
            </div>
          </div>
        </div>
        <div class="clr"></div>
      </div>
    </div>
  </div>
  <div class="fbg">
    <div class="fbg_resize">
      <div class="col c1">
        <h2><span>Image Gallery</span></h2>
        <a href="#"><img src="static/img/images/pic_1.jpg" width="58" height="58" alt="" /></a> 
        <a href="#"><img src="static/img/images/pic_2.jpg" width="58" height="58" alt="" /></a> 
        <a href="#"><img src="static/img/images/pic_3.jpg" width="58" height="58" alt="" /></a> 
        <a href="#"><img src="static/img/images/pic_4.jpg" width="58" height="58" alt="" /></a> 
        <a href="#"><img src="static/img/images/pic_5.jpg" width="58" height="58" alt="" /></a> 
        <a href="#"><img src="static/img/images/pic_6.jpg" width="58" height="58" alt="" /></a> </div>
      <div class="col c2">
        <h2><span>Lorem Ipsum</span></h2>
        <p>Lorem ipsum dolor<br />
          Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. <a href="#">Morbi tincidunt, orci ac convallis aliquam</a>, lectus turpis varius lorem, eu posuere nunc justo tempus leo. Donec mattis, purus nec placerat bibendum, dui pede condimentum odio, ac blandit ante orci ut diam.</p>
      </div>
      <div class="col c3">
        <h2><span>About</span></h2>
        <p>This Social network allows users to share ideas, digital photos and videos, posts, and to inform others about online or 
        real-world activities and events with people in their network. While in-person social networking – such as gathering in a 
        village market to talk about events – has existed since the earliest development of towns, the web enables 
        people to connect with others who live in different locations, ranging from across a city to across the world. Depending on 
        the social media platform, members may be able to contact any other member. In other cases, members can contact anyone they 
        have a connection to, and subsequently anyone that contact has a connection to, and so on. Some services require members to have 
        a preexisting connection to contact other members.. <a href="#">Learn more...</a></p>
      </div>
      <div class="clr"></div>
    </div>
  </div>
</div>
<div class="footer">
  <div class="footer_resize">
    <p class="lf">&copy; Copyright <a href="#">MyWebSite</a>.</p>
    <p class="rf">Layout by Rocket <a href="#">Website Templates</a></p>
    <div class="clr"></div>
  </div>
</div>
</html>