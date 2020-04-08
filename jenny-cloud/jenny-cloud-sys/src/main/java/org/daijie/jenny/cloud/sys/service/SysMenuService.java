package org.daijie.jenny.cloud.sys.service;

import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.daijie.jdbc.scripting.Wrapper;
import org.daijie.jenny.common.feign.sys.SysMenuFeign;
import org.daijie.jenny.common.feign.sys.enumtype.MoveType;
import org.daijie.jenny.common.feign.sys.request.SysMenuAddRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuMoveRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuPageRequest;
import org.daijie.jenny.common.feign.sys.request.SysMenuUpdateRequest;
import org.daijie.jenny.common.feign.sys.response.SysMenuResponse;
import org.daijie.jenny.common.feign.sys.response.SysMenuTreeResponse;
import org.daijie.jenny.common.mapper.sys.SysMenuMapper;
import org.daijie.jenny.common.model.sys.SysMenu;
import org.daijie.swagger.exception.ApiException;
import org.daijie.swagger.result.ModelResult;
import org.daijie.swagger.result.PageResult;
import org.daijie.swagger.result.Result;
import org.daijie.swagger.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SysMenuService implements SysMenuFeign {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public ModelResult<PageResult<SysMenuResponse>> getMenuAll(SysMenuPageRequest sysMenuPageRequest) {
		Wrapper conditions = Wrapper.newWrapper()
				.and(StringUtils.isNotEmpty(sysMenuPageRequest.getMenuName()), wrapper -> wrapper.andEqualTo("menuName", sysMenuPageRequest.getMenuName()))
				.and(sysMenuPageRequest.getMenuId() != null, wrapper -> wrapper.andEqualTo("menuId", sysMenuPageRequest.getMenuId()))
				.page(sysMenuPageRequest.getPageNumber(), sysMenuPageRequest.getPageSize());
		org.daijie.jdbc.result.PageResult<SysMenu> page = this.sysMenuMapper.selectPageByWrapper(conditions);
		return Result.build(new PageResult<SysMenuResponse>(page.getRows(), page.getTotal(), SysMenuResponse.class));
	}

	@Override
	public ModelResult<List<SysMenuTreeResponse>> getMenuTree() {
		List<SysMenu> menus = this.sysMenuMapper.selectByWrapper(Wrapper.newWrapper().orderByAsc("menuCode"));
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
	public ModelResult<SysMenuResponse> getMenu(Integer menuId) {
		SysMenu sysMenu = this.sysMenuMapper.selectById(menuId);
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
			List<SysMenu> menus = sysMenuMapper.selectByWrapper(
					Wrapper.newWrapper().andEqualTo("level", 1).orderByDesc("menuId"));
			if (menus.size() > 0) {
				sysMenu.setMenuCode(generatorMenuId(menus.get(0).getMenuId(), 1, null));
			} else {
				sysMenu.setMenuCode(10000);
			}
			sysMenu.setParentId(0);
			sysMenu.setLevel(1);
			sysMenu.setSort(menus.size()+1);
		} else {
			SysMenu parentMenu = this.sysMenuMapper.selectById(sysMenu.getParentId());
			if (parentMenu.getLevel() >= 3) {
				throw new ApiException(ResultCode.CODE_100, "不能创建3级及以上菜单");
			}
			List<SysMenu> menus = this.sysMenuMapper.selectByWrapper(
					Wrapper.newWrapper().andEqualTo("parentId", sysMenu.getParentId()).orderByDesc("menuId"));
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
		sysMenuMapper.updateSelectiveByWrapper(sysMenu, Wrapper.newWrapper().andEqualTo("menuId", sysMenuRequest.getMenuId()));
		SysMenuResponse sysMenuResponse = new SysMenuResponse();
		BeanUtil.copyProperties(sysMenu, sysMenuResponse);
		return Result.build(sysMenuResponse);
	}

	@Override
	@Transactional
	public ModelResult<SysMenuResponse> deleteMenu(@PathVariable(name = "menuId") Integer menuId) {
		List<SysMenu> list = sysMenuMapper.selectByWrapper(Wrapper.newWrapper().andEqualTo("menuId", menuId));
		if (list.size() == 1) {
			sysMenuMapper.deleteById(list.get(0).getMenuId());
			deleteChildMenu(menuId);
			forwardMoveMenu(list.get(0).getParentId(), list.get(0).getSort(), 1);
			SysMenuResponse sysMenuResponse = new SysMenuResponse();
			BeanUtil.copyProperties(list.get(0), sysMenuResponse);
			return Result.build(sysMenuResponse);
		}
		throw new ApiException(ResultCode.CODE_102, "无效的菜单编号！");
	}
	
	public void deleteChildMenu(Integer parentId) {
		List<SysMenu> list = sysMenuMapper.selectByWrapper(Wrapper.newWrapper().andEqualTo("parentId", parentId));
		if (list.size() > 0) {
			for (SysMenu sysMenu : list) {
				deleteChildMenu(sysMenu.getMenuId());
			}
			sysMenuMapper.deleteByWrapper(Wrapper.newWrapper().andEqualTo("parentId", parentId));
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
		List<SysMenu> menus = sysMenuMapper.selectByWrapper(
				Wrapper.newWrapper().andEqualTo("parentId", parentId)
				.andGreaterThanOrEqualTo("sort", sortNum));
		for (SysMenu sysMenu : menus) {
			sysMenu.setSort(sysMenu.getSort()-1);
			sysMenu.setMenuCode(generatorMenuId(sysMenu.getMenuCode(), sysMenu.getLevel(), num*-1));
			sysMenuMapper.updateById(sysMenu);
			renewChildMenu(sysMenu);
		}
	}
	
	public void backwardMoveMenu(int num) {
		
	}

	@Override
	@Transactional
	public ModelResult<Boolean> moveMenu(SysMenuMoveRequest sysMenuRequest) {
		SysMenu menu = this.sysMenuMapper.selectById(sysMenuRequest.getTargetId());
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
		List<SysMenu> menus = this.sysMenuMapper.selectByWrapper(
				Wrapper.newWrapper().andEqualTo("parentId", targetMenu.getParentId())
				.andNotIn("menuId", menuIds)
				.orderByAsc("menuCode"));
		int menuCode = targetMenu.getParentCode();
		int sort = 0;
		List<Integer> oldParentIds = new ArrayList<Integer>();
		for (SysMenu sysMenu : menus) {
			if (sysMenu.getMenuId().equals(targetMenu.getMenuId())) {
				for (Integer menuId : menuIds) {
					menuCode = generatorMenuId(menuCode, targetMenu.getLevel(), 1);
					SysMenu menu = this.sysMenuMapper.selectById(menuId);
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
					this.sysMenuMapper.updateById(menu);
					renewChildMenu(menu);
				}
			}
			menuCode = generatorMenuId(menuCode, targetMenu.getLevel(), 1);
			sysMenu.setSort(++sort);
			sysMenu.setMenuCode(menuCode);
			this.sysMenuMapper.updateById(sysMenu);
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
		List<SysMenu> menus = this.sysMenuMapper.selectByWrapper(
				Wrapper.newWrapper().andEqualTo("parentId", targetMenu.getParentId())
				.andNotIn("menuId", menuIds)
				.orderByAsc("menuCode"));
		int menuCode = targetMenu.getParentCode();
		int sort = 0;
		List<Integer> oldParentIds = new ArrayList<Integer>();
		for (SysMenu sysMenu : menus) {
			menuCode = generatorMenuId(menuCode, targetMenu.getLevel(), 1);
			sysMenu.setSort(++sort);
			sysMenu.setMenuCode(menuCode);
			this.sysMenuMapper.updateById(sysMenu);
			renewChildMenu(sysMenu);
			if (sysMenu.getMenuId().equals(targetMenu.getMenuId())) {
				for (Integer menuId : menuIds) {
					menuCode = generatorMenuId(menuCode, targetMenu.getLevel(), 1);
					SysMenu menu = this.sysMenuMapper.selectById(menuId);
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
					this.sysMenuMapper.updateById(menu);
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
		List<SysMenu> menus = sysMenuMapper.selectByWrapper(
				Wrapper.newWrapper().andEqualTo("parentId", parentMenu.getMenuId())
				.orderByDesc("menuCode"));
		int menuCode = menus.size() > 0 ? menus.get(0).getMenuCode() : parentMenu.getMenuCode();
		int sort = menus.size();
		for (int menuId: menuIds) {
			if (parentMenu.getLevel() >= 3) {
				throw new ApiException("不能创建3级及以上菜单");
			}
			menuCode = generatorMenuId(menuCode, parentMenu.getLevel()+1, 1);
			SysMenu sysMenu = this.sysMenuMapper.selectById(menuId);
			sysMenu.setMenuCode(menuCode);
			sysMenu.setParentId(parentMenu.getMenuId());
			sysMenu.setLevel(parentMenu.getLevel()+1);
			sysMenu.setSort(++sort);
			sysMenu.setParentCode(parentMenu.getMenuCode());
			this.sysMenuMapper.updateById(sysMenu);
			renewChildMenu(sysMenu);
		}
	}

	/**
	 * 遍历某个父级菜单，将所有的子菜单移动至新的父级菜单
	 * @param parentMenu 父级菜单
	 */
	@Transactional
	public void renewChildMenu(SysMenu parentMenu) {
		List<SysMenu> menus = this.sysMenuMapper.selectByWrapper(
				Wrapper.newWrapper().andEqualTo("parentId", parentMenu.getMenuId())
				.orderByAsc("menuCode"));
		int menuCode = parentMenu.getMenuCode();
		int sort = 0;
		for (SysMenu sysMenu : menus) {
			if (parentMenu.getLevel() >= 3) {
				throw new ApiException("不能创建3级及以上菜单");
			}
			menuCode = generatorMenuId(menuCode, parentMenu.getLevel()+1, 1); 
			sysMenu.setMenuCode(menuCode);
			sysMenu.setLevel(parentMenu.getLevel()+1);
			sysMenu.setSort(++sort);
			sysMenu.setParentCode(parentMenu.getMenuCode());
			this.sysMenuMapper.updateById(sysMenu);
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
			List<SysMenu> menus = sysMenuMapper.selectByWrapper(
					Wrapper.newWrapper().andEqualTo("level", 1)
					.orderByAsc("menuCode"));
			int menuCode = parentId;
			int sort = 0;
			for (SysMenu sysMenu : menus) {
				menuCode = generatorMenuId(menuCode, 1, 1); 
				sysMenu.setMenuCode(menuCode);
				sysMenu.setLevel(1);
				sysMenu.setSort(++sort);
				sysMenu.setParentCode(0);
				this.sysMenuMapper.updateById(sysMenu);
				correctChildMenu(sysMenu.getMenuId());
			}
		} else {
			SysMenu parentMenu = this.sysMenuMapper.selectById(parentId);
			List<SysMenu> menus = this.sysMenuMapper.selectByWrapper(
					Wrapper.newWrapper().andEqualTo("parentId", parentId)
					.orderByAsc("menuCode"));
			if (menus.size() > 0) {
				int menuCode = parentMenu.getMenuCode();
				int sort = 0;
				for (SysMenu sysMenu : menus) {
					if (parentMenu.getLevel() >= 3) {
						throw new ApiException("不能创建3级及以上菜单");
					}
					menuCode = generatorMenuId(menuCode, parentMenu.getLevel()+1, 1); 
					sysMenu.setMenuCode(menuCode);
					sysMenu.setLevel(parentMenu.getLevel()+1);
					sysMenu.setSort(++sort);
					sysMenu.setParentCode(parentMenu.getMenuCode());
					this.sysMenuMapper.updateById(sysMenu);
					correctChildMenu(sysMenu.getMenuId());
				}
			}
		}
	}
}
