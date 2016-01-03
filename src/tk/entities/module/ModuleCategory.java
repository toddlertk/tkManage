package tk.entities.module;

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
 * SmModuleCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_module_category")
public class ModuleCategory implements java.io.Serializable {

	// Fields

	private String moduleCategoryKey;
	private String moduleCategoryName;
	private Integer sort;
	private String icon;
	private String menuFlag;

	// Constructors

	/** default constructor */
	public ModuleCategory() {
	}
	
	// Property accessors
	@Id
	@Column(name = "MODULE_CATEGORY_KEY", unique = true, nullable = false, length = 10)
	public String getModuleCategoryKey() {
		return this.moduleCategoryKey;
	}

	public void setModuleCategoryKey(String moduleCategoryKey) {
		this.moduleCategoryKey = moduleCategoryKey;
	}

	@Column(name = "MODULE_CATEGORY_NAME", nullable = false, length = 120)
	public String getModuleCategoryName() {
		return this.moduleCategoryName;
	}

	public void setModuleCategoryName(String moduleCategoryName) {
		this.moduleCategoryName = moduleCategoryName;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "ICON", length = 100)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "MENU_FLAG", length = 1)
	public String getMenuFlag() {
		return this.menuFlag;
	}

	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}

}