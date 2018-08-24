$(function() {
	var documentation;
	request('get', '', '/api-focus-resources', '', function(result) {
		for (var i = 0; i < result.data.length; i++) {
			var documentationName = result.data[0].location.substring(result.data[0].location.lastIndexOf('=')+1);
			$('#documentationName').append('<option value='+documentationName+'>'+documentationName+'</option>')
		}
		
		var param = result.data[0].location.substring(result.data[0].location.lastIndexOf('=')+1);
		loadApiInfo(param);
		$('#documentationName').change(function() {
			loadApiInfo($(this).val());
		});
	});
	
	function loadApiInfo(param) {
		request('get', '', '/api-focus/api-docs?group='+param, '', function(result) {
			documentation = result.data;
			console.log(result.data);
			//加载菜单
			jQuery.each(documentation.tags, function(index, controller) {
				var str = '';
				str += '<div class="collapsible-menu">';
				str += '	<span class="inbox-leftbar-category clearfix gallery">';
				str += '		<a href="javascript:;" data-toggle="collapse" data-target="#folders" class="category-heading">'+controller.description+'<span class="badge badge-warning">3</span></a>';
				str += '	</span>';
				str += '	<div class="collapse in" name="'+controller.name+'"></div>';
				str += '</div>';
				$('.inbox-menu').append(str);
				
				jQuery.each(documentation.paths, function(apiKey, api) {
					jQuery.each(api, function(methodKey, method) {
						if (method.tags && method.tags[0] == controller.name) {
							var str2 = '<a href="#" class="inbox-menu-item hide" tag="'+apiKey+'">'+method.summary+'</a>';
							$('.collapsible-menu .collapse[name="'+controller.name+'"]').append(str2);
						}
					});
				});
			});
			
			//加载方法信息
			jQuery.each(documentation.paths, function(apiKey, api) {
				jQuery.each(api, function(methodKey, method) {
					var joint = '';
					method.tags[0].split('-').forEach(function(value) {
						joint += value.substring(0,1).toUpperCase()+value.substring(1);
					})
					var str = '';
					str += '<div class="hide" tag='+apiKey+'>';
					str += '	<table class="table table-condensed">';
					str += '		<tbody>';
					str += '			<tr><td>接口描述</td><td>'+method.summary+'</td></tr>';
					str += '			<tr><td>接口地址</td><td>'+apiKey+'</td></tr>';
					str += '			<tr><td>请求方式</td><td>'+methodKey+'</td></tr>';
					str += '			<tr><td>类名</td><td>'+joint+'</td></tr>';
					str += '			<tr><td>方法名</td><td>'+method.operationId+'</td></tr>';
					str += '		</tbody>';
					str += '	</table>';
					str += '</div>';
					$('#documentation').append(str);
				});
			});
			
			$('.collapsible-menu').eq('0').find('a').removeClass('hide');
			$('.collapsible-menu').click(function (){
				$('.inbox-menu-item').addClass('hide');
				$(this).find('a').removeClass('hide');
			});
			$('.inbox-menu-item').click(function (){
				$('.collapsible-menu').find('a').removeClass('active');
				$(this).addClass('active');
				$('#documentation').find('div').addClass('hide');
				$('#documentation').find('div[tag="'+$(this).attr('tag')+'"]').removeClass('hide');
			});
		});
	}
});