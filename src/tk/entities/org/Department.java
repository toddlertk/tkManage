package tk.entities.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SmDepartment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_department")
public class Department implements java.io.Serializable {

	// Fields

	private String departmentId;
	private String departmentName;

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** full constructor */
	public Department(String departmentId, String departmentName) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	// Property accessors
	@Id
	@Column(name = "DEPARTMENT_ID", unique = true, nullable = false, length = 20)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "DEPARTMENT_NAME", nullable = false, length = 50)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}