<%@page import="model.bean.menu"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/inc/header.jsp" %>
    <div class="editor" style="margin-top:50px;margin-left:500px ;height: 200px;margin-bottom: 80px;"> <!-- menu content -->
		<h3 style="font-weight: bold;">Order</h3>
		<%
			if(request.getAttribute("item") != null){
				menu item = (menu) request.getAttribute("item");
		%>
      <form action="<%=request.getContextPath() %>/order" method="post" style="width: 300px; height: 300px" >
      	<div style="height: 25px; margin-bottom: 10px">
      		<label style="float: left;">Name</label>
      		<input type="text" name="name" value="<%=item.getName()%>" class="username" style="width: 200px; float: right">
      	</div>
      	<div style="height: 25px; margin-bottom: 10px">
      		<label style="float: left;">Price</label>
      		<input type="text" value="<%=item.getPrice() %>" name="price" class="username" style="width: 200px; float: right">
      	</div>
      	<div style="height: 25px; margin-bottom: 10px">
      		<label style="float: left;">Number</label>
      		<input type="number" name="number" value="" class="username" style="width: 200px; float: right">
      	</div>
      	
      	
      	<button class="tm-more-button tm-more-button-welcome" type="submit" style="height: 45px;">Order</button>
      </form>
      <%} %>
    </div>

    <script type="text/javascript">
   		document.getElementById("info").classList.add('active');
	</script>
	<%@include file="/inc/footer.jsp"%>