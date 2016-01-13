package tk.entities.active;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxUserScore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_user_score")
public class WxUserScore implements java.io.Serializable {

	// Fields

	private String scoreId;
	private String userId;
	private String userName;
	private String departmentId;
	private String departmentName;
	private Integer score;
	private String isValid;
	
	// Constructors

	/** default constructor */
	public WxUserScore() {
	}

	/** minimal constructor */
	public WxUserScore(String scoreId, String userId, String userName,
			String departmentId, String departmentName) {
		this.scoreId = scoreId;
		this.userId = userId;
		this.userName = userName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	/** full constructor */
	public WxUserScore(String scoreId, String userId, String userName,
			String departmentId, String departmentName, Integer score) {
		this.scoreId = scoreId;
		this.userId = userId;
		this.userName = userName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.score = score;
	}

	// Property accessors
	@Id
	@Column(name = "SCORE_ID", unique = true, nullable = false, length = 32)
	public String getScoreId() {
		return this.scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}

	@Column(name = "USER_ID", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "DEPARTMENT_ID", nullable = false, length = 10)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "DEPARTMENT_NAME", nullable = false, length = 100)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "SCORE")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "IS_VALID")
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

}