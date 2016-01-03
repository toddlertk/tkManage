package tk.entities.org;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SmRoleUserId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class RoleUserId implements java.io.Serializable {

	// Fields

	private String roleId;
	private String userId;

	// Constructors

	/** default constructor */
	public RoleUserId() {
	}

	/** full constructor */
	public RoleUserId(String roleId, String userId) {
		this.roleId = roleId;
		this.userId = userId;
	}

	// Property accessors

	@Column(name = "ROLE_ID", nullable = false)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "USER_ID", nullable = false)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RoleUserId))
			return false;
		RoleUserId castOther = (RoleUserId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}