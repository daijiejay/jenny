$(function() {
	loadMenu();
	
	var item = {'id':'-1','name':'首页','url':'main.html','closable':false};
	closableTab.addTab(item);
	sys_index_link($(document).find('.userinfo'));
	
	request('get', '', '/sysuser/userCache', 'SYS', function(result) {
		if (!result.data.portrait) {
			result.data.portrait = '../assets/demo/avatar/profile.png';
		}
		$('#header_userName').html(result.data.userName);
		$('#header_portrait').attr('src', result.data.portrait);
		$('#menu_userName').html(result.data.userName);
		$('#menu_portrait').attr('src', result.data.portrait);
	});
});

//加载菜单
function loadMenu() {
	request('get', '', '/sysindex/menu/authorized', 'SYS', function(result) {
		for (var i = 0; i < result.data.length; i++) {
			var str = '';
			if (result.data[i].level == 1) {
				str += '<li code="'+result.data[i].menuId+'" level="'+result.data[i].level
				+'"><a href="#"><i class="'+result.data[i].icon+'"></i><span>'
				+result.data[i].menuName+'</span>'
				+'<span class="badge badge-dark">1</span>'
				+'</a></li>';
				$('#side-menu').append(str);
			} else {
				if ($('#side-menu li[code="'+result.data[i].parentId+'"]').children('ul').length == 0) {
					$('#side-menu li[code="'+result.data[i].parentId+'"]').append('<ul class="acc-menu"></ul>');
					$('#side-menu li[code="'+result.data[i].parentId+'"]').addClass('hasChild');
				}
				if (result.data[i].mapping) {
					str += '<li code="'+result.data[i].menuId+'" level="'+result.data[i].level+'">'
						+'<a href="#" src="'+result.data[i].mapping+'" menuId="'+result.data[i].menuId+'">'+result.data[i].menuName+'</a>'
						+'</li>';
				} else {
					str += '<li code="'+result.data[i].menuId+'" level="'+result.data[i].level+'">'
						+'<a href="#" menuId="'+result.data[i].menuId+'">'+result.data[i].menuName+'</a>'
						+'</li>';
				}
				$('#side-menu li[code="'+result.data[i].parentId+'"]').children('ul').append(str);
			}
		}
		sys_index_link($(document).find('#side-menu'));
	});
}

//菜单连接加载页面
function sys_index_link(doc) {
	doc.find('a').click(function() {
		doc.find('li').removeClass('active');
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
