package com.yourcompany.catcafepos.controller;
import com.yourcompany.catcafepos.model.Product; import com.yourcompany.catcafepos.service.ProductService;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/products")
public class ProductController {
  private final ProductService service; public ProductController(ProductService s){this.service=s;}
  @GetMapping public List<Product> all(){return service.all();}
  @GetMapping("/{id}") public Product get(@PathVariable Long id){return service.get(id);}
  @PostMapping public Product create(@Valid @RequestBody Product p){return service.save(p);}
  @PutMapping("/{id}") public Product update(@PathVariable Long id, @Valid @RequestBody Product p){p.setId(id); return service.save(p);}
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
