$(function() {
	var documentation;
	request('get', '', '/api-focus-resources', '', function(result) {
		for (var i = 0; i < result.data.length; i++) {
			var documentationName = result.data[i].location.substring(result.data[i].location.lastIndexOf('=')+1);
			$('#documentationName').append('<option value='+documentationName+'>'+result.data[i].name+'</option>')
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
				var controllerStr = '';
				controllerStr += '<div class="collapsible-menu">';
				controllerStr += '	<div class="inbox-leftbar-category clearfix gallery">';
				controllerStr += '		<a href="javascript:;" data-toggle="collapse" data-target="#folders" class="menu-title">'+controller.description+'<span class="badge badge-warning"></span></a>';
				controllerStr += '	</div>';
				controllerStr += '	<div class="collapse in" name="'+controller.name+'"></div>';
				controllerStr += '</div>';
				$('.inbox-menu').append(controllerStr);
				
				jQuery.each(documentation.paths, function(apiKey, api) {
					jQuery.each(api, function(methodKey, method) {
						if (method.tags && method.tags[0] == controller.name) {
							var methodStr = '<a href="#" class="inbox-menu-item hide" tag="'+apiKey+'">'+method.summary+'</a>';
							var $this = $('.collapsible-menu .collapse[name="'+controller.name+'"]');
							$this.append(methodStr);
							var count = $this.prev().find('.badge').html();
							count = count ? ++count : 1;
							$this.prev().find('.badge').html(count);
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
					var methodInfoStr = '';
					methodInfoStr += '<div class="hide methodInfo" tag='+apiKey+'>';
					methodInfoStr += '	<table class="table table-condensed">';
					methodInfoStr += '		<tbody>';
					methodInfoStr += '			<tr><td>接口描述</td><td>'+method.summary+'</td></tr>';
					methodInfoStr += '			<tr><td>接口地址</td><td>'+apiKey+'</td></tr>';
					methodInfoStr += '			<tr><td>请求方式</td><td>'+methodKey+'</td></tr>';
					if (method.consumes) {
						methodInfoStr += '			<tr><td>内容类型</td><td>'+method.consumes[0]+'</td></tr>';
					}
					methodInfoStr += '			<tr><td>类名</td><td>'+joint+'</td></tr>';
					methodInfoStr += '			<tr><td>方法名</td><td>'+method.operationId.substring(0, method.operationId.lastIndexOf('Using'))+'</td></tr>';
					methodInfoStr += '		</tbody>';
					methodInfoStr += '	</table>';
					methodInfoStr += '</div>';
					$('#documentation').append(methodInfoStr);
					
					var methodRequestInfoStr = '';
					methodRequestInfoStr += '<div class="panel panel-info">';
					methodRequestInfoStr += '	<div class="panel-heading">';
					methodRequestInfoStr += '		<h2>请求参数</h2><div class="options"></div>';
					methodRequestInfoStr += '	</div>';
					methodRequestInfoStr += '	<div class="panel-body panel-no-padding">';
					methodRequestInfoStr += '		<table class="table table-striped">';
					methodRequestInfoStr += '			<thead><tr class="info">';
					methodRequestInfoStr += '				<th>参数名称</th>';
					methodRequestInfoStr += '				<th>说明</th>';
					methodRequestInfoStr += '				<th>是否必须</th>';
					methodRequestInfoStr += '				<th>类型</th>';
					methodRequestInfoStr += '			</tr></thead>';
					methodRequestInfoStr += '			<tbody class="methodRequestInfo" tag='+apiKey+'></tbody>';
					methodRequestInfoStr += '		</table>';
					methodRequestInfoStr += '	</div>';
					methodRequestInfoStr += '</div>';
					$('#documentation div[tag="'+apiKey+'"]').append(methodRequestInfoStr);
					if (method.parameters) {
						jQuery.each(method.parameters, function(paramKey, param) {
							var methodRequestParamStr = '';
							methodRequestParamStr += '<tr>';
							methodRequestParamStr += '	<td>'+param.name+'</td>';
							methodRequestParamStr += '	<td>'+param.description+'</td>';
							methodRequestParamStr += '	<td>'+param.required+'</td>';
							methodRequestParamStr += '	<td>'+param.type+'</td>';
							methodRequestParamStr += '</tr>';
							$('#documentation div[tag="'+apiKey+'"] .methodRequestInfo').append(methodRequestParamStr);
						});
					}
					
					var methodResponseInfoStr = '';
					methodResponseInfoStr += '<div class="panel panel-success">';
					methodResponseInfoStr += '	<div class="panel-heading">';
					methodResponseInfoStr += '		<h2>返回参数</h2><div class="options"></div>';
					methodResponseInfoStr += '	</div>';
					methodResponseInfoStr += '	<div class="panel-body panel-no-padding">';
					methodResponseInfoStr += '		<table class="table table-striped">';
					methodResponseInfoStr += '			<thead><tr class="info">';
					methodResponseInfoStr += '				<th></th>';
					methodResponseInfoStr += '				<th>参数名称</th>';
					methodResponseInfoStr += '				<th>说明</th>';
					methodResponseInfoStr += '				<th>是否必须</th>';
					methodResponseInfoStr += '				<th>类型</th>';
					methodResponseInfoStr += '			</tr></thead>';
					methodResponseInfoStr += '			<tbody class="methodResponseInfo"></tbody>';
					methodResponseInfoStr += '		</table>';
					methodResponseInfoStr += '	</div>';
					methodResponseInfoStr += '</div>';
					$('#documentation div[tag="'+apiKey+'"]').append(methodResponseInfoStr);
					
					var id = method.responses[200].schema.$ref;
					id = id.substring(id.lastIndexOf('/')+1)
					initResponseInfo(documentation.definitions, $('#documentation div[tag="'+apiKey+'"] .methodResponseInfo'), id);
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
				$('#documentation').find('.methodInfo').addClass('hide');
				$('#documentation').find('div[tag="'+$(this).attr('tag')+'"]').removeClass('hide');
			});
			$('table .detail-icon').click(function (){
				if ($(this).find('i').hasClass("glyphicon-minus")) {
					$(this).find('i').addClass('glyphicon-plus').removeClass('glyphicon-minus');
					$(this).parents('tr:first').next().addClass('hide');
				} else {
					$(this).find('i').addClass('glyphicon-minus').removeClass('glyphicon-plus');
					$(this).parents('tr:first').next().removeClass('hide');
				}
			});
		});
	}
	
	//加载返回参数数据
	function initResponseInfo(models, ele, id) {
		if (models) {
			jQuery.each(models, function(modelKey, model) {
				if (id == modelKey) {
					jQuery.each(model.properties, function(propertyKey, property) {
						var methodRequestModelStr = '', isObj = false;
						if (property.$ref || property.type == 'array') {
							isObj = true;
							if (property.items && (property.items.type && property.items.type != 'object' && property.items.type != 'array')) {
								isObj = false;
								property.type += '('+property.items.type+')';
							}
						}
						methodRequestModelStr += '<tr>';
						if (isObj) {
							methodRequestModelStr += '	<td><a class="detail-icon" href="#"> <i class="glyphicon glyphicon-minus icon-minus"></i></a></td>';
						} else {
							methodRequestModelStr += '	<td></td>';
						}
						methodRequestModelStr += '	<td>'+propertyKey+'</td>';
						methodRequestModelStr += '	<td>'+property.description+'</td>';
						methodRequestModelStr += '	<td>'+property.allowEmptyValue+'</td>';
						if (property.$ref && !property.type) {
							methodRequestModelStr += '	<td>object</td>';
						} else {
							methodRequestModelStr += '	<td>'+property.type+'</td>';
						}
						methodRequestModelStr += '</tr>';
						ele.append(methodRequestModelStr);
						
						//加载子列表
						if (isObj) {
							var childRequestModelStr = '';
							childRequestModelStr += '<tr class="detail-view">';
							childRequestModelStr += '	<td colspan="9">';
							childRequestModelStr += '		<div class="bootstrap-table">';
							childRequestModelStr += '			<table class="table table-striped">';
							childRequestModelStr += '				<tbody class="childResponseInfo">';
							childRequestModelStr += '				</tbody>';
							childRequestModelStr += '			</table>';
							childRequestModelStr += '		</div>';
							childRequestModelStr += '	</td>';
							childRequestModelStr += '</tr>';
							ele.append(childRequestModelStr);
							var id = property.$ref ? property.$ref : property.items.$ref;
							id = id.substring(id.lastIndexOf('/')+1)
							//加载子列表数据
							initResponseInfo(models, ele.find('.childResponseInfo'), id);
						}
					});
				}
			});
		}
	}
});