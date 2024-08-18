<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.bean.thongtindonhang"%>
<%@page import="model.bean.chitietdonhang"%>
<%@page import="model.dao.ThongTinDonHangDAO"%>
<%@page import="model.dao.ChiTietDonHangDAO"%>
<%@page import="model.bean.giohang"%>
<%@page import="model.dao.GioHangDAO"%>
<%@page import="model.dao.MenuDAO"%>
<%@page import="model.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.bean.menu"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/inc/header.jsp" %>
    <div class="editor" style="margin: 95px auto;width:1000px;margin-bottom: 80px;"> <!-- menu content -->
    <%
    	 int err = 0;
    	if(request.getAttribute("err") != null){
    		err = (Integer)request.getAttribute("err");
    	}
    	switch(err){
    		case 1:
    			%>
    			<p style="color: red;font-style: italic;">Chọn sản phẩm để mua</p>
    			<%
    			break;
    	}
    %>
    <form action="<%=request.getContextPath() %>/public/datnhieuhang" method="post" style="margin: 0px auto;margin-bottom: 20px;  text-align: center;">
		<h1>Giỏ hàng</h1>
		<table style="border: 1px solid #ccc; text-align: center; width:900px;">
				<tr>
					<th style="text-align: center;">Stt</th>
					<th style="text-align: center;">Tên sản phẩm</th>
					<th style="text-align: center;">Số lượng</th>
					<th style="text-align: center;">Chọn</th>
				</tr>
		<%
		HttpSession session2 = request.getSession();
		user itemLogin = (user)session2.getAttribute("user");
		MenuDAO menuDAO = new MenuDAO();
			GioHangDAO gioHangDAO = new GioHangDAO();
			ArrayList<giohang> listgh = gioHangDAO.getList();
			int j = 0;
			for(giohang item : listgh){
				if(item.getId_user() == itemLogin.getId()){
				MenuDAO sanPhamDAO = new MenuDAO();
				menu itemSp = sanPhamDAO.getItem(item.getId_sanpham());
				j++;
		%>
			<tr>
				<td><%=j %></td>
				<td><%=itemSp.getName()  %></td>
				<td><input name="sl" type="text" style="border: none; text-align: center" value="<%=item.getSoluong() %>" readonly="readonly"></td>
				<td>
					<input name="idsp" type="checkbox" value="<%=itemSp.getId()%>">
				</td>
			</tr>
		<%}} %>
		</table>
		<input style="margin-top: 10px; color: white; background: red; width: 50px" value="Mua" type="submit">
	</form>
    
		<h3 style="font-weight: bold;">Đang chờ xử lý</h3>
		<%
		ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
		ThongTinDonHangDAO thongTinDonHangDAO = new ThongTinDonHangDAO();
		ArrayList<chitietdonhang> listdh = new ArrayList<>();
		UserDAO userDAO = new UserDAO();
		

		if(itemLogin.isRole() == 1){
			listdh = chiTietDonHangDAO.getListDH();
		}else{
			ArrayList<thongtindonhang> listDhByIdKH = thongTinDonHangDAO.getItemByIdKH(itemLogin.getId());
			for(thongtindonhang itDHByIdKh : listDhByIdKH){
				if(itDHByIdKh.getTrangthai() != 2){
					listdh.add(chiTietDonHangDAO.getDHByIdDH(itDHByIdKh.getId()));
				}
			}
		}
	%>
		<table border="1px solid black" style="text-align: center;">
			<tr>
				<th width="200px" style="text-align: center; font-size: 16px">Tên sản phẩm</th>
				<th width="200px" style="text-align: center; font-size: 16px">Tên khách hàng</th>
				<th width="100px" style="text-align: center; font-size: 16px">Số lượng</th>
				<th width="200px" style="text-align: center; font-size: 16px">Tổng tiền</th>
				<th width="100px" style="text-align: center; font-size: 16px">Tình trạng</th>
			</tr>
			
			<%
				if(listdh!=null && listdh.size()>0){
					int i = 0;
				for(chitietdonhang item : listdh){
					menu itemsp = menuDAO.getItem(item.getId_sanpham());
					
					i++;
					menu itSP = menuDAO.getItem(item.getId_sanpham());
					thongtindonhang itDH = thongTinDonHangDAO.getItemById(item.getId_thongtindonhang());
					user nguoimua = userDAO.getUserById(itDH.getId_nguoimua());
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					String datemua = format.format(itDH.getNgaymua());
					
			%>
			
			<tr>
				<td><%=itSP.getName() %></td>
				<td><%=nguoimua.getName()%></td>
				<td><%=item.getSoluong() %></td>
				<td><%=item.getDongia() %></td>

				<%
				if(itemLogin.isRole() == 1 && itDH.getTrangthai() == 0){
			%>
				<td style="background-color: #f0e7da;"><a href="<%=request.getContextPath()%>/public/giohang/adactive?iddh=<%=itDH.getId()%>">Xác nhận đơn hàng</a></td>
			<%} %>
			<%
				if(itemLogin.isRole() == 2 && itDH.getTrangthai() == 0){
			%>
				<td style="background-color: #f0e7da;"><p style="color: blue; font-style: italic;">Đang chờ xác nhận</p></td>
			<%} %>
			<%
				if(itemLogin.isRole() == 1 && itDH.getTrangthai() == 1){
			%>
				<td style="background-color: #f0e7da;"><a href="<%=request.getContextPath()%>/public/giohang/xngh?iddh=<%=itDH.getId()%>">Xác nhận giao hàng</a></td>
			<%} %>
			<%
				if(itemLogin.isRole() == 2 && itDH.getTrangthai() == 1){
			%>
				<td style="background-color: #f0e7da;"><p style="color: blue; font-style: italic;">Chờ nhận hàng</p></td>
			<%} %>
			<%
				if(itemLogin.isRole() == 2 && itDH.getTrangthai() == 2){
			%>
				<td style="background-color: #f0e7da;"><p style="color: green; font-style: italic;font-weight: bold;">Đã giao</p></td>
			<%} %>
			<%
				if(itemLogin.isRole() == 1 && itDH.getTrangthai() == 2){
			%>
				<td style="background-color: #f0e7da;"><p style="color: green; font-style: italic;font-weight: bold;">Đã nhận</p></td>
			<%}}
				}else{ %>
				<p>Không có sản phẩm nào đang chờ xử lý</p>
			<%} %>	
				</tr>
					
		</table>
		
    </div>


	<%@include file="/inc/footer.jsp"%>