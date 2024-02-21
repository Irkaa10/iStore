package Client.Components;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordUtils {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 128;

    public static String hashPassword(String password, byte[] salt) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKey secretKey = keyFactory.generateSecret(keySpec);
            byte[] hashedPasswordBytes = secretKey.getEncoded();
            return Base64.getEncoder().encodeToString(hashedPasswordBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password.", e);
        }
    }
}
