package tk.entities.msg;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MsgLink entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg_link")
public class MsgLink implements java.io.Serializable {

	// Fields

	private String tableMsgId;
	private String toUserName;
	private String fromUserName;
	private Timestamp createTime;
	private String msgType;
	private String title;
	private String description;
	private String url;
	private String msgId;

	// Constructors

	/** default constructor */
	public MsgLink() {
		this.tableMsgId = UUID.randomUUID().toString().replace("-", "");
	}

	/** minimal constructor */
	public MsgLink(String tableMsgId, String toUserName, String fromUserName,
			Timestamp createTime, String msgType, String msgId) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.msgId = msgId;
	}

	/** full constructor */
	public MsgLink(String tableMsgId, String toUserName, String fromUserName,
			Timestamp createTime, String msgType, String title,
			String description, String url, String msgId) {
		this.tableMsgId = tableMsgId;
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.title = title;
		this.description = description;
		this.url = url;
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

	@Column(name = "Title", length = 500)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "Description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "Url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "MsgID", nullable = false, length = 128)
	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}