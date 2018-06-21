package org.daijie.jenny.cloud.sys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.common.feign.sys.SysMenuAuthorizedFeign;
import org.daijie.jenny.common.feign.sys.response.SysMenuAuthorizedResponse;
import org.daijie.jenny.common.feign.sys.response.SysRoleMenuResponse;
import org.daijie.jenny.common.mapper.sys.SysMenuMapper;
import org.daijie.jenny.common.mapper.sys.SysRoleAuthorizedMapper;
import org.daijie.jenny.common.mapper.sys.SysUserAuthorizedMapper;
import org.daijie.jenny.common.model.sys.SysMenu;
import org.daijie.jenny.common.model.sys.SysRoleAuthorized;
import org.daijie.jenny.common.model.sys.SysUserAuthorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysMenuAuthorizedService implements SysMenuAuthorizedFeign {
	
	private final String ADMIN_CODE = "admin";
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysRoleAuthorizedMapper sysRoleAuthorizedMapper;
	
	@Autowired
	private SysUserAuthorizedMapper sysUserAuthorizedMapper;

	@Override
	public ModelResult<SysRoleMenuResponse> getMenuAll() {
		SysRoleMenuResponse menuResponse = new SysRoleMenuResponse();
		List<SysMenu> list1 = sysMenuMapper.selectByExample(ExampleBuilder.create(SysMenu.class).andEqualTo("level", 1).build());
		List<SysMenuAuthorizedResponse> level1 = new ArrayList<SysMenuAuthorizedResponse>();
		list1.forEach(sysMenu -> {
			SysMenuAuthorizedResponse menuAuthorizedResponse = new SysMenuAuthorizedResponse();
			menuAuthorizedResponse.setAuthorizedTypes(sysMenu.getAuthorityTypes());
			menuAuthorizedResponse.setLevel(sysMenu.getLevel());
			menuAuthorizedResponse.setMapping(sysMenu.getMapping());
			menuAuthorizedResponse.setMenuCode(sysMenu.getMenuCode());
			menuAuthorizedResponse.setMenuName(sysMenu.getMenuName());
			menuAuthorizedResponse.setRoleCode(ADMIN_CODE);
		});
		menuResponse.setLevel1(level1);
		
		List<SysMenu> list2 = sysMenuMapper.selectByExample(ExampleBuilder.create(SysMenu.class).andEqualTo("level", 2).build());
		List<SysMenuAuthorizedResponse> level2 = new ArrayList<SysMenuAuthorizedResponse>();
		list2.forEach(sysMenu -> {
			SysMenuAuthorizedResponse menuAuthorizedResponse = new SysMenuAuthorizedResponse();
			menuAuthorizedResponse.setAuthorizedTypes(sysMenu.getAuthorityTypes());
			menuAuthorizedResponse.setLevel(sysMenu.getLevel());
			menuAuthorizedResponse.setMapping(sysMenu.getMapping());
			menuAuthorizedResponse.setMenuCode(sysMenu.getMenuCode());
			menuAuthorizedResponse.setMenuName(sysMenu.getMenuName());
			menuAuthorizedResponse.setRoleCode(ADMIN_CODE);
		});
		menuResponse.setLevel2(level2);
		return Result.build(menuResponse);
	}

	@Override
	public ModelResult<SysRoleMenuResponse> getMenuByRoles(String... roleCodes) {
		SysRoleMenuResponse menuResponse = getMenuAll().getData();
		List<SysRoleAuthorized> roleAuthorities = sysRoleAuthorizedMapper
				.selectByExample(ExampleBuilder.create(SysMenu.class).andIn("roleCode", Arrays.asList(roleCodes)).build());
		
		menuResponse.setLevel1(menuResponse.getLevel1().stream().filter(menu -> {
			return roleAuthorities.stream().anyMatch(authorized -> menu.getMenuCode().equals(authorized.getMenuCode()));
		}).map(menu -> {
			menu.setAuthorizedTypes(roleAuthorities.stream()
					.filter(authorized -> menu.getMenuCode().equals(authorized.getMenuCode())).findFirst().get().getAuthorizedTypes());
			return menu;
		}).collect(Collectors.toList()));
		
		menuResponse.setLevel2(menuResponse.getLevel2().stream().filter(menu -> {
			return roleAuthorities.stream().anyMatch(authorized -> menu.getMenuCode().equals(authorized.getMenuCode()));
		}).map(menu -> {
			menu.setAuthorizedTypes(roleAuthorities.stream()
					.filter(authorized -> menu.getMenuCode().equals(authorized.getMenuCode())).findFirst().get().getAuthorizedTypes());
			return menu;
		}).collect(Collectors.toList()));
		return Result.build(menuResponse);
	}

	@Override
	public ModelResult<SysRoleMenuResponse> getMenuByUser(String usercode) {
		List<SysUserAuthorized> list = sysUserAuthorizedMapper
				.selectByExample(ExampleBuilder.create(SysMenu.class).andEqualTo("userCode", usercode).build());
		String[] roleCodes = new String[list.size()];
		list.stream().forEach(sysUserAuthorized -> {
			int i = 0;
			roleCodes[i] = sysUserAuthorized.getRoleCode();
			i ++;
		});
		return getMenuByRoles(roleCodes);
	}

}
