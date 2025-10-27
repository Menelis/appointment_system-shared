package co.appointment.shared.service.impl;

import co.appointment.shared.config.SharedAppConfigProperties;
import co.appointment.shared.constant.SharedConstants;
import co.appointment.shared.service.EncryptionService;
import co.appointment.shared.util.SharedObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Slf4j
public class AESEncryptionServiceImpl implements EncryptionService {

    private final String encryptionKey;

    public AESEncryptionServiceImpl(final SharedAppConfigProperties sharedAppConfigProperties) {
        this.encryptionKey = sharedAppConfigProperties.getEncryptionKey();
    }

    @Override
    public SecretKey generateEncryptionKey() {
        return generateSecretKey(SharedConstants.AES_KEY_SIZE);
    }

    @Override
    public SecretKey generateEncryptionKey(int keySize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(SharedConstants.AESAlgorithm);
            keyGenerator.init(keySize);
            return keyGenerator.generateKey();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return null;
    }

    @Override
    public String encryptText(final String plainText) {
       try {
           Cipher cipher = Cipher.getInstance(SharedConstants.AESCipherInstance);
           cipher.init(Cipher.ENCRYPT_MODE, SharedObjectUtils.getSecretKey(encryptionKey));
           return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));
       } catch (Exception exception) {
           log.error(exception.getMessage(), exception);
       }
       return null;
    }

    @Override
    public String decryptCipherText(final String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(SharedConstants.AESCipherInstance);
            cipher.init(Cipher.DECRYPT_MODE, SharedObjectUtils.getSecretKey(encryptionKey));
            return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return null;
    }

    private SecretKey generateSecretKey(final int keySize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(SharedConstants.AESAlgorithm);
            keyGenerator.init(keySize);
            return keyGenerator.generateKey();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return null;
    }
}
