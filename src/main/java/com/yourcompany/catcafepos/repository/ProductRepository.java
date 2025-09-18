package com.yourcompany.catcafepos.repository;
import com.yourcompany.catcafepos.model.Product; import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> { Product findByCode(String code); }
