package tk.entities.org;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxDepartmentUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_department_user")
public class DepartmentUser implements java.io.Serializable {

	// Fields

	private String duId;
	private String departmentId;
	private String openId;
	private String accountId;

	// Constructors

	/** default constructor */
	public DepartmentUser() {
		this.duId = UUID.randomUUID().toString().replace("-", "");
	}

	/** full constructor */
	public DepartmentUser(String duId, String departmentId, String openId,
			String accountId) {
		this.duId = duId;
		this.departmentId = departmentId;
		this.openId = openId;
		this.accountId = accountId;
	}

	// Property accessors
	@Id
	@Column(name = "DU_ID", unique = true, nullable = false, length = 32)
	public String getDuId() {
		return this.duId;
	}

	public void setDuId(String duId) {
		this.duId = duId;
	}

	@Column(name = "DEPARTMENT_ID", nullable = false, length = 20)
	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "OPEN_ID", nullable = false, length = 128)
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "ACCOUNT_ID", nullable = false, length = 128)
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}