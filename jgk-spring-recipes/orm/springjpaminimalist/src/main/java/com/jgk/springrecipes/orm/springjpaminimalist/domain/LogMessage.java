package com.jgk.springrecipes.orm.springjpaminimalist.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="LOG_MSG")
public class LogMessage {
	private Long id;
	private String message;
	private String author;
	private Date date;

	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static LogMessage createLogMessage(String message, String author) {
		LogMessage logMessage = new LogMessage();
		logMessage.setMessage(message);
		logMessage.setAuthor(author);
		
		return logMessage;
	}

	
	
}
