package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysMenuTreeResponse implements Serializable {

	@ApiModelProperty(value = "菜单编号", required = true)
	private int id;

	@ApiModelProperty(value = "父级菜单编号", required = true)
	private int pId;

	@ApiModelProperty(value = "菜单名称", required = true)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPId() {
		return pId;
	}

	public void setPId(Integer pId) {
		this.pId = pId == null ? 0 : pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
