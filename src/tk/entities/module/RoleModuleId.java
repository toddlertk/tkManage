package tk.entities.module;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SmRoleModuleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class RoleModuleId implements java.io.Serializable {

	// Fields

	private String roleId;
	private String moduleKey;

	// Constructors

	/** default constructor */
	public RoleModuleId() {
	}

	/** full constructor */
	public RoleModuleId(String roleId, String moduleKey) {
		this.roleId = roleId;
		this.moduleKey = moduleKey;
	}

	// Property accessors

	@Column(name = "ROLE_ID", nullable = false, length = 10)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "MODULE_KEY", nullable = false, length = 10)
	public String getModuleKey() {
		return this.moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RoleModuleId))
			return false;
		RoleModuleId castOther = (RoleModuleId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getModuleKey() == castOther.getModuleKey()) || (this
						.getModuleKey() != null
						&& castOther.getModuleKey() != null && this
						.getModuleKey().equals(castOther.getModuleKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getModuleKey() == null ? 0 : this.getModuleKey().hashCode());
		return result;
	}

}