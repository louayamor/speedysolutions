package service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
	
	public static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    public static String cryptage(String pass) {
        byte[] md5InBytes = PasswordEncryption.digest(pass.getBytes(PasswordEncryption.UTF_8));
        System.out.println(PasswordEncryption.bytesToHex(md5InBytes));
        return PasswordEncryption.bytesToHex(md5InBytes);
    }

}
