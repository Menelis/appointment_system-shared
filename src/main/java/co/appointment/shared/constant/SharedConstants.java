package co.appointment.shared.constant;

public interface SharedConstants {
    /**
     * AES Encryption Key size
     */
    public static final Integer AES_KEY_SIZE = 256;
    /**
     * AES Algorithm Constant
     */
    public static final String AESAlgorithm = "AES";
    /**
     * AES Cipher Padding Instance
     */
    public static final String AESCipherInstance = String.format("%s/ECB/PKCS5PADDING", AESAlgorithm);
}
