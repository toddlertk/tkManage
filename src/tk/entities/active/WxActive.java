package tk.entities.active;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxActive entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_active")
public class WxActive implements java.io.Serializable {

	// Fields

	private Integer activeIndex;
	private String activeName;
	private Timestamp begTime;
	private Timestamp endTime;

	// Constructors

	/** default constructor */
	public WxActive() {
	}

	@Id
	@Column(name = "ACTIVE_INDEX", nullable = false)
	public Integer getActiveIndex() {
		return this.activeIndex;
	}

	public void setActiveIndex(Integer activeIndex) {
		this.activeIndex = activeIndex;
	}

	@Column(name = "ACTIVE_NAME", nullable = false, length = 100)
	public String getActiveName() {
		return this.activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	@Column(name = "BEG_TIME", nullable = false, length = 19)
	public Timestamp getBegTime() {
		return this.begTime;
	}

	public void setBegTime(Timestamp begTime) {
		this.begTime = begTime;
	}

	@Column(name = "END_TIME", nullable = false, length = 19)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

}