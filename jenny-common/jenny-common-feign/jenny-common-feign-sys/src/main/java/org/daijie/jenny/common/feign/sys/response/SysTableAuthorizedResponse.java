package org.daijie.jenny.common.feign.sys.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SysTableAuthorizedResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "列表", required = true)
	private List<SysTableResponse> tables = new ArrayList<SysTableResponse>();

	public List<SysTableResponse> getTables() {
		return tables;
	}

	public void setTables(List<SysTableResponse> tables) {
		this.tables = tables;
	}
}
