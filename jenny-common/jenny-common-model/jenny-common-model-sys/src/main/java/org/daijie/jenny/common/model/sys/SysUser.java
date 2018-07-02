package org.daijie.jenny.common.model.sys;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser implements Serializable {
    /**
     * 用户编号
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 加密盐
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private Date birthday;

    /**
     * 性别
     */
    @Column(name = "gender")
    private byte[] gender;

    /**
     * 头像
     */
    @Column(name = "portrait")
    private String portrait;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否管理员用户
     */
    @Column(name = "admin")
    private Boolean admin;

    /**
     * 是否禁用
     */
    @Column(name = "enable")
    private Boolean enable;

    /**
     * 是否删除
     */
    @Column(name = "cancel")
    private Boolean cancel;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public Integer getUserId() {
        return userId;
    }

    public SysUser withUserId(Integer userId) {
        this.setUserId(userId);
        return this;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    public SysUser withUserName(String userName) {
        this.setUserName(userName);
        return this;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    public SysUser withMobile(String mobile) {
        this.setMobile(mobile);
        return this;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    public SysUser withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    public SysUser withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取加密盐
     *
     * @return salt - 加密盐
     */
    public String getSalt() {
        return salt;
    }

    public SysUser withSalt(String salt) {
        this.setSalt(salt);
        return this;
    }

    /**
     * 设置加密盐
     *
     * @param salt 加密盐
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    public SysUser withBirthday(Date birthday) {
        this.setBirthday(birthday);
        return this;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public byte[] getGender() {
        return gender;
    }

    public SysUser withGender(byte[] gender) {
        this.setGender(gender);
        return this;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(byte[] gender) {
        this.gender = gender;
    }

    /**
     * 获取头像
     *
     * @return portrait - 头像
     */
    public String getPortrait() {
        return portrait;
    }

    public SysUser withPortrait(String portrait) {
        this.setPortrait(portrait);
        return this;
    }

    /**
     * 设置头像
     *
     * @param portrait 头像
     */
    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    public SysUser withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否管理员用户
     *
     * @return admin - 是否管理员用户
     */
    public Boolean getAdmin() {
        return admin;
    }

    public SysUser withAdmin(Boolean admin) {
        this.setAdmin(admin);
        return this;
    }

    /**
     * 设置是否管理员用户
     *
     * @param admin 是否管理员用户
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * 获取是否禁用
     *
     * @return enable - 是否禁用
     */
    public Boolean getEnable() {
        return enable;
    }

    public SysUser withEnable(Boolean enable) {
        this.setEnable(enable);
        return this;
    }

    /**
     * 设置是否禁用
     *
     * @param enable 是否禁用
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * 获取是否删除
     *
     * @return cancel - 是否删除
     */
    public Boolean getCancel() {
        return cancel;
    }

    public SysUser withCancel(Boolean cancel) {
        this.setCancel(cancel);
        return this;
    }

    /**
     * 设置是否删除
     *
     * @param cancel 是否删除
     */
    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", birthday=").append(birthday);
        sb.append(", gender=").append(gender);
        sb.append(", portrait=").append(portrait);
        sb.append(", createTime=").append(createTime);
        sb.append(", admin=").append(admin);
        sb.append(", enable=").append(enable);
        sb.append(", cancel=").append(cancel);
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
        SysUser other = (SysUser) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (Arrays.equals(this.getGender(), other.getGender()))
            && (this.getPortrait() == null ? other.getPortrait() == null : this.getPortrait().equals(other.getPortrait()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getAdmin() == null ? other.getAdmin() == null : this.getAdmin().equals(other.getAdmin()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getCancel() == null ? other.getCancel() == null : this.getCancel().equals(other.getCancel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + (Arrays.hashCode(getGender()));
        result = prime * result + ((getPortrait() == null) ? 0 : getPortrait().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getAdmin() == null) ? 0 : getAdmin().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getCancel() == null) ? 0 : getCancel().hashCode());
        return result;
    }
}