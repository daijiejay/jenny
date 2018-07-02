package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_table_column")
public class SysTableColumn implements Serializable {
    /**
     * 行编号
     */
    @Id
    @Column(name = "column_id")
    private Integer columnId;

    /**
     * 表格编号
     */
    @Column(name = "table_id")
    private Integer tableId;

    /**
     * 列字段名
     */
    @Column(name = "field")
    private String field;

    /**
     * 列字段标题
     */
    @Column(name = "title")
    private String title;

    private static final long serialVersionUID = 1L;

    /**
     * 获取行编号
     *
     * @return column_id - 行编号
     */
    public Integer getColumnId() {
        return columnId;
    }

    public SysTableColumn withColumnId(Integer columnId) {
        this.setColumnId(columnId);
        return this;
    }

    /**
     * 设置行编号
     *
     * @param columnId 行编号
     */
    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    /**
     * 获取表格编号
     *
     * @return table_id - 表格编号
     */
    public Integer getTableId() {
        return tableId;
    }

    public SysTableColumn withTableId(Integer tableId) {
        this.setTableId(tableId);
        return this;
    }

    /**
     * 设置表格编号
     *
     * @param tableId 表格编号
     */
    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    /**
     * 获取列字段名
     *
     * @return field - 列字段名
     */
    public String getField() {
        return field;
    }

    public SysTableColumn withField(String field) {
        this.setField(field);
        return this;
    }

    /**
     * 设置列字段名
     *
     * @param field 列字段名
     */
    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    /**
     * 获取列字段标题
     *
     * @return title - 列字段标题
     */
    public String getTitle() {
        return title;
    }

    public SysTableColumn withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    /**
     * 设置列字段标题
     *
     * @param title 列字段标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", columnId=").append(columnId);
        sb.append(", tableId=").append(tableId);
        sb.append(", field=").append(field);
        sb.append(", title=").append(title);
        sb.append(", serialVersionUID=").append(serialVersionUID);
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
            && (this.getTableId() == null ? other.getTableId() == null : this.getTableId().equals(other.getTableId()))
            && (this.getField() == null ? other.getField() == null : this.getField().equals(other.getField()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getColumnId() == null) ? 0 : getColumnId().hashCode());
        result = prime * result + ((getTableId() == null) ? 0 : getTableId().hashCode());
        result = prime * result + ((getField() == null) ? 0 : getField().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        return result;
    }
}