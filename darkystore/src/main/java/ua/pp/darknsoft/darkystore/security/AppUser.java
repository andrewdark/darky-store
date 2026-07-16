package ua.pp.darknsoft.darkystore.security;

import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ua.pp.darknsoft.darkystore.dto.AddressDto;
import ua.pp.darknsoft.darkystore.model.Address;

import java.util.Collection;

@Getter
@Setter
public class AppUser extends User {
    private final Long id;
    private String mobileNumber;
    private String email;
    private String name;
    private AddressDto address;

    public AppUser(Long id, String username, @Nullable String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    public AppUser(Long id, String username, @Nullable String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }


}
