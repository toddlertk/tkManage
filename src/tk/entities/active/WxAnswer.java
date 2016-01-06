package tk.entities.active;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxAnswer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_answer")
public class WxAnswer implements java.io.Serializable {

	// Fields
	private String pkId;
	private String answerId;
	private String userId;
	private String departmentId;
	private String answer;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public WxAnswer() {
		this.pkId = UUID.randomUUID().toString().replace("-", "");
	}

	/** full constructor */
	public WxAnswer(String answerId, String userId, String departmentId,
			String answer, Timestamp createTime) {
		this.answerId = answerId;
		this.userId = userId;
		this.departmentId = departmentId;
		this.answer = answer;
		this.createTime = createTime;
	}

	@Id
	@Column(name = "pk_id", unique = true, nullable = false, length = 32)
	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	// Property accessors
	@Column(name = "answer_id",nullable = false)
	public String getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	@Column(name = "USER_ID", nullable = false, length = 20)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "DEPARTMENT_ID", nullable = false, length = 20)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "ANSWER", nullable = false, length = 100)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "CREATE_TIME", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}