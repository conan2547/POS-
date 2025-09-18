package com.yourcompany.catcafepos.controller;
import com.yourcompany.catcafepos.model.Customer; import com.yourcompany.catcafepos.service.CustomerService;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService service; public CustomerController(CustomerService s){this.service=s;}
  @GetMapping public List<Customer> all(){return service.all();}
  @GetMapping("/{id}") public Customer get(@PathVariable Long id){return service.get(id);}
  @PostMapping public Customer create(@Valid @RequestBody Customer c){return service.save(c);}
  @PutMapping("/{id}") public Customer update(@PathVariable Long id, @Valid @RequestBody Customer c){c.setId(id); return service.save(c);}
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
