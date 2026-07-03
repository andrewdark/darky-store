package ua.pp.darknsoft.darkystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.darkystore.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
