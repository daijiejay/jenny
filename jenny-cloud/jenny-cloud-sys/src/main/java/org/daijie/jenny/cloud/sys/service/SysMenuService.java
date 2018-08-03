package org.daijie.jenny.cloud.sys.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.daijie.core.controller.enums.ResultCode;
import org.daijie.core.controller.exception.ApiException;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.PageResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.jdbc.mybatis.example.ExampleBuilder;
import org.daijie.jenny.common.feign.sys.SysMenuFeign;
import org.daijie.jenny.common.feign.sys.enumtype.MoveType;
import org.daijie.jenny.common.feign.sys.request.SysMenuMoveRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.daijie.jenny.common.feign.sys.response.SysMenuTreeResponse;
import org.daijie.jenny.common.mapper.sys.SysMenuMapper;
import org.daijie.jenny.common.model.sys.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.bean.BeanUtil;

@RestController
public class SysMenuService implements SysMenuFeign {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public ModelResult<PageResult<SysMenuResponse>> getMenuAll(SysMenuPageRequest sysMenuPageRequest) {
		PageHelper.startPage(sysMenuPageRequest.getPageNumber(), sysMenuPageRequest.getPageSize());
		SysMenu sysMenu = new SysMenu();
		BeanUtil.copyProperties(sysMenuPageRequest, sysMenu);
		List<SysMenu> menus = sysMenuMapper.select(sysMenu);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(menus);
		return Result.build(new PageResult<SysMenuResponse>(pageInfo.getList(), pageInfo.getTotal(), SysMenuResponse.class));
	}

	@Override
	public ModelResult<List<SysMenuTreeResponse>> getMenuTree() {
		List<SysMenu> menus = sysMenuMapper.selectByExample(ExampleBuilder.create(SysMenu.class).orderByAsc("menuCode").build());
		List<SysMenuTreeResponse> list = new ArrayList<SysMenuTreeResponse>();
		menus.forEach(menu -> {
			SysMenuTreeResponse sysMenuTreeResponse = new SysMenuTreeResponse();
			sysMenuTreeResponse.setId(menu.getMenuId());
			sysMenuTreeResponse.setPId(menu.getParentId());
			sysMenuTreeResponse.setName(menu.getMenuName());
			list.add(sysMenuTreeResponse);
		});
		return Result.build(list);
	}

	@Override
	public ModelResult<SysMenuResponse> getMenu(String menuId) {
		SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);
		SysMenuResponse sysMenuResponse = new SysMenuResponse();
		BeanUtil.copyProperties(sysMenu, sysMenuResponse);
		return Result.build(sysMenuResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysMenuResponse> addMenu(SysMenuAddRequest sysMenuRequest) {
		SysMenu sysMenu = new SysMenu();
		BeanUtil.copyProperties(sysMenuRequest, sysMenu);
		addMenu(sysMenu);
		SysMenuResponse sysMenuResponse = new SysMenuResponse();
		BeanUtil.copyProperties(sysMenu, sysMenuResponse);
		return Result.build(sysMenuResponse);
	}
	
	public void addMenu(SysMenu sysMenu) {
		if (sysMenu.getParentId() == null || sysMenu.getParentId() == 0) {
			List<SysMenu> menus = sysMenuMapper.selectByExample(
					ExampleBuilder.create(SysMenu.class).andEqualTo("level", 1).orderByDesc("menuId").build());
			if (menus.size() > 0) {
				sysMenu.setMenuCode(generatorMenuId(menus.get(0).getMenuId(), 1, null));
			} else {
				sysMenu.setMenuCode(10000);
			}
			sysMenu.setParentId(0);
			sysMenu.setLevel(1);
			sysMenu.setSort(menus.size()+1);
		} else {
			SysMenu parentMenu = sysMenuMapper.selectByPrimaryKey(sysMenu.getParentId());
			if (parentMenu.getSort() == 2) {
				throw new ApiException(ResultCode.CODE_100, "不能创建3级及以上菜单");
			}
			List<SysMenu> menus = sysMenuMapper.selectByExample(
					ExampleBuilder.create(SysMenu.class).andEqualTo("parentId", sysMenu.getParentId()).orderByDesc("menuId").build());
			sysMenu.setLevel(parentMenu.getLevel()+1);
			int id = menus.size() > 0 ? menus.get(0).getMenuCode() : parentMenu.getMenuCode();
			sysMenu.setParentCode(parentMenu.getMenuCode());
			sysMenu.setMenuCode(generatorMenuId(id, sysMenu.getLevel(), null));
			sysMenu.setSort(menus.size()+1);
		}
		sysMenuMapper.insertSelective(sysMenu);
	}
	
	/**
	 * 根据同等级的ID自动增长生成下一个ID
	 * @param id 同等级ID
	 * @param level 等级
	 * @param num 向后添加数字几，可传null，默认为1
	 * @return Integer
	 */
	public Integer generatorMenuId(Integer id, Integer level, Integer num) {
		if (id == 0) {
			return 10000;
		}
		Integer generateId = 0;
		char[] charArray = id.toString().toCharArray();
		int[] nums = new int[(int) Math.ceil(charArray.length/2.0)];
		int j = nums.length - 1;
		for (int i = charArray.length-2; i >= -1 ; i-=2) {
			int g = new Integer(charArray[i+1]+"");
			int s = 0;
			if (i >= 0) {
				s = new Integer(charArray[i]+"");
			}
			nums[j] = s * 10 + g;
			j--;
		}
		if (num == null) {
			num = 1;
		}
		nums[level-1] += num;
		for (int i = 0; i < nums.length; i++) {
			int digit = nums[i];
			for (int k = nums.length - (i * 2); k >= 0; k--) {
				digit *= 10;
			}
			generateId += digit;
		}
		return generateId;
	}

	@Override
	@Transactional
	public ModelResult<SysMenuResponse> updateMenu(SysMenuUpdateRequest sysMenuRequest) {
		SysMenu sysMenu = new SysMenu();
		BeanUtil.copyProperties(sysMenuRequest, sysMenu);
		sysMenuMapper.updateByExampleSelective(sysMenu, ExampleBuilder.create(SysMenu.class).andEqualTo("menuId", sysMenuRequest.getMenuId()).build());
		SysMenuResponse sysMenuResponse = new SysMenuResponse();
		BeanUtil.copyProperties(sysMenu, sysMenuResponse);
		return Result.build(sysMenuResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysMenuResponse> deleteMenu(@PathVariable(name = "menuId") Integer menuId) {
		List<SysMenu> list = sysMenuMapper.selectByExample(ExampleBuilder.create(SysMenu.class).andEqualTo("menuId", menuId).build());
		if (list.size() == 1) {
			sysMenuMapper.deleteByPrimaryKey(list.get(0).getMenuId());
			deleteChildMenu(menuId);
			forwardMoveMenu(list.get(0).getParentId(), list.get(0).getSort(), 1);
			SysMenuResponse sysMenuResponse = new SysMenuResponse();
			BeanUtil.copyProperties(list.get(0), sysMenuResponse);
			return Result.build(sysMenuResponse);
		}
		throw new ApiException(ResultCode.CODE_102, "无效的菜单编号！");
	}
	
	public void deleteChildMenu(Integer parentId) {
		List<SysMenu> list = sysMenuMapper.selectByExample(ExampleBuilder.create(SysMenu.class).andEqualTo("parentId", parentId).build());
		if (list.size() > 0) {
			for (SysMenu sysMenu : list) {
				deleteChildMenu(sysMenu.getMenuId());
			}
			sysMenuMapper.deleteByExample(ExampleBuilder.create(SysMenu.class).andEqualTo("parentId", parentId).build());
		}
	}

	/**
	 * 根据某个菜单下的子菜单往前移数量
	 * @param parentId 父级菜单编号
	 * @param sortNum 从第几个菜单开始前移
	 * @param num 前移数量
	 */
	@Transactional
	public void forwardMoveMenu(Integer parentId, int sortNum, int num) {
		List<SysMenu> menus = sysMenuMapper.selectByExample(
				ExampleBuilder.create(SysMenu.class).andEqualTo("parentId", parentId)
				.andGreaterThanOrEqualTo("sort", sortNum).build());
		for (SysMenu sysMenu : menus) {
			sysMenu.setSort(sysMenu.getSort()-1);
			sysMenu.setMenuCode(generatorMenuId(sysMenu.getMenuCode(), sysMenu.getLevel(), num*-1));
			sysMenuMapper.updateByPrimaryKey(sysMenu);
			renewChildMenu(sysMenu);
		}
	}
	
	public void backwardMoveMenu(int num) {
		
	}

	@Override
	@Transactional
	public ModelResult<Boolean> moveMenu(SysMenuMoveRequest sysMenuRequest) {
		SysMenu menu = sysMenuMapper.selectByPrimaryKey(sysMenuRequest.getTargetId());
		if (sysMenuRequest.getMoveType() == MoveType.INNER) {
			if (menu.getLevel() > 3) {
				throw new ApiException(ResultCode.CODE_100, "不能创建3级及以上菜单");
			}
			renewMoveMenu(menu, sysMenuRequest.getMenuIds());
		} else if (sysMenuRequest.getMoveType() == MoveType.PREV) {
			moveBeforeMenu(menu, sysMenuRequest.getMenuIds());
		} else if (sysMenuRequest.getMoveType() == MoveType.NEXT) {
			moveAfterMenu(menu, sysMenuRequest.getMenuIds());
		}
		return Result.build(true);
	}
	
	/**
	 * 菜单移动到指定菜单前
	 * @param targetMenu 被移动的菜单
	 * @param menuIds 移动的菜单编号数组
	 */
	@Transactional
	public void moveBeforeMenu(SysMenu targetMenu, List<Integer> menuIds) {
		List<SysMenu> menus = sysMenuMapper.selectByExample(
				ExampleBuilder.create(SysMenu.class).andEqualTo("parentId", targetMenu.getParentId())
				.andNotIn("menuId", menuIds)
				.orderByAsc("menuCode").build());
		int menuCode = targetMenu.getParentCode();
		int sort = 0;
		List<Integer> oldParentIds = new ArrayList<Integer>();
		for (SysMenu sysMenu : menus) {
			if (sysMenu.getMenuId().equals(targetMenu.getMenuId())) {
				for (Integer menuId : menuIds) {
					menuCode = generatorMenuId(menuCode, targetMenu.getLevel(), 1);
					SysMenu menu = sysMenuMapper.selectByPrimaryKey(menuId);
					if (!menuId.equals(menu.getParentId())) {
						oldParentIds.add(menu.getParentId());
					}
					menu.setSort(++sort);
					menu.setMenuCode(menuCode);
					if (!menu.getLevel().equals(sysMenu.getLevel())) {
						menu.setParentCode(targetMenu.getParentCode());
					} else {
						menu.setParentCode(sysMenu.getParentCode());
					}
					menu.setLevel(sysMenu.getLevel());
					menu.setParentId(sysMenu.getParentId());
					sysMenuMapper.updateByPrimaryKey(menu);
					renewChildMenu(menu);
				}
			}
			menuCode = generatorMenuId(menuCode, targetMenu.getLevel(), 1);
			sysMenu.setSort(++sort);
			sysMenu.setMenuCode(menuCode);
			sysMenuMapper.updateByPrimaryKey(sysMenu);
			renewChildMenu(sysMenu);
		}
		for (Integer oldParentId : oldParentIds) {
			correctChildMenu(oldParentId);
		}
		
	}
	
	/**
	 * 菜单移动到指定菜单后
	 * @param targetMenu 被移动的菜单
	 * @param menuIds 移动的菜单编号数组
	 */
	@Transactional
	public void moveAfterMenu(SysMenu targetMenu, List<Integer> menuIds) {
		List<SysMenu> menus = sysMenuMapper.selectByExample(
				ExampleBuilder.create(SysMenu.class).andEqualTo("parentId", targetMenu.getParentId())
				.andNotIn("menuId", menuIds)
				.orderByAsc("menuCode").build());
		int menuCode = targetMenu.getParentCode();
		int sort = 0;
		List<Integer> oldParentIds = new ArrayList<Integer>();
		for (SysMenu sysMenu : menus) {
			menuCode = generatorMenuId(menuCode, targetMenu.getLevel(), 1);
			sysMenu.setSort(++sort);
			sysMenu.setMenuCode(menuCode);
			sysMenuMapper.updateByPrimaryKey(sysMenu);
			renewChildMenu(sysMenu);
			if (sysMenu.getMenuId().equals(targetMenu.getMenuId())) {
				for (Integer menuId : menuIds) {
					menuCode = generatorMenuId(menuCode, targetMenu.getLevel(), 1);
					SysMenu menu = sysMenuMapper.selectByPrimaryKey(menuId);
					if (!menuId.equals(menu.getParentId())) {
						oldParentIds.add(menu.getParentId());
					}
					menu.setSort(++sort);
					menu.setMenuCode(menuCode);
					if (!menu.getLevel().equals(sysMenu.getLevel())) {
						menu.setParentCode(targetMenu.getParentCode());
					} else {
						menu.setParentCode(sysMenu.getParentCode());
					}
					menu.setLevel(sysMenu.getLevel());
					menu.setParentId(sysMenu.getParentId());
					sysMenuMapper.updateByPrimaryKey(menu);
					renewChildMenu(menu);
				}
			}
		}
		for (Integer oldParentId : oldParentIds) {
			correctChildMenu(oldParentId);
		}
	}

	/**
	 * 移动到某个父级菜单下的子菜单
	 * @param parentMenu 父级菜单
	 * @param menuIds 子菜单编号数组
	 */
	@Transactional
	public void renewMoveMenu(SysMenu parentMenu, List<Integer> menuIds) {
		List<SysMenu> menus = sysMenuMapper.selectByExample(
				ExampleBuilder.create(SysMenu.class).andEqualTo("parentId", parentMenu.getMenuId())
				.orderByDesc("menuCode").build());
		int menuCode = menus.size() > 0 ? menus.get(0).getMenuCode() : parentMenu.getMenuCode();
		int sort = menus.size();
		for (int menuId: menuIds) {
			if (parentMenu.getLevel() == 3) {
				throw new ApiException("不能创建3级及以上菜单");
			}
			menuCode = generatorMenuId(menuCode, parentMenu.getLevel()+1, 1);
			SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);
			sysMenu.setMenuCode(menuCode);
			sysMenu.setParentId(parentMenu.getMenuId());
			sysMenu.setLevel(parentMenu.getLevel()+1);
			sysMenu.setSort(++sort);
			sysMenu.setParentCode(parentMenu.getMenuCode());
			sysMenuMapper.updateByPrimaryKey(sysMenu);
			renewChildMenu(sysMenu);
		}
	}

	/**
	 * 遍历某个父级菜单，将所有的子菜单移动至新的父级菜单
	 * @param parentMenu 父级菜单
	 */
	@Transactional
	public void renewChildMenu(SysMenu parentMenu) {
		List<SysMenu> menus = sysMenuMapper.selectByExample(
				ExampleBuilder.create(SysMenu.class).andEqualTo("parentId", parentMenu.getMenuId())
				.orderByAsc("menuCode").build());
		int menuCode = parentMenu.getMenuCode();
		int sort = 0;
		for (SysMenu sysMenu : menus) {
			if (parentMenu.getLevel() == 3) {
				throw new ApiException("不能创建3级及以上菜单");
			}
			menuCode = generatorMenuId(menuCode, parentMenu.getLevel()+1, 1); 
			sysMenu.setMenuCode(menuCode);
			sysMenu.setLevel(parentMenu.getLevel()+1);
			sysMenu.setSort(++sort);
			sysMenu.setParentCode(parentMenu.getMenuCode());
			sysMenuMapper.updateByPrimaryKey(sysMenu);
			renewChildMenu(sysMenu);
		}
	}

	/**
	 * 纠正某个菜单下的所有菜单
	 * @param parentId 父级菜单编号，0为根菜单
	 */
	@Transactional
	public void correctChildMenu(Integer parentId) {
		if (parentId == 0) {
			List<SysMenu> menus = sysMenuMapper.selectByExample(
					ExampleBuilder.create(SysMenu.class).andEqualTo("level", 1)
					.orderByAsc("menuCode").build());
			int menuCode = parentId;
			int sort = 0;
			for (SysMenu sysMenu : menus) {
				menuCode = generatorMenuId(menuCode, 1, 1); 
				sysMenu.setMenuCode(menuCode);
				sysMenu.setLevel(1);
				sysMenu.setSort(++sort);
				sysMenu.setParentCode(0);
				sysMenuMapper.updateByPrimaryKey(sysMenu);
				correctChildMenu(sysMenu.getMenuId());
			}
		} else {
			SysMenu parentMenu = sysMenuMapper.selectByPrimaryKey(parentId);
			List<SysMenu> menus = sysMenuMapper.selectByExample(
					ExampleBuilder.create(SysMenu.class).andEqualTo("parentId", parentId)
					.orderByAsc("menuCode").build());
			if (menus.size() > 0) {
				int menuCode = parentMenu.getMenuCode();
				int sort = 0;
				for (SysMenu sysMenu : menus) {
					if (parentMenu.getLevel() == 3) {
						throw new ApiException("不能创建3级及以上菜单");
					}
					menuCode = generatorMenuId(menuCode, parentMenu.getLevel()+1, 1); 
					sysMenu.setMenuCode(menuCode);
					sysMenu.setLevel(parentMenu.getLevel()+1);
					sysMenu.setSort(++sort);
					sysMenu.setParentCode(parentMenu.getMenuCode());
					sysMenuMapper.updateByPrimaryKey(sysMenu);
					correctChildMenu(sysMenu.getMenuId());
				}
			}
		}
	}
}
