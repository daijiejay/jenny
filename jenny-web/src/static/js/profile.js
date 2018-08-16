$(function() {
	request('get', '', '/sysuser/userCache', 'SYS', function(result) {
		if (!result.data.portrait) {
			result.data.portrait = '../assets/demo/avatar/profile.png';
		}
		$('#portrait').attr('src', result.data.portrait);
		$('#userName').html(result.data.userName);
		$('#email').html(result.data.email);
		$('#mobile').html(result.data.mobile);
		$('#birthday').html(result.data.birthday);
		$('#gander').html(result.data.gander);
		
		$('#email').editable();
		$('#mobile').editable();
		$('#birthday').editable();
		$('#gander').editable({
			prepend: "未选择",
			source: [{
					value: 1,
					text: '男'
				},
				{
					value: 0,
					text: '女'
				}
			],
			display: function(value, sourceData) {
				var colors = {
						"": "gray",
						1: "green",
						2: "blue"
					},
					elem = $.grep(sourceData, function(o) {
						return o.value == value;
					});
				if(elem.length) {
					$(this).text(elem[0].text).css("color", colors[value]);
				} else {
					$(this).empty();
				}
			}
		});
	});
});