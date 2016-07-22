package qqClient.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import qqServer.entity.User;

public class SelectShow {
	private List<User> friend;
	private JPanel content;

	public static void main(String[] args) {
		List<User> list = new ArrayList<>();
		list.add(new User("1", "T", 0, null, null));
		list.add(new User("1", "T", 0, null, null));
		list.add(new User("1", "T", 0, null, null));
		list.add(new User("1", "T", 0, null, null));
		new SelectShow(list);
	}

	public SelectShow(List<User> friend) {
		content = new JPanel();// 默认FlowLayout布局（流水）
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		// content.setLayout(null);
		this.friend = friend;
		JFrame f = new JFrame();// 默认BorderLayout布局（分东南西北中5块）
		f.getContentPane().add(content, BorderLayout.SOUTH);// 把content加到南（下方）
		String[][] playerInfo = new String[friend.size()][2];
		// 创建表格中的数据
		for (int i = 0; i < friend.size(); i++) {
			playerInfo[i][0] = friend.get(i).getId();
			playerInfo[i][1] = friend.get(i).getSickname();
		}
		String[] Names = { "账号", "昵称" };
		// 以Names和playerInfo为参数，创建一个表格
		JTable table = new JTable(playerInfo, Names);
		// 设置此表视图的首选大小
		table.setPreferredScrollableViewportSize(new Dimension(550, 100));
		// 将表格加入到滚动条组件中
		JScrollPane scrollPane = new JScrollPane(table);
		f.getContentPane().add(scrollPane);
		// 再将滚动条组件添加到中间容器中
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JButton button = new JButton("查看资料");
		// JButton jbutton2=new JButton("加为好友");
		button.setBounds(120, 120, 93, 23);
		content.add(button);
		// f.getContentPane().add(button,BorderLayout.SOUTH)；
		// f.getContentPane().add(jbutton2);
	}

}
