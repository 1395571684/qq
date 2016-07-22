package qqServer.entity;

import java.io.Serializable;

public class RegisterRs implements Serializable {
	private boolean rs;// 注册是否成功
	private String id;// 注册成功获得的账号

	public boolean getRs() {
		return rs;
	}

	public void setRs(boolean rs) {
		this.rs = rs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
