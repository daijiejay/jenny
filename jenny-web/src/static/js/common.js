var serverMap = new Map();
serverMap.set('SYS', 'http://daijie.org:12801/')

var requestMap = new Map();

function request(method, data, url, serverId, callback) {
	if(requestMap.get(url)) {
		return;
	}
	requestMap.set(url, true);
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
				if(result.code == '200') {
					return callback(result);
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
				if(result.code == '200') {
					return callback(result);
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
			}
		});
	}
}