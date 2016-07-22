package qqClient.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import qqClient.biz.SysBiz;
import qqClient.util.DiaLogUtil;
import qqServer.entity.User;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	// ---------------------------------
	private Socket socket;
	private SysBiz sysBiz;
	private JPasswordField passwordField;
	private RegisterFrame rframe;
	// ----------------------------------

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		// -------------------------------------------
//		super();
//		this.setLocationRelativeTo(null);
//		this.setVisible(true);
		try {
			socket = new Socket("localhost", 8000);
			sysBiz = new SysBiz(socket);

		} catch (Exception e) {
			// TODO: handle exception
			DiaLogUtil.errorDiaLog("连接服务器失败！");
			e.printStackTrace();
		}
		// ------------------------------------------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 296);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/qqClient/img/img/login/qq.PNG")));
		lblNewLabel.setBounds(0, -13, 414, 78);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/qqClient/img/img/login/id.png")));
		lblNewLabel_1.setBounds(20, 92, 49, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/qqClient/img/img/login/pw.png")));
		lblNewLabel_2.setBounds(20, 135, 54, 15);
		contentPane.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(64, 86, 179, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(LoginFrame.class.getResource("/qqClient/img/img/login/2-2.png")));
		lblNewLabel_3.setBounds(20, 179, 223, 21);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				register();
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon(LoginFrame.class.getResource("/qqClient/img/img/login/reg.png")));
		lblNewLabel_4.setBounds(253, 86, 64, 21);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(LoginFrame.class.getResource("/qqClient/img/img/login/qh.png")));
		lblNewLabel_5.setBounds(253, 129, 64, 21);
		contentPane.add(lblNewLabel_5);

		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		btnNewButton.setBounds(20, 210, 75, 23);
		contentPane.add(btnNewButton);

		JButton button = new JButton("\u8BBE\u7F6E");
		button.setBounds(168, 210, 75, 23);
		contentPane.add(button);

		passwordField = new JPasswordField();
		passwordField.setBounds(64, 129, 179, 21);
		contentPane.add(passwordField);
	}

	// ----------------------------------------
	private void login() {
		User user = new User();
		String id = textField.getText();
		String password = new String(passwordField.getPassword());
		user.setId(id);
		user.setPassword(password);
		System.out.println(user.getId() + user.getPassword());
		User u = sysBiz.login(user);
		if (u == null) {
			DiaLogUtil.errorDiaLog("用户名与密码不匹配");
		} else {
			System.out.println("好友数量" + u.getFriend().size());
			this.dispose();
			MainFrame mf = new MainFrame(u, socket);
			mf.setVisible(true);
		}

	}

	private void register() {
		this.setVisible(false);
		rframe = new RegisterFrame(this, socket);
		rframe.setLocationRelativeTo(null);
		rframe.setVisible(true);

	}
	// ----------------------------------------
}
