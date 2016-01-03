package tk.entities.org;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SmRoleUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_role_user")
public class RoleUser implements java.io.Serializable {

	// Fields

	private RoleUserId id;
	private User smUser;
	private Role smRole;

	// Constructors

	/** default constructor */
	public RoleUser() {
	}

	/** full constructor */
	public RoleUser(RoleUserId id, User smUser, Role smRole) {
		this.id = id;
		this.smUser = smUser;
		this.smRole = smRole;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, length = 10)),
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 32)) })
	public RoleUserId getId() {
		return this.id;
	}

	public void setId(RoleUserId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false, insertable = false, updatable = false)
	public User getSmUser() {
		return this.smUser;
	}

	public void setSmUser(User smUser) {
		this.smUser = smUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false, insertable = false, updatable = false)
	public Role getSmRole() {
		return this.smRole;
	}

	public void setSmRole(Role smRole) {
		this.smRole = smRole;
	}

}