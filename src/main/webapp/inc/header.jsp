<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.bean.user"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Cafe House Template</title>
<!-- 
Cafe House Template
http://www.templatemo.com/tm-466-cafe-house
-->
  <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,600,700' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Damion' rel='stylesheet' type='text/css'>
  <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath() %>/css/font-awesome.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath() %>/css/templatemo-style.css" rel="stylesheet">
  
  <link rel="shortcut icon" href="<%=request.getContextPath() %>/img/favicon.ico" type="image/x-icon" />
  <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
   		<script type="text/javascript" src="<%=request.getContextPath() %>/js/templatemo-script.js"></script>      <!-- Templatemo Script -->
   		<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.validate.min.js"></script>
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>
  <body>
    <!-- Preloader -->
    <div id="loader-wrapper">
      <div id="loader"></div>
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
    </div>
    <!-- End Preloader -->
    <div class="tm-top-header">
      <div class="container">
        <div class="row">
          <div class="tm-top-header-inner">
            <div class="tm-logo-container">
              <img src="<%=request.getContextPath() %>/img/logo.png" alt="Logo" class="tm-site-logo">
              <h1 class="tm-site-name tm-handwriting-font">Cafe House</h1>
            </div>
            <div class="mobile-menu-icon">
              <i class="fa fa-bars"></i>
            </div>
            <nav class="tm-nav">
              <ul>
                <li><a href="<%=request.getContextPath() %>/index" id="home">Home</a></li>
                
                <li><a href="<%=request.getContextPath() %>/menu.jsp" id="menu">Menu</a></li>
                <li><a href="<%=request.getContextPath() %>/contact" id="contact">Contact</a></li>
                <li><a href="<%=request.getContextPath() %>/info" id="info">Info</a></li>
                <%
                	user user = (user)session.getAttribute("user");
                	if(user == null){
                %>
				<li><a href="<%=request.getContextPath() %>/login" id="login">Login</a></li>
				<%}else{ %>
					<li><span style="background-color: white; color: black; margin-left: 10px; padding: 5px"><%=user.getName() %></span><a href="<%=request.getContextPath() %>/logout" id="login">logout</a></li>
				<%} %>
              </ul>
            </nav>   
          </div>           
        </div>    
      </div>
    </div>
    <section class="tm-welcome-section">
      <div class="container tm-position-relative">
        <div class="tm-lights-container">
          <img src="<%=request.getContextPath() %>/img/light.png" alt="Light" class="light light-1">
          <img src="<%=request.getContextPath() %>/img/light.png" alt="Light" class="light light-2">
          <img src="<%=request.getContextPath() %>/img/light.png" alt="Light" class="light light-3">  
        </div>        
        <div class="row tm-welcome-content">
        </div>
        <img src="<%=request.getContextPath() %>/img/table-set.png" alt="Table Set" class="tm-table-set img-responsive">             
      </div>      
    </section>