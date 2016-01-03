package tk.entities.module;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SmRoleFunctionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class RoleFunctionId implements java.io.Serializable {

	// Fields

	private String roleId;
	private String functionKey;

	// Constructors

	/** default constructor */
	public RoleFunctionId() {
	}

	/** full constructor */
	public RoleFunctionId(String roleId, String functionKey) {
		this.roleId = roleId;
		this.functionKey = functionKey;
	}

	// Property accessors

	@Column(name = "ROLE_ID", nullable = false, length = 10)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "FUNCTION_KEY", nullable = false, length = 20)
	public String getFunctionKey() {
		return this.functionKey;
	}

	public void setFunctionKey(String functionKey) {
		this.functionKey = functionKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RoleFunctionId))
			return false;
		RoleFunctionId castOther = (RoleFunctionId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getFunctionKey() == castOther.getFunctionKey()) || (this
						.getFunctionKey() != null
						&& castOther.getFunctionKey() != null && this
						.getFunctionKey().equals(castOther.getFunctionKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37
				* result
				+ (getFunctionKey() == null ? 0 : this.getFunctionKey()
						.hashCode());
		return result;
	}

}