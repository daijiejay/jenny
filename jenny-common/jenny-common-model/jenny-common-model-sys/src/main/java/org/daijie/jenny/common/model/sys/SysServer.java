package org.daijie.jenny.common.model.sys;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author 86189
 * @date 2020年02月19日
 */
@Table(name = "sys_server")
public class SysServer implements Serializable {

	/**
	 * 服务名	
	 */
	@Id
	@Column(name = "server_id")
	public String serverId;

	/**
	 * 服务地址	
	 */
	@Column(name = "server_addr")
	public String serverAddr;

	/**
	 * 设置服务名	
	 * @param serverId 服务名	
	 */
	public void setServerId(String serverId) {	
		this.serverId = serverId;	
	}

	/**
	 * 获取服务名	
	 * @return server_id 服务名	
	 */
	public String getServerId() {	
		return this.serverId;	
	}

	/**
	 * 设置服务地址	
	 * @param serverAddr 服务地址	
	 */
	public void setServerAddr(String serverAddr) {	
		this.serverAddr = serverAddr;	
	}

	/**
	 * 获取服务地址	
	 * @return server_addr 服务地址	
	 */
	public String getServerAddr() {	
		return this.serverAddr;	
	}

	@Override
	public int hashCode() {	
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.serverId == null) ? 0 : this.serverId.hashCode());
		result = prime * result + ((this.serverAddr == null) ? 0 : this.serverAddr.hashCode());
		return result;
	}

	@Override
	public String toString() {	
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", serverId=").append(this.serverId);
		sb.append(", serverAddr=").append(this.serverAddr);
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
		SysServer other = (SysServer) that;
		return (this.getServerId() == null ? other.getServerId() == null : this.getServerId().equals(other.getServerId()))
			&& (this.getServerAddr() == null ? other.getServerAddr() == null : this.getServerAddr().equals(other.getServerAddr()));
	}

}