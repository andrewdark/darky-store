package ua.pp.darknsoft.darkystore.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.darkystore.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Cacheable(value = "roles", key = "#name")
    Optional<Role> findByName(String name);
}
