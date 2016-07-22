package qqClient.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import qqClient.biz.SysBiz;
import qqClient.util.DiaLogUtil;
import qqServer.entity.Find;
import qqServer.entity.User;

public class SelectFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	// ---------------------------------
	private JRadioButton radioButton;
	private JRadioButton rdbtnNewRadioButton;
	private Find f;
	private SysBiz sysBiz;
	private Socket s;
	private User user;// �û��Լ�
	// -----------------------------------

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SelectFrame(Socket s, User user) {
		this.s = s;
		this.user = user;
		sysBiz = new SysBiz(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		radioButton = new JRadioButton("\u7CBE\u786E\u67E5\u627E");
		radioButton.setBackground(Color.CYAN);
		radioButton.setBounds(29, 40, 121, 23);
		contentPane.add(radioButton);

		rdbtnNewRadioButton = new JRadioButton("\u67E5\u627E\u6240\u6709");
		rdbtnNewRadioButton.setBackground(Color.CYAN);
		rdbtnNewRadioButton.setBounds(29, 143, 121, 23);
		rdbtnNewRadioButton.setSelected(true);
		contentPane.add(rdbtnNewRadioButton);

		ButtonGroup bg = new ButtonGroup();
		bg.add(radioButton);
		bg.add(rdbtnNewRadioButton);

		textField = new JTextField();
		textField.setBounds(40, 87, 262, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton button = new JButton("\u67E5\u627E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				find();
			}
		});
		button.setBounds(40, 206, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		button_1.setBounds(258, 206, 93, 23);
		contentPane.add(button_1);
	}

	private void close() {// ����
		this.dispose();
	}

	private void find() {// ����
		// SelectShowFrame sf;
		if (radioButton.isSelected()) {// ѡ��ȷ����
			f = new Find(f.EXACT, textField.getText());
		} else// ��������
		{
			f = new Find(f.ALL, user.getId());
		}
		// List<User> list=
		sysBiz.selectFriend(f);
		// System.out.println("���Ѹ���Ϊ��"+list.size());
		// if(list.size()==0){
		// DiaLogUtil.errorDiaLog("û���ҵ����������ĺ���");
		// }
		// else
		// sf=new SelectShowFrame(list,s,user);

	}
}
