package co.appointment.shared.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public interface AuthenticationFacade {
    /**
     * Returns user authentication from {@link SecurityContextHolder} instance.
     * @return {@link Authentication}
     */
    Authentication getAuthentication();

    /**
     * Get LoggedIn user id.
     * @return user id
     */
    UUID getUserId();
}
