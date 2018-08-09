$(function() {
	$("#sysIconTable").initTable({
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

//图标添加表单检验
function validAdd(form) {
	form.validate({
		ignore: "",
		rules: {
			iconCode: {
				required: true,
				maxlength: 50
			},
			iconName: {
				required: true,
				maxlength: 20
			}
		},
		errorPlacement: function(error, element) {
			var ele = element.parent('.input-group').parent().parent();
			ele.addClass('has-error');
			ele.removeClass('has-success');
			ele.find('.help-block').html('<i class="fa fa-times-circle"></i>'+error[0].outerText);
		},
		success: function(label) {
			var ele = form.find('input[name="' + label[0].htmlFor + '"]').parent().parent().parent();
			ele.addClass('has-success');
			ele.removeClass('has-error');
			ele.find('.help-block').html('<i class="fa fa-check"></i>');
		}
	});
}


