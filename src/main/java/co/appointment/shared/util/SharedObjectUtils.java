package co.appointment.shared.util;

import co.appointment.shared.constant.SharedConstants;
import co.appointment.shared.entity.base.BaseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

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

    /**
     * Returns {@link Pageable} instance with provided pageNo, pageSize with sorting.
     * @param pageNo page No - default 1
     * @param pageSize Page Size - default 10
     * @param sort {@link Sort} instance
     * @return {@link Pageable} instance
     */
    public static Pageable getPageable(final int pageNo, final int pageSize, final Sort sort) {
        return PageRequest.of(pageNo - 1, pageSize, sort);
    }

    /**
     * Returns {@link Pageable} instance with provided pageNo, pageSize without sorting.
     * @param pageNo page No - default 1
     * @param pageSize Page Size - default 10
     * @return {@link Pageable} instance
     */
    public static Pageable getPageable(final int pageNo, final int pageSize) {
        return PageRequest.of(pageNo -1, pageSize);
    }
    public static String dateToString(final Date date, final String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        return formatter.format(date);
    }

    public static String returnEmptyIfNullOrBlank(final String stringValue) {
        if(StringUtils.hasText(stringValue)) {
            return stringValue;
        }
        return "";
    }
}
