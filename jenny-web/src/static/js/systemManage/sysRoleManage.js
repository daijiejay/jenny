$(function() {
	$("#sysRoleTable").initTable({
		searchTarget: '.table-search',
		listenModalSave: function(modal, action) {
			var form = modal.find('form');
			validAdd(form);
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
				 || action.actionName == '设置菜单') {
				return !row.enable;
			}
			if (action.actionName == '启用') {
				return row.enable;
			}
			return true;
		}
	});
});

//角色添加表单检验
function validAdd(form) {
	form.validate({
		ignore: "",
		rules: {
			roleName: {
				required: true,
				maxlength: 20
			}
		},
		errorPlacement: function(error, element) {
			var ele = element.parent('.input-group').parent();
			if(ele.find('label.error')) {
				ele.find('label.error').remove();
			}
			ele.append(error[0].outerHTML);
		}
	});
}