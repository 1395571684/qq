package qqClient.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import qqClient.biz.UserBiz;
import qqClient.util.DiaLogUtil;
import qqServer.entity.AddFriendMsg;
import qqServer.entity.User;

public class SelectShowFrame extends JFrame {
	private List<User> friend;
	private JScrollPane jScrollPane1;
	private JTable table;
	private JButton jButton1;
	private JButton jButton2;
	// ----------------------------
	private UserBiz userBiz;
	private User user;// �û��Լ�
	// -----------------------------

	/**
	 * ��list�е�User��Ϣת��ΪString[][]��ʽ
	 * 
	 * @return
	 */
	private String[][] getForTable() {
		String strs[][] = new String[friend.size()][2];
		for (int x = 0; x < friend.size(); x++) {
			strs[x][0] = friend.get(x).getId();
			strs[x][1] = friend.get(x).getSickname();
		}
		return strs;
	}

	public SelectShowFrame(List<User> friend, Socket s, User user) {
		this.user = user;
		userBiz = new UserBiz(s);
		getContentPane().setBackground(Color.CYAN);
		this.friend = friend;
		this.setBounds(0, 0, 430, 300);
		String[][] playerInfo = getForTable();
		String[] Names = { "�˺�", "�ǳ�" };
		this.getContentPane().setLayout(null);
		// ��Names��playerInfoΪ����������һ�����
		table = new JTable(playerInfo, Names);
		table.setBackground(Color.WHITE);
		// ���ô˱���ͼ����ѡ��С
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// �������뵽�����������
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 0, 394, 171);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(scrollPane);

		JButton button = new JButton("\u67E5\u770B\u8D44\u6599");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkData();// �鿴��������
			}
		});
		button.setBounds(43, 209, 105, 30);
		this.getContentPane().add(button);

		JButton button_1 = new JButton("\u6DFB\u52A0\u597D\u53CB");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFriend();// ��Ӻ���
			}
		});
		button_1.setBounds(269, 209, 113, 30);
		this.getContentPane().add(button_1);
		// �ٽ������������ӵ��м�������
		this.setVisible(true);

	}

	// ---------------------------------------------
	private void checkData() {// �鿴��������
		int index = table.getSelectedRow();
		if (index == -1)// û���б�ѡ��
		{
			DiaLogUtil.errorDiaLog("��û��ѡ��Ҫ�鿴���ϵĺ��ѣ�");
		} else {// ��ʾ��ѡ��ĺ���
			FriendDataFrame f = new FriendDataFrame(friend.get(index));
			f.setVisible(true);
		}
	}

	private void addFriend() {// ��Ӻ���
		int index = table.getSelectedRow();
		System.out.println("index:" + index);
		if (index == -1)// û���б�ѡ��
		{
			DiaLogUtil.errorDiaLog("��û��ѡ��Ҫ��ӵĺ��ѣ�");
		} else {// ��Ӻ���
			AddFriendMsg msg = new AddFriendMsg(user, friend.get(index));
			userBiz.addFriend(msg);
		}

	}
}
