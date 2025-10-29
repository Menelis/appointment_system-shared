package co.appointment.shared.service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface EncryptionService {
    /**
     * Generate a 256 AES Secret Key
     * @return {@link SecretKey}
     */
    SecretKey generateEncryptionKey();

    /**
     * Generate an AES Secret Key of keySize
     * @param keySize Key Size
     * @return {@link SecretKey}
     */
    SecretKey generateEncryptionKey(int keySize);

    /**
     * Encrypt a plaintext using AES Algorithm with the provided encryption key
     * @param plainText String to encrypt
     * @return CipherText
     */
    String encryptText(String plainText);

    /**
     * Decrypts CipherText using AES algorithm with provided encryption key
     * @param cipherText Cipher Text
     * @return Plain text
     */
    String decryptCipherText(String cipherText);
}
