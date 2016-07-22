package qqServer.entity;

import java.io.Serializable;

public class AddFriendMsg implements Serializable {
	// 存放向服务器发送的请求添加好友信息
	private User from;// 请求加好友的一方
	private User to;// 要添加的好友

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

	public AddFriendMsg(User from, User to) {
		super();
		this.from = from;
		this.to = to;
	}

}
