package tk.entities.module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SmModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_module")
public class Module implements java.io.Serializable {

	// Fields

	private String moduleKey;
	private String moduleCategoryKey;
	private String moduleName;
	private String url;
	private String authKey;
	private Integer sort;

	// Constructors

	/** default constructor */
	public Module() {
	}

	// Property accessors
	@Id
	@Column(name = "MODULE_KEY", unique = true, nullable = false, length = 10)
	public String getModuleKey() {
		return this.moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

	@Column(name = "MODULE_CATEGORY_KEY", unique = true, nullable = false, length = 10)
	public String getModuleCategoryKey() {
		return moduleCategoryKey;
	}

	public void setModuleCategoryKey(String moduleCategoryKey) {
		this.moduleCategoryKey = moduleCategoryKey;
	}

	@Column(name = "MODULE_NAME", nullable = false, length = 120)
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "URL", nullable = false)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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