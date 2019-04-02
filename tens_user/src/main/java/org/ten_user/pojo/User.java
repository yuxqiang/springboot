
package org.ten_user.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户(tb_user)
 * 
 * @author bianj
 * @version 1.0.0 2019-04-02
 */
@Entity
@Table(name = "tb_user")
public class User implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = -4715554155851708045L;

	/** ID */
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 20)
	private String id;

	/** 手机号码 */
	@Column(name = "mobile", nullable = true, length = 100)
	private String mobile;

	/** 密码 */
	@Column(name = "password", nullable = true, length = 100)
	private String password;

	/** 昵称 */
	@Column(name = "nickname", nullable = true, length = 100)
	private String nickname;

	/** 性别 */
	@Column(name = "sex", nullable = true, length = 2)
	private String sex;

	/** 出生年月日 */
	@Column(name = "birthday", nullable = true)
	private Date birthday;

	/** 头像 */
	@Column(name = "avatar", nullable = true, length = 100)
	private String avatar;

	/** E-Mail */
	@Column(name = "email", nullable = true, length = 100)
	private String email;

	/** 注册日期 */
	@Column(name = "regdate", nullable = true)
	private Date regdate;

	/** 修改日期 */
	@Column(name = "updatedate", nullable = true)
	private Date updatedate;

	/** 最后登陆日期 */
	@Column(name = "lastdate", nullable = true)
	private Date lastdate;

	/** 在线时长（分钟） */
	@Column(name = "online", nullable = true, length = 19)
	private Long online;

	/** 兴趣 */
	@Column(name = "interest", nullable = true, length = 100)
	private String interest;

	/** 个性 */
	@Column(name = "personality", nullable = true, length = 100)
	private String personality;

	/** 粉丝数 */
	@Column(name = "fanscount", nullable = true, length = 10)
	private Integer fanscount;

	/** 关注数 */
	@Column(name = "followcount", nullable = true, length = 10)
	private Integer followcount;

	/**
	 * 获取ID
	 * 
	 * @return ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取手机号码
	 * 
	 * @return 手机号码
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 设置手机号码
	 * 
	 * @param mobile 手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取密码
	 * 
	 * @return 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置密码
	 * 
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取昵称
	 * 
	 * @return 昵称
	 */
	public String getNickname() {
		return this.nickname;
	}

	/**
	 * 设置昵称
	 * 
	 * @param nickname 昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取性别
	 * 
	 * @return 性别
	 */
	public String getSex() {
		return this.sex;
	}

	/**
	 * 设置性别
	 * 
	 * @param sex 性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取出生年月日
	 * 
	 * @return 出生年月日
	 */
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * 设置出生年月日
	 * 
	 * @param birthday 出生年月日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 获取头像
	 * 
	 * @return 头像
	 */
	public String getAvatar() {
		return this.avatar;
	}

	/**
	 * 设置头像
	 * 
	 * @param avatar 头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 获取E-Mail
	 * 
	 * @return E-Mail
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 设置E-Mail
	 * 
	 * @param email E-Mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取注册日期
	 * 
	 * @return 注册日期
	 */
	public Date getRegdate() {
		return this.regdate;
	}

	/**
	 * 设置注册日期
	 * 
	 * @param regdate 注册日期
	 */
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	/**
	 * 获取修改日期
	 * 
	 * @return 修改日期
	 */
	public Date getUpdatedate() {
		return this.updatedate;
	}

	/**
	 * 设置修改日期
	 * 
	 * @param updatedate 修改日期
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	/**
	 * 获取最后登陆日期
	 * 
	 * @return 最后登陆日期
	 */
	public Date getLastdate() {
		return this.lastdate;
	}

	/**
	 * 设置最后登陆日期
	 * 
	 * @param lastdate 最后登陆日期
	 */
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	/**
	 * 获取在线时长（分钟）
	 * 
	 * @return 在线时长（分钟）
	 */
	public Long getOnline() {
		return this.online;
	}

	/**
	 * 设置在线时长（分钟）
	 * 
	 * @param online 在线时长（分钟）
	 */
	public void setOnline(Long online) {
		this.online = online;
	}

	/**
	 * 获取兴趣
	 * 
	 * @return 兴趣
	 */
	public String getInterest() {
		return this.interest;
	}

	/**
	 * 设置兴趣
	 * 
	 * @param interest 兴趣
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}

	/**
	 * 获取个性
	 * 
	 * @return 个性
	 */
	public String getPersonality() {
		return this.personality;
	}

	/**
	 * 设置个性
	 * 
	 * @param personality 个性
	 */
	public void setPersonality(String personality) {
		this.personality = personality;
	}

	/**
	 * 获取粉丝数
	 * 
	 * @return 粉丝数
	 */
	public Integer getFanscount() {
		return this.fanscount;
	}

	/**
	 * 设置粉丝数
	 * 
	 * @param fanscount 粉丝数
	 */
	public void setFanscount(Integer fanscount) {
		this.fanscount = fanscount;
	}

	/**
	 * 获取关注数
	 * 
	 * @return 关注数
	 */
	public Integer getFollowcount() {
		return this.followcount;
	}

	/**
	 * 设置关注数
	 * 
	 * @param followcount 关注数
	 */
	public void setFollowcount(Integer followcount) {
		this.followcount = followcount;
	}
}