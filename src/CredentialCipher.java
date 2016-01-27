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
 * Purpose: Generate a salted and hashed password, and save it to a file/database.
 *
 * @author William Beckler
 * @version 1.0 1/26/2016
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

    /**
     * Generate a random salt sequence
     *
     * @param args Not used
     * @return A byte array with the randomly generated salt
     */
    public static byte[] generateSalt() {
        byte[] salt = new byte[32];
        RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * Generate hash of the salt and password
     *
     * @param password the password provided by the user
     * @param salt the randomly generated salt crated by the generateSalt method
     * @return A byte array with the generated hash of the salt and password
     * @throws GeneralSecurityException
     */
    public static byte[] generateHash(char[] password, byte[] salt) throws GeneralSecurityException {
        PBEKeySpec key = new PBEKeySpec(password, salt, ITERATIONCOUNT, KEYLENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        SecretKeyFactory secret = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  //PBKDF2WithHmacSHA256
        return secret.generateSecret(key).getEncoded();
    }

    /**
     * A check to verify if a password matches by running it with the previous salt and hash.
     *
     * @param password the attempted password entry by the user.
     * @param salt the salt used by the original password.
     * @param hashedPassword the hashed version of the original password that will be used to check
     *                       if the attempted password is correct.
     * @return A boolean, true if the passwords match and false if not.
     * @throws GeneralSecurityException
     */
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

    /**
     * Add a user to the file/database for storage.
     *
     * @param username the name the user will use to log in.
     * @param password the password the user wants to use with their account
     * @return A boolean, true if the account was added and false if not.
     */
    public static boolean addNewUser(String username, String password) {
        return true;
    }
}
