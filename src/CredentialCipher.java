import java.io.*;
import java.util.Scanner;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by William Beckler on 1/5/2016.
 */
public class CredentialCipher {

    private static final byte[] SALTSEQUENCE = {(byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12};

    public static void main(String[] args) throws Exception {
        String pwd = "secret";
        System.out.println("Original password: " + pwd);
        String encryptedPassword = encrypt(pwd);
        System.out.println("Encrypted password: " + encryptedPassword);
        String decryptedPassword = decrypt(encryptedPassword);
        System.out.println("Decrypted password: " + decryptedPassword);
    }

    private static String getPassword() throws FileNotFoundException {

        File file = new File("MASTERKEY.txt");
        Scanner scan = new Scanner(file);
        String password = scan.nextLine();

        return password;
    }

    private static String base64Encode(byte[] bytes) {
        //this class is internal, and you probably should use another impl
        return new BASE64Encoder().encode(bytes);
    }

    private static byte[] base64Decode(String property) throws IOException {
        // This class is internal, and you probably should use another impl
        return new BASE64Decoder().decodeBuffer(property);
    }
    private static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException, FileNotFoundException {
        char[] PASSWORD = getPassword().toCharArray();
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALTSEQUENCE, 20));
        return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
    }

    private static String decrypt(String property) throws GeneralSecurityException, IOException, FileNotFoundException {
        char[] PASSWORD = getPassword().toCharArray();
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALTSEQUENCE, 20));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }
}
