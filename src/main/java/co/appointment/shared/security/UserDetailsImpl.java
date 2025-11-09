package co.appointment.shared.security;

import co.appointment.grpc.GetUserResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    private final UUID id;

    @Getter
    private final String firstName;

    @Getter
    private final String lastName;

    @Getter
    private final String email;

    @Getter
    private final String contactNo;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(
            final UUID id,
            final String firstName,
            final String lastName,
            final String email,
            final String contactNo,
            final String password,
            final Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(final GetUserResponse user) {
        log.info("Id:{}, Roles: {}", user.getId(), user.getRolesList());
        List<GrantedAuthority> authorities = user.getRolesList()
                .stream()
                .map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role)))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                UUID.fromString(user.getId()),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getContactNo(),
                null,
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
