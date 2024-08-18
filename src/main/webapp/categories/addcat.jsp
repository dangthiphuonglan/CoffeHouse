<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.bean.menu"%>
<%@page import="model.bean.categories"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoriesDAO"%>
<%@include file="/inc/header.jsp" %>
    <div class="tm-main-section light-gray-bg">
      <div class="container" id="main">
        <section class="tm-section row">
          <div class="col-lg-9 col-md-9 col-sm-8">
            <h2 class="tm-section-header gold-text tm-handwriting-font">Variety of Menus</h2>
            <h2>Cafe House</h2>
            <p class="tm-welcome-description">This is free HTML5 website template from <span class="blue-text">template</span><span class="green-text">mo</span>. Fndimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Ettiam sit amet orci eget eros faucibus tincidunt.</p>
            <a href="#" class="tm-more-button margin-top-30">Read More</a> 
          </div>
          <div class="col-lg-3 col-md-3 col-sm-4 tm-welcome-img-container">
            <div class="inline-block shadow-img">
              <img src="<%=request.getContextPath() %>/img/1.jpg" alt="Image" class="img-circle img-thumbnail">  
            </div>              
          </div>            
        </section>          
        <section class="tm-section row">
          <div class="col-lg-12 tm-section-header-container margin-bottom-30">
            <h2 class="tm-section-header gold-text tm-handwriting-font"><img src="<%=request.getContextPath() %>/img/logo.png" alt="Logo" class="tm-site-logo"> Our Menus</h2>
            <div class="tm-hr-container"><hr class="tm-hr"></div>
          </div>
          <div>
            <div class="col-lg-3 col-md-3">
              <div class="tm-position-relative margin-bottom-30" style="background-color: white;">       
              	      
                <nav class="tm-side-menu" style="margin-left: -10px">
                
                  <ul>
                  <%
	                  CategoriesDAO categoriesDAO = new CategoriesDAO();
	                  ArrayList<categories> list = categoriesDAO.getCategories();
	                  if(list!=null && list.size()>0){
                  			for(categories item : list){
                  %>
                    <li><a href="<%=request.getContextPath()%>/menu?cid=<%=item.getId()%>"><%=item.getName() %></a></li>
                    <%}} %>
                  </ul>              
                </nav>    
              </div>  
            </div>    
               <div class="tm-product">
               
	              	<form id="myform" action="" method="post" enctype="multipart/form-data">
	              		<div class="menu1" style="margin-bottom: 5px">
	              			<label>Name: </label>
	              			<input type="text" name="name" value="" required="">
	              			
	              		</div>
	              		<br>
	              		<div class="menu1" style="margin-bottom: 5px">
	              			<label>Detail: </label>
	              			<input type="text" name="detail" value="" required="" >
	              		</div>
	              		<br>
	              		<div class="menu1">
	              			<label>Picture: </label>
	              			<input type="file" name="picture" id="file" required="" onchange="return fileValidation()">
	  
	              		</div>
	              		
	              		<br>
	              		
	              		<input type="submit" value="Add">
	              	</form>
	              </div>
          </div>          
        </section>
      </div>
    </div> 
 
    <script>
    $("#myform").validate({
    	  rules: {
    	    name: "required",
    	    email: {
    	      required: true,
    	      email: true
    	    },
    	    
    	  },
    	  messages: {
    	    name: "Please specify your name",
    	    email: {
    	      required: "We need your email address to contact you",
    	      email: "Your email address must be in the format of name@domain.com"
    	    },
    	    
    	  }
    	});
</script>
<script>
function fileValidation() {
    var fileInput =
        document.getElementById('file');
     
    var filePath = fileInput.value;
 
    // Allowing file type
    var allowedExtensions =
            /(\.jpg|\.jpeg|\.png|\.gif)$/i;
     
    if (!allowedExtensions.exec(filePath)) {
        alert('Invalid file type');
        fileInput.value = '';
        return false;
    }
    
}
</script>
    <script>
    	document.getElementById("menu").classList.add('active');
	</script>
<%@include file="/inc/footer.jsp" %>