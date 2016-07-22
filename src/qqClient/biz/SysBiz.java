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
	// ��¼
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

	public User login(User user) {// ��¼
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

	public RegisterRs register(User user) {// ע��
		try {
			ObjectUtil.writeObject(ss, user);
			return (RegisterRs) ObjectUtil.readObject(ss);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public void selectFriend(Find find) {// ���Һ���
		try {
			ObjectUtil.writeObject(ss, find);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showSelectFriend(List<User> list, User user) {
		SelectShowFrame sf;
		System.out.println("���Ѹ���Ϊ��" + list.size());
		if (list.size() == 0) {
			DiaLogUtil.errorDiaLog("û���ҵ����������ĺ���");
		} else
			sf = new SelectShowFrame(list, ss, user);
	}

	public void AddRs(boolean result, AddFriendMsg msg, MainFrame mf) {// ������ӽ��
		AddFriendRSMsg addRs;
		addRs = new AddFriendRSMsg(msg.getTo(), msg.getFrom(), result);
		if (addRs.isResult() == true) {// ͬ�����
			mf.addFriendImg(msg.getFrom());// ��Ӻ���ͷ��
		}
		System.out.println(addRs.getFrom().getSickname() + "�ظ�" + addRs.getTo().getSickname());
		try {
			ObjectUtil.writeObject(ss, addRs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
