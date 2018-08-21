$(function() {
	$("#sysServerTable").initTable({
		searchTarget: '.table-search',
		listenModalSave: function(modal, action) {
			if (action.mutualType == 'FORM') {
				var form = modal.find('form');
				validAdd(form);
				return form.valid();
			}
		},
		listenModalShow: function(modal) {
			modal.find('form div').removeClass('has-success').removeClass('has-error');
			modal.find('.help-block').html('');
			$('#iconShow').removeAttr('class');
		},
		columnFormatter: function(value, row, index, field) {
			if (field == 'class') {
				return '<i class="'+row.iconCode+'"></i>';
			}
			return value;
		}
	});
});

//服务添加表单检验
function validAdd(form) {
	form.validate({
		ignore: "",
		rules: {
			serverId: {
				required: true,
				maxlength: 50
			},
			serverAddr: {
				required: true,
				maxlength: 255
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
}


