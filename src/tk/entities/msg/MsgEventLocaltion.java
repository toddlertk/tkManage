package tk.entities.msg;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MsgEventLocaltion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg_event_localtion")
public class MsgEventLocaltion implements java.io.Serializable {

	// Fields

	private String tableMsgId;
	private String toUserName;
	private String fromUserName;
	private Timestamp createTime;
	private String msgType;
	private String event;
	private Double latitude;
	private Double longitude;
	private Double locationPrecision;

	// Constructors

	/** default constructor */
	public MsgEventLocaltion() {
		this.tableMsgId = UUID.randomUUID().toString().replace("-", "");
	}

	/** minimal constructor */
	public MsgEventLocaltion(String tableMsgId, String toUserName,
			String fromUserName, Timestamp createTime, String msgType) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
	}

	/** full constructor */
	public MsgEventLocaltion(String tableMsgId, String toUserName,
			String fromUserName, Timestamp createTime, String msgType,
			String event, Double latitude, Double longitude,
			Double locationPrecision) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.event = event;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationPrecision = locationPrecision;
	}

	// Property accessors
	@Id
	@Column(name = "TABLE_MSG_ID", unique = true, nullable = false, length = 32)
	public String getTableMsgId() {
		return this.tableMsgId;
	}

	public void setTableMsgId(String tableMsgId) {
		this.tableMsgId = tableMsgId;
	}

	@Column(name = "ToUserName", nullable = false, length = 50)
	public String getToUserName() {
		return this.toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	@Column(name = "FromUserName", nullable = false, length = 128)
	public String getFromUserName() {
		return this.fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@Column(name = "CreateTime", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "MsgType", nullable = false, length = 20)
	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name = "Event", length = 15)
	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Column(name = "Latitude", precision = 22, scale = 0)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "Longitude", precision = 22, scale = 0)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "LOCATION_Precision", precision = 22, scale = 0)
	public Double getLocationPrecision() {
		return this.locationPrecision;
	}

	public void setLocationPrecision(Double locationPrecision) {
		this.locationPrecision = locationPrecision;
	}

}