package com.react.meteo.fct;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Codification {
	
	
	
	
	private  byte[] linebreak = {}; // Remove Base64 encoder default linebreak
	private  String secret = "S2flvjj333@12345!DawiniProject"; // secret key length must be 16
	private  SecretKey key;
	private  Cipher cipher;
	private  Base64 coder;

	 {
				 try {
				     key = new SecretKeySpec(secret.getBytes(), "AES");
				     cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
				     coder = new Base64();
				 } catch (Throwable t) {
				     t.printStackTrace();
				 }
	}
	
	
public  synchronized String encrypt(String plainText) throws Exception {
	       cipher.init(Cipher.ENCRYPT_MODE, key);
	       byte[] cipherText = cipher.doFinal(plainText.getBytes());
	       return  new String(coder.encode(cipherText));
	}

public  synchronized String decrypt(String codedText) throws Exception {
	       byte[] encypted = coder.decode(codedText.getBytes());
	       cipher.init(Cipher.DECRYPT_MODE, key);
	       byte[] decrypted = cipher.doFinal(encypted);  
	       return new String(decrypted);
	}	
	
public  String md5(String plaintext){
	 String hashtext;
	try {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
	    hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
	} catch (NoSuchAlgorithmException e) {
		hashtext = null;
	//	e.printStackTrace();
	}
	return hashtext;
}
	
public  String cd_structure(String str){
	String code = md5(str);

	if(str.equals("")){
		code = null;
	}else{
		String f_m  = code.substring(0, 15);
		String l_m  = code.substring(15, 32);
		code = (""+Integer.toHexString((int)(code.hashCode()/6395))).replaceAll("f", "").toUpperCase();
		code = code +"-"+(""+Integer.toHexString((int)(f_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(l_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(code.hashCode()/6415))).replaceAll("f", "").toUpperCase();
	}
	return code;
}


public  String cd_convention(String str, String str2){
	String code = md5(str + str2);

	if(str.equals("")){
		code = null;
	}else{
		String f_m  = code.substring(0, 15);
		String l_m  = code.substring(15, 32);
		code = (""+Integer.toHexString((int)(code.hashCode()/6411))).replaceAll("f", "").toUpperCase();
		code = code +"-"+(""+Integer.toHexString((int)(f_m.hashCode()/6406))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(l_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(code.hashCode()/6415))).replaceAll("f", "").toUpperCase();
	}
	return code;
}


public  String cd_morale(String str){
	String code = md5(str);

	if(str.equals("")){
		code = null;
	}else{
		String f_m  = code.substring(0, 15);
		String l_m  = code.substring(15, 32);
		String mix  = l_m + f_m;
		code = (""+Integer.toHexString((int)(code.hashCode()/6395))).replaceAll("f", "").toUpperCase();
		code = (""+Integer.toHexString((int)(f_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(l_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(mix.hashCode()/6395))).replaceAll("f", "").toUpperCase();
	}
	return code;
}

public  String cd_prs(String str){
	String code = md5(str);
	if(str.equals("")){
		code = null;
	}else{
		String f_m  = code.substring(0, 15);
		String l_m  = code.substring(15, 32);
		code = (""+Integer.toHexString((int)(f_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(l_m.hashCode()/6395))).replaceAll("f", "").toUpperCase();
	}
	return code;
}

public  String generate(int length)
{
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
	    String pass = "";
	    for(int x=0;x<length;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
	       pass += chars.charAt(i);
	    }
	   // System.out.println(pass);
	    return pass;
}

}
