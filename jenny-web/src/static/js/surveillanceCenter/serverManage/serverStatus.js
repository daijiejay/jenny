$(function() {
	$("#serverMonitoringTable").initTable({
		searchTarget: '.table-search',
		columnFormatter: function(value, row, index, field) {
			if (field == 'status') {
				if(value == 'UP') {
					return '<span class="label label-success">正常</span>';
				} else if (value == 'DOWN') {
					return '<span class="label label-danger">宕机</span>';
				}
			}
			if (field == 'serverNumber') {
				if (value > 0) {
					var str = '';
					str += '<span href="#" id="'+row.name+'" class="badge badge-info gallery pl5" data-trigger="click" data-toggle="popover" data-placement="bottom" title="'+row.name+'" data-content="">';
					str += value;
					str += '</span>';
					return str;
				}
			}
			return value;
		}
	});
	$("#serverMonitoringTable").on('load-success.bs.table',function(data){
		$(document).find("[data-toggle='popover']").popover({
			html: true,
			delay: {show: 100, hide: 100},
			content: generateServerInfoHmtl()
		}).on('shown.bs.popover', function () {
		  	var $this = $(this);
		  	var id = $this.attr('id');
		  	var row = $("#serverMonitoringTable").bootstrapTable('getRowByUniqueId', id);
		  	if (row.instances) {
		  		row.instances.forEach(function(value, index) {
		  			$this.next().find('.instances').append('<li class"gallery"><a href"#" id="'+value.instanceId+'">'+value.serverUrl+'</a><li>')
		  		});
		  	}
		});
    });
	
	function generateServerInfoHmtl() {
		var html = '';
		html += '<div style="width:270px" id="instances"><ul class="instances scrollthis pl0"></ul></div>';
		return html;
	}
});