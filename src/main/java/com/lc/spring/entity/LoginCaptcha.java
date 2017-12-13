package com.lc.spring.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "login_captcha")
public class LoginCaptcha {
    /**
     * ID
     */
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 时间
     */
    @Column(name = "time")
    private Date time;

    /**
     * 结果码（是否验证成功)
     */
    @Column(name = "result_code")
    private String resultCode;

    /**
     * 类型（提交/生成)
     */
    @Column(name = "type")
    private int type;

    /**
     * appkey
     */
    @Column(name = "appkey")
    private String appkey;

    /**
     * ip
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 帐号
     */
    @Column(name = "account")
    private String account;

    /**
     * 是否需要验证码
     */
    @Column(name = "is_login_captcha")
    private int isLoginCaptcha;

    /**
     * 是否神策异常IP
     */
    @Column(name = "is_shence")
    private int isShence;

    /**
     * token(验证码的标识)
     */
    @Column(name = "token")
    private String token;

    /**
     * 登录方式
     */
    @Column(name = "login_method")
    private String loginMethod;

    /**
     * 用户提交验证码
     */
    @Column(name = "verification_code")
    private String verificationCode;

    /**
     * visitversion
     */
    @Column(name = "visitversion")
    private String visitversion;

    /**
     * client_type
     */
    @Column(name = "client_type")
    private String clientType;

    /**
     * 描述
     */
    @Column(name = "msg")
    private String msg;

    /**
     * 接口地址
     * @return
     */
    @Column(name = "interface_address")
    private String interfaceAddress;

	@Column(name = "is_black_app_id")
	private int isBlackAppId;

	@Column(name = "is_white_account")
	private int isWhiteAccount;

	@Column(name = "client_ip")
	private String clientIp;

	@Column(name = "user_agent")
	private String userAgent;

	@Column(name = "validate_type")
	private Integer validateType;

	@Column(name = "validate_result")
	private Integer validateResult;

	@Column(name = "is_intercept")
	private Integer isIntercept;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getIsLoginCaptcha() {
        return isLoginCaptcha;
    }

    public void setIsLoginCaptcha(int isLoginCaptcha) {
        this.isLoginCaptcha = isLoginCaptcha;
    }

    public int getIsShence() {
        return isShence;
    }

    public void setIsShence(int isShence) {
        this.isShence = isShence;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVisitversion() {
        return visitversion;
    }

    public void setVisitversion(String visitversion) {
        this.visitversion = visitversion;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getInterfaceAddress() {
        return interfaceAddress;
    }

    public void setInterfaceAddress(String interfaceAddress) {
        this.interfaceAddress = interfaceAddress;
    }

	public int getIsBlackAppId() {
		return isBlackAppId;
	}

	public void setIsBlackAppId(int isBlackAppId) {
		this.isBlackAppId = isBlackAppId;
	}

	public int getIsWhiteAccount() {
		return isWhiteAccount;
	}

	public void setIsWhiteAccount(int isWhiteAccount) {
		this.isWhiteAccount = isWhiteAccount;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Integer getValidateType() {
		return validateType;
	}

	public void setValidateType(Integer validateType) {
		this.validateType = validateType;
	}

	public Integer getValidateResult() {
		return validateResult;
	}

	public void setValidateResult(Integer validateResult) {
		this.validateResult = validateResult;
	}

	public Integer getIsIntercept() {
		return isIntercept;
	}

	public void setIsIntercept(Integer isIntercept) {
		this.isIntercept = isIntercept;
	}

	@Override
    public String toString() {
        return "LoginCaptcha{" +
                "id=" + id +
                ", time=" + time +
                ", resultCode='" + resultCode + '\'' +
                ", type='" + type + '\'' +
                ", appkey='" + appkey + '\'' +
                ", ip='" + ip + '\'' +
                ", account='" + account + '\'' +
                ", isLoginCaptcha='" + isLoginCaptcha + '\'' +
                ", isShence='" + isShence + '\'' +
                ", token='" + token + '\'' +
                ", loginMethod='" + loginMethod + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", visitversion='" + visitversion + '\'' +
                ", clientType='" + clientType + '\'' +
                ", msg='" + msg + '\'' +
                ", interfaceAddress='" + interfaceAddress + '\'' +
                '}';
    }
}
