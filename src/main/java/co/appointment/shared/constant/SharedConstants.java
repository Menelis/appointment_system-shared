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
    /**
     * Internal Server Error message
     */
    String INTERNAL_SERVER_ERROR = "Oops! Something went wrong!!";

    /**
     * Page Number default value - 1.
     */
    String PAGE_NUMBER_DEFAULT_VALUE = "1";
    /**
     * Page Size default value = 10.
     */
    String PAGE_SIZE_DEFAULT_VALUE = "10";
    /**
     * Page Number parameter name.
     */
    String PAGE_NUMBER_PARAMETER_NAME = "pageNo";
    /**
     * Page Size parameter name.
     */
    String PAGE_SIZE_PARAMETER_NAME = "pageSize";
    String APPOINTMENT_SYSTEM_EMAIL_FOOTER = "Kind Regards,<br/>Appointment System Team.";
}
