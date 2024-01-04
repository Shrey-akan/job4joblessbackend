package com.demo.oragejobsite.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class SendMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String messageTo;
    private String messageFrom;
    private String message;
    
    @CreationTimestamp
    private Date sendTime;
    
	public SendMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SendMessage(Long messageId, String messageTo, String messageFrom, String message, Date sendTime) {
		super();
		this.messageId = messageId;
		this.messageTo = messageTo;
		this.messageFrom = messageFrom;
		this.message = message;
		this.sendTime = sendTime;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

    
   
}
