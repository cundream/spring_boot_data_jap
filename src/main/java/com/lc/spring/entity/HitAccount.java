package com.lc.spring.entity;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "hit_account")
public class HitAccount {

    /**
     * ID
     */
    @Id
    private int id;

    /**
     * ip
     */
    @Column(name = "ip")
    private String ip;
    /**
     * Unix时间戳
     */
    @Column(name = "time_at")
    private int timeAt;

    /**
     * 账号
     */
    @Column(name="account")
    private String account;

    /**
     * 描述
     */
    @Column(name="descri")
    private String descri;

    /**
     * status
     */
    @Column(name="status")
    private String status;

    /**
     * userId
     */
    @Column(name="user_id")
    private Long userId;

    @Column(name="appkey")
    private String appkey;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getTimeAt() {
        return timeAt;
    }

    public void setTimeAt(int timeAt) {
        this.timeAt = timeAt;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "HitAccount{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", timeAt=" + timeAt +
                ", account='" + account + '\'' +
                ", descri='" + descri + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
