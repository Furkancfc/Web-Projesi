package model.interfaces;

import java.io.*;

public interface IGeneric<T> extends Serializable {

	public static Object getInstance(byte[] bytes) {
		try {
			return org.apache.commons.lang3.SerializationUtils.deserialize(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] getBytes(Object obj) {
		try {
			return org.apache.commons.lang3.SerializationUtils.serialize((Serializable) obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getBytesAsString(Object obj) {
		try {
			return new String(org.apache.commons.lang3.SerializationUtils.serialize((Serializable) obj));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
