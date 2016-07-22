package qqClient.biz;

import java.io.IOException;
import java.net.Socket;

import qqClient.ui.ChatFrame;
import qqClient.ui.MainFrame;
import qqClient.util.ObjectUtil;
import qqServer.entity.AddFriendMsg;
import qqServer.entity.SendMsg;

public class UserBiz {
	// ���ڽ�����û���ص��߼�����
	private Socket s;

	public UserBiz(Socket s) {
		// TODO Auto-generated constructor stub
		this.s = s;
	}

	/**
	 * ��Ӻ���
	 * 
	 * @param msg
	 */
	public void addFriend(AddFriendMsg msg) {
		try {
			ObjectUtil.writeObject(s, msg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ������������
	 * 
	 * @param sMsg
	 */
	public void sendMsg(SendMsg sMsg) {
		try {
			ObjectUtil.writeObject(s, sMsg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * ��ʾ��������
	 */
	public void showMsg(MainFrame mf, SendMsg sMsg) {
		mf.tip(sMsg);
	}

}
