package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysRoleAuthorizedSetRequest implements Serializable {

    /**
     * 角色编号
     */
	@ApiModelProperty(value = "角色编号", required = true)
    private Integer roleId;

	@ApiModelProperty(value = "操作编号集合", required = true)
	private List<Integer> operateIds = new ArrayList<Integer>();

	@ApiModelProperty(value = "操作授权集合", required = true)
	private List<SysOperateAuthorizedAddRequest> sysOperateAuthorizedRequests = new ArrayList<SysOperateAuthorizedAddRequest>();

	@ApiModelProperty(value = "菜单编号集合", required = true)
	private List<Integer> menuIds = new ArrayList<Integer>();
	
	@ApiModelProperty(value = "菜单授权集合", required = true)
	private List<SysMenuAuthorizedAddRequest> sysMenuAuthorizedRequests = new ArrayList<SysMenuAuthorizedAddRequest>();

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<SysOperateAuthorizedAddRequest> getSysOperateAuthorizedRequests() {
		return sysOperateAuthorizedRequests;
	}

	public void setSysOperateAuthorizedRequests(List<SysOperateAuthorizedAddRequest> sysOperateAuthorizedRequests) {
		this.sysOperateAuthorizedRequests = sysOperateAuthorizedRequests;
	}

	public List<SysMenuAuthorizedAddRequest> getSysMenuAuthorizedRequests() {
		return sysMenuAuthorizedRequests;
	}

	public void setSysMenuAuthorizedRequests(List<SysMenuAuthorizedAddRequest> sysMenuAuthorizedRequests) {
		this.sysMenuAuthorizedRequests = sysMenuAuthorizedRequests;
	}

	public List<Integer> getOperateIds() {
		return operateIds;
	}

	public void setOperateIds(List<Integer> operateIds) {
		this.operateIds = operateIds;
	}

	public List<Integer> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}

}
