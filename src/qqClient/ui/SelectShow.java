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
		content = new JPanel();// Ĭ��FlowLayout���֣���ˮ��
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		// content.setLayout(null);
		this.friend = friend;
		JFrame f = new JFrame();// Ĭ��BorderLayout���֣��ֶ���������5�飩
		f.getContentPane().add(content, BorderLayout.SOUTH);// ��content�ӵ��ϣ��·���
		String[][] playerInfo = new String[friend.size()][2];
		// ��������е�����
		for (int i = 0; i < friend.size(); i++) {
			playerInfo[i][0] = friend.get(i).getId();
			playerInfo[i][1] = friend.get(i).getSickname();
		}
		String[] Names = { "�˺�", "�ǳ�" };
		// ��Names��playerInfoΪ����������һ�����
		JTable table = new JTable(playerInfo, Names);
		// ���ô˱���ͼ����ѡ��С
		table.setPreferredScrollableViewportSize(new Dimension(550, 100));
		// �������뵽�����������
		JScrollPane scrollPane = new JScrollPane(table);
		f.getContentPane().add(scrollPane);
		// �ٽ������������ӵ��м�������
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JButton button = new JButton("�鿴����");
		// JButton jbutton2=new JButton("��Ϊ����");
		button.setBounds(120, 120, 93, 23);
		content.add(button);
		// f.getContentPane().add(button,BorderLayout.SOUTH)��
		// f.getContentPane().add(jbutton2);
	}

}
