(function($) {
	var that,initTable = {
		table:'',
		actions:[]
	},
	settings = {
		searchTarget: '.table-search',
		toolbarTarget: undefined,
		listenModalSave: function(form, action) {
			return true;
		},
		searchParams: function(params) {
			return params;
		},
		columnFormatter: function(value, row, index, field) {
			return value;
		},
		operateFormatter: function(action, row) {
			return true;
		}
	},
	modalMap = new Map();
	$.fn.extend({
		initTable: function(settings) {
			that = this;
			this.settings = settings;
			var menuId = window.parent.$('#page-wrapper iframe').attr('menuId');
			request('get', '', '/sysindex/menu/authorized/action/'+menuId, 'SYS', function(result) {
				initTable.table = result.data.table;
				initTable.actions = result.data.actions;
				that.initTableData();
				that.listenSearch();
				that.listenToolbar();
			});
		},
		initTableData: function(params) {
			var table = initTable.table;
			var actions = initTable.actions;
			this.bootstrapTable({
				method: table.interfaceMethod,
				url: serverMap.get(table.interfaceServerId)+table.interfaceUrl,
				dataField: 'rows',
				cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
				striped: true, //表格显示条纹，默认为false
				pagination: true, // 在表格底部显示分页组件，默认false
				pageList: [10, 20, 50, 100], // 设置页面可以显示的数据条数
				pageSize: 10, // 页面数据条数
				pageNumber: 1, // 首页页码
				uniqueId: table.uniqueId, //每一行的唯一标识，一般为主键列
				sidePagination: 'server', // 设置为服务器端分页
				queryParams: that.initQueryParams,
				toolbar: that.initToolbar,
				columns: that.initColumns
			});
		},
		initQueryParams: function(params) {
			var queryParams = $(settings.searchTarget).serializeJson();;
			queryParams.pageSize = params.limit;
			queryParams.pageNumber = params.offset + 1;
			return settings.searchParams(queryParams);
			
		},
		initToolbar: function() {
			var toolbar = '<div class="btn-group">';
			var actions = initTable.actions;
			actions.forEach(function(action, i) {
				if (action.actionType == 'TOOLBAR') {
					if (action.mutualType == 'FORM') {
						toolbar += '<a actionId="'+action.actionId+'" href="#" class="btn btn-primary" data-toggle="modal" data-target="'+action.formTarget+'">'+action.actionName+'</a>';
					}
				}
			});
			toolbar += '</div>';
			return toolbar;
		},
		initColumns: function() {
			var columns = initTable.table.columns;
			columns.forEach(function(column, i) {
				column.formatter = that.settings.columnFormatter;
			})
			columns.push({
				field: 'operate',
				title: '操作',
				events: operateEvents,
				formatter: that.initOperates
			});
			return columns;
		},
		initOperates: function(value, row, index) {
			var operate = new Array();
			initTable.actions.forEach(function(action, i) {
				if (action.actionType == 'OPERATE' && that.settings.operateFormatter(action, row)) {
					if (action.mutualType == 'CONFIRM') {
						operate.push('<a actionId="'+action.actionId+'" class="btn active" href="#">'+action.actionName+'</a>');
					} else if(action.mutualType == 'FORM') {
						operate.push('<a id="'+row.userId+'" actionId="'+action.actionId+'" class="btn active" data-toggle="modal" data-target="'+action.formTarget+'" href="#">'+action.actionName+'</a>');
					}
				}
			});
			return operate
		},
		listenSearch: function() {
			var table = initTable.table;
			$(settings.searchTarget).find('button.query').click(function() {
				that.bootstrapTable('refresh');
			});
		},
		listenToolbar: function() {
			var actions = initTable.actions;
			actions.forEach(function(action, i) {
				that.listenModalEvents(action);
			});
		},
		listenModalEvents: function(action) {
			if (!modalMap.get(action.formTarget)) {
				modalMap.set(action.formTarget, $(action.formTarget));
				modalMap.get(action.formTarget).on('shown.bs.modal', function(event) {
					var modal = $(this);
					var button = $(event.relatedTarget);
					var id = button.attr('id');
					var actionId = button.attr('actionId');
					if(id) {
						var row = that.bootstrapTable('getRowByUniqueId', id);
						modal.find('form').initForm(row);
						modal.find('.modal-title').html(action.actionName);
					} else {
						modal.find('.modal-title').html(action.actionName);
						$(this).val("");
					}
					modal.find('button.save').click(function() {
						that.saveFormData(actionId, modal);
					});
				});
			}
		},
		saveFormData: function(actionId, modal) {
			var actions = initTable.actions;
			actions.forEach(function(action, i) {
				if (action.actionId == actionId) {
					if (that.settings.listenModalSave(modal.find('form'), action)) {
						var formData = modal.find('form').serializeJson();
						var url = that.formatterUrl(action.interfaceUrl, formData);
						request(action.interfaceMethod, formData, url, action.interfaceServerId, function(result) {
							modal.modal('hide');
							that.bootstrapTable('refresh');
						});
					}
				}
			});
		},
		formatterUrl: function(url, row) {
			if (url.indexOf('{') >= 0 && url.indexOf('}') >= 0) {
				var key = url.substring(url.indexOf('{')+1, url.indexOf('}'));
				url = url.substring(0,url.lastIndexOf('/')+1);
				jQuery.each(row, function(k, val) {  
					if (k == key) {
						url += val;
					}
				}); 
			}
			return url;
		}
	});
	
	window.operateEvents = {
		'click .btn': function(e, value, row, index) {
			initTable.actions.forEach(function(action, i) {
				if (action.actionType == 'OPERATE' && action.actionName == e.target.innerHTML) {
					if (action.mutualType == 'CONFIRM') {
						layer.confirm('确定需要'+action.actionName+'吗？', {
							yes: function(index, layero){
								var url = action.interfaceUrl;
								that.formatterUrl(url, row);
								request(action.interfaceMethod,  '', url, action.interfaceServerId, function(result) {
									$(table.tableTerget).bootstrapTable('refresh');
									layer.close(index);
								})
							}
						});
					} else if (action.mutualType == 'FORM') {
						that.listenModalEvents(action);
					}
				}
			});
			
		}
	}
})(jQuery)