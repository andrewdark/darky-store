package ua.pp.darknsoft.darkystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.darkystore.model.Product;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductIdIn(Collection<Long> productIds);
}
