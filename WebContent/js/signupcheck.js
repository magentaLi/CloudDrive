$("#signupForm").validate({
	submitHandler : function(form) {
		form.submit();
	}
});
$(document).ready(function() {
	$("#signupForm").validate({
		rules : {
			ruserName : {
				required : true,
				minlength : 5
			},
			rpwd : {
				required : true,
				minlength : 8
			},
			rrepwd : {
				required : true,
				minlength : 8,
				equalTo : "#rpwd"
			}
		},
		messages : {
			ruserName : {
				required : "请输入用户名",
				minlength : "用户名至少5个字符"
			},
			rpwd : {
				required : "请输入密码",
				minlength : "密码长度不能小于 8 个字母"
			},
			rrepwd : {
				required : "请输入密码",
				minlength : "密码长度不能小于 8 个字母",
				equalTo : "两次密码输入不一致"
			}
		}
	})
});