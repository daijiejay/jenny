$(function() {
	if(/(iPhone|iPad|iPod|iOS|Android)/i.test(navigator.userAgent)) { //移动端
		ele = $('#form-mobile');
		$('#form-mobile').show();
		$('#form-computer').hide();
		$('body').removeClass('login-body');
	} else {
		ele = $('#form-computer');
		$('#form-mobile').hide();
		$('#form-computer').show();
		$('body').addClass('login-body');
	}
//	if($.cookie('password') != '') {
//		ele.find('input[name="password"]').val($.cookie("password"));
//	}
//	if($.cookie('username') != '') {
//		ele.find('input[name="username"]').val($.cookie("username"));
//	}
//	if($.cookie('remember')) {
//		ele.find('input[type="checkbox"]').attr('chacked', $.cookie("remember"));
//	}
	valid(ele.find('form'));
	ele.find('button').click(function() {
		if(ele.find('form').valid()) {
			login(ele.find('form'));
		}
	});
	$(window).keydown(function(event) {
		if(event.keyCode == 13) {
			if(ele.find('form').valid()) {
				login(ele.find('form'));
			}
		}
	});
});
var ele;

//登录
function login(form) {
	effect();
	var formData = form.serializeJson();
	formData.password = encrypt.encrypt(formData.password);
	request('post', formData, '/syslogin', 'SYS', function(result) {
		success = true;
		code = result.code;
	});
}

var success = false,
	code;

function effect() {
	ele.find('button').addClass('processing');
	setTimeout(function() {
		if(!success) {
			effect();
		} else {
			if(code == '200') {
				ele.find('button').addClass('success');
//				if ($('input[type="checkbox"]').length == 1) {
//					$.cookie('remember', $('input[type="checkbox"]').is(':checked'));
//				}
//				if($('input[type="checkbox"]').length == 1 && $('input[type="checkbox"]').is(':checked')) {
//					$.cookie('username', ele.find('input[name="username"]').val());
//					$.cookie('password', ele.find('input[name="password"]').val(), {
//						expires: 7
//					});
//				} else {
//					$.cookie("username", "");
//					$.cookie("password", "");
//				}
				setTimeout(function() {
					location.href = 'index.html';
				}, 100);
			} else {
				ele.find('button').removeClass('processing');
			}
		}
	}, 300);
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
				minlength: 6
			}
		},
		errorPlacement: function(error, element) {
			var ele = element.parent();
			ele.removeClass('has-success');
			ele.addClass('has-error');
		},
		success: function(label) {
			form.find('input[name="' + label[0].htmlFor + '"]').parent().addClass('has-success');
			form.find('input[name="' + label[0].htmlFor + '"]').parent().removeClass('has-error');
		}
	});
}

//加载加密公钥
loadPublicKey();
var encrypt = new JSEncrypt();

function loadPublicKey() {
	request('get', '', '/publicKey', 'SYS', function(result) {
		encrypt.setPublicKey(result.data);
	});
}