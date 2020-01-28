$(function(){
	$("form").submit(function(e){
		e.preventDefault();
		
		//this : form
		$(this).find("input[type=password]").each(function(){
			//this : 입력창
			var text = $(this).val();
			text = CryptoJS.HmacSHA256(text,"kh");
			text = CryptoJS.enc.Base64.stringify(text);
			$(this).val(text);
		})
	})
});