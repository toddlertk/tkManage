package tk.entities.module;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import tk.entities.org.Role;

/**
 * SmRoleFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_role_function")
public class RoleFunction implements java.io.Serializable {

	// Fields

	private RoleFunctionId id;
	private Role smRole;
	private Function smFunction;

	// Constructors

	/** default constructor */
	public RoleFunction() {
	}

	/** full constructor */
	public RoleFunction(RoleFunctionId id, Role smRole,
			Function smFunction) {
		this.id = id;
		this.smRole = smRole;
		this.smFunction = smFunction;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, length = 10)),
			@AttributeOverride(name = "functionKey", column = @Column(name = "FUNCTION_KEY", nullable = false, length = 20)) })
	public RoleFunctionId getId() {
		return this.id;
	}

	public void setId(RoleFunctionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false, insertable = false, updatable = false)
	public Role getSmRole() {
		return this.smRole;
	}

	public void setSmRole(Role smRole) {
		this.smRole = smRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FUNCTION_KEY", nullable = false, insertable = false, updatable = false)
	public Function getSmFunction() {
		return this.smFunction;
	}

	public void setSmFunction(Function smFunction) {
		this.smFunction = smFunction;
	}

}