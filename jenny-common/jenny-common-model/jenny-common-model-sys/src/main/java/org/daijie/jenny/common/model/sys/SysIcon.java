package org.daijie.jenny.common.model.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_icon")
public class SysIcon implements Serializable {

	/**
	 * 图标编号	
	 */
	@Id
	@Column(name = "icon_id")
	public Integer iconId;

	/**
	 * 图标代码	
	 */
	@Column(name = "icon_code")
	public String iconCode;

	/**
	 * 图标名称	
	 */
	@Column(name = "icon_name")
	public String iconName;

	/**
	 * 设置图标代码	
	 * @param iconCode 图标代码	
	 */
	public void setIconCode(String iconCode) {	
		this.iconCode = iconCode;	
	}

	/**
	 * 获取图标代码	
	 * @return icon_code 图标代码	
	 */
	public String getIconCode() {	
		return this.iconCode;	
	}

	/**
	 * 设置图标名称	
	 * @param iconName 图标名称	
	 */
	public void setIconName(String iconName) {	
		this.iconName = iconName;	
	}

	/**
	 * 获取图标名称	
	 * @return icon_name 图标名称	
	 */
	public String getIconName() {	
		return this.iconName;	
	}

	/**
	 * 设置图标编号	
	 * @param iconId 图标编号	
	 */
	public void setIconId(Integer iconId) {	
		this.iconId = iconId;	
	}

	/**
	 * 获取图标编号	
	 * @return icon_id 图标编号	
	 */
	public Integer getIconId() {	
		return this.iconId;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.iconId == null) ? 0 : this.iconId.hashCode());
		result = prime * result + ((this.iconCode == null) ? 0 : this.iconCode.hashCode());
		result = prime * result + ((this.iconName == null) ? 0 : this.iconName.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", iconId=").append(this.iconId);
		sb.append(", iconCode=").append(this.iconCode);
		sb.append(", iconName=").append(this.iconName);
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

}