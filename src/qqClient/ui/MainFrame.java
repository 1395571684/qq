package qqClient.ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import qqClient.thread.ServerThread;
import qqServer.entity.SendMsg;
import qqServer.entity.User;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	// --------------------------------
	private User user;// 用户自己
	private JPanel panel;
	private ServerThread thread;
	private Socket s;
	private Map<String, ChatFrame> mapCf = new HashMap<>();// 用于存放双击过的聊天画面

	public Map<String, ChatFrame> getMapCf() {
		return mapCf;
	}

	/**
	 * Create the frame.
	 */
	public MainFrame(User user, Socket s) {
		thread = new ServerThread(s, user, this);
		thread.start();
		this.user = user;
		this.s = s;
		System.out.println(user.getSickname());
		setTitle(user.getSickname());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 235, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		// lblNewLabel.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// }
		// });
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/qqClient/img/img/main/1.PNG")));
		lblNewLabel.setBounds(0, 0, 237, 56);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MainFrame.class.getResource("/qqClient/img/img/main/2.png")));
		lblNewLabel_1.setBounds(0, 63, 54, 448);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				find(s);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(MainFrame.class.getResource("/qqClient/img/img/main/3.png")));
		lblNewLabel_2.setBounds(0, 498, 219, 75);
		contentPane.add(lblNewLabel_2);

		// JLabel friend2=new JLabel();
		// friend2.setIcon(new
		// ImageIcon(MainFrame.class.getResource("/qqClient/img/img/icon/1.png")));
		// friend2.setBounds(37, 75, 54, 38);
		// contentPane.add(friend2);

		panel = new JPanel();
		panel.setBounds(32, 55, 187, 448);
		JScrollPane js = new JScrollPane(panel);
		js.setBounds(32, 55, 187, 448);
		contentPane.add(js);
		panel.setLayout(new GridLayout(50, 1, 0, 0));
		initFriend();
	}

	// ---------------------------------------
	private void initFriend() {// 初始化好友

		List<User> friendList = new ArrayList<User>();
		friendList = user.getFriend();
		for (User f : friendList) {
			addFriendImg(f);
			// JLabel img=new JLabel();
			// img.setIcon(new
			// ImageIcon(MainFrame.class.getResource("/qqClient/img/img/icon/"+f.getImg()+".png")));
			// img.setText(f.getSickname());
			// img.setToolTipText(f.getId());
			// panel.add(img);
		}

	}

	private void find(Socket s) {// 查找好友
		SelectFrame sf = new SelectFrame(s, user);
		sf.setLocationRelativeTo(null);
		sf.setVisible(true);
	}

	public void addFriendImg(User f) {// 添加好友头像
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon(MainFrame.class.getResource("/qqClient/img/img/icon/" + f.getImg() + ".png")));
		img.setText(f.getSickname());
		img.setToolTipText(f.getId());
		panel.add(img);
		img.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {// 双击了窗口
					// 弹出对话框
					{
						if (mapCf.get(f.getId()) == null) {// 第一次双击窗口
							// 新建聊天窗口并把聊天窗口放入mapCf中
							ChatFrame cf = new ChatFrame(user, f, s);
							cf.setTitle(f.getSickname());
							cf.setLocationRelativeTo(null);
							cf.setVisible(true);
							mapCf.put(f.getId(), cf);
						} else {// 不是第一次双击窗口，显示原来的窗口
							mapCf.get(f.getId()).setVisible(true);

						}
						// 把头像从跳动还原到静态
						img.setIcon(new ImageIcon(
								MainFrame.class.getResource("/qqClient/img/img/icon/" + f.getImg() + ".png")));
					}
				}
			}
		});
	}

	/**
	 * 头像跳动
	 * 
	 * @param sMsg
	 */
	public void tip(SendMsg sMsg) {
		ChatFrame cf = mapCf.get(sMsg.getFrom().getId());
		if (cf == null) {// 第一次双击
			cf = new ChatFrame(sMsg.getTo(), sMsg.getFrom(), s);
			cf.setTitle(sMsg.getFrom().getSickname());
			mapCf.put(sMsg.getFrom().getId(), cf);
		}
		// 添加消息
		cf.getTextArea().append(sMsg.getMsg());
		if (!cf.isVisible()) {// 窗口不可见则跳动头像
			Component[] img = panel.getComponents();
			for (Component c : img) {
				System.out.println(c);
				JLabel f = (JLabel) c;
				System.out.println(f.getToolTipText() + " " + sMsg.getFrom().getId());
				if (f.getToolTipText().equals(sMsg.getFrom().getId())) {// 找到应该跳动的头像
					f.setIcon(new ImageIcon(
							MainFrame.class.getResource("/qqClient/img/img/icon/" + sMsg.getFrom().getImg() + ".gif")));
					System.out.println("跳动的头像是" + sMsg.getFrom().getSickname());
					break;
				}
			}
		}
	}

}
