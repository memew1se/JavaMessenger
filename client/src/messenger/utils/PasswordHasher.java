package messenger.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher {

    private static final byte[] SALT = System.getenv("MESSENGER_SALT").getBytes();
    private static final int ITERATIONS = 20_000;
    private static final int PREFERABLE_PASSWORD_LEN = 256;

    public static String hashPassword(String password) {
        try {
            SecretKeyFactory function = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            SecretKey hashedPassword = function.generateSecret(new PBEKeySpec(
                    password.toCharArray(), SALT, ITERATIONS, PREFERABLE_PASSWORD_LEN));

            return Base64.encodeBase64String(hashedPassword.getEncoded());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
