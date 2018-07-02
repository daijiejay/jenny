$(function() {
	//1.初始化Table
	var oTable = new TableInit();
	oTable.Init();

	$('.table-search form button.query').click(function() {
		$("#sysRoleTable").bootstrapTable('refresh');
	});

	//绑定模态框展示的方法 
	$('#saveSysRole').on('shown.bs.modal', function(event) {
		var button = $(event.relatedTarget); // 触发事件的按钮  
		var roleId = button.attr('roleId');
		var modal = $(this) //获得模态框本身
		modal.find('form input').each(function() {
			if(roleId) {
				var row = $("#sysRoleTable").bootstrapTable('getRowByUniqueId', roleId);
				modal.find('form').initForm(row);
				modal.find('.modal-title').html('编辑');
				modal.find('button.save').attr('action', 'update')
				modal.find('button.save').click(function() {
					validUpdate(modal.find('form'));
					if(modal.find('form').valid()) {
						save(modal.find('form'), $(this).attr('action'));
					}
				});
			} else {
				modal.find('.modal-title').html('添加');
				modal.find('button.save').attr('action', 'add')
				$(this).val("");
				modal.find('button.save').click(function() {
					validAdd(modal.find('form'));
					if(modal.find('form').valid()) {
						save(modal.find('form'), $(this).attr('action'));
					}
				});
			}
			var ele = $(this).parent('.input-group').parent();
			if(ele.find('label.error')) {
				ele.find('label.error').remove();
			}
		})
	})
});

//初始化列表
var TableInit = function() {
	var oTableInit = new Object();
	//初始化Table
	oTableInit.Init = function() {
		$('#sysRoleTable').bootstrapTable({
			method: 'get',
			url: server_sys + '/sysrole/all',
			dataField: 'rows',
			cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
			striped: true, //表格显示条纹，默认为false
			pagination: true, // 在表格底部显示分页组件，默认false
			pageList: [10, 20, 50, 100], // 设置页面可以显示的数据条数
			pageSize: 10, // 页面数据条数
			pageNumber: 1, // 首页页码
			uniqueId: 'roleId', //每一行的唯一标识，一般为主键列
			sidePagination: 'server', // 设置为服务器端分页
			queryParams: queryParams,
			toolbar: '#toolbar',
			columns: [{
					field: 'roleCode',
					title: '角色代码'
				}, {
					field: 'roleName',
					title: '角色名称'
				}, {
					field: 'enable',
					title: '是否禁用',
					formatter: booleanFormatter
				},
				{
					field: 'operate',
					title: '操作',
					events: operateEvents,
					formatter: operateFormatter //自定义方法，添加操作按钮
				},
			]
		});
	}
	return oTableInit;
}

//搜索条件
function queryParams(params) {
	var paramsTemp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		userName: $('#roleName').val(),
		pageSize: params.limit, //页面大小
		pageNumber: params.offset + 1 // 每页显示数据的开始行号
	};
	return paramsTemp;
}

//操作按扭
function operateFormatter(value, row, index) { //赋予的参数
	var operate = new Array();
	if (!row.enable) {
		operate.push('<a roleId="' + row.roleId + '" class="btn active" data-toggle="modal" data-target="#saveSysRole" href="#">编辑</a>');
		operate.push('<a class="btn active enable" href="#">禁用</a>');
		operate.push('<a class="btn active delete" href="#">删除</a>');
		operate.push('<a class="btn active" href="#">设置菜单</a>');
	} else {
		operate.push('<a class="btn active notenable" href="#">启用</a>');
		operate.push('<a class="btn active delete" href="#">删除</a>');
	}
	return operate;
}

//布尔值转文字
function booleanFormatter(value, row, index) {
	if(value.enable) {
		return '是';
	}
	return '否';
}

//保存角色
function save(form, action) {
	var method = action == 'add' ? 'post' : 'put';
	var formData = form.serializeJson();
	request(method, formData, '/sysrole/' + action, function(result) {
		$('#saveSysRole').modal('hide');
		$("#sysRoleTable").bootstrapTable('refresh');
	});
}

//用户添加表单检验
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

//监听表格操作按扭事件
window.operateEvents = {
	'click .enable': function(e, value, row, index) {
		layer.confirm('确定需要禁用'+row.userName+'吗？', {
			yes: function(index, layero){
				request('put',  '', '/sysrole/enable?roleCode='+row.roleCode,function(result) {
					$("#sysRoleTable").bootstrapTable('refresh');
					layer.close(index);
				})
			}
		});
	},
	'click .notenable': function(e, value, row, index) {
		layer.confirm('确定需要启用'+row.userName+'吗？', {
			yes: function(index, layero){
				request('put',  '', '/sysrole/notenable?roleCode='+row.roleCode, function(result) {
					$("#sysRoleTable").bootstrapTable('refresh');
					layer.close(index);
				})
			}
		});
	},
	'click .delete': function(e, value, row, index) {
		layer.confirm('确定需要删除'+row.userName+'吗？', {
			yes: function(index, layero){
				request('delete',  '', '/sysrole/delete/roleCode?roleCode='+row.roleCode, function(result) {
					$("#sysRoleTable").bootstrapTable('refresh');
					layer.close(index);
				})
			}
		});
	}
};

