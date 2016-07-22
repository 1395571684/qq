package qqServer.entity;

import java.io.Serializable;

public class AddFriendRSMsg implements Serializable {
	// 记录是否同意添加好友
	private User from;// 发送端
	private User to;// 接收端
	private boolean result;// 是否同意结果

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

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public AddFriendRSMsg(User from, User to, boolean result) {
		super();
		this.from = from;
		this.to = to;
		this.result = result;
	}

}
