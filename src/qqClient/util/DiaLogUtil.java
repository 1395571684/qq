package qqClient.util;

import javax.swing.JOptionPane;

public class DiaLogUtil {
	public static void errorDiaLog(String str) {
		JOptionPane.showConfirmDialog(null, str, "ϵͳ����", JOptionPane.WARNING_MESSAGE);
	}

	public static void messageDiaLog(String str) {
		JOptionPane.showConfirmDialog(null, str, "ϵͳ��Ϣ", JOptionPane.CLOSED_OPTION);
	}

	public static boolean askDiaLog(String str) {
		int result = JOptionPane.showConfirmDialog(null, str, "ϵͳѯ��", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return true;
		} else
			return false;

	}

}
