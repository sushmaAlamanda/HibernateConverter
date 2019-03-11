<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
     <%
		System.out.println("data is");
		System.out.println(request.getParameter("dialog"));
		String payment_type = request.getParameter("dialog");
		System.out.println("param is "+payment_type);
		String payment_amount = request.getParameter("amount");
		if(payment_type.equals("CardPayment")){
			System.out.println("ifffffffffffffffffff");
	%>
	<div class="form-group" id="cardpopup_val">
		<label><input type="radio"  name="card_type_popup"  value="Visa" id="card_typepopup"> <img  src="${pageContext.request.contextPath}/resources/images/2.png" alt="Visa" style="width:70px;height:50px;"> </label>&nbsp;&nbsp;&nbsp;&nbsp;
		<label><input type="radio" name="card_type_popup"  value="Master" id="card_typepopup">   <img  src="${pageContext.request.contextPath}/resources/images/1.png" alt="Master" style="width:70px;height:50px;"> </label>&nbsp;&nbsp;&nbsp;&nbsp;
		<label><input type="radio" name="card_type_popup"  value="AmericanExpress" id="card_typepopup">  <img   src="${pageContext.request.contextPath}/resources/images/3.png" alt="AmericanExpress" style="width:70px;height:50px;"></label>&nbsp;&nbsp;&nbsp;&nbsp;
		<label><input type="radio" name="card_type_popup"  value="Discover" id="card_typepopup">  <img  src="${pageContext.request.contextPath}/resources/images/4.png" alt="Discover" style="width:70px;height:50px;"></label>&nbsp;&nbsp;&nbsp;&nbsp;
		<label><input type="radio" name="card_type_popup"  value="Mastero" id="card_typepopup">  <img  src="${pageContext.request.contextPath}/resources/images/5.png" alt="Mastero" style="width:70px;height:50px;"></label>&nbsp;&nbsp;&nbsp;&nbsp;
		<label><input type="radio" name="card_type_popup"   value="VisaElectronic" id="card_typepopup">  <img  src="${pageContext.request.contextPath}/resources/images/6.png" alt="VisaElectronic" style="width:70px;height:50px;"></label>&nbsp;&nbsp;&nbsp;&nbsp;
		<label><input type="radio" name="card_type_popup"   value="Cirrus" id="card_typepopup">  <img src="${pageContext.request.contextPath}/resources/images/7.png" alt="Cirrus" style="width:70px;height:50px;"></label>&nbsp;&nbsp;&nbsp;&nbsp;
		<label><input type="radio" name="card_type_popup" value="Solo" id="card_typepopup">  <img  src="${pageContext.request.contextPath}/resources/images/8.png" alt="Solo" style="width:70px;height:50px;"></label>
		<input type="hidden" class="form-control" id="card_amount" placeholder="Enter card amount" readonly value="<%=payment_amount %>" />
	</div>
	<%} else if(payment_type.equals("EwalletPayment")){System.out.println("elseeeeeeeeeeeeeeeeeee"); %>
	<div class="form-group" id="cardpopup_val">
		<label><input type="radio"  name="wallet_type_popup"  value="Paytm"  id="ewallet_typepopup"> <img  src="${pageContext.request.contextPath}/resources/images/paytm.jpg" alt="Paytm" style="width:107px;height:59px;"> </label>&nbsp;&nbsp;&nbsp;&nbsp;
		
		<input type="hidden" class="form-control" id="ewallet_amount" placeholder="Enter card amount" readonly value="<%=payment_amount %>" />
	</div>
	<%} %>
</body>
</html>