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
 * SmRoleModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_role_module")
public class RoleModule implements java.io.Serializable {

	// Fields

	private RoleModuleId id;
	private Role smRole;
	private Module smModule;

	// Constructors

	/** default constructor */
	public RoleModule() {
	}

	/** full constructor */
	public RoleModule(RoleModuleId id, Role smRole, Module smModule) {
		this.id = id;
		this.smRole = smRole;
		this.smModule = smModule;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, length = 10)),
			@AttributeOverride(name = "moduleKey", column = @Column(name = "MODULE_KEY", nullable = false, length = 10)) })
	public RoleModuleId getId() {
		return this.id;
	}

	public void setId(RoleModuleId id) {
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
	@JoinColumn(name = "MODULE_KEY", nullable = false, insertable = false, updatable = false)
	public Module getSmModule() {
		return this.smModule;
	}

	public void setSmModule(Module smModule) {
		this.smModule = smModule;
	}

}