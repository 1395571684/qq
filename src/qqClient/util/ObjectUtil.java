package qqClient.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectUtil {
	public static void writeObject(Socket s, Object o) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		out.writeObject(o);
	}

	public static Object readObject(Socket s) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		return in.readObject();
	}
}
