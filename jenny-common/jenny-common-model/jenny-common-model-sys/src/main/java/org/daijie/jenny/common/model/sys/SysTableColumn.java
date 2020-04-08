package org.daijie.jenny.common.model.sys;

import java.math.BigDecimal;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_table_column")
public class SysTableColumn implements Serializable {

	/**
	 * 行编号	
	 */
	@Id
	@Column(name = "column_id")
	public Integer columnId;

	/**
	 * 是否显示排序按扭	
	 */
	@Column(name = "sortable")
	public Boolean sortable;

	/**
	 * 列字段名	
	 */
	@Column(name = "field")
	public String field;

	/**
	 * 展示排序	
	 */
	@Column(name = "show_sort")
	public Integer showSort;

	/**
	 * 列字段标题	
	 */
	@Column(name = "title")
	public String title;

	/**
	 * 宽度	
	 */
	@Column(name = "width")
	public BigDecimal width;

	/**
	 * 表格编号	
	 */
	@Column(name = "table_id")
	public Integer tableId;

	/**
	 * 设置是否显示排序按扭	
	 * @param sortable 是否显示排序按扭	
	 */
	public void setSortable(Boolean sortable) {	
		this.sortable = sortable;	
	}

	/**
	 * 获取是否显示排序按扭	
	 * @return sortable 是否显示排序按扭	
	 */
	public Boolean getSortable() {	
		return this.sortable;	
	}

	/**
	 * 设置列字段名	
	 * @param field 列字段名	
	 */
	public void setField(String field) {	
		this.field = field;	
	}

	/**
	 * 获取列字段名	
	 * @return field 列字段名	
	 */
	public String getField() {	
		return this.field;	
	}

	/**
	 * 设置展示排序	
	 * @param showSort 展示排序	
	 */
	public void setShowSort(Integer showSort) {	
		this.showSort = showSort;	
	}

	/**
	 * 获取展示排序	
	 * @return show_sort 展示排序	
	 */
	public Integer getShowSort() {	
		return this.showSort;	
	}

	/**
	 * 设置行编号	
	 * @param columnId 行编号	
	 */
	public void setColumnId(Integer columnId) {	
		this.columnId = columnId;	
	}

	/**
	 * 获取行编号	
	 * @return column_id 行编号	
	 */
	public Integer getColumnId() {	
		return this.columnId;	
	}

	/**
	 * 设置列字段标题	
	 * @param title 列字段标题	
	 */
	public void setTitle(String title) {	
		this.title = title;	
	}

	/**
	 * 获取列字段标题	
	 * @return title 列字段标题	
	 */
	public String getTitle() {	
		return this.title;	
	}

	/**
	 * 设置宽度	
	 * @param width 宽度	
	 */
	public void setWidth(BigDecimal width) {	
		this.width = width;	
	}

	/**
	 * 获取宽度	
	 * @return width 宽度	
	 */
	public BigDecimal getWidth() {	
		return this.width;	
	}

	/**
	 * 设置表格编号	
	 * @param tableId 表格编号	
	 */
	public void setTableId(Integer tableId) {	
		this.tableId = tableId;	
	}

	/**
	 * 获取表格编号	
	 * @return table_id 表格编号	
	 */
	public Integer getTableId() {	
		return this.tableId;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.columnId == null) ? 0 : this.columnId.hashCode());
		result = prime * result + ((this.sortable == null) ? 0 : this.sortable.hashCode());
		result = prime * result + ((this.field == null) ? 0 : this.field.hashCode());
		result = prime * result + ((this.showSort == null) ? 0 : this.showSort.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		result = prime * result + ((this.width == null) ? 0 : this.width.hashCode());
		result = prime * result + ((this.tableId == null) ? 0 : this.tableId.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", columnId=").append(this.columnId);
		sb.append(", sortable=").append(this.sortable);
		sb.append(", field=").append(this.field);
		sb.append(", showSort=").append(this.showSort);
		sb.append(", title=").append(this.title);
		sb.append(", width=").append(this.width);
		sb.append(", tableId=").append(this.tableId);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object that) {	
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		SysTableColumn other = (SysTableColumn) that;
		return (this.getColumnId() == null ? other.getColumnId() == null : this.getColumnId().equals(other.getColumnId()))
			&& (this.getSortable() == null ? other.getSortable() == null : this.getSortable().equals(other.getSortable()))
			&& (this.getField() == null ? other.getField() == null : this.getField().equals(other.getField()))
			&& (this.getShowSort() == null ? other.getShowSort() == null : this.getShowSort().equals(other.getShowSort()))
			&& (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
			&& (this.getWidth() == null ? other.getWidth() == null : this.getWidth().equals(other.getWidth()))
			&& (this.getTableId() == null ? other.getTableId() == null : this.getTableId().equals(other.getTableId()));
	}

}