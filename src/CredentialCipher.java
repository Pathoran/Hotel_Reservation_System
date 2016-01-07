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

        byte[] salt = generateSalt();
        System.out.println("Please enter your username: ");
        String username = scan.nextLine();
        System.out.println("Please enter your password: ");
        String password = scan.nextLine();
        while (password.length() < 6 || !(password.matches(".*\\d+.*"))) { //password.matches(".*\\d+.*") checks for a numeric value in the password
            System.out.println("Invalid password.  Please enter password: ");
            password = scan.nextLine();
        }
        byte[] encrypted = generateHash(password.toCharArray(), salt);



        System.out.println("Thank you.  Your username and password has been saved.");
    }

    public static byte[] generateSalt() {
        byte[] salt = new byte[32];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static byte[] generateHash(char[] password, byte[] salt) throws GeneralSecurityException {
        PBEKeySpec key = new PBEKeySpec(password, salt, ITERATIONCOUNT, KEYLENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        SecretKeyFactory secret = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  //PBKDF2WithHmacSHA256
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

    public static boolean addNewUser(String username, String password) {
        return true;
    }
}
