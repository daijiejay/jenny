package org.daijie.jenny.common.feign.sys.request;

import org.daijie.core.result.Page;
import org.daijie.jdbc.mybatis.example.ExampleConditions;
import org.daijie.jenny.common.feign.sys.enumtype.ActionType;
import org.daijie.jenny.common.feign.sys.enumtype.MutualType;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysTableActionPageRequest extends Page implements ExampleConditions {

	/**
     * 功能编号
     */
	@ApiModelProperty(value = "功能编号")
    private Integer actionId;

    /**
     * 表格编号
     */
	@ApiModelProperty(value = "表格编号", required = true)
    private Integer tableId;

    /**
     * 功能名称
     */
	@ApiModelProperty(value = "功能名称")
    private String actionName;

    /**
     * 功能类型：列表单行操作,列表工具栏
     */
	@ApiModelProperty(value = "功能类型")
    private ActionType actionType;

    /**
     * 交互类型：确认,表单
     */
	@ApiModelProperty(value = "交互类型")
    private MutualType mutualType;

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
     * 表单目标元素
     */
	@ApiModelProperty(value = "表单目标元素")
    private String formTarget;

    /**
     * 图标
     */
	@ApiModelProperty(value = "图标")
    private String icon;

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = ActionType.valueOf(actionType);
	}
	
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public MutualType getMutualType() {
		return mutualType;
	}

	public void setMutualType(String mutualType) {
		this.mutualType = MutualType.valueOf(mutualType);
	}

	public void setMutualType(MutualType mutualType) {
		this.mutualType = mutualType;
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

	public String getFormTarget() {
		return formTarget;
	}

	public void setFormTarget(String formTarget) {
		this.formTarget = formTarget;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
