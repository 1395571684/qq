package qqClient.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import qqClient.biz.SysBiz;
import qqClient.util.DiaLogUtil;
import qqServer.entity.RegisterRs;
import qqServer.entity.User;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	// --------------------------------
	private LoginFrame lFrame;
	private JComboBox<String> comboBox;
	private SysBiz sBiz;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public RegisterFrame(LoginFrame lFrame, Socket s) {
		this.sBiz = new SysBiz(s);
		this.lFrame = lFrame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u6635\u79F0\uFF1A");
		label.setBounds(24, 37, 54, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(66, 34, 186, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(24, 93, 54, 15);
		contentPane.add(label_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(66, 90, 186, 21);
		contentPane.add(passwordField);

		JLabel label_2 = new JLabel("\u5E74\u9F84\uFF1A");
		label_2.setBounds(24, 152, 54, 15);
		contentPane.add(label_2);

		textField_1 = new JTextField();
		textField_1.setBounds(66, 149, 186, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_3 = new JLabel("\u90AE\u7BB1\uFF1A");
		label_3.setBounds(24, 208, 54, 15);
		contentPane.add(label_3);

		textField_2 = new JTextField();
		textField_2.setBounds(66, 205, 186, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel label_4 = new JLabel("\u5934\u50CF\uFF1A");
		label_4.setBounds(279, 40, 41, 15);
		contentPane.add(label_4);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(RegisterFrame.class.getResource("/qqClient/img/img/icon/1.png")));
		lblNewLabel.setBounds(331, 80, 60, 40);
		contentPane.add(lblNewLabel);
		// --------------------------------
		comboBox = new JComboBox();
		String[] str = new String[] { "Ð¦Á³", "»¨¶ä", "±ÊÄ«", "Á³Æ×" };
		for (int i = 0; i < str.length; i++) {
			comboBox.addItem(str[i]);

		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex() + 1;
				lblNewLabel.setIcon(
						new ImageIcon(RegisterFrame.class.getResource("/qqClient/img/img/icon/" + index + ".png")));

			}
		});
		// --------------------------------
		comboBox.setBounds(330, 34, 61, 21);
		contentPane.add(comboBox);

		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		button.setBounds(279, 175, 68, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		button_1.setBounds(356, 175, 68, 23);
		contentPane.add(button_1);

	}

	// ----------------------------------------------------
	private void exit() {
		// Ïú»Ù½çÃæ
		this.dispose();
		// ·µ»ØµÇÂ¼½çÃæ
		this.lFrame.setVisible(true);
	}

	private void register() {
		String sickname = textField.getText();
		String password = new String(passwordField.getPassword());
		int age = Integer.parseInt(textField_1.getText());
		String email = textField_2.getText();
		String img = String.valueOf(comboBox.getSelectedIndex());
		User user = new User();
		user.setSickname(sickname);
		user.setPassword(password);
		user.setAge(age);
		user.setEmail(email);
		user.setImg(img);
		RegisterRs rs = new RegisterRs();
		rs = sBiz.register(user);
		// ×¢²á³É¹¦
		if (rs != null) {
			this.dispose();
			DiaLogUtil.messageDiaLog("×¢²á³É¹¦£¡\n" + "ÄúµÄqqºÅÎª£º" + rs.getId());
		} else// Ê§°Ü
		{
			DiaLogUtil.errorDiaLog("×¢²áÊ§°Ü£¡");
		}
	}
	// ----------------------------------------------------

}
