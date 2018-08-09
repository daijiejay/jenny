package org.daijie.jenny.common.feign.sys.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class SysTableColumnUpdateRequest implements Serializable {

	/**
     * 行编号
     */
	@ApiModelProperty(value = "表格工具栏目录元素", required = true)
    private Integer columnId;

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

    /**
     * 是否显示排序按扭
     */
	@ApiModelProperty(value = "是否显示排序按扭", required = true)
    private Boolean sortable;

    /**
     * 展示排序
     */
	@ApiModelProperty(value = "展示排序")
    private Integer showSort;

    /**
     * 宽度
     */
	@ApiModelProperty(value = "宽度")
    private Double width;

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
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

	public Boolean getSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	public Integer getShowSort() {
		return showSort;
	}

	public void setShowSort(Integer showSort) {
		this.showSort = showSort;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

}
