$(function() {
	initZTree();
	
	dynamicIconsElement = $('#icon').fontIconPicker({
		source: fa_icons,
		searchSource: fa_icon_search,
		useAttribute: true,
		attributeName: 'data-icomoon',
		theme: 'fip-bootstrap',
		iconsPerPage: 10,
		emptyIcon: true,
        hasSearch: true
	});
		
	$('#menuForm').find('.submit').click(function() {
		var formData = $('#menuForm').serializeJson();
		if (!formData.menuId) {
			layer.alert('请选中左侧菜单进行编辑保存！')
			return;
		}
		request('put', formData, '/sysmenu/update', 'SYS');
	});
});
var dynamicIconsElement;
var treeName = 'tree';

/**
 * 初始化菜单树
 */
function initZTree() {
	var setting = {
		treeId: treeName,
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
                idKey: 'id',
                pIdKey: 'pid'
			}
		},
		edit: {
			enable: true,
			renameTitle: '修改',
			removeTitle: '删除'
		},
		callback: {
			beforeClick: zTreeBeforeClick,
			beforeRemove: zTreeBeforeRemove,
			beforeRename: zTreeBeforeRename,
			beforeDrop: zTreeBeforeDrop
		}
	};
	request('get', '', '/sysmenu/query/tree', 'SYS', function(result) {
		$.fn.zTree.init($("#tree"), setting, result.data);
	});
}

/**
 * 监听点击菜单前事件
 * @param {Object} treeId
 * @param {Object} treeNode
 * @param {Object} clickFlag
 */
function zTreeBeforeClick(treeId, treeNode, clickFlag) {
	var parentNode = treeNode.getParentNode();
	request('get', '', '/sysmenu/query/'+treeNode.id, 'SYS', function(result) {
		var data = result.data;
		if (parentNode) {
			data.parentName = parentNode.name;
		} else {
			data.parentName = '';
		}
		dynamicIconsElement.refreshToInput(data.icon);
		$('#menuForm').initForm(data);
	});
	
	$("#sysTableTable").initTable({
		listenModalSave: function(modal, action) {
			var form = modal.find('form');
			validTable(form);
			return form.valid();
		},
		columnFormatter: function(value, row, index, field) {
			return value;
		},
		operateFormatter: function(action, row) {
			return true;
		},
		searchParams: function(params) {
			params.menuId = treeNode.id;
			return params
		},
		operateExtend: function(action, row) {
			if (action.formTarget == '#setAction') {
				loadAction(row.tableId);
			} else if (action.formTarget == '#setColumn') {
				loadColumn(row.tableId);
			}
		},
		listenModalShow: function(modal) {
			modal.find('form #menuId').val(treeNode.id);
			modal.find('form #menuName').val(treeNode.name);
		}
	});
}

/**
 * 删除菜单前监听事件
 * @param {Object} treeId
 * @param {Object} treeNode
 */
function zTreeBeforeRemove(treeId, treeNode) {
	request('delete', '', '/sysmenu/delete/'+treeNode.id, 'SYS');
}

/**
 * 修改菜单名前监听事件
 * @param {Object} treeId
 * @param {Object} treeNode
 * @param {Object} newName
 * @param {Object} isCancel
 */
function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
	var formData = {
		'menuId': treeNode.id,
		'menuName': newName
	};
	request('put', formData, '/sysmenu/update', 'SYS');
}

/**
 * 拖拽菜单前监听事件
 * @param {Object} treeId
 * @param {Object} treeNodes
 * @param {Object} targetNode
 * @param {Object} moveType
 */
function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
	if (!targetNode) {
		return false;
	}
	var formData = {'targetId': targetNode.id};
	var ids = new Array();
	var parentLevel = targetNode.level+1,chlidLevel;
	if (moveType.toUpperCase() != 'INNER') {
		parentLevel--;
	}
	$.each(treeNodes,function(index,value){  
		ids.push(value.id);
		chlidLevel = deeply(value);
	})
	if ((parentLevel + chlidLevel) > 3) {
		layer.alert('不能创建3级及以上菜单');
		return false;
	}
	formData.menuIds = ids;
	formData.moveType = moveType.toUpperCase();
	request('put', formData, '/sysmenu/move', 'SYS');
}

