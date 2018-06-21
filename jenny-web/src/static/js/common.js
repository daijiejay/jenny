var server_sys = 'http://localhost:12801/';

function request(method, data, url, callback) {
	if(method.toUpperCase() == 'GET') {
		jQuery.ajax({
			type: method,
			url: server_sys + url,
			data: data,
			success: function(result) {
				return callback(result);
			}
		});
	} else {
		jQuery.ajax({
			type: method,
			url: server_sys + url,
			data: JSON.stringify(data),
			dataType: 'json',
			contentType: 'application/json',
			success: function(result) {
				return callback(result);
			}
		});
	}
}
