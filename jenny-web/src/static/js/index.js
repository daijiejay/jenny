$(function() {
	addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);

	function hideURLbar() {
		window.scrollTo(0, 1);
	}

	new WOW().init();

	var menuLeft = document.getElementById('cbp-spmenu-s1'),
		showLeftPush = document.getElementById('showLeftPush'),
		body = document.body;

	showLeftPush.onclick = function() {
		classie.toggle(this, 'active');
		classie.toggle(body, 'cbp-spmenu-push-toright');
		classie.toggle(menuLeft, 'cbp-spmenu-open');
		disableOther('showLeftPush');
	};
	function disableOther(button) {
		if(button !== 'showLeftPush') {
			classie.toggle(showLeftPush, 'disabled');
		}
	}
	//设置iframe的高度
	var height = document.documentElement.clientHeight - $('.sticky-header').height();
	$('#page-wrapper iframe').css("height", height + 'px')
	
	loadMenu();
});

//加载菜单
function loadMenu() {
	request('get', '', '/sysindex/menu/authorized', 'SYS', function(result) {
		var menu_level1 = '';
		for (var i = 0; i < result.data.level1.length; i++) {
			menu_level1 += '<li code="'+result.data.level1[i].menuId
				+'"><a href="#"><i class="fa '+result.data.level1[i].icon+' nav_icon"></i>'
				+result.data.level1[i].menuName+'<span class="fa arrow"></span></a></li>';
		}
		$('#side-menu').html(menu_level1);
		
		var menu = $('#side-menu li');
		for (var i = 0; i < result.data.level2.length; i++) {
			menu.each(function(){
				if ($(this).attr("code") == result.data.level2[i].parentId) {
					var menu_level2 = '';
					if ($(this).children('ul').length == 0) {
						$(this).append('<ul class="nav nav-second-level collapse"></ul>');
					}
					menu_level2 += '<li><a href="#" src="'+result.data.level2[i].mapping+'" menuId="'+result.data.level2[i].menuId+'">'
						+result.data.level2[i].menuName+'</a></li>';
					$(this).find(".nav-second-level").append(menu_level2);
				}
			})
		}
		$('#side-menu').metisMenu();
		sys_index_link();
	});
}

//菜单连接加载页面
function sys_index_link() {
	$(document).find('#side-menu a').click(function() {
		if ($(this).attr('src')) {
			$('#page-wrapper iframe').attr('src', $(this).attr('src'));
			$('#page-wrapper iframe').attr('menuId', $(this).attr('menuId'));
		}
	});
}