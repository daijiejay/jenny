(function($) {
	var _table = function() {
		this.that = {},
		this.loaded = false,
		this.table = {
			actions: [],
			columns: []
		},
		this.settings = {
			searchTarget: '.table-search',
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
		}
	},
	_modalMap = new Map(),
	_tableMap = new Map();
	
	$.fn.extend({
		initTable: function(settings) {
			var tab = _tableMap.get(this.selector);
			if (!tab) {
				_tableMap.set(this.selector, tab = new _table());
			}
			var _this = this;
			tab.that = this;
			tab.settings = settings;
			var menuId = window.parent.$('#page-wrapper iframe').attr('menuId');
			request('get', '', '/sysindex/menu/authorized/action/' + menuId, 'SYS', function(result) {
				tab.table = result.data.tables[0];
				tab.table.actions = result.data.actions;
				_this.initTableData(tab);
				_this.listenSearch(tab);
				_this.listenToolbar(tab);
				if (tab.loaded) {
					_this.bootstrapTable('refresh');
				} else {
					tab.loaded = true;
				}
			});
		},
		initTableData: function(tab) {
			var table = tab.table;
			tab.that.bootstrapTable({
				method: table.interfaceMethod,
				url: serverMap.get(table.interfaceServerId) + table.interfaceUrl,
				dataField: 'rows',
				cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
				striped: true, //表格显示条纹，默认为false
				pagination: true, // 在表格底部显示分页组件，默认false
				pageList: [10, 20, 50, 100], // 设置页面可以显示的数据条数
				pageSize: 10, // 页面数据条数
				pageNumber: 1, // 首页页码
				uniqueId: table.uniqueId, //每一行的唯一标识，一般为主键列
				sidePagination: 'server', // 设置为服务器端分页
				queryParams: tab.that.initQueryParams,
				toolbar: tab.that.initToolbar(tab),
				columns: tab.that.initColumns(tab)
			});
		},
		initQueryParams: function(params, ele) {
			var tab = _tableMap.get('#' + ele.id);
			var queryParams = $(tab.settings.searchTarget).serializeJson();
			queryParams.pageSize = params.limit;
			queryParams.pageNumber = params.offset + 1;
			if (typeof tab.settings.searchParams == 'function') {
				return tab.settings.searchParams(queryParams);
			}
			return queryParams;

		},
		initToolbar: function(tab) {
			var toolbar = '<div class="btn-group">';
			var actions = tab.table.actions;
			actions.forEach(function(action, i) {
				if(action.actionType == 'TOOLBAR') {
					if(action.mutualType == 'FORM') {
						toolbar += '<a actionId="' + action.actionId + '" href="#" class="btn btn-primary" data-toggle="modal" data-target="' + action.formTarget + '">' + action.actionName + '</a>';
					}
				}
			});
			toolbar += '</div>';
			return toolbar;
		},
		initColumns: function(tab) {
			var columns = tab.table.columns;
			columns.forEach(function(column, i) {
				column.formatter = tab.settings.columnFormatter;
			})
			columns.push({
				field: 'operate',
				title: '操作',
				events: operateEvents,
				formatter: tab.that.initOperates
			});
			return columns;
		},
		initOperates: function(value, row, index, field, field, ele) {
			var tab = _tableMap.get('#' + ele.id);
			var operate = new Array();
			tab.table.actions.forEach(function(action, i) {
				if(action.actionType == 'OPERATE' && tab.settings.operateFormatter(action, row)) {
					if(action.mutualType == 'CONFIRM') {
						operate.push('<a actionId="' + action.actionId + '" class="btn active" href="#">' + action.actionName + '</a>');
					} else if(action.mutualType == 'FORM') {
						operate.push('<a id="' + tab.that.getUniqueIdValue(row, tab) + '" actionId="' + action.actionId + '" class="btn active" data-toggle="modal" data-target="' + action.formTarget + '" href="#">' + action.actionName + '</a>');
					}
				}
			});
			return operate
		},
		getUniqueIdValue: function(row, tab) {
			var value, field = tab.table.uniqueId;
			jQuery.each(row, function(key, val) {
				if (key == field) {
					value = val;
					return;
				}
			})
			return value;
		},
		listenSearch: function(tab) {
			$(tab.settings.searchTarget).find('button.query').click(function() {
				tab.that.bootstrapTable('refresh');
			});
		},
		listenToolbar: function(tab) {
			var actions = tab.table.actions;
			actions.forEach(function(action, i) {
				tab.that.listenModalEvents(tab, action);
			});
		},
		listenModalEvents: function(tab, action) {
			if(!_modalMap.get(action.formTarget)) {
				_modalMap.set(action.formTarget, $(action.formTarget));
				_modalMap.get(action.formTarget).on('shown.bs.modal', function(event) {
					var modal = $(this);
					var button = $(event.relatedTarget);
					var id = button.attr('id');
					var actionId = button.attr('actionId');
					if(id) {
						var row = tab.that.bootstrapTable('getRowByUniqueId', id);
						modal.find('form').initForm(row);
						modal.find('.modal-title').html(button.html());
					} else {
						modal.find('.modal-title').html(button.html());
						modal.find('form')[0].reset();
					}
					modal.find('button.save').click(function() {
						tab.that.saveFormData(tab, actionId, modal);
					});
				});
			}
		},
		saveFormData: function(tab, actionId, modal) {
			var actions = tab.table.actions;
			actions.forEach(function(action, i) {
				if(action.actionId == actionId) {
					if(tab.settings.listenModalSave(modal.find('form'), action)) {
						var formData = modal.find('form').serializeJson();
						var url = tab.that.formatterUrl(action.interfaceUrl, formData);
						request(action.interfaceMethod, formData, url, action.interfaceServerId, function(result) {
							modal.find('button.save').unbind("click");
							modal.modal('hide');
							tab.that.bootstrapTable('refresh');
						});
					}
				}
			});
		},
		formatterUrl: function(url, row) {
			if(url.indexOf('{') >= 0 && url.indexOf('}') >= 0) {
				var key = url.substring(url.indexOf('{') + 1, url.indexOf('}'));
				url = url.substring(0, url.lastIndexOf('/') + 1);
				jQuery.each(row, function(k, val) {
					if(k == key) {
						url += val;
					}
				});
			}
			return url;
		}
	});

	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(function() {
			if(serializeObj[this.name]) {
				if($.isArray(serializeObj[this.name])) {
					serializeObj[this.name].push(this.value);
				} else {
					serializeObj[this.name] = [serializeObj[this.name], this.value];
				}
			} else {
				serializeObj[this.name] = this.value;
			}
		});
		return serializeObj;
	};

	$.fn.initForm = function(options) {
		//默认参数  
		var defaults = {
			jsonValue: "",
			isDebug: false //是否需要调试，这个用于开发阶段，发布阶段请将设置为false，默认为false,true将会把name value打印出来  
		}
		//设置参数  
		var setting = $.extend({}, defaults, options);
		var form = this;
		var jsonValue;
		if(setting.jsonValue == "") {
			jsonValue = setting;
		} else {
			jsonValue = setting.jsonValue;
		}
		//如果传入的json字符串，将转为json对象  
		if($.type(jsonValue) === "string") {
			jsonValue = $.parseJSON(jsonValue);
		}
		//如果传入的json对象为空，则不做任何操作  
		if(!$.isEmptyObject(jsonValue)) {
			var debugInfo = "";
			$.each(jsonValue, function(key, value) {
				//是否开启调试，开启将会把name value打印出来  
				if(setting.isDebug) {
					alert("name:" + key + "; value:" + value);
					debugInfo += "name:" + key + "; value:" + value + " || ";
				}
				var formField = form.find("[name='" + key + "']");
				if($.type(formField[0]) === "undefined") {
					if(setting.isDebug) {
						alert("can not find name:[" + key + "] in form!!!"); //没找到指定name的表单  
					}
				} else {
					var fieldTagName = formField[0].tagName.toLowerCase();
					if(fieldTagName == "input") {
						if(formField.attr("type") == "radio") {
							$("input:radio[name='" + key + "'][value='" + value + "']").attr("checked", "checked");
						} else {
							formField.val(value);
						}
					} else if(fieldTagName == "select") {
						//do something special  
						formField.val(value);
					} else if(fieldTagName == "textarea") {
						//do something special  
						formField.val(value);
					} else {
						formField.val(value);
					}
				}
			})
			if(setting.isDebug) {
				alert(debugInfo);
			}
		}
		return form; //返回对象，提供链式操作  
	};
	$.fn.sleep = function(numberMillis) {
		var now = new Date();
		var exitTime = now.getTime() + numberMillis;
		while(true) {
			now = new Date();
			if(now.getTime() > exitTime) {
				return;
			}
		}
	};

	window.operateEvents = {
		'click .btn': function(e, value, row, index, ele) {
			var tab = _tableMap.get('#' + ele.id);
			tab.table.actions.forEach(function(action, i) {
				if(action.actionType == 'OPERATE' && action.actionName == e.target.innerHTML) {
					if(action.mutualType == 'CONFIRM') {
						layer.confirm('确定需要' + action.actionName + '吗？', {
							yes: function(index, layero) {
								var url = tab.that.formatterUrl(action.interfaceUrl, row);
								request(action.interfaceMethod, '', url, action.interfaceServerId, function(result) {
									tab.that.bootstrapTable('refresh');
									layer.close(index);
								})
							}
						});
					} else if(action.mutualType == 'FORM') {
						tab.that.listenModalEvents(tab, action);
					}
				}
			});
		}
	}
})(jQuery)