var server_sys = 'http://daijie.org:12801/';

var requestMap = new Map();
function request(method, data, url, callback) {
	if (requestMap.get(url)) {
		return;
	}
	requestMap.set(url, true);
	if(method.toUpperCase() == 'GET') {
		jQuery.ajax({
			type: method,
			url: server_sys + url,
			data: data,
	        xhrFields: {
	            withCredentials: true
	        },
			headers: {
				'Access-Control-Allow-Origin': 'http://localhost'
		    },
	        crossDomain: true,
			success: function(result, textStatus, request) {
				requestMap.delete(url);
				if(!result.success) {
					layer.alert(result.msg);
					return;
				}
				return callback(result);
			},
			error: function(e) {
				layer.alert('访问服务器异常！');
				requestMap.delete(url);
			}
		});
	} else {
		jQuery.ajax({
			type: method,
			url: server_sys + url,
			data: JSON.stringify(data),
			dataType: 'json',
			contentType: 'application/json',
	        xhrFields: {
	            withCredentials: true
	        },
	        crossDomain: true,
			success: function(result, textStatus, request) {
				requestMap.delete(url);
				if(!result.success) {
					layer.alert(result.msg);
					return;
				}
				return callback(result);
			},
			error: function(e) {
				layer.alert('访问服务器异常！');
				requestMap.delete(url);
			}
		});
	}
}
