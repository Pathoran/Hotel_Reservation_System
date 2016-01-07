import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Created by William Beckler on 1/5/2016.
 */
public class CredentialCipher {

    private static final Random RANDOM = new SecureRandom();
    private static final int ITERATIONCOUNT = 1000;
    private static final int KEYLENGTH = 256;

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String pwd = "secret0";
        byte[] salt = generateSalt();
        System.out.println("Original password: " + pwd);
        char[] pwdChar = pwd.toCharArray();
        byte[] orig = generateHash(pwdChar, salt);
        String encrypted = Arrays.toString(orig);
        System.out.println("Encrypted password: " + encrypted);
        System.out.println("Enter password: ");
        String blah = scan.nextLine();
        char[] sChar = blah.toCharArray();
        byte[] newh = generateHash(blah.toCharArray(), salt);
        sChar = blah.toCharArray();
        String check = Arrays.toString(newh);
        System.out.println("Attempted password: " + check);
        if (hashCheck(blah.toCharArray(), salt, orig)) {
            System.out.println("It's a match!");
        }
        else {
            System.out.println("It's not a match.");
        }
    }

    public static byte[] generateSalt() {
        byte[] salt = new byte[32];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static byte[] generateHash(char[] password, byte[] salt) throws GeneralSecurityException {
        PBEKeySpec key = new PBEKeySpec(password, salt, ITERATIONCOUNT, KEYLENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        SecretKeyFactory secret = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return secret.generateSecret(key).getEncoded();
    }

    public static boolean hashCheck(char[] password, byte[] salt, byte[] hashedPassword) throws GeneralSecurityException {
        byte[] hashAttempt = generateHash(password, salt);
        Arrays.fill(password, Character.MIN_VALUE);
        if (hashAttempt.length != hashedPassword.length) {
            return false;
        }
        for (int i = 0; i < hashAttempt.length; i++) {
            if (hashAttempt[i] != hashedPassword[i]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONCOUNT, KEYLENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }
}
