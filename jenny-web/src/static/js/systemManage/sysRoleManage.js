$(function() {
	$("#sysRoleTable").initTable({
		searchTarget: '.table-search',
		listenModalSave: function(modal, action) {
			if (action.mutualType == 'FORM') {
				var form = modal.find('form');
				validAdd(form);
				return form.valid();
			} else if (action.mutualType == 'EXTEND') {
				if (action.formTarget == '#setMenu') {
					return saveSetMenu();
				}
			}
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
		},
		operateExtend: function(action, row) {
			if (action.formTarget == '#setMenu') {
				loadMenuTree(row);
			}
		}
	});
});

//角色添加表单检验
function validAdd(form) {
	form.validate({
		ignore: "",
		rules: {
			roleCode: {
				required: true,
				maxlength: 20
			},
			roleName: {
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

var treeName = 'tree';
function loadMenuTree(row) {
	$("#setMenu #roleCode").html(row.roleCode)
	$("#setMenu #roleName").html(row.roleName)
	if (treeName.indexOf('_') > 0) {
		treeName = treeName.substring(0, treeName.indexOf('_'));
	}
	treeName += '_' + row.roleId;
	var setting = {
		treeId: treeName,
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true,
                idKey: 'id',
                pIdKey: 'pid'
			}
		}
	};
	request('get', '', '/sysrole/query/selectedMenus/'+row.roleId, 'SYS', function(result) {
		$.fn.zTree.init($("#selectedMenu"), setting, result.data);
	});
}

function saveSetMenu() {
	var ztree = $.fn.zTree.getZTreeObj(treeName);
	var nodes = ztree.getCheckedNodes(true);
	var data = {}, 
		operateIds = new Array(), sysOperateAuthorizedRequests = new Array(),
		menuIds = new Array(), sysMenuAuthorizedRequests = new Array();
	data.roleId = treeName.substring(treeName.indexOf("_")+1);
	nodes.forEach(function(node) {
		if (node.id > 0) {
			var menu = {};
			menu.menuId = node.id;
			sysMenuAuthorizedRequests.push(menu);
			menuIds.push(menu.menuId);
		} else {
			var operate = {};
			operate.roleId = data.roleId;
			operate.menuId = node.pid;
			operate.operateId = node.id*-1;
			sysOperateAuthorizedRequests.push(operate);
			operateIds.push(operate.operateId);
		}
	})
	data.menuIds = menuIds;
	data.operateIds = operateIds;
	data.sysOperateAuthorizedRequests = sysOperateAuthorizedRequests;
	data.sysMenuAuthorizedRequests = sysMenuAuthorizedRequests;
	var res = false;
	requestSynchronized('PUT', data, '/sysrole/update/selectedMenus', 'SYS', false, function(result) {
		res = result.data;
		ztree.destroy();
	});
	return res;
}
