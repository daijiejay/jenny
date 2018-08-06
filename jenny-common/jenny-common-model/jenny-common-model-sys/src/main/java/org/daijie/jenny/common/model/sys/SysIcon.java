package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_icon")
public class SysIcon implements Serializable {
    /**
     * 图标编号
     */
    @Id
    @Column(name = "icon_id")
    private Integer iconId;

    /**
     * 图标代码
     */
    @Column(name = "icon_code")
    private String iconCode;

    /**
     * 图标名称
     */
    @Column(name = "icon_name")
    private String iconName;

    private static final long serialVersionUID = 1L;

    /**
     * 获取图标编号
     *
     * @return icon_id - 图标编号
     */
    public Integer getIconId() {
        return iconId;
    }

    public SysIcon withIconId(Integer iconId) {
        this.setIconId(iconId);
        return this;
    }

    /**
     * 设置图标编号
     *
     * @param iconId 图标编号
     */
    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    /**
     * 图标代码
     * 
     * @return icon_code 图标代码
     */
    public String getIconCode() {
        return iconCode;
    }

    public SysIcon withIconCode(String iconCode) {
        this.setIconCode(iconCode);
        return this;
    }

    /**
     * 图标代码
     * 
     * @param iconCode 图标代码
     */
    public void setIconCode(String iconCode) {
        this.iconCode = iconCode == null ? null : iconCode.trim();
    }

    /**
     * 获取图标名称
     *
     * @return icon_name - 图标名称
     */
    public String getIconName() {
        return iconName;
    }

    public SysIcon withIconName(String iconName) {
        this.setIconName(iconName);
        return this;
    }

    /**
     * 设置图标名称
     *
     * @param iconName 图标名称
     */
    public void setIconName(String iconName) {
        this.iconName = iconName == null ? null : iconName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", iconId=").append(iconId);
        sb.append(", iconCode=").append(iconCode);
        sb.append(", iconName=").append(iconName);
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
        SysIcon other = (SysIcon) that;
        return (this.getIconId() == null ? other.getIconId() == null : this.getIconId().equals(other.getIconId()))
            && (this.getIconCode() == null ? other.getIconCode() == null : this.getIconCode().equals(other.getIconCode()))
            && (this.getIconName() == null ? other.getIconName() == null : this.getIconName().equals(other.getIconName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIconId() == null) ? 0 : getIconId().hashCode());
        result = prime * result + ((getIconCode() == null) ? 0 : getIconCode().hashCode());
        result = prime * result + ((getIconName() == null) ? 0 : getIconName().hashCode());
        return result;
    }
}