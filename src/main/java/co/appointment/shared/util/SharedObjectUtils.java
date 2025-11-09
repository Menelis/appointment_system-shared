package co.appointment.shared.util;

import co.appointment.shared.constant.SharedConstants;
import co.appointment.shared.entity.base.BaseEntity;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SharedObjectUtils {
    /**
     * Returns a SecretKey from encoded string
     * @param secretKey secret key
     * @return {@link SecretKey}
     */
    public static SecretKey getSecretKey(String secretKey) {
        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        // rebuild key using SecretKeySpec
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, SharedConstants.AESAlgorithm);
    }

    public static  <T extends BaseEntity> void mapAuditFields(T newEntity, T oldEntity) {
        newEntity.setCreatedAt(oldEntity.getCreatedAt());
    }

}
