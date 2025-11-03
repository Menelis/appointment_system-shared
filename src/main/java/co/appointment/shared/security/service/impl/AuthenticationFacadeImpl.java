package co.appointment.shared.security.service.impl;

import co.appointment.shared.security.UserDetailsImpl;
import co.appointment.shared.security.service.AuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return authentication();
    }

    @Override
    public UUID getUserId() {
        Authentication authentication = authentication();
        if(authentication == null) {
            return null;
        }
        return ((UserDetailsImpl)authentication).getId();
    }
    private Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
