/**
 * 存储服务名地址
 */
var serverMap = new Map();
serverMap.set('SYS', 'http://daijie.org:12801/')

/**
 * 存储请求地址缓存，请求完成后清除，防止重复请求
 */
var requestMap = new Map();
/**
 * 请求封装，处理请求重复提交，加载效果展示，自动拦截用户过期跳转登录，请求错误提示
 * @param {Object} method 请求方式
 * @param {Object} data 请求数据
 * @param {Object} url 请求地址
 * @param {Object} serverId 请求服务名
 * @param {Object} callback 请求回调
 */
function request(method, data, url, serverId, callback) {
	if(requestMap.get(url)) {
		return;
	}
	requestMap.set(url, true);
	if (!loader) {
		initFakeloader();
	}
    loader.fadeIn();
	if(method.toUpperCase() == 'GET') {
		jQuery.ajax({
			type: method,
			url: serverMap.get(serverId) + url,
			data: data,
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			success: function(result, textStatus, request) {
				requestMap.delete(url);
				loader.fadeOut();
				if(result.code == '200') {
					if(typeof callback == 'function') {
						return callback(result);
					}
				} else if(result.code == '300') {
					location.href = '../html/login.html';
				} else {
					layer.alert(result.msg);
					return;
				}
			},
			error: function(e) {
				layer.alert('访问服务器异常！');
				requestMap.delete(url);
				loader.fadeOut();
			}
		});
	} else {
		jQuery.ajax({
			type: method,
			url: serverMap.get(serverId) + url,
			data: JSON.stringify(data),
			dataType: 'json',
			contentType: 'application/json',
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			success: function(result, textStatus, request) {
				requestMap.delete(url);
				loader.fadeOut();
				if(result.code == '200') {
					if(typeof callback == 'function') {
						return callback(result);
					}
				} else if(result.code == '300') {
					location.href = '../html/login.html';
				} else {
					layer.alert(result.msg);
					return;
				}
			},
			error: function(e) {
				layer.alert('访问服务器异常！');
				requestMap.delete(url);
				loader.fadeOut();
			}
		});
	}
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
	var ifm = parent.window.document.getElementById("iframepage");
	if(ifm) {
		ifm.height = document.body.clientHeight < 600 ? 600 : document.body.clientHeight;
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
	var that, loadTable, initTable = {
			table: '',
			actions: []
		},
		settings = {
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
		},
		modalMap = new Map();
	$.fn.extend({
		initTable: function(settings) {
			that = this;
			this.settings = settings;
			var menuId = window.parent.$('#page-wrapper iframe').attr('menuId');
			request('get', '', '/sysindex/menu/authorized/action/' + menuId, 'SYS', function(result) {
				initTable.table = result.data.table;
				initTable.actions = result.data.actions;
				that.initTableData();
				that.listenSearch();
				that.listenToolbar();
				if (loadTable) {
					that.bootstrapTable('refresh');
				} else {
					loadTable = true;
				}
			});
		},
		initTableData: function(params) {
			var table = initTable.table;
			var actions = initTable.actions;
			this.bootstrapTable({
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
				queryParams: that.initQueryParams,
				toolbar: that.initToolbar,
				columns: that.initColumns
			});
		},
		initQueryParams: function(params) {
			var queryParams = $(settings.searchTarget).serializeJson();;
			queryParams.pageSize = params.limit;
			queryParams.pageNumber = params.offset + 1;
			if (typeof that.settings.searchParams == 'function') {
				return that.settings.searchParams(queryParams);
			}
			return settings.searchParams(queryParams);

		},
		initToolbar: function() {
			var toolbar = '<div class="btn-group">';
			var actions = initTable.actions;
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
				if(action.actionType == 'OPERATE' && that.settings.operateFormatter(action, row)) {
					if(action.mutualType == 'CONFIRM') {
						operate.push('<a actionId="' + action.actionId + '" class="btn active" href="#">' + action.actionName + '</a>');
					} else if(action.mutualType == 'FORM') {
						operate.push('<a id="' + row.userId + '" actionId="' + action.actionId + '" class="btn active" data-toggle="modal" data-target="' + action.formTarget + '" href="#">' + action.actionName + '</a>');
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
			if(!modalMap.get(action.formTarget)) {
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
				if(action.actionId == actionId) {
					if(that.settings.listenModalSave(modal.find('form'), action)) {
						var formData = modal.find('form').serializeJson();
						var url = that.formatterUrl(action.interfaceUrl, formData);
						request(action.interfaceMethod, formData, url, action.interfaceServerId, function(result) {
							modal.find('button.save').unbind("click");
							modal.modal('hide');
							that.bootstrapTable('refresh');
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
		'click .btn': function(e, value, row, index) {
			initTable.actions.forEach(function(action, i) {
				if(action.actionType == 'OPERATE' && action.actionName == e.target.innerHTML) {
					if(action.mutualType == 'CONFIRM') {
						layer.confirm('确定需要' + action.actionName + '吗？', {
							yes: function(index, layero) {
								var url = that.formatterUrl(action.interfaceUrl, row);
								request(action.interfaceMethod, '', url, action.interfaceServerId, function(result) {
									that.bootstrapTable('refresh');
									layer.close(index);
								})
							}
						});
					} else if(action.mutualType == 'FORM') {
						that.listenModalEvents(action);
					}
				}
			});

		}
	}
})(jQuery)