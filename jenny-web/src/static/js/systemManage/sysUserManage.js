$(function() {
	//1.初始化Table
	var oTable = new TableInit();
	oTable.Init();

	$('.table-search form button.query').click(function() {
		$("#sysUserTable").bootstrapTable('refresh');
	});

	//绑定模态框展示的方法 
	$('#saveSysUser').on('shown.bs.modal', function(event) {
		var button = $(event.relatedTarget); // 触发事件的按钮  
		var userCode = button.attr('userCode');
		var modal = $(this) //获得模态框本身
		modal.find('form input').each(function() {
			if(userCode) {
				var row = $("#sysUserTable").bootstrapTable('getRowByUniqueId', userCode);
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
		//		$(document).delegate('#saveSysUser button.save', "click", function() {
		//			valid(modal.find('form'));
		//			if(modal.find('form').valid()) {
		//				save(modal.find('form'), $(this).attr('action'));
		//			}
		//		});
	})
});

//初始化列表
var TableInit = function() {
	var oTableInit = new Object();
	//初始化Table
	oTableInit.Init = function() {
		$('#sysUserTable').bootstrapTable({
			method: 'get',
			url: server_sys + '/sysuser/all',
			dataField: 'rows',
			cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
			striped: true, //表格显示条纹，默认为false
			pagination: true, // 在表格底部显示分页组件，默认false
			pageList: [10, 20, 50, 100], // 设置页面可以显示的数据条数
			pageSize: 10, // 页面数据条数
			pageNumber: 1, // 首页页码
			uniqueId: 'userCode', //每一行的唯一标识，一般为主键列
			sidePagination: 'server', // 设置为服务器端分页
			queryParams: queryParams,
			toolbar: '#toolbar',
			columns: [{
					field: 'userCode',
					title: '用户编号'
				}, {
					field: 'userName',
					title: '用户名称'
				}, {
					field: 'mobile',
					title: '手机号'
				}, {
					field: 'email',
					title: '邮箱'
				}, {
					field: 'birthday',
					title: '生日'
				}, {
					field: 'gender',
					title: '性别'
				}, {
					field: 'portrait',
					title: '头像'
				}, {
					field: 'createTime',
					title: '创建时间'
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
		userName: $('#userName').val(),
		mobile: $('#mobile').val(),
		pageSize: params.limit, //页面大小
		pageNumber: params.offset + 1 // 每页显示数据的开始行号
	};
	return paramsTemp;
}

//操作按扭
function operateFormatter(value, row, index) { //赋予的参数
	var operate = new Array();
	if (!row.cancel) {
		if (!row.enable) {
			operate.push('<a userCode="' + row.userCode + '" class="btn active" data-toggle="modal" data-target="#saveSysUser" href="#">编辑</a>');
			operate.push('<a class="btn active enable" href="#">禁用</a>');
			operate.push('<a class="btn active delete" href="#">删除</a>');
			operate.push('<a class="btn active" href="#">设置角色</a>');
		} else {
			operate.push('<a class="btn active notenable" href="#">启用</a>');
			operate.push('<a class="btn active delete" href="#">删除</a>');
		}
	}
	return operate;
}

function booleanFormatter(value, row, index) {
	if(value.enable) {
		return '是';
	}
	return '否';
}

//保存用户
function save(form, action) {
	var method = action == 'add' ? 'post' : 'put';
	var formData = form.serializeJson();
	request(method, formData, '/sysuser/' + action, function(result) {
		$('#saveSysUser').modal('hide');
		$("#sysUserTable").bootstrapTable('refresh');
	});
}

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
				isMobile: true
			},
			password: {
				required: true,
				isPassword: true
			},
			email: {
				email: true
			},
			birthday: {
				date: true
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
				isMobile: true
			},
			password: {
				isPassword: true
			},
			email: {
				email: true
			},
			birthday: {
				date: true
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
				request('put',  '', '/sysuser/enable?id='+row.id,function(result) {
					$("#sysUserTable").bootstrapTable('refresh');
					layer.close(index);
				})
			}
		});
	},
	'click .notenable': function(e, value, row, index) {
		layer.confirm('确定需要启用'+row.userName+'吗？', {
			yes: function(index, layero){
				request('put',  '', '/sysuser/notenable?id='+row.id, function(result) {
					$("#sysUserTable").bootstrapTable('refresh');
					layer.close(index);
				})
			}
		});
	},
	'click .delete': function(e, value, row, index) {
		layer.confirm('确定需要删除'+row.userName+'吗？', {
			yes: function(index, layero){
				request('delete',  '', '/sysuser/delete?id='+row.id, function(result) {
					$("#sysUserTable").bootstrapTable('refresh');
					layer.close(index);
				})
			}
		});
	}
};