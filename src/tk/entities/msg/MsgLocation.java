package tk.entities.msg;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MsgLocation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg_location")
public class MsgLocation implements java.io.Serializable {

	// Fields

	private String tableMsgId;
	private String toUserName;
	private String fromUserName;
	private Timestamp createTime;
	private String msgType;
	private Double locationX;
	private Double locationY;
	private Integer scale;
	private String label;
	private String msgId;

	// Constructors

	/** default constructor */
	public MsgLocation() {
		this.tableMsgId = UUID.randomUUID().toString().replace("-", "");
	}

	/** minimal constructor */
	public MsgLocation(String tableMsgId, String toUserName,
			String fromUserName, Timestamp createTime, String msgType,
			Double locationX, Double locationY, Integer scale, String msgId) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.msgId = msgId;
	}

	/** full constructor */
	public MsgLocation(String tableMsgId, String toUserName,
			String fromUserName, Timestamp createTime, String msgType,
			Double locationX, Double locationY, Integer scale, String label,
			String msgId) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label = label;
		this.msgId = msgId;
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

	@Column(name = "Location_X", nullable = false, precision = 22, scale = 0)
	public Double getLocationX() {
		return this.locationX;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}

	@Column(name = "Location_Y", nullable = false, precision = 22, scale = 0)
	public Double getLocationY() {
		return this.locationY;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}

	@Column(name = "Scale", nullable = false)
	public Integer getScale() {
		return this.scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	@Column(name = "Label", length = 128)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "MsgID", nullable = false, length = 128)
	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}