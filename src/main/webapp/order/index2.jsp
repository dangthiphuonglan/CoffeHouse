<%@page import="model.bean.menu"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/inc/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="editor" style="margin-top: 50px; margin-left: 500px;">
	<!-- menu content -->
	<h3 style="font-weight: bold;">Order</h3>
	<%
	HttpSession session2 = request.getSession();
	user itemLogin = (user) session2.getAttribute("user");
	ArrayList<menu> itemDH = new ArrayList<>();
	if (request.getAttribute("listspMua") != null) {
		itemDH = (ArrayList<menu>) request.getAttribute("listspMua");
	}
	
	String sl = "";
	if(request.getAttribute("sl") != null){
		sl = (String)request.getAttribute("sl");
	}
	%>


	<form action="<%=request.getContextPath()%>/order" method="post"
		style="width: 300px">
		<%
		for (menu item : itemDH) {
		%>
		<div style="border-bottom: 1px dotted black; margin-bottom: 10px; padding 5px ">
			<div style="height: 25px; margin-bottom: 10px">
				<label style="float: left;">Name</label> <input type="text"
					name="name" value="<%=item.getName()%>" class="username"
					style="width: 200px; float: right" readonly>
			</div>
			<div style="height: 25px; margin-bottom: 10px">
				<label style="float: left;">Price</label> <input type="text"
					value="<%=item.getPrice()%>" name="chiphi" class="username"
					style="width: 200px; float: right" dreadonlyisabled="disabled">
			</div>
			<div style="height: 25px; margin-bottom: 10px">
				<label style="float: left;">Number</label> <input type="number"
					name="soluong" value="<%if(!sl.equals("")) out.print(sl);%>" class="username"
					style="width: 200px; float: right">
			</div>
			
		</div>
		<%
		}
		%>
		
		<div>
				<label>Tên khách hàng:</label>
				<input type="text" name="tenkh" value="<%if(itemLogin != null) out.print(itemLogin.getName()); %>" size="40" style="margin-bottom: 5px" class="wpcf7-form-control wpcf7-text wpcf7-email wpcf7-validates-as-required wpcf7-validates-as-email" aria-required="true" aria-invalid="false">
				<label>Địa chỉ:</label>
				<input type="text" name="dc" value="" size="40" style="margin-bottom: 5px" class="wpcf7-form-control wpcf7-text wpcf7-email wpcf7-validates-as-required wpcf7-validates-as-email" aria-required="true" aria-invalid="false" placeholder="Địa chỉ">
				<label>Số điện thoại:</label>
				<input type="text" name="sdt" value="" size="40" style="margin-bottom: 5px" class="wpcf7-form-control wpcf7-text wpcf7-email wpcf7-validates-as-required wpcf7-validates-as-email" aria-required="true" aria-invalid="false" placeholder="Số điện thoại">
				<label>Email:</label>
				<input readonly type="text" name="email" value="<%if(itemLogin != null) out.print(itemLogin.getEmail()); %>" size="40" style="margin-bottom: 5px" class="wpcf7-form-control wpcf7-text wpcf7-email wpcf7-validates-as-required wpcf7-validates-as-email" aria-required="true" aria-invalid="false" placeholder="Email">
						
			</div>

		<button class="tm-more-button tm-more-button-welcome" type="submit"
			style="height: 45px;color: white; font-weight: bold">Order</button>
	</form>

</div>

<script type="text/javascript">
	document.getElementById("info").classList.add('active');
</script>
<%@include file="/inc/footer.jsp"%>