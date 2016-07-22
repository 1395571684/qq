package qqClient.thread;

import java.net.Socket;
import java.util.List;

import qqClient.biz.SysBiz;
import qqClient.biz.UserBiz;
import qqClient.ui.MainFrame;
import qqClient.util.DiaLogUtil;
import qqClient.util.ObjectUtil;
import qqServer.entity.AddFriendMsg;
import qqServer.entity.AddFriendRSMsg;
import qqServer.entity.SendMsg;
import qqServer.entity.User;

public class ServerThread extends Thread {
	// 用于接收服务器的消息
	private Socket s;
	private User user;
	private SysBiz sysBiz;
	private UserBiz uBiz;
	private MainFrame mf;

	public ServerThread(Socket s, User user, MainFrame mf) {
		super();
		this.s = s;
		this.user = user;
		this.mf = mf;
	}

	public void run() {
		while (true) {
			Object o;
			sysBiz = new SysBiz(s);
			uBiz = new UserBiz(s);
			try {
				o = ObjectUtil.readObject(s);
				if (o instanceof List) {// 进行查找好友操作，收到的是好友列表
					List<User> list = (List<User>) o;
					sysBiz.showSelectFriend(list, user);
				} else if (o instanceof AddFriendMsg) {// 接收到加好友的消息
					AddFriendMsg msg = (AddFriendMsg) o;
					boolean result = DiaLogUtil.askDiaLog(msg.getFrom().getSickname() + "请求加为好友，是否添加？");
					System.out.println(msg.getFrom().getSickname() + "  " + msg.getTo().getSickname());
					sysBiz.AddRs(result, msg, mf);
				} else if (o instanceof AddFriendRSMsg) {
					AddFriendRSMsg rs = (AddFriendRSMsg) o;
					if (rs.isResult() == true) {
						DiaLogUtil.messageDiaLog(rs.getFrom().getSickname() + "同意添加你为好友");
						// 添加好友头像
						mf.addFriendImg(rs.getFrom());
					} else {
						DiaLogUtil.messageDiaLog(rs.getFrom().getSickname() + "拒绝添加你为好友");
					}
				} else if (o instanceof SendMsg) {// 接收到好友发送的消息
					SendMsg sMsg = (SendMsg) o;
					System.out.println(
							sMsg.getTo().getSickname() + "收到" + sMsg.getFrom().getSickname() + "发送的内容" + sMsg.getMsg());
					uBiz.showMsg(mf, sMsg);

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
