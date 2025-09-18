package com.yourcompany.catcafepos.service;
import com.yourcompany.catcafepos.model.Product; import com.yourcompany.catcafepos.repository.ProductRepository;
import org.springframework.stereotype.Service; import java.util.*;
@Service public class ProductService {
  private final ProductRepository repo; public ProductService(ProductRepository repo){this.repo=repo;}
  public List<Product> all(){return repo.findAll();} public Product get(Long id){return repo.findById(id).orElse(null);}
  public Product save(Product p){return repo.save(p);} public void delete(Long id){repo.deleteById(id);}
}
