$(function(){
	$('#imagetool').click(function(){
		var timenow = new Date().getTime();
		$(this).attr("src", "${cp }/imagetool?date=" + timenow);
	});
	
    /*$("#loginForm").validate({
        errorPlacement: function(error, element) {
			error.appendTo( element.parent("span").next("span") );
		},
		rules: {
            j_username: "required",
            j_password: "required",
            lengthReal: {
				required : true,
				rangelength : [4,4]
			}
        },
        messages: {
            j_username: "用户名不能为空!",
            j_password: "密码不能为空!",
            lengthReal: {
        		required : "验证码不能为空!",
        		rangelength : "验证码只能是4个字符!"
        	}
        }
    
    });*/
   /* $(".login_text")[0].focus();
    $(".login_text").change(function(){
    	$("#user_error_message").each(function(){
    		$(this).text("");
    	});
    });
    $(".login_texta").change(function(){
    	$("#lengthReal_error_message").each(function(){
    		$(this).text("");
    	});
    });*/
});