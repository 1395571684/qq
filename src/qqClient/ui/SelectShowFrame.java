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
	private User user;// 用户自己
	// -----------------------------

	/**
	 * 把list中的User信息转换为String[][]格式
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
		String[] Names = { "账号", "昵称" };
		this.getContentPane().setLayout(null);
		// 以Names和playerInfo为参数，创建一个表格
		table = new JTable(playerInfo, Names);
		table.setBackground(Color.WHITE);
		// 设置此表视图的首选大小
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 将表格加入到滚动条组件中
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 0, 394, 171);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(scrollPane);

		JButton button = new JButton("\u67E5\u770B\u8D44\u6599");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkData();// 查看好友资料
			}
		});
		button.setBounds(43, 209, 105, 30);
		this.getContentPane().add(button);

		JButton button_1 = new JButton("\u6DFB\u52A0\u597D\u53CB");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFriend();// 添加好友
			}
		});
		button_1.setBounds(269, 209, 113, 30);
		this.getContentPane().add(button_1);
		// 再将滚动条组件添加到中间容器中
		this.setVisible(true);

	}

	// ---------------------------------------------
	private void checkData() {// 查看好友资料
		int index = table.getSelectedRow();
		if (index == -1)// 没有行被选中
		{
			DiaLogUtil.errorDiaLog("您没有选择要查看资料的好友！");
		} else {// 显示被选择的好友
			FriendDataFrame f = new FriendDataFrame(friend.get(index));
			f.setVisible(true);
		}
	}

	private void addFriend() {// 添加好友
		int index = table.getSelectedRow();
		System.out.println("index:" + index);
		if (index == -1)// 没有行被选中
		{
			DiaLogUtil.errorDiaLog("您没有选择要添加的好友！");
		} else {// 添加好友
			AddFriendMsg msg = new AddFriendMsg(user, friend.get(index));
			userBiz.addFriend(msg);
		}

	}
}
