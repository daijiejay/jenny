$(function() {
	valid($('.form-signin'));
	$("#login").click(function() {
		if($('.form-signin').valid()) {
			login($('.form-signin'));
		}
	});
});

//登录
function login(form) {
	var formData = form.serializeJson();
	formData.password = encrypt.encrypt(formData.password);
	request('post', formData, '/syslogin', function(result) {
		location.href = 'index.html';
	});
}

//登录表单校验
function valid(form) {
	form.validate({
		ignore: "",
		rules: {
			username: {
				required: true,
				maxlength: 20
			},
			password: {
				required: true,
				isPassword: true
			}
		},
		errorPlacement: function(error, element) {
			var ele = element.parent();
			ele.removeClass('has-success');
			ele.addClass('has-error');
		},
		success: function(label) {
			$('#'+label[0].htmlFor).parent().addClass('has-success');
			$('#'+label[0].htmlFor).parent().remove('has-error');
		}
	});
}

//加载加密公钥
loadPublicKey();
var encrypt = new JSEncrypt();
function loadPublicKey() {
	request('get', '', '/publicKey', function(result) {
		encrypt.setPublicKey(result.data);
	});
}
