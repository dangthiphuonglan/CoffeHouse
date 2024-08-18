<%@page import="model.bean.categories"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoriesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp" %>
    <div class="tm-main-section light-gray-bg">
      <div class="container" id="main">
        <section class="tm-section row">
          <div class="col-lg-9 col-md-9 col-sm-8">
            <h2 class="tm-section-header gold-text tm-handwriting-font">The Best Coffee for you</h2>
            <h2>Cafe House</h2>
            <a href="#" class="tm-more-button margin-top-30">Read More</a> 
          </div>
          <div class="col-lg-3 col-md-3 col-sm-4 tm-welcome-img-container">
            <div class="inline-block shadow-img">
              <img src="img/1.jpg" alt="Image" class="img-circle img-thumbnail">  
            </div>              
          </div>            
        </section>          
        <section class="tm-section tm-section-margin-bottom-0 row">
          <div class="col-lg-12 tm-section-header-container">
            <h2 class="tm-section-header gold-text tm-handwriting-font"><img src="img/logo.png" alt="Logo" class="tm-site-logo"> Popular Items</h2>
            <div class="tm-hr-container"><hr class="tm-hr"></div>
          </div>
          <div class="col-lg-12 tm-popular-items-container">
          <%
          	HttpSession session2 = request.getSession();
         	user user2 = (user)session2.getAttribute("user");
          	CategoriesDAO categoriesDAO = new CategoriesDAO();
            ArrayList<categories> list = categoriesDAO.getCategories();
            if(list!=null && list.size()>0){
            	for(categories item : list){
          %>
            <div class="tm-popular-item">
              <img src="<%=request.getContextPath() %>/file/<%=item.getPicture() %>" alt="Popular" class="tm-popular-item-img" width="286px" height="150px">
              <div class="tm-popular-item-description">
                <h3 class="tm-handwriting-font tm-popular-item-title"><span class="tm-handwriting-font bigger-first-letter"><%=item.getName().substring(0, 1) %></span><%=item.getName().substring(1) %></h3><hr class="tm-popular-item-hr">
                <p><%=item.getDetail() %></p>
                <div class="order-now-container"> 
                <%if(user2.isRole() != 0){ %>
                  <a href="<%=request.getContextPath() %>/menu?cid=<%=item.getId() %>" class="order-now-link tm-handwriting-font">Order Now</a>
                  <%} %>
                </div>
              </div>              
            </div>
            <% }}%>
          </div>      
          <%
          	
            if(user2.isRole() == 0){
          %>     
          	<a href="<%=request.getContextPath() %>/cat/add" class="tm-more-button margin-top-30" style="color: white; font-weight: bold">Thêm danh mục</a> 
          <%} %>
        </section>
        
        <section class="tm-section">
          <div class="row">
            <div class="col-lg-12 tm-section-header-container">
              <h2 class="tm-section-header gold-text tm-handwriting-font"><img src="img/logo.png" alt="Logo" class="tm-site-logo"> Daily Menu</h2> 
              <div class="tm-hr-container"><hr class="tm-hr"></div> 
            </div>  
          </div>          
          <div class="row">
            <div class="tm-daily-menu-container margin-top-60">
              <div class="col-lg-4 col-md-4">
                <img src="<%=request.getContextPath()%>/img/menu-board.png" alt="Menu board" class="tm-daily-menu-img">      
              </div>            
              <div class="col-lg-8 col-md-8">
                <p>Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus.</p>
                <ol class="margin-top-30">
                  <li>Tellus eget condimentum rhoncus.</li> 
                  <li>Sem quam semper libero.</li>
                  <li>Sit amet adipiscing sem neque sed ipsum.</li> 
                  <li>Nam quam nunc, blandit vel, luctus pulvinar.</li> 
                  <li>Maecenas nec odio et ante tincidunt tempus.</li> 
                  <li>Donec vitae sapien ut libero ventenatis faucibus.</li> 
                </ol>
                <a href="#" class="tm-more-button margin-top-30">Read More</a>    
              </div>
            </div>
          </div>          
        </section>
      </div>
    </div> 
    <<script type="text/javascript">
    	document.getElementById("home").classList.add('active');
	</script>
    <%@include file="/inc/footer.jsp"%>