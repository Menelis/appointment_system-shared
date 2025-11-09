package co.appointment.shared.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Slf4j
public class SharedJwtUtils {
    /**
     * Extract clams from token
     * @param token - Auth token
     * @param secretKey - Secret Key
     * @return {@link Claims}
     */
    public static Claims extractClaims(final String token, final String secretKey) {
        return Jwts
                .parser()
                .verifyWith(generateSecretKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    /**
     * Generate {@link SecretKey}
     * @param secretKey - secret key string
     * @return {@link SecretKey}
     */
    public static SecretKey generateSecretKey(final String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public static  <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver,
                              final String secretKey) {
        final Claims claims = extractClaims(token, secretKey);
        return claimsResolver.apply(claims);
    }
    public static Date extractExpiration(final String token, final String secretKey) {
        return extractClaim(token, Claims::getExpiration, secretKey);
    }
    public static String extractClaimByKey(final String token, final String claimName, final String secretKey) {
        final Claims claims = extractClaims(token, secretKey);
        Object claimValue = claims.get(claimName);
        if (claimValue == null) {
            return null;
        }
        return claimValue.toString();
    }
    public static  <T> T extractClaimByName(
            final String token,
            final String claimName,
            final String secretKey,
            Class<T> type) {
        final Claims claims = extractClaims(token, secretKey);
        return claims.get(claimName, type);
    }

    public static boolean isValidToken(final String token, final String secretKey) {
        try {
            Jwts.parser()
                    .verifyWith(generateSecretKey(secretKey))
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException exception) {
            log.error("Invalid JWT token: {}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            log.error("JWT token is expired: {}", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("JWT token is unsupported: {}", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error("JWT claims string is empty: {}", exception.getMessage());
        }
        return false;
    }
}
