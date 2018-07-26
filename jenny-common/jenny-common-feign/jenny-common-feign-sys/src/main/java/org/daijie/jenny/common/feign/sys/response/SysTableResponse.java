package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SysTableResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
     * 表格编号
     */
	@ApiModelProperty(value = "表格编号", required = true)
    private Integer tableId;

    /**
     * 表格名称
     */
	@ApiModelProperty(value = "表格名称")
    private String tableName;

    /**
     * 菜单编号
     */
	@ApiModelProperty(value = "菜单编号", required = true)
    private Integer menuId;

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
	
	@ApiModelProperty(value = "表格列字段集合", required = true)
	private List<SysTableColumnResponse> columns = new ArrayList<SysTableColumnResponse>();

	@ApiModelProperty(value = "功能集合", required = true)
	private List<SysTableActionResponse> actions = new ArrayList<SysTableActionResponse>();

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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

	public List<SysTableColumnResponse> getColumns() {
		return columns;
	}

	public void setColumns(List<SysTableColumnResponse> columns) {
		this.columns = columns;
	}

	public List<SysTableActionResponse> getActions() {
		return actions;
	}

	public void setActions(List<SysTableActionResponse> actions) {
		this.actions = actions;
	}
}
