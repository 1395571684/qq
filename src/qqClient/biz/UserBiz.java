package qqClient.biz;

import java.io.IOException;
import java.net.Socket;

import qqClient.ui.ChatFrame;
import qqClient.ui.MainFrame;
import qqClient.util.ObjectUtil;
import qqServer.entity.AddFriendMsg;
import qqServer.entity.SendMsg;

public class UserBiz {
	// 用于解决和用户相关的逻辑处理
	private Socket s;

	public UserBiz(Socket s) {
		// TODO Auto-generated constructor stub
		this.s = s;
	}

	/**
	 * 添加好友
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
	 * 发送聊天内容
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
	 * 显示聊天内容
	 */
	public void showMsg(MainFrame mf, SendMsg sMsg) {
		mf.tip(sMsg);
	}

}
