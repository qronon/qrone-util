package org.qrone.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Digest {

	public static byte[] encrypt(byte[] data, byte[] key){
		if(data == null) return null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "Blowfish"));
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
		} catch (InvalidKeyException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (BadPaddingException e) {
		}
		return null;
	}

	public static byte[] decrypt(byte[] data, byte[] key){
		if(data == null) return null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "Blowfish"));
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
		} catch (InvalidKeyException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (BadPaddingException e) {
		}
		return null;
	}
	
	public static String sha1(byte[] data){
		try {
			return digest_hex("SHA-1", data);
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static String digest_hex(String type, byte[] data) throws NoSuchAlgorithmException{
		byte[] digest = digest(type, data);
		return Hex.bytearray2hex(digest);
	}
	public static String digest_hex(String type, String data) throws NoSuchAlgorithmException{
		return digest_hex(type, data.getBytes());
	}
	
	public static byte[] digest(String type, byte[] data) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance(type);
		md.update(data);
		return md.digest();
		
	}
	
}
