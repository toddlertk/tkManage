package tk.entities.org;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SmRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_role")
public class Role implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;

	// Constructors

	/** default constructor */
	public Role() {
	}
	
	// Property accessors
	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", nullable = false)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}