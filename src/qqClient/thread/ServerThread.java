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
	// ���ڽ��շ���������Ϣ
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
				if (o instanceof List) {// ���в��Һ��Ѳ������յ����Ǻ����б�
					List<User> list = (List<User>) o;
					sysBiz.showSelectFriend(list, user);
				} else if (o instanceof AddFriendMsg) {// ���յ��Ӻ��ѵ���Ϣ
					AddFriendMsg msg = (AddFriendMsg) o;
					boolean result = DiaLogUtil.askDiaLog(msg.getFrom().getSickname() + "�����Ϊ���ѣ��Ƿ���ӣ�");
					System.out.println(msg.getFrom().getSickname() + "  " + msg.getTo().getSickname());
					sysBiz.AddRs(result, msg, mf);
				} else if (o instanceof AddFriendRSMsg) {
					AddFriendRSMsg rs = (AddFriendRSMsg) o;
					if (rs.isResult() == true) {
						DiaLogUtil.messageDiaLog(rs.getFrom().getSickname() + "ͬ�������Ϊ����");
						// ��Ӻ���ͷ��
						mf.addFriendImg(rs.getFrom());
					} else {
						DiaLogUtil.messageDiaLog(rs.getFrom().getSickname() + "�ܾ������Ϊ����");
					}
				} else if (o instanceof SendMsg) {// ���յ����ѷ��͵���Ϣ
					SendMsg sMsg = (SendMsg) o;
					System.out.println(
							sMsg.getTo().getSickname() + "�յ�" + sMsg.getFrom().getSickname() + "���͵�����" + sMsg.getMsg());
					uBiz.showMsg(mf, sMsg);

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
