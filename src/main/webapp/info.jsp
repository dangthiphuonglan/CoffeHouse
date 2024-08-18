<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp"%>
<div class="tm-menu-product-content col-lg-9 col-md-9">
	<!-- menu content -->
	<%
	HttpSession ss = request.getSession();
	user ul = (user) ss.getAttribute("user");
	String msg = "";
	if(request.getParameter("msg") != null) {
		msg = (String)request.getParameter("msg");
	}
	if(msg.equals("1")){
	%>
		<div class="tm-product">
			<p style="color:green; font-weight: bold">Cập nhật thành công</p>
	</div>
	<%} %>
	<div class="tm-product">
		<h3 style="color:#106512">Tài khoản cá nhân:</h3>
	</div>
	<div class="tm-product">
		<img src="<%=request.getContextPath()%>/img/Avatar-Facebook-trắng.jpg"
			style="width: 100px" alt="Product">
		<div class="tm-product-text">
			<h3 class="tm-product-title">
				Nick name:
				<%=ul.getName()%></h3>
			<p class="tm-product-description">
				Email:
				<%=ul.getEmail()%></p>
			<p class="tm-product-description">
				Password:
				<%=ul.getPass()%></p>
		</div>
		<div class="tm-product-price">
			<a href="<%=request.getContextPath()%>/info/edit?id=<%=ul.getId()%>"
				class="tm-more-button" type="submit" name="submit"
				style="background: none">Edit</a>
		</div>
	</div>
	<%
	if (ul.isRole() == 0) {
	%>
	<div class="tm-product">

		<form action="" method="post">
			<label>Search:</label> <input type="text" name="searchinfo">
			<input type="submit" value="Search">
		</form>

	</div>
	<div class="tm-product">
		<a href="<%=request.getContextPath()%>/admin/updateEmpl">+ Cập
			nhật Nhân viên</a>
	</div>

	<%
	}
	if(ul.isRole() == 0){
	%>
		<div class="tm-product">
		<h3 style="color: blue; font-weight: bold">Danh sách nhân viên</h3>
	</div>
	<%
	}else{
		if(ul.isRole() == 1){
		%>
		<div class="tm-product">
		<h3 style="color: green; font-weight: bold">Danh sách khách hàng</h3>
	</div>
		<%
		}
	}
	ArrayList<user> listtk = (ArrayList) request.getAttribute("listtk");
	if (listtk != null && listtk.size() > 0) {
		for (user item : listtk) {
			if (ul.getId() != item.getId()) {
	%>
	<div class="tm-product">
		<img src="<%=request.getContextPath()%>/img/Avatar-Facebook-trắng.jpg"
			style="width: 100px" alt="Product">
		<div class="tm-product-text">
			<h3 class="tm-product-title">
				Nick name:
				<%=item.getName()%></h3>
			<p class="tm-product-description">
				Email:
				<%=item.getEmail()%></p>
			<p class="tm-product-description">
				Password:
				<%=item.getPass()%></p>
		</div>
		<div class="tm-product-price">
			<%
			if (item.isRole() == ul.isRole()) {
			%>
			<a href="<%=request.getContextPath()%>/info/del?id=<%=item.getId()%>"
				class="tm-more-button" type="submit" name="submit"
				style="background: none; color: red;"
				onclick="return confirm('bạn có chắc muốn xóa không!!!');">Xóa</a> <a
				href="<%=request.getContextPath()%>/info/edit?id=<%=item.getId()%>"
				class="tm-more-button" type="submit" name="submit"
				style="background: none; color: green;"
				onclick="return confirm('bạn có chắc muốn xóa không!!!');">Sửa</a>
			<%
			} else {
			%>
			<a href="<%=request.getContextPath()%>/info/del?id=<%=item.getId()%>"
				class="tm-more-button" type="submit" name="submit"
				style="background: none; color: red;">Delete</a>
			<%
			}
			%>
		</div>
	</div>
	<%
	}
	}
	} else {
	if (request.getAttribute("userList") != null) {
	ArrayList<user> list = (ArrayList) request.getAttribute("userList");
	for (user item : list) {
		if(ul.getId() != item.getId()){
	%>
		<div class="tm-product">
		<img src="<%=request.getContextPath()%>/img/Avatar-Facebook-trắng.jpg"
			style="width: 100px" alt="Product">
		<div class="tm-product-text">
			<h3 class="tm-product-title">
				Nick name:
				<%=item.getName()%></h3>
			<p class="tm-product-description">
				Email:
				<%=item.getEmail()%></p>
		</div>
		<div class="tm-product-price">
			<%
			if (item.isRole() == ul.isRole()) {
			%>
			<a href="<%=request.getContextPath()%>/info/del?id=<%=item.getId()%>"
				class="tm-more-button" type="submit" name="submit"
				style="background: none; color: red;"
				onclick="return confirm('bạn có chắc muốn xóa không!!!');">Xóa</a> <a
				href="<%=request.getContextPath()%>/info/edit?id=<%=item.getId()%>"
				class="tm-more-button" type="submit" name="submit"
				style="background: none; color: green;"">Sửa</a>
			<%
			} else {
			%>
			<a href="<%=request.getContextPath()%>/info/del?id=<%=item.getId()%>"
				class="tm-more-button" type="submit" name="submit"
				style="background: none"
				onclick="return confirm('bạn có chắc muốn xóa không!!!');">Delete</a>
			<%
			}
			%>
		</div>
	</div>
	<%
	}}
	} 
	}
	%>
</div>
<
<script type="text/javascript">
	document.getElementById("info").classList.add('active');
</script>
<%@include file="/inc/footer.jsp"%>