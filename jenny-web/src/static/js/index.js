$(function() {
	loadMenu();
});

//加载菜单
function loadMenu() {
	request('get', '', '/sysindex/menu/authorized', 'SYS', function(result) {
		for (var i = 0; i < result.data.length; i++) {
			var str = '';
			if (result.data[i].level == 1) {
				str += '<li code="'+result.data[i].menuId+'" level="'+result.data[i].level
				+'"><a href="#"><i class="fa '+result.data[i].icon+'"></i><span>'
				+result.data[i].menuName+'</span>'
				+'<span class="badge badge-dark">1</span>'
				+'</a></li>';
				$('#side-menu').append(str);
			} else {
				if ($('#side-menu li[code="'+result.data[i].parentId+'"]').children('ul').length == 0) {
					$('#side-menu li[code="'+result.data[i].parentId+'"]').append('<ul class="acc-menu"></ul>');
				}
				str += '<li code="'+result.data[i].menuId+'" level="'+result.data[i].level+'">'
					+'<a href="#" src="'+result.data[i].mapping+'" menuId="'+result.data[i].menuId+'">'+result.data[i].menuName+'</a>'
					+'</li>';
				$('#side-menu li[code="'+result.data[i].parentId+'"] ul').append(str);
			}
		}
		sys_index_link();
	});
}

//菜单连接加载页面
function sys_index_link() {
	var item = {'id':'-1','name':'首页','url':'main.html','closable':false};
	closableTab.addTab(item);
	$(document).find('#side-menu a').click(function() {
		$('#side-menu li').removeClass('active');
		$(this).parent().addClass('active');
		if ($(this).attr('src')) {
			var menuId = $(this).attr('menuId');
			
			var menuName = $(this).html();
			if ($(this).find('span').length > 0) {
				menuName = $(this).find('span').eq('0').html();
			}
			item = {'id':menuId,'name':menuName,'url':$(this).attr('src'),'closable':true};
			closableTab.addTab(item);
			$('#page-wrapper .tab-content div.active iframe').attr('src', $(this).attr('src'));
		}
	});
}

//退出登录
function logout() {
	request('post', '', '/syslogout', 'SYS', function(result) {
		location.href = 'login.html';
	});
}
