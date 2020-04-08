/**
 * 存储服务名地址
 */
var api_url = 'http://127.0.0.1:12000/';

/**
 * 获取当前访问静态资源地址
 */
var local = window.location.href.substring(0, window.location.href.indexOf('/static/html') + '/static/html'.length);
/**
 * 获取父级窗口
 */
var widget = window.location;
var ifm = window.parent.$('#page-wrapper .tab-content div.active iframe');
if (ifm.length > 0) {
	widget = window.parent.location;
}
/**
 * 存储请求地址缓存，请求完成后清除，防止重复请求
 */
var requestMap = new Map();
/**
 * 可同步请求
 * 请求封装，处理请求重复提交，加载效果展示，自动拦截用户过期跳转登录，请求错误提示
 * @param {Object} method 请求方式
 * @param {Object} data 请求数据
 * @param {Object} url 请求地址
 * @param {Object} serverId 请求服务名
 * @param {Object} synType 是否异步请求
 * @param {Object} isloader 是否显示加载效果
 * @param {Object} callback 请求回调
 */
function requestSynchronizedNonloader(method, data, url, serverId, synType, isloader, callback) {
	if(requestMap.get(url)) {
		return;
	}
	requestMap.set(url, true);
	if (isloader) {
		if (!loader) {
			initFakeloader();
		}
	    loader.fadeIn();
	}
	if(method.toUpperCase() == 'GET') {
		jQuery.ajax({
			type: method,
			url: api_url + serverId + url,
			data: data,
			async: synType,
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			success: function(result, textStatus, request) {
				requestMap.delete(url);
				if (isloader) {
					loader.fadeOut();
				}
				if(result.code == '200') {
					if(typeof callback == 'function') {
						return callback(result);
					}
				} else if(result.code == '300') {
					widget.href = local + '/login.html';
				} else {
					layer.alert(result.msg);
					return callback(result);
				}
			},
			error: function(e) {
				layer.alert('访问服务器异常！');
				requestMap.delete(url);
				if (isloader) {
					loader.fadeOut();
				}
			}
		});
	} else {
		if (data == '') {
			data = {};
		}
		jQuery.ajax({
			type: method,
			url: api_url + serverId + url,
			data: JSON.stringify(data),
			dataType: 'json',
			contentType: 'application/json',
			async: synType,
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			success: function(result, textStatus, request) {
				requestMap.delete(url);
				if (isloader) {
					loader.fadeOut();
				}
				if(result.code == '200') {
					if(typeof callback == 'function') {
						return callback(result);
					}
				} else if(result.code == '300') {
					widget.href = local + '/login.html';
				} else {
					layer.alert(result.msg);
					return callback(result);
				}
			},
			error: function(e) {
				layer.alert('访问服务器异常！');
				requestMap.delete(url);
				if (isloader) {
					loader.fadeOut();
				}
			}
		});
	}
}
/**
 * 可同步请求
 * 请求封装，处理请求重复提交，加载效果展示，自动拦截用户过期跳转登录，请求错误提示
 * @param {Object} method 请求方式
 * @param {Object} data 请求数据
 * @param {Object} url 请求地址
 * @param {Object} serverId 请求服务名
 * @param {Object} synType 是否异步请求
 * @param {Object} callback 请求回调
 */
function requestSynchronized(method, data, url, serverId, synType, callback) {
	requestSynchronizedNonloader(method, data, url, serverId, true, true, function(result) {
		if (typeof callback === 'function') {
			return callback(result);
		}
	});
}
/**
 * 异步请求
 * 请求封装，处理请求重复提交，加载效果展示，自动拦截用户过期跳转登录，请求错误提示
 * @param {Object} method 请求方式
 * @param {Object} data 请求数据
 * @param {Object} url 请求地址
 * @param {Object} serverId 请求服务名
 * @param {Object} callback 请求回调
 */
function request(method, data, url, serverId, callback) {
	requestSynchronized(method, data, url, serverId, true, function(result) {
		if (typeof callback === 'function') {
			return callback(result);
		}
	});
}
/**
 * 初始化图标
 * @param {Object} ele 元素对象
 * @param {Object} defaultIcon 默认图标
 */
function initFontIconPicker(ele, defaultIcon){
	request('get', '', '/sysicon/query/fontIconPicker', 'sys', function(result){
		if (result.code == '200') {
			dynamicIconsElement = $(ele).fontIconPicker({
				source: result.data.iconCodes,
				searchSource: result.data.iconNames,
				useAttribute: true,
				attributeName: 'data-icomoon',
				theme: 'fip-bootstrap',
				iconsPerPage: 10,
				emptyIcon: true,
		        hasSearch: true
			});
			dynamicIconsElement.refreshToInput(defaultIcon);
		}
	})
}
/**
 * 初始化加载效果
 */
var loader;
function initFakeloader() {
	var pre = '';
	var atr = $(document)[0].baseURI.split('/');
	for (var i = atr.length-2; i > 0; i--) {
		if (atr[i] == 'static') {
			break;
		}
		pre += '../';
	}
	if ($('.fakeloader').length == 0) {
		$('body').append('<div class="fakeloader"></div>');
	}
	loader = $('.fakeloader').fakeLoader({
        timeToHide:36000,
        zIndex: '9999', 
        spinner:'spinner2',
        imagePath:pre+'public/images/loading-0.gif'
    });
    return loader;
}
/**
 * 子页面变动iframe高度自适应
 */
async function changeFrameHeight() {
	var ifm = window.parent.$('#page-wrapper .tab-content div.active iframe');
	if (ifm.length > 0) {
		var height = window.screen.height - 115;
//		if (ifm[0].attr('menuId') == -1) {
//			return;
//		}
		ifm[0].height = document.body.clientHeight < height ? height : document.body.clientHeight;
	}
}
/**
 * 监听窗口操作，时时配置ifram高度
 */
window.onresize = function() {
	changeFrameHeight();
}
/**
 * 监听窗口点击事件，时时配置ifram高度
 */
$(window).mouseup(function(event) {
	setTimeout(()=>{
		changeFrameHeight();
        return;
    },300)
});

/**
 * 带天数的倒计时
 * @param {Object} times 时间戳
 */
function countDown(times) {
	var timer = null;
	timer = setInterval(function() {
		var day = 0,
			hour = 0,
			minute = 0,
			second = 0; 
		if(times > 0) {
			day = Math.floor(times / (60 * 60 * 24));
			hour = Math.floor(times / (60 * 60)) - (day * 24);
			minute = Math.floor(times / 60) - (day * 24 * 60) - (hour * 60);
			second = Math.floor(times) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
		}
		if(day <= 9) day = '0' + day;
		if(hour <= 9) hour = '0' + hour;
		if(minute <= 9) minute = '0' + minute;
		if(second <= 9) second = '0' + second;
		console.log(day + "天:" + hour + "小时：" + minute + "分钟：" + second + "秒");
		times--;
	}, 1000);
	if(times <= 0) {
		clearInterval(timer);
	}
}

/**
 * 自定义juery插件
 * @param {Object} $
 */
(function($) {
	var _table = function() {
		this.that = {},
		this.loaded = false,
		this.table = {
			actions: [],
			columns: []
		},
		this.settings = {
			dataField: 'rows',
			cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
			striped: true, //表格显示条纹，默认为false
			pagination: true, // 在表格底部显示分页组件，默认false
			sortable: true, //是否启用排序
			showToggle: true, //是否显示详细视图和列表视图的切换按钮
			cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表
            clickToSelect: true, //是否启用点击选中行
            singleSelect: false, //是否禁止多选。
            showRefresh: true,  //是否显示刷新按钮
            showColumns: true, //是否显示所有的列（选择显示的列）
            strictSearch: true,
            search: false, //是否显示表格搜索
			pageList: [10, 20, 50, 100], // 设置页面可以显示的数据条数
			pageSize: 10, // 页面数据条数
			pageNumber: 1, // 首页页码
			checkbox: false,  //是否显示多选
            visible: true,
            showExport: true, //是否显示导出
            exportDataType: "all", //basic', 'all', 'selected' 表示导出的模式是当前页、所有数据还是选中数据
            exportTypes: ['excel'], //导出文件
			searchTarget: '.table-search', //搜索表单元素
			/**
			 * 模态框保存前监听事件
			 * @param {Object} modal 模态框元素
			 * @param {Object} action 功能对象
			 * @param {Object} data 数据
			 */
			listenModalSave: function(modal, action, data) {
				return true;
			},
			/**
			 * 监听加载表格数据前设置请求参数
			 * @param {Object} params 请求参数
			 */
			searchParams: function(params) {
				return params;
			},
			/**
			 * 表格列字段值格式化
			 * @param {Object} value 行字段值
			 * @param {Object} row 行对象
			 * @param {Object} index 行下标
			 * @param {Object} field 行字段名
			 */
			columnFormatter: function(value, row, index, field) {
				return value;
			},
			/**
			 * 功能按扭格式化
			 * @param {Object} action 功能对象
			 * @param {Object} row 行对象
			 */
			operateFormatter: function(action, row) {
				return true;
			},
			/**
			 * 功能按扭自定义扩展
			 * @param {Object} action 功能对象
			 * @param {Object} row 行对象
			 */
			operateExtend: function(action, row) {
				return;
			},
			/**
			 * 模态框显示监听事件
			 * @param {Object} modal 模态框元素
			 * @param {Object} row 行对象
			 */
			listenModalShow: function(modal, row) {
				return;
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
			if (menuId <= 0) {
				menuId = window.parent.$('#page-wrapper .tab-content div.active iframe').attr('menuId');
			}
			request('get', '', '/sysindex/menu/authorized/table/' + menuId, 'sys', function(result) {
				result.data.tables.forEach(function(value, index) {
					if (value.tableTarget == _this.selector) {
						tab.table = value;
					}
				})
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
			var defaultSettings = new _table().settings;
			tab.that.bootstrapTable({
				method: table.interfaceMethod,
				url: api_url + table.interfaceServerId + table.interfaceUrl,
				dataField: tab.settings.dataField ? tab.settings.dataField : defaultSettings.dataField,
				cache: tab.settings.cache ? tab.settings.cache : defaultSettings.cache,
				striped: tab.settings.striped ? tab.settings.striped : defaultSettings.striped,
				pagination: tab.settings.pagination ? tab.settings.pagination : defaultSettings.pagination,
				sortable: tab.settings.sortable ? tab.settings.sortable : defaultSettings.sortable,
				showToggle: tab.settings.showToggle ? tab.settings.showToggle : defaultSettings.showToggle,
				cardView: tab.settings.cardView ? tab.settings.cardView : defaultSettings.cardView,
                detailView: tab.settings.detailView ? tab.settings.detailView : defaultSettings.detailView,
                clickToSelect: tab.settings.clickToSelect ? tab.settings.clickToSelect : defaultSettings.clickToSelect,
                singleSelect: tab.settings.singleSelect ? tab.settings.singleSelect : defaultSettings.singleSelect,
                showRefresh: tab.settings.showRefresh ? tab.settings.showRefresh : defaultSettings.showRefresh,
                showColumns: tab.settings.showColumns ? tab.settings.showColumns : defaultSettings.showColumns,
                strictSearch: tab.settings.strictSearch ? tab.settings.strictSearch : defaultSettings.strictSearch,
                search: tab.settings.search ? tab.settings.search : defaultSettings.search,
				pageList: tab.settings.pageList ? tab.settings.pageList : defaultSettings.pageList,
				pageSize: tab.settings.pageSize ? tab.settings.pageSize : defaultSettings.pageSize,
				pageNumber: tab.settings.pageNumber ? tab.settings.pageNumber : defaultSettings.pageNumber,
				showExport: tab.settings.showExport ? tab.settings.showExport : defaultSettings.showExport,
				exportDataType: tab.settings.exportDataType ? tab.settings.exportDataType : defaultSettings.exportDataType,
				exportTypes: tab.settings.exportTypes ? tab.settings.exportTypes : defaultSettings.exportTypes,
				uniqueId: table.uniqueId,
				sidePagination: 'server',
				queryParams: tab.that.initQueryParams,
				toolbar: tab.that.initToolbar(tab),
				columns: tab.that.initColumns(tab)
			});
		},
		initQueryParams: function(params, ele) {
			var tab = _tableMap.get('#' + ele.id);
			var queryParams = {};
			if (tab.settings.searchTarget) {
				var form = $(tab.settings.searchTarget);
				if (form[0].tagName != 'FORM') {
					form = form.find('form');
				}
				queryParams = form.serializeJson();
			}
			if (params.limit) {
				queryParams.pageSize = params.limit;
				queryParams.pageNumber = params.offset / params.limit + 1;
			}
			queryParams.order = params.order;
			queryParams.sort = params.sort;
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
						var actionName = action.icon ? '' : action.actionName;
						toolbar += '<a actionId="' + action.actionId + '" href="#" class="btn btn-default ' + action.icon + '" title="' + action.actionName + '" data-toggle="modal" data-target="' + action.formTarget + '">' + actionName + '</a>';
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
				column.editable = true;
			})
			if (tab.settings.checkbox) {
				columns.unshift({
					checkbox: tab.settings.checkbox,  
                    visible: tab.settings.visible,
                    formatter: tab.that.checkFormatter
				});
			}
			if (JSON.stringify(tab.table.actions).indexOf('"mutualType":"FORM"') >= 0 
				|| JSON.stringify(tab.table.actions).indexOf('"mutualType":"EXTEND"') >= 0) {
				columns.push({
					field: 'operate',
					title: '操作',
					events: operateEvents,
					formatter: tab.that.initOperates
				});
			}
			return columns;
		},
		checkFormatter: function(value, row, index) {
			return {
		    	checked : row.checked == undefined ? row.selected : row.checked
		    };
		},
		initOperates: function(value, row, index, field, field, ele) {
			var tab = _tableMap.get('#' + ele.id);
			var operate = '';
			tab.table.actions.forEach(function(action, i) {
				if (action.actionType == 'OPERATE' && (!tab.settings.operateFormatter || tab.settings.operateFormatter(action, row))) {
					var actionName = action.icon ? '' : action.actionName;
					if (action.mutualType == 'CONFIRM') {
						operate += '<a mutualType="' + action.mutualType + '" actionId="' + action.actionId + '" title="' + action.actionName + '" class="action ' + action.icon + '" href="#">' + actionName + '</a>';
					} else if (action.mutualType == 'FORM') {
						operate += '<a id="' + tab.that.getUniqueIdValue(row, tab) + '" mutualType="' + action.mutualType + '" actionId="' + action.actionId + '" title="' + action.actionName + '" class="action ' + action.icon + '" data-toggle="modal" data-target="' + action.formTarget + '" href="#">' + actionName + '</a>';
					} else if (action.mutualType == 'EXTEND') {
						operate += '<a id="' + tab.that.getUniqueIdValue(row, tab) + '" mutualType="' + action.mutualType + '" actionId="' + action.actionId + '" title="' + action.actionName + '" class="action ' + action.icon + '" data-toggle="modal" data-target="' + action.formTarget + '" href="#">' + actionName + '</a>';
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
					if (typeof modal.draggable === 'function') {
						modal.draggable();
					}
					var button = $(event.relatedTarget);
					var id = button.attr('id');
					var actionId = button.attr('actionId');
					var mutualType = button.attr('mutualType');
					modal.find('.modal-title').html(button.attr('title'));
					var row;
					if (mutualType == 'FORM') {
						if(id) {
							row = tab.that.bootstrapTable('getRowByUniqueId', id);
							modal.find('form').initForm(row);
						}
					} else if (mutualType == 'EXTEND') {
						
					}
					if (typeof tab.settings.listenModalShow == 'function') {
						tab.settings.listenModalShow(modal, row);
					}
					
					modal.find('button.save').click(function() {
						tab.that.saveFormData(tab, actionId, modal);
					});
				});
				_modalMap.get(action.formTarget).on('hidden.bs.modal', function(event) {
					var modal = $(this);
					modal.find('button.save').unbind("click");
					if (modal.find('form').length > 0) {
						modal.find('label.error').remove();
						modal.find('.error').removeClass('error');
						modal.find('form')[0].reset();
					}
				});
			}
		},
		saveFormData: function(tab, actionId, modal) {
			var actions = tab.table.actions;
			actions.forEach(function(action, i) {
				if(action.actionId == actionId) {
					if (action.mutualType == 'FORM') {
						if(tab.settings.listenModalSave(modal, action)) {
							var formData = modal.find('form').serializeJson();
							var url = tab.that.formatterUrl(action.interfaceUrl, formData);
							request(action.interfaceMethod, formData, url, action.interfaceServerId, function(result) {
								if(result.code == '200') {
									modal.find('button.save').unbind("click");
									modal.modal('hide');
									tab.that.bootstrapTable('refresh');
								}
							});
						}
					} else if (action.mutualType == 'EXTEND') {
						var table, tables = modal.find('table');
						for (var i = 0; i < tables.length; i++) {
							if (_tableMap.get('#' + tables[i].id)) {
								table = _tableMap.get('#' + tables[i].id);
							}
						}
						if (table) {
							var selectedRows = table.that.bootstrapTable('getAllSelections');
							var data = {
								selectedRows: selectedRows
							};
							if(table.settings.listenModalSave(modal, action, data)) {
								modal.find('button.save').unbind("click");
								modal.modal('hide');
								tab.that.bootstrapTable('refresh');
							}
						} else {
							if(tab.settings.listenModalSave(modal, action, data)) {
								modal.find('button.save').unbind("click");
								modal.modal('hide');
								tab.that.bootstrapTable('refresh');
							}
						}
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
						formField.val(value);
					} else if(fieldTagName == "textarea") {
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
		'click .action': function(e, value, row, index, ele) {
			var tab = _tableMap.get('#' + ele.id);
			tab.table.actions.forEach(function(action, i) {
				if (action.actionType == 'OPERATE' && action.actionName == e.target.title) {
					if (action.mutualType == 'CONFIRM') {
						layer.confirm('确定需要' + action.actionName + '吗？', {
							yes: function(index, layero) {
								var url = tab.that.formatterUrl(action.interfaceUrl, row);
								request(action.interfaceMethod, '', url, action.interfaceServerId, function(result) {
									tab.that.bootstrapTable('refresh');
									layer.close(index);
								})
							}
						});
					} else if (action.mutualType == 'FORM') {
						tab.that.listenModalEvents(tab, action);
					} else if (action.mutualType == 'EXTEND') {
						if (typeof tab.settings.operateExtend == 'function') {
							tab.settings.operateExtend(action, row);
						}
					}
				}
			});
		}
	}
})(jQuery)