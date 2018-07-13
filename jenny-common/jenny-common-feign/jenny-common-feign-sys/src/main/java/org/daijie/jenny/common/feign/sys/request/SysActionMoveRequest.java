package org.daijie.jenny.common.feign.sys.request;

import java.util.ArrayList;
import java.util.List;

import org.daijie.jenny.common.feign.sys.enumtype.MoveType;

import io.swagger.annotations.ApiModelProperty;

public class SysActionMoveRequest {

	@ApiModelProperty(value = "菜单编号数组", required = true)
	private List<Integer> menuIds = new ArrayList<Integer>();
	
	@ApiModelProperty(value = "目标菜单编号", required = true)
	private Integer targetId;
	
	@ApiModelProperty(value = "移动类型", required = true)
	private MoveType moveType;

	public List<Integer> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}
}
