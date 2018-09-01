$(function() {
	$("#serverMonitoringTable").initTable({
		searchTarget: '.table-search',
		columnFormatter: function(value, row, index, field) {
			if (field == 'enable') {
				if(value) {
					return '是';
				}
				return '否';
			}
			return value;
		}
	});
});