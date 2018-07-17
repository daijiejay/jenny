package org.daijie.jenny.common.feign.sys.request;

import org.daijie.core.result.Page;
import org.daijie.jdbc.mybatis.example.ExampleConditions;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysTablePageRequest extends Page implements ExampleConditions {

	/**
     * 表格编号
     */
	@ApiModelProperty(value = "表格编号")
	private Integer tableId;

    /**
     * 菜单编号
     */
	@ApiModelProperty(value = "菜单编号")
    private Integer menuId;

    /**
     * 表格名称
     */
	@ApiModelProperty(value = "表格名称")
    private String tableName;

    /**
     * 接口服务名
     */
	@ApiModelProperty(value = "接口服务名")
    private String interfaceServerId;

    /**
     * 接口地址
     */
	@ApiModelProperty(value = "接口地址")
    private String interfaceUrl;

    /**
     * 接口方式
     */
	@ApiModelProperty(value = "接口方式")
    private String interfaceMethod;

    /**
     * 表格目标元素
     */
	@ApiModelProperty(value = "表格目标元素")
    private String tableTarget;

    /**
     * 表格行主键编号名
     */
	@ApiModelProperty(value = "表格行主键编号名")
    private String uniqueId;

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

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
