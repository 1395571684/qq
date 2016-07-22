package qqServer.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMsg implements Serializable {
	private User from;// 来处
	private User to;// 去处
	private String msg;// 聊天内容
	private Date date;// 发送时间

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		String str;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = "\n" + from.getSickname() + "\t" + formatter1.format(date) + "\n";
		return str;

	}

}
