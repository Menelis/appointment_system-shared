package co.appointment.shared.util;

import co.appointment.shared.constant.SharedConstants;
import co.appointment.shared.entity.base.BaseEntity;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@SuppressWarnings({"HideUtilityClassConstructor"})
public class SharedObjectUtils {

    /**
     * Returns a SecretKey from encoded string.
     *
     * @param secretKey secret key
     * @return {@link SecretKey}
     */
    public static SecretKey getSecretKey(final String secretKey) {
        // decode the base64 encoded string
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        // rebuild key using SecretKeySpec
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, SharedConstants.AESALGORITHM);
    }

    public static <T extends BaseEntity> void mapAuditFields(final T newEntity, final T oldEntity) {
        newEntity.setCreatedAt(oldEntity.getCreatedAt());
    }

    /**
     * Returns {@link Pageable} instance with provided pageNo, pageSize with sorting.
     *
     * @param pageNo page No - default 1
     * @param pageSize Page Size - default 10
     * @param sort {@link Sort} instance
     * @return {@link Pageable} instance
     */
    public static Pageable getPageable(final int pageNo, final int pageSize, final Sort sort) {
        validatePageNo(pageNo);
        validatePageSize(pageSize);
        return PageRequest.of(pageNo - 1, pageSize, sort);
    }

    /**
     * Returns {@link Pageable} instance with provided pageNo, pageSize without sorting.
     *
     * @param pageNo page No - default 1
     * @param pageSize Page Size - default 10
     * @return {@link Pageable} instance
     */
    public static Pageable getPageable(final int pageNo, final int pageSize) {
        validatePageNo(pageNo);
        validatePageSize(pageSize);
        return PageRequest.of(pageNo - 1, pageSize);
    }

    public static String dateToString(final Date date, final String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        return formatter.format(date);
    }

    private static void validatePageSize(final int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero");
        }

        if (pageSize > 50) {
            throw new IllegalArgumentException("Page size must be less than or equal to 50");
        }
    }

    private static void validatePageNo(final int pageNo) {
        if (pageNo < 1) {
            throw new IllegalArgumentException("Page number must be greater than zero");
        }
    }

    public static String returnEmptyIfNullOrBlank(final String stringValue) {
        if (StringUtils.hasText(stringValue)) {
            return stringValue;
        }
        return "";
    }
}
