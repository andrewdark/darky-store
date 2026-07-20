package ua.pp.darknsoft.darkystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.darkystore.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
