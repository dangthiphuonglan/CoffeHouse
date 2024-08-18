<%@page import="java.util.ArrayList"%>
<%@include file="/inc/header.jsp" %>
	<%
		if(request.getAttribute("userEdit")!=null){
			user item = (user) request.getAttribute("userEdit");

	%>
    <div class="editor" style="margin-top:50px;margin-left:500px ;height: 200px;margin-bottom: 50px;"> <!-- menu content -->
		<h3 style="font-weight: bold;">EDIT USER</h3>
      <form action="" method="post" style="width: 300px; height: 300px" >
      	<div style="height: 25px; margin-bottom: 10px">
      		<label style="float: left;">Nick name</label>
      		<input type="text" name="username" value="<%=item.getName() %>" class="username" style="width: 200px; float: right">
      	</div>
      	<div style="height: 25px; margin-bottom: 10px">
      		<label style="float: left;">Password</label>
      		<input type="text" value="<%=item.getPass()%>" name="password" class="username" style="width: 200px; float: right">
      	</div>
      	<div style="height: 25px; margin-bottom: 10px">
      		<label style="float: left;">Email</label>
      		<input type="email" name="email" value="<%=item.getEmail()%>" class="username" style="width: 200px; float: right" disabled="disabled">
      	</div>
      	
      	<button class="tm-more-button tm-more-button-welcome" type="submit" style="height: 45px;color:white; font-weight: bold">Save</button>
      </form>
    </div>
    <%} %>
    <script type="text/javascript">
   		document.getElementById("info").classList.add('active');
	</script>
	<%@include file="/inc/footer.jsp"%>