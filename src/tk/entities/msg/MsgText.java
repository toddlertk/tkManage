package tk.entities.msg;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MsgText entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg_text")
public class MsgText implements java.io.Serializable {

	// Fields

	private String tableMsgId;
	private String toUserName;
	private String fromUserName;
	private Timestamp createTime;
	private String msgType;
	private String content;
	private String msgId;
	private String contentType;

	// Constructors

	/** default constructor */
	public MsgText() {
		this.tableMsgId = UUID.randomUUID().toString().replace("-", "");
	}

	/** minimal constructor */
	public MsgText(String tableMsgId, String toUserName, String fromUserName,
			Timestamp createTime, String msgType, String contentType) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.contentType = contentType;
	}

	/** full constructor */
	public MsgText(String tableMsgId, String toUserName, String fromUserName,
			Timestamp createTime, String msgType, String content, String msgId,
			String contentType) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.content = content;
		this.msgId = msgId;
		this.contentType = contentType;
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

	@Column(name = "Content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "MsgId", length = 128)
	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Column(name = "CONTENT_TYPE", nullable = false, length = 4)
	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}