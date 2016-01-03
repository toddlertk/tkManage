package tk.entities.msg;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MsgEventSubscribe entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg_event_subscribe")
public class MsgEventSubscribe implements java.io.Serializable {

	// Fields

	private String tableMsgId;
	private String toUserName;
	private String fromUserName;
	private Timestamp createTime;
	private String msgType;
	private String event;
	private String eventKey;
	private String ticket;

	// Constructors

	/** default constructor */
	public MsgEventSubscribe() {
		this.tableMsgId = UUID.randomUUID().toString().replace("-", "");
	}

	/** minimal constructor */
	public MsgEventSubscribe(String tableMsgId, String toUserName,
			String fromUserName, Timestamp createTime, String msgType) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
	}

	/** full constructor */
	public MsgEventSubscribe(String tableMsgId, String toUserName,
			String fromUserName, Timestamp createTime, String msgType,
			String event, String eventKey, String ticket) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.event = event;
		this.eventKey = eventKey;
		this.ticket = ticket;
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

	@Column(name = "EventKey", length = 128)
	public String getEventKey() {
		return this.eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@Column(name = "Ticket", length = 128)
	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}