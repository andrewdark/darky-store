package ua.pp.darknsoft.darkystore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ua.pp.darknsoft.darkystore.dto.RegisterRequestDto;
import ua.pp.darknsoft.darkystore.model.Address;
import ua.pp.darknsoft.darkystore.model.Customer;
import ua.pp.darknsoft.darkystore.model.Role;
import ua.pp.darknsoft.darkystore.repository.CustomerRepository;
import ua.pp.darknsoft.darkystore.repository.RoleRepository;
import ua.pp.darknsoft.darkystore.service.IAuthService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ua.pp.darknsoft.darkystore.exception.ExceptionHelper.createValidationException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements IAuthService {
    private final CustomerRepository  customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final RoleRepository roleRepository;

    @Override
    public void checkRegistrationData(RegisterRequestDto registerRequestDto) throws MethodArgumentNotValidException {

        Optional<Customer> res = customerRepository.findByEmailOrMobileNumber(registerRequestDto.email(), registerRequestDto.mobileNumber());
        CompromisedPasswordDecision decision = compromisedPasswordChecker.check(registerRequestDto.password());
        Map<String, String> errors = new HashMap<>();

        if(decision.isCompromised()) {
            errors.put("password", "Choose a strong password");
        }

        if(res.isPresent()){
            Customer customer = res.get();

            if (customer.getEmail().equalsIgnoreCase(registerRequestDto.email())) {
                errors.put( "email", "Email is already registered");
            }

            if (customer.getMobileNumber().equals(registerRequestDto.mobileNumber())) {
                errors.put( "mobileNumber", "Mobile number is already registered");
            }

        }

        if(!errors.isEmpty()){throw createValidationException(res, errors);}
    }

    @Override
    @Transactional
    public void register(RegisterRequestDto registerRequestDto) {

        Customer customer = new Customer();

        BeanUtils.copyProperties(registerRequestDto, customer);
        customer.setPasswordHash(passwordEncoder.encode(registerRequestDto.password()));
        Address address = new Address();
        address.setCustomer(customer);
        address.setStreet("");
        address.setCity("");
        address.setState("");
        address.setPostalCode("");
        address.setCountry("");
        customer.setAddress(address);

        roleRepository.findByName("ROLE_USER").ifPresent(customer::addRole);
        customerRepository.save(customer);
    }
}
