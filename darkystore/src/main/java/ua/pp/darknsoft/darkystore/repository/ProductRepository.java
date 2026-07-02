package ua.pp.darknsoft.darkystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.darkystore.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
