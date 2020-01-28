<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>결제 확인 페이지</h1>
<form action="confirm" method="post">
	<input type="text" name="partner_order_id" value="1234"><br><br>
	<input type="text" name="partner_user_id" value="1000"><br><br>
	<input type="text" name="item_name" value="강남아파트"><br><br>
	<input type="text" name="quantity" value="1"><br><br>
	<input type="text" name="total_amount" value="999000"><br><br>
	<input type="text" name="vat_amount" value="99900"><br><br>
	<input type="text" name="tax_free_amount" value="0"><br><br>
	<input type="submit" value="결제하기">
</form>