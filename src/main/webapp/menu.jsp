<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.dao.MenuDAO"%>
<%@page
	import="javax.print.attribute.standard.PrinterMoreInfoManufacturer"%>
<%@page import="model.bean.menu"%>
<%@page import="model.bean.categories"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoriesDAO"%>
<%@include file="/inc/header.jsp"%>
<div class="tm-main-section light-gray-bg">
	<div class="container" id="main">
		<section class="tm-section row">
			<div class="col-lg-9 col-md-9 col-sm-8">
				<h2 class="tm-section-header gold-text tm-handwriting-font">Variety
					of Menus</h2>
				<h2>Cafe House</h2>
				<a href="#" class="tm-more-button margin-top-30">Read More</a>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-4 tm-welcome-img-container">
				<div class="inline-block shadow-img">
					<img src="<%=request.getContextPath()%>/img/1.jpg" alt="Image"
						class="img-circle img-thumbnail">
				</div>
			</div>
		</section>
		<section class="tm-section row">
			<div class="col-lg-12 tm-section-header-container margin-bottom-30">
				<h2 class="tm-section-header gold-text tm-handwriting-font">
					<img src="<%=request.getContextPath()%>/img/logo.png" alt="Logo"
						class="tm-site-logo"> Our Menus
				</h2>
				<div class="tm-hr-container">
					<hr class="tm-hr">
				</div>
			</div>
			<div>
				<div class="col-lg-3 col-md-3">
					<div class="tm-position-relative margin-bottom-30"
						style="background-color: white;">

						<nav class="tm-side-menu" style="margin-left: -10px">

							<ul>
								<%
								CategoriesDAO categoriesDAO = new CategoriesDAO();
								ArrayList<categories> list = categoriesDAO.getCategories();
								if (list != null && list.size() > 0) {
									for (categories item : list) {
								%>
								<li><a
									href="<%=request.getContextPath()%>/menu?cid=<%=item.getId()%>"
									style="color: #d26e07;"><%=item.getName()%></a></li>
								<%
								}
								}
								%>
							</ul>
						</nav>
					</div>
				</div>
				<div class="tm-menu-product-content col-lg-9 col-md-9">
					<!-- menu content -->
					<%
					if (request.getAttribute("msg") != null) {
						String msg = (String) request.getAttribute("msg");
						if (msg.equals("2")) {
					%>
					<p style="color: green; font-weight: bold;">Xóa thành công</p>
					<%
					}

					if (msg.equals("1")) {
					%>
					<div class="tm-product">
						<p style="color: green; font-weight: bold;">Sửa thành công</p>
					</div>
					<%
					}
					}
					%>
					<div class="tm-product">
						<form action="<%=request.getContextPath()%>/menu" method="post">
							<label style="margin-left: 10px; margin-right: 10px">Search:</label>
							<input type="text" name="menuname" placeholder=""
								style="width: 200px"> <input type="submit"
								value="Search">
						</form>
					</div>

					<%
					if (request.getAttribute("err") != null) {
						int err = (Integer) request.getAttribute("err");
						if (err == 0) {
					%>
					<p style="color: red; font-weight: bold; margin-left: 30px">Đã
						hết thực đơn cho danh mục</p>
					<%
					}
					}
					HttpSession ss = request.getSession();
					user ul = (user) ss.getAttribute("user");
					if (ul.isRole() == 0) {
					%>
					<div class="tm-product" style="background-color: #ffdead00;">
						<a href="<%=request.getContextPath()%>/menu/add">Thêm</a>
					</div>
					<%
					}
					%>
					<%
						if(ul.isRole() != 0){
					%>
					<div class="tm-product" style="background-color: #ffdead00;">
						<a href="<%=request.getContextPath()%>/order/list">Giỏ hàng</a>
					</div>
					<%
						}
					if (request.getAttribute("listtk") != null) {
						ArrayList<menu> listtk = (ArrayList) request.getAttribute("listtk");
						if (listtk != null && listtk.size() > 0) {
							for (menu item : listtk) {
					%>
					<div class="tm-product">
						<img
							src="<%=request.getContextPath()%>/file/<%=item.getPicture()%>"
							style="width: 200px; height: 150px" alt="Product">
						<div class="tm-product-text" style="margin: 10px">
							<h3 class="tm-product-title">
								<a title="<%=item.getDetail()%>">Name: <%=item.getName()%></a>
							</h3>
							<p class="tm-product-description" title="<%=item.getDetail()%>">
								Detail:
								<%
							if (item.getDetail().length() > 10)
								out.print(item.getDetail().substring(0, 10));
							else
								out.print(item.getDetail());
							%>...
							</p>
							<p style="margin-top: 5px">
								Price:<%=item.getPrice()%></p>

						</div>


						<%
						HttpSession ss2 = request.getSession();
						user ul2 = (user) ss.getAttribute("user");
						if (ul2.isRole() == 0) {
						%>
						<div style="margin-top: 10px; margin-left: 30px">
							<a
								href="<%=request.getContextPath()%>/menu/editmenu?menuid=<%=item.getId()%>"
								style="float: left; align-items: center; font-size: 20px; text-decoration: none; color: blue;">Edit</a>
							<br> <a
								href="<%=request.getContextPath()%>/menu/del?menuid=<%=item.getId()%>"
								style="float: right; margin-top: 10px; align-items: center; font-size: 20px; color: blue; text-decoration: none;"
								onclick="return confirm('Bạn có chắc muốn xóa không');">Delete</a>
						</div>
						<%
						} else {
						%>
						<div class="" style="background-color: white;">
							<p>
								<a
									href="<%=request.getContextPath()%>/admin/giohang?idsp=<%=item.getId()%>"
									style="color: green; font-weight: bold; text-decoration: none;">Thêm
									vào giỏ hàng</a>
							</p>
							<p>
								<a style="color: red; font-weight: bold; text-decoration: none;">Mua
									ngay</a>
							</p>
						</div>
						<%
						}
						%>
					</div>
					<%
					}
					}
					} else {
					ArrayList<menu> listmenu = (ArrayList) request.getAttribute("listmenu");
					if (listmenu != null && listmenu.size() > 0) {
					for (menu item : listmenu) {
					%>
					<div class="tm-product">
						<img
							src="<%=request.getContextPath()%>/file/<%=item.getPicture()%>"
							style="width: 200px; height: 150px" alt="Product">
						<div class="tm-product-text" style="margin: 10px">
							<h3 class="tm-product-title">
								Name:
								<%=item.getName()%></h3>
							<p class="tm-product-description" title="<%=item.getDetail()%>">
								Detail:
								<%
							if (item.getDetail().length() > 10)
								out.print(item.getDetail().substring(0, 10));
							else
								out.print(item.getDetail());
							%>...
							</p>
							<p style="margin-top: 5px">
								Price:<%=item.getPrice()%></p>
						</div>
						
						<%
						HttpSession ss2 = request.getSession();
						user ul2 = (user) ss.getAttribute("user");
						if (ul2.isRole() == 0) {
						%>
						<div style="margin-top: 10px; margin-left: 30px">
							<a
								href="<%=request.getContextPath()%>/menu/editmenu?menuid=<%=item.getId()%>"
								style="float: left; align-items: center; font-size: 20px; text-decoration: none; color: blue;">Edit</a>
							<br> <a
								href="<%=request.getContextPath()%>/menu/del?menuid=<%=item.getId()%>"
								style="float: right; margin-top: 10px; align-items: center; font-size: 20px; color: blue; text-decoration: none;"
								onclick="return confirm('Bạn có chắc muốn xóa không');">Delete</a>
						</div>
						<%
						}else {
							%>
							<div class="" style="background-color: white;">
								<p>
									<a
										href="<%=request.getContextPath()%>/admin/giohang?idsp=<%=item.getId()%>"
										style="color: green; font-weight: bold; text-decoration: none;">Thêm
										vào giỏ hàng</a>
								</p>
								<p>
									<a style="color: red; font-weight: bold; text-decoration: none;">Mua
										ngay</a>
								</p>
							</div>
							<%
							}
							%>
						
					</div>
					<%
					}
					} else {
					MenuDAO menuDAO = new MenuDAO();
					ArrayList<menu> listmn = menuDAO.getListMenu();
					if (listmn != null && listmn.size() > 0) {
					for (menu item : listmn) {
					%>
					<div class="tm-product">
						<img
							src="<%=request.getContextPath()%>/file/<%=item.getPicture()%>"
							style="width: 200px; height: 150px" alt="Product">
						<div class="tm-product-text" style="margin: 10px">
							<h3 class="tm-product-title">
								Name:
								<%=item.getName()%></h3>
							<p class="tm-product-description" title="<%=item.getDetail()%>">
								Detail:
								<%
							if (item.getDetail().length() > 10)
								out.print(item.getDetail().substring(0, 10));
							else
								out.print(item.getDetail());
							%>...
							</p>
							<p style="margin-top: 5px">
								Price:<%=item.getPrice()%></p>
						</div>

						
						<%
						HttpSession ss1 = request.getSession();
						user ul1 = (user) ss.getAttribute("user");
						if (ul1.isRole() == 0) {
						%>
						<div style="margin-top: 10px; margin-left: 30px">
							<a
								href="<%=request.getContextPath()%>/menu/editmenu?menuid=<%=item.getId()%>"
								style="float: left; align-items: center; font-size: 20px; text-decoration: none; color: blue;">Edit</a>
							<br> <a
								href="<%=request.getContextPath()%>/menu/del?menuid=<%=item.getId()%>"
								style="float: right; margin-top: 10px; align-items: center; font-size: 20px; text-decoration: none; color: blue; text-decoration: none;"
								onclick="return confirm('Bạn có chắc muốn xóa không');">Delete</a>
						</div>
						<%
						}else {
							%>
							<div class="" style="background-color: white;">
								<p>
									<a
										href="<%=request.getContextPath()%>/admin/giohang?idsp=<%=item.getId()%>"
										style="color: green; font-weight: bold; text-decoration: none;">Thêm
										vào giỏ hàng</a>
								</p>
								<p>
									<a style="color: red; font-weight: bold; text-decoration: none;">Mua
										ngay</a>
								</p>
							</div>
							<%
							}
							%>
						
					</div>
					<%
					}
					} else {
					%>
					<div class="tm-product">
						<p>Không có dữ liệu</p>
					</div>
					<%
					}
					}
					}
					%>
				</div>
			</div>
		</section>
	</div>
</div>
<script>
	document.getElementById("menu").classList.add('active');
</script>
<%@include file="/inc/footer.jsp"%>