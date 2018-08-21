package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_server")
public class SysServer implements Serializable {
    /**
     * 服务名
     */
    @Id
    @Column(name = "server_id")
    private String serverId;

    /**
     * 服务地址
     */
    @Column(name = "server_addr")
    private String serverAddr;

    private static final long serialVersionUID = 1L;

    /**
     * 获取服务名
     *
     * @return server_id - 服务名
     */
    public String getServerId() {
        return serverId;
    }

    public SysServer withServerId(String serverId) {
        this.setServerId(serverId);
        return this;
    }

    /**
     * 设置服务名
     *
     * @param serverId 服务名
     */
    public void setServerId(String serverId) {
        this.serverId = serverId == null ? null : serverId.trim();
    }

    /**
     * 获取服务地址
     *
     * @return server_addr - 服务地址
     */
    public String getServerAddr() {
        return serverAddr;
    }

    public SysServer withServerAddr(String serverAddr) {
        this.setServerAddr(serverAddr);
        return this;
    }

    /**
     * 设置服务地址
     *
     * @param serverAddr 服务地址
     */
    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr == null ? null : serverAddr.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serverId=").append(serverId);
        sb.append(", serverAddr=").append(serverAddr);
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
        SysServer other = (SysServer) that;
        return (this.getServerId() == null ? other.getServerId() == null : this.getServerId().equals(other.getServerId()))
            && (this.getServerAddr() == null ? other.getServerAddr() == null : this.getServerAddr().equals(other.getServerAddr()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getServerId() == null) ? 0 : getServerId().hashCode());
        result = prime * result + ((getServerAddr() == null) ? 0 : getServerAddr().hashCode());
        return result;
    }
}