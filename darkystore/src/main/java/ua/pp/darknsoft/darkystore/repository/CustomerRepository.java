package ua.pp.darknsoft.darkystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.darkystore.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailOrMobileNumber(String email, String mobileNumber);
}
