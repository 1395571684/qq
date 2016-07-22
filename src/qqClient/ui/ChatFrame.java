package qqClient.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import qqClient.biz.UserBiz;
import qqServer.entity.SendMsg;
import qqServer.entity.User;

public class ChatFrame extends JFrame {

	private JPanel contentPane;
	// -----------------------------
	private User user;// 用户自己
	private User f;// 好友
	private UserBiz userBiz;
	private JTextArea textArea;
	private JTextArea textArea_1;

	public JTextArea getTextArea() {
		return textArea;
	}
	// ------------------------------

	/**
	 * Create the frame.
	 */
	public ChatFrame(User user, User f, Socket s) {
		this.user = user;
		this.f = f;
		userBiz = new UserBiz(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 583);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ChatFrame.class.getResource("/qqClient/img/img/chat/female.png")));
		lblNewLabel.setBounds(274, 0, 132, 211);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ChatFrame.class.getResource("/qqClient/img/img/chat/1.png")));
		lblNewLabel_1.setBounds(0, 210, 410, 44);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(ChatFrame.class.getResource("/qqClient/img/img/chat/male.png")));
		lblNewLabel_2.setBounds(274, 251, 126, 216);
		contentPane.add(lblNewLabel_2);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 10, 254, 201);
		JScrollPane js = new JScrollPane(textArea);
		js.setBounds(10, 10, 254, 201);
		contentPane.add(js);
		textArea.setLineWrap(true);// 激活自动换行功能
		// textArea.setWrapStyleWord(true);//激活断行不断字功能

		textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 251, 254, 201);
		contentPane.add(textArea_1);
		textArea.setLineWrap(true);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ChatFrame.class.getResource("/qqClient/img/img/chat/folder.png")));
		lblNewLabel_3.setBounds(10, 462, 54, 44);
		contentPane.add(lblNewLabel_3);

		JButton button = new JButton("\u5173\u95ED");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		button.setBounds(58, 499, 72, 23);
		contentPane.add(button);

		JButton btnNewButton = new JButton("\u53D1\u9001");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMsg();
			}
		});
		btnNewButton.setBounds(171, 499, 72, 23);
		contentPane.add(btnNewButton);
	}

	public void close() {
		this.dispose();
	}

	/**
	 * 发送消息
	 * 
	 */
	public void sendMsg() {
		SendMsg sMsg = new SendMsg();
		sMsg.setFrom(user);
		sMsg.setTo(f);
		sMsg.setDate(new Date());
		sMsg.setMsg(sMsg.toString() + textArea_1.getText());
		textArea.append(sMsg.toString() + textArea_1.getText());
		textArea_1.setText("");
		userBiz.sendMsg(sMsg);

	}
}
