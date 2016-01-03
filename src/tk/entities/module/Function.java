package tk.entities.module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SmFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_function")
public class Function implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7718437189022109554L;
	private String functionKey;
	private String moduleKey;
	private String functionName;
	private String authKey;
	private Integer sort;

	// Constructors

	/** default constructor */
	public Function() {
	}

	// Property accessors
	@Id
	@Column(name = "FUNCTION_KEY", unique = true, nullable = false, length = 10)
	public String getFunctionKey() {
		return this.functionKey;
	}

	public void setFunctionKey(String functionKey) {
		this.functionKey = functionKey;
	}

	@Column(name = "MODULE_KEY", nullable = false)
	public String getModuleKey() {
		return this.moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

	@Column(name = "FUNCTION_NAME", nullable = false, length = 120)
	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Column(name = "AUTH_KEY", nullable = false, length = 20)
	public String getAuthKey() {
		return this.authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}