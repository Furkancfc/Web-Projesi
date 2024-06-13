package model.interfaces;

import java.io.*;

public interface IGeneric<T> extends Serializable {

	public static Object getInstance(byte[] bytes) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] getBytes(Object obj) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject((Object) obj);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getBytesAsString(Object obj) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject((Object) obj);
			byte[] bytes = baos.toByteArray();
			return bytes.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
