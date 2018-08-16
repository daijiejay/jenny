$(function() {
	$('#setPassword').find('button.save').click(function() {
		var form = $('#setPassword').find('form');
		form.validate({
			ignore: "",
			rules: {
				oldPassword: {
					required: true,
					minlength: 6
				},
				newPassword: {
					required: true,
					password3: true
				},
				rePassword: {
					required: true,
					equalTo: 'input[name="newPassword"]'
				}
			},
			errorPlacement: function(error, element) {
				var ele = element.parents('.form-group');
				ele.addClass('has-error');
				ele.removeClass('has-success');
				ele.find('.help-block').html('<i class="fa fa-times-circle"></i>'+error[0].outerText);
			},
			success: function(label) {
				var ele = form.find('input[name="' + label[0].htmlFor + '"]').parents('.form-group');
				ele.addClass('has-success');
				ele.removeClass('has-error');
				ele.find('.help-block').html('<i class="fa fa-check"></i>');
			}
		});
		if (form.valid()) {
			var formData = form.serializeJson();
			formData.oldPassword = encrypt.encrypt(formData.oldPassword);
			formData.newPassword = encrypt.encrypt(formData.newPassword);
			request('put', formData, '/sysuser/update/password', 'SYS', function(result) {
				if(result.code == '200') {
					layer.alert("修改成功");
					$('#setPassword').modal('hide');
				}
			});
		}
	});
});

//加载加密公钥
loadPublicKey();
var encrypt = new JSEncrypt();

function loadPublicKey() {
	request('get', '', '/publicKey', 'SYS', function(result) {
		encrypt.setPublicKey(result.data);
	});
}