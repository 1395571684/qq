package qqClient.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import qqServer.entity.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FriendDataFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FriendDataFrame(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 298);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setBounds(24, 38, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u90AE\u7BB1\uFF1A");
		label_1.setBounds(24, 120, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u5E74\u9F84\uFF1A");
		label_2.setBounds(24, 196, 54, 15);
		contentPane.add(label_2);

		JLabel lblNewLabel = new JLabel("\u5934\u50CF\uFF1A");
		lblNewLabel.setBounds(253, 38, 54, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(
				new ImageIcon(FriendDataFrame.class.getResource("/qqClient/img/img/icon/" + user.getImg() + ".png")));
		lblNewLabel_1.setBounds(286, 81, 54, 38);
		contentPane.add(lblNewLabel_1);

		JButton button = new JButton("\u5173\u95ED");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(278, 192, 73, 23);
		contentPane.add(button);

		JLabel lblNewLabel_2 = new JLabel(user.getId());
		lblNewLabel_2.setBounds(65, 38, 160, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(user.getEmail());
		lblNewLabel_3.setBounds(65, 120, 160, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(user.getAge() + "");
		lblNewLabel_4.setBounds(65, 196, 160, 15);
		contentPane.add(lblNewLabel_4);
	}
}
