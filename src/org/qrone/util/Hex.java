package org.qrone.util;

public class Hex {

	public static double hex2double(String hex){
		return Double.longBitsToDouble(hex2long(hex));
	}

	public static String double2hex(double d) {
		return long2hex(Double.doubleToRawLongBits(d));
	}
	
	public static long hex2long(String hex){
		return Long.parseLong(hex, 16);
	}

	public static String long2hex(long l) {
		return Long.toHexString(l);
	}
	
	public static String hex2str(String hex){
		return new String(hex2bytearray(hex));
	}

	public static String str2hex(String l) {
		return bytearray2hex(l.getBytes());
	}
	
	public static String bytearray2hex(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}
	
	public static byte[] hex2bytearray(String hex){
		byte[] bytes = new byte[hex.length() / 2];
		for (int index = 0; index < bytes.length; index++) {
			bytes[index] = (byte) Integer.parseInt(
					hex.substring(index * 2, (index + 1) * 2), 16);
		}
		return bytes;
	}
}
