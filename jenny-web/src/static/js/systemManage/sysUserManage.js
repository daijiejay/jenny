$(function() {
	$("#sysUserTable").initTable({
		searchTarget: '.table-search',
		listenModalSave: function(modal, action) {
			var form = modal.find('form');
			if (action.actionName == '编辑') {
				validUpdate(form);
			} else {
				validAdd(form);
			}
			return form.valid();
		},
		columnFormatter: function(value, row, index, field) {
			if (field == 'enable') {
				if(value) {
					return '是';
				}
				return '否';
			}
			return value;
		},
		operateFormatter: function(action, row) {
			if (row.cancel) {
				return false;
			}
			if (action.actionName == '编辑' || action.actionName == '禁用'
				 || action.actionName == '设置角色') {
				return !row.enable;
			}
			if (action.actionName == '启用') {
				return row.enable;
			}
			return true;
		},
		operateExtend: function(action, row) {
			if (action.formTarget == '#setRole') {
				loadRole(row.userId);
			}
		},
		listenModalShow: function(modal) {
			modal.find('form div').removeClass('has-success').removeClass('has-error');
			modal.find('.help-block').html('');
		}
	});
});

//用户添加表单检验
function validAdd(form) {
	form.validate({
		ignore: "",
		rules: {
			userName: {
				required: true,
				maxlength: 20
			},
			mobile: {
				required: true,
				mobile: true
			},
			password: {
				required: true,
				password: true
			},
			email: {
				email: true
			},
			birthday: {
				date: true
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

//用户修改表单检验
function validUpdate(form) {
	form.validate({
		ignore: "",
		rules: {
			userName: {
				required: true,
				maxlength: 20
			},
			mobile: {
				required: true,
				mobile: true
			},
			password: {
				password: true
			},
			email: {
				email: true
			},
			birthday: {
				date: true
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

function loadRole(userId) {
	$("#sysRoleSelectedTable").initTable({
		checkbox: true,
		searchParams: function(params) {
			params.userId = userId;
			return params
		},
		listenModalSave: function(modal, action, data) {
			var requestData = {userId: userId, roleIds: new Array()};
			data.selectedRows.forEach(function(role, index) {
				requestData.roleIds.push(role.roleId);
			});
			var res = false;
			requestSynchronized('put', requestData, '/sysuser/setRoles', 'sys', false, function(result) {
				res = result.data;
			});
			return res;
		}
	});
}
