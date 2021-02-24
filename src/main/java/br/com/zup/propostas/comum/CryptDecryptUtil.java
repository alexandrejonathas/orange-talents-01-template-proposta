package br.com.zup.propostas.comum;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Component
public class CryptDecryptUtil {

    // Valor usado em desenvolvimento "AES/ECB/PKCS5Padding";
    @Value("${proposta-api.crypto.algoritimo}")
    private String ALGORITHM;

    // Valor usado em desenvolvimento "26452948404D6351665468576D5A7134";
    @Value("${proposta-api.crypto.key}")
    private String KEY;

    public String encode(String value) {
        try {
            Cipher c = getCipher(Cipher.ENCRYPT_MODE);
            final String encrypted = new String(Base64.encodeBase64(c.doFinal(value.getBytes())), "UTF-8");
            return encrypted;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decode(String value) {
        try {
            Cipher c = getCipher(Cipher.DECRYPT_MODE);
            final String decrypted = new String(c.doFinal(Base64.decodeBase64(value.getBytes("UTF-8"))));
            return decrypted;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Cipher getCipher(int opmode) {
        try {
            Key key = new SecretKeySpec(KEY.getBytes(), "AES");
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(opmode, key);
            return c;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
