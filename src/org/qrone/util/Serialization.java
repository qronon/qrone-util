package org.qrone.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

public class Serialization {


	public static byte[] serialize(Object o){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		serialize(o,out);
		return out.toByteArray();
	}
	
	public static boolean serialize(Object o, OutputStream out){
		ObjectOutputStream oout = null;
		try {
			oout = new ObjectOutputStream(out);
			oout.writeObject(o);
			oout.flush();
			return true;
		} catch (InvalidClassException e) {
		} catch (NotSerializableException e) {
		} catch (IOException e) {
		} finally {
			if (oout != null) {
				try {
					oout.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}

	public static Object unserialize(byte[] bytes){
		return unserialize(new ByteArrayInputStream(bytes));
	}
	
	public static Object unserialize(InputStream in){
		ObjectInputStream oin = null;
		try {
			if (in != null) {
				oin = new ObjectInputStream(in);
				return oin.readObject();
			}
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			if (oin != null) {
				try {
					oin.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	public static boolean extenalize(Externalizable o, OutputStream out){
		ObjectOutputStream oout = null;
		try {
			oout = new ObjectOutputStream(out);
			o.writeExternal(oout);
			oout.flush();
			return true;
		} catch (InvalidClassException e) {
		} catch (NotSerializableException e) {
		} catch (IOException e) {
		} finally {
			if (oout != null) {
				try {
					oout.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}
	
	public static Object unextenalize(Class c, InputStream in){
		ObjectInputStream oin = null;
		try {
			if (in != null) {
				oin = new ObjectInputStream(in);
				Externalizable r = (Externalizable)c.getConstructor().newInstance();
				r.readExternal(oin);
				return r;
			}
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} catch (IllegalArgumentException e) {
		} catch (SecurityException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		} finally {
			if (oin != null) {
				try {
					oin.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}
}