/**
 * 获取节点的子节点深度
 */
var deeplyLevel;
function deeply(treeNode, rootLevel) {
	if (!rootLevel) {
		rootLevel = treeNode.level;
		deeplyLevel = rootLevel;
	}
	if (treeNode.level >= deeplyLevel) {
		deeplyLevel = treeNode.level + 1;
	}
	var treeNodes = treeNode.children;
	if (treeNodes && treeNodes.length > 0) {
		$.each(treeNodes,function(index,value){  
			deeply(value, rootLevel);
		})
	}
	return deeplyLevel - rootLevel;
}

/**
 * 点击菜单后面的添加按扭监听事件
 * @param {Object} treeId
 * @param {Object} treeNode
 * @param {Object} callback
 */
function zTreeBeforeAddNode(treeId, treeNode, callback) {
	var formData = {
		'menuName': treeNode.name,
		'parentId': treeNode.pid
	};
	request('post', formData, '/sysmenu/add', 'SYS', function(result) {
		callback(result.data);
	});
}

/**
 * 鼠标指针放在菜单上监听事件
 */
var newCount = 1;
function addHoverDom(treeId, treeNode) {
	if (treeNode.level >= 2) {
		return;
	}
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='创建'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		var zTree = $.fn.zTree.getZTreeObj(treeName);
		var newTreeNode = {id:(treeNode.id + newCount), pid:treeNode.id, name:"新建菜单"};
		zTreeBeforeAddNode(treeId, newTreeNode, function(data) {
			newTreeNode.id = data.menuId;
			zTree.addNodes(treeNode, newTreeNode);
		});
		return false;
	});
};
/**
 * 鼠标指针移出菜单上监听事件
 * @param {Object} treeId
 * @param {Object} treeNode
 */
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};

//表格表单检验
function validTable(form) {
	form.validate({
		ignore: "",
		rules: {
			tableName: {
				required: true
			},
			interfaceServerId: {
				required: true
			},
			interfaceUrl: {
				required: true
			},
			interfaceMethod: {
				required: true
			},
			uniqueId: {
				required: true
			},
			tableTarget: {
				required: true
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

//加载表格功能列表
function loadAction(tableId) {
	$("#sysActionTable").initTable({
		listenModalSave: function(modal, action) {
			var form = modal.find('form');
			validAction(form);
			return form.valid();
		},
		columnFormatter: function(value, row, index, field) {
			if (field == 'actionType') {
				if(value == 'OPERATE') {
					return '列表单行操作';
				}
				return '列表工具栏';
			}
			if (field == 'mutualType') {
				if(value == 'CONFIRM') {
					return '确认';
				}else if (value == 'EXTEND') {
					return '扩展';
				}
				return '表单';
			}
			return value;
		},
		operateFormatter: function(action, row) {
			return true;
		},
		searchParams: function(params) {
			params.tableId = tableId;
			return params
		},
		listenModalShow: function(modal) {
			modal.find('form #tableId').val(tableId);
		}
	});
}

//表格功能表单检验
function validAction(form) {
	form.validate({
		ignore: "",
		rules: {
			actionName: {
				required: true
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

//加载表格功能列表
function loadColumn(tableId) {
	$("#sysColumnTable").initTable({
		listenModalSave: function(modal, action) {
			var form = modal.find('form');
			validColumn(form);
			return form.valid();
		},
		columnFormatter: function(value, row, index, field) {
			return value;
		},
		operateFormatter: function(action, row) {
			return true;
		},
		searchParams: function(params) {
			params.tableId = tableId;
			return params
		},
		listenModalShow: function(modal) {
			modal.find('form #tableId').val(tableId);
		}
	});
}

//表格列字段表单检验
function validColumn(form) {
	form.validate({
		ignore: "",
		rules: {
			field: {
				required: true
			},
			title: {
				required: true
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
	
var fa_icons = [
	'fa-home','fa-cogs'
];

var fa_icon_search = [
	'首页','设置'
];