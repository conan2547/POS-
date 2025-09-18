package com.yourcompany.catcafepos.repository;
import com.yourcompany.catcafepos.model.Order; import org.springframework.data.jpa.repository.JpaRepository;
import java.time.*; import java.util.*;
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
}
