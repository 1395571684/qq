package qqClient.biz;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import qqClient.ui.MainFrame;
import qqClient.ui.SelectShowFrame;
import qqClient.util.DiaLogUtil;
import qqClient.util.ObjectUtil;
import qqServer.entity.AddFriendMsg;
import qqServer.entity.AddFriendRSMsg;
import qqServer.entity.Find;
import qqServer.entity.RegisterRs;
import qqServer.entity.User;

public class SysBiz {
	// 登录
	private Socket ss;

	public Socket getSs() {
		return ss;
	}

	public void setSs(Socket ss) {
		this.ss = ss;
	}

	public SysBiz(Socket ss) {
		super();
		this.ss = ss;
	}

	public User login(User user) {// 登录
		try {
			System.out.println(user.getId());
			ObjectUtil.writeObject(ss, user);
			return (User) ObjectUtil.readObject(ss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public RegisterRs register(User user) {// 注册
		try {
			ObjectUtil.writeObject(ss, user);
			return (RegisterRs) ObjectUtil.readObject(ss);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public void selectFriend(Find find) {// 查找好友
		try {
			ObjectUtil.writeObject(ss, find);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showSelectFriend(List<User> list, User user) {
		SelectShowFrame sf;
		System.out.println("好友个数为：" + list.size());
		if (list.size() == 0) {
			DiaLogUtil.errorDiaLog("没有找到符合条件的好友");
		} else
			sf = new SelectShowFrame(list, ss, user);
	}

	public void AddRs(boolean result, AddFriendMsg msg, MainFrame mf) {// 处理添加结果
		AddFriendRSMsg addRs;
		addRs = new AddFriendRSMsg(msg.getTo(), msg.getFrom(), result);
		if (addRs.isResult() == true) {// 同意添加
			mf.addFriendImg(msg.getFrom());// 添加好友头像
		}
		System.out.println(addRs.getFrom().getSickname() + "回复" + addRs.getTo().getSickname());
		try {
			ObjectUtil.writeObject(ss, addRs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
