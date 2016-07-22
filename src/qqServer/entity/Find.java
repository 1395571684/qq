package qqServer.entity;

import java.io.Serializable;

public class Find implements Serializable {
	// 设置两个属性 查找类型和账号
	private int type;
	private String id;
	public static final int EXACT = 1;// 精确查找
	public static final int ALL = 2;// 查找全部

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Find(int type, String id) {
		super();
		this.type = type;
		this.id = id;
	}

}
