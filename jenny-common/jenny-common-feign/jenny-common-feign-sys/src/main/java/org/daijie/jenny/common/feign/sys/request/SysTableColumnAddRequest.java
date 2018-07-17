package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysTableColumnAddRequest implements Serializable {

    /**
     * 表格编号
     */
	@ApiModelProperty(value = "表格工具栏目录元素", required = true)
    private Integer tableId;

    /**
     * 列字段名
     */
	@ApiModelProperty(value = "表格工具栏目录元素", required = true)
    private String field;

    /**
     * 列字段标题
     */
	@ApiModelProperty(value = "表格工具栏目录元素", required = true)
    private String title;

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
