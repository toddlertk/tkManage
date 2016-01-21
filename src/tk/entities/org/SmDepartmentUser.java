package tk.entities.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxDepartmentUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_department_user")
public class SmDepartmentUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String departmentId;

	// Constructors

	/** default constructor */
	public SmDepartmentUser() {
	}

	// Property accessors
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false)

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "DEPARTMENT_ID", nullable = false)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}