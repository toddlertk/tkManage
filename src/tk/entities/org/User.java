package tk.entities.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SmUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sm_user")
public class User implements java.io.Serializable {

	// Fields

	private String userId;
	private String userName;
	private String userPws;
	private String userStatus;

	// Constructors

	/** default constructor */
	public User() {
	}

	// Property accessors
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "User_name", nullable = false)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "User_pws", nullable = false)
	public String getUserPws() {
		return this.userPws;
	}

	public void setUserPws(String userPws) {
		this.userPws = userPws;
	}

	@Column(name = "User_status", nullable = false)
	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}