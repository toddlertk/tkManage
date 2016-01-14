package tk.entities.active;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

/**
 * WxActiveScore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_active_score")
public class WxActiveScore implements java.io.Serializable {

	// Fields

	private String scoreId;
	private String openId;
	private Integer activeIndex;
	private String departmentId;
	private Integer score;
	private String scoreText;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public WxActiveScore() {
		this.scoreId = UUID.randomUUID().toString().replace("-", "");
	}

	/** full constructor */
	public WxActiveScore(String scoreId, String openId, String departmentId,
			Integer score, String scoreText) {
		this.scoreId = scoreId;
		this.openId = openId;
		this.departmentId = departmentId;
		this.score = score;
		this.scoreText = scoreText;
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

	@Column(name = "OPEN_ID", nullable = false, length = 128)
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "ACTIVE_INDEX")
	public Integer getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(Integer activeIndex) {
		this.activeIndex = activeIndex;
	}

	@Column(name = "DEPARTMENT_ID", nullable = false, length = 20)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "SCORE", nullable = false)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "SCORE_TEXT", nullable = false, length = 100)
	public String getScoreText() {
		return this.scoreText;
	}

	public void setScoreText(String scoreText) {
		this.scoreText = scoreText;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}