
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author surajit.samui
 */
public class SurajitCoder {

    public static String decodeBase64(String encodedString) {
        return new String(Base64.decodeBase64(encodedString.getBytes()));
    }
    public static String encode(String secret, String secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] kbytes = secretKey.getBytes();
        SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encoding = cipher.doFinal(secret.getBytes());
        BigInteger n = new BigInteger(encoding);
        return n.toString(16);
    }

    public static String decode(String secret, String secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] kbytes = secretKey.getBytes();
        SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

        BigInteger n = new BigInteger(secret, 16);
        byte[] encoding = n.toByteArray();

        if (encoding.length % 8 != 0) {
            int length = encoding.length;
            int newLength = ((length / 8) + 1) * 8;
            int pad = newLength - length; //number of leading zeros
            byte[] old = encoding;
            encoding = new byte[newLength];
            for (int i = old.length - 1; i >= 0; i--) {
                encoding[i + pad] = old[i];
            }
            //SECURITY-563: handle negative numbers
            if (n.signum() == -1) {
                for (int i = 0; i < newLength - length; i++) {
                    encoding[i] = (byte) -1;
                }
            }
        }

        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decode = cipher.doFinal(encoding);
        return new String(decode);
    }

    public static void main(String[] args) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchPaddingException, InvalidKeyException {
        if (args[0] != null && args[1] != null) {
            String password = args[0];
            String operation = args[1];

            String output = null;
            if ("encode".equalsIgnoreCase(operation)) {
                output = encode(password, "jaas is the way");
            } else if ("decode".equalsIgnoreCase(operation)) {
                output = decode(password, "jaas is the way");
            }
            System.out.println("output : " + output);
        }
    }

}
