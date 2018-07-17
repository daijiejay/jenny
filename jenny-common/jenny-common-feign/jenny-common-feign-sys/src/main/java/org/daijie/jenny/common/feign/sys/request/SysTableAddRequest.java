package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysTableAddRequest implements Serializable {

	 /**
     * 菜单编号
     */
	@ApiModelProperty(value = "菜单编号", required = true)
    private Integer menuId;

    /**
     * 表格名称
     */
	@ApiModelProperty(value = "表格名称", required = true)
    private String tableName;

    /**
     * 接口服务名
     */
	@ApiModelProperty(value = "接口服务名", required = true)
    private String interfaceServerId;

    /**
     * 接口地址
     */
	@ApiModelProperty(value = "接口地址", required = true)
    private String interfaceUrl;

    /**
     * 接口方式
     */
	@ApiModelProperty(value = "接口方式", required = true)
    private String interfaceMethod;

    /**
     * 表格目标元素
     */
	@ApiModelProperty(value = "表格目标元素", required = true)
    private String tableTarget;

    /**
     * 表格行主键编号名
     */
	@ApiModelProperty(value = "表格行主键编号名", required = true)
    private String uniqueId;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getInterfaceServerId() {
		return interfaceServerId;
	}

	public void setInterfaceServerId(String interfaceServerId) {
		this.interfaceServerId = interfaceServerId;
	}

	public String getInterfaceUrl() {
		return interfaceUrl;
	}

	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}

	public String getInterfaceMethod() {
		return interfaceMethod;
	}

	public void setInterfaceMethod(String interfaceMethod) {
		this.interfaceMethod = interfaceMethod;
	}

	public String getTableTarget() {
		return tableTarget;
	}

	public void setTableTarget(String tableTarget) {
		this.tableTarget = tableTarget;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
}
