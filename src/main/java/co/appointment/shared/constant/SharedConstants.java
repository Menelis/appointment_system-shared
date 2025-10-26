package co.appointment.shared.constant;

public interface SharedConstants {
    /**
     * AES Encryption Key size
     */
    Integer AES_KEY_SIZE = 256;
    /**
     * AES Algorithm Constant
     */
    String AESAlgorithm = "AES";
    /**
     * AES Cipher Padding Instance
     */
    String AESCipherInstance = String.format("%s/ECB/PKCS5PADDING", AESAlgorithm);
}
