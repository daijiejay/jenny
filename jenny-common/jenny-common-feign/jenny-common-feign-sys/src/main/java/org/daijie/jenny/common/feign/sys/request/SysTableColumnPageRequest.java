package org.daijie.jenny.common.feign.sys.request;

import org.daijie.core.result.Page;
import org.daijie.jdbc.mybatis.example.ExampleConditions;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysTableColumnPageRequest extends Page implements ExampleConditions {

	/**
     * 行编号
     */
	@ApiModelProperty(value = "表格工具栏目录元素")
    private Integer columnId;

    /**
     * 表格编号
     */
	@ApiModelProperty(value = "表格工具栏目录元素")
    private Integer tableId;

    /**
     * 列字段名
     */
	@ApiModelProperty(value = "表格工具栏目录元素")
    private String field;

    /**
     * 列字段标题
     */
	@ApiModelProperty(value = "表格工具栏目录元素")
    private String title;

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

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
