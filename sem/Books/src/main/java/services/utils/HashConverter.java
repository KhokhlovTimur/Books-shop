package services.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashConverter {
    public static String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passwordBytes = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, passwordBytes);
            String passwordHash = no.toString(16);
            while (passwordHash.length() < 32) {
                passwordHash = "0" + passwordHash;
            }
            return passwordHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
