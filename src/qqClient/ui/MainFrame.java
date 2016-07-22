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
	private User user;// �û��Լ�
	private JPanel panel;
	private ServerThread thread;
	private Socket s;
	private Map<String, ChatFrame> mapCf = new HashMap<>();// ���ڴ��˫���������컭��

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
	private void initFriend() {// ��ʼ������

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

	private void find(Socket s) {// ���Һ���
		SelectFrame sf = new SelectFrame(s, user);
		sf.setLocationRelativeTo(null);
		sf.setVisible(true);
	}

	public void addFriendImg(User f) {// ��Ӻ���ͷ��
		JLabel img = new JLabel();
		img.setIcon(new ImageIcon(MainFrame.class.getResource("/qqClient/img/img/icon/" + f.getImg() + ".png")));
		img.setText(f.getSickname());
		img.setToolTipText(f.getId());
		panel.add(img);
		img.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {// ˫���˴���
					// �����Ի���
					{
						if (mapCf.get(f.getId()) == null) {// ��һ��˫������
							// �½����촰�ڲ������촰�ڷ���mapCf��
							ChatFrame cf = new ChatFrame(user, f, s);
							cf.setTitle(f.getSickname());
							cf.setLocationRelativeTo(null);
							cf.setVisible(true);
							mapCf.put(f.getId(), cf);
						} else {// ���ǵ�һ��˫�����ڣ���ʾԭ���Ĵ���
							mapCf.get(f.getId()).setVisible(true);

						}
						// ��ͷ���������ԭ����̬
						img.setIcon(new ImageIcon(
								MainFrame.class.getResource("/qqClient/img/img/icon/" + f.getImg() + ".png")));
					}
				}
			}
		});
	}

	/**
	 * ͷ������
	 * 
	 * @param sMsg
	 */
	public void tip(SendMsg sMsg) {
		ChatFrame cf = mapCf.get(sMsg.getFrom().getId());
		if (cf == null) {// ��һ��˫��
			cf = new ChatFrame(sMsg.getTo(), sMsg.getFrom(), s);
			cf.setTitle(sMsg.getFrom().getSickname());
			mapCf.put(sMsg.getFrom().getId(), cf);
		}
		// �����Ϣ
		cf.getTextArea().append(sMsg.getMsg());
		if (!cf.isVisible()) {// ���ڲ��ɼ�������ͷ��
			Component[] img = panel.getComponents();
			for (Component c : img) {
				System.out.println(c);
				JLabel f = (JLabel) c;
				System.out.println(f.getToolTipText() + " " + sMsg.getFrom().getId());
				if (f.getToolTipText().equals(sMsg.getFrom().getId())) {// �ҵ�Ӧ��������ͷ��
					f.setIcon(new ImageIcon(
							MainFrame.class.getResource("/qqClient/img/img/icon/" + sMsg.getFrom().getImg() + ".gif")));
					System.out.println("������ͷ����" + sMsg.getFrom().getSickname());
					break;
				}
			}
		}
	}

}
