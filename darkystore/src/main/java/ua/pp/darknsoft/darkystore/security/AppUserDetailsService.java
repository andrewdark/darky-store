package ua.pp.darknsoft.darkystore.security;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.darkystore.model.Customer;
import ua.pp.darknsoft.darkystore.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User details not found for the user: " + username)
        );
        AppUser abbUser = new AppUser(customer.getCustomerId(), customer.getEmail(), customer.getPasswordHash(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        abbUser.setName(customer.getName());
        abbUser.setEmail(customer.getEmail());
        abbUser.setMobileNumber(String.valueOf(customer.getMobileNumber()));
        return abbUser;
    }
}
