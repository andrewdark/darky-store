package ua.pp.darknsoft.darkystore.security;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        List<SimpleGrantedAuthority> grantedAuthorities = customer.getRoles().stream().map(el -> new SimpleGrantedAuthority(el.getName())).toList();
        AppUser abbUser = new AppUser(customer.getCustomerId(), customer.getEmail(), customer.getPasswordHash(), grantedAuthorities);
        abbUser.setName(customer.getName());
        abbUser.setEmail(customer.getEmail());
        abbUser.setMobileNumber(String.valueOf(customer.getMobileNumber()));

        abbUser.setAddress(customer.getAddress().toRecord());
        return abbUser;
    }
}
