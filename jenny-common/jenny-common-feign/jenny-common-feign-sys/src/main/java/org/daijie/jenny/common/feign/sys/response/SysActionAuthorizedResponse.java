package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SysActionAuthorizedResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "列表", required = true)
	private SysTableResponse table;

	@ApiModelProperty(value = "功能", required = true)
	private List<SysActionResponse> actions = new ArrayList<SysActionResponse>();

	public SysTableResponse getTable() {
		return table;
	}

	public void setTable(SysTableResponse table) {
		this.table = table;
	}

	public List<SysActionResponse> getActions() {
		return actions;
	}

	public void setActions(List<SysActionResponse> actions) {
		this.actions = actions;
	}
}
