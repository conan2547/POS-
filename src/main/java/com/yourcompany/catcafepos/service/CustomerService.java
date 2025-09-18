package com.yourcompany.catcafepos.service;
import com.yourcompany.catcafepos.model.Customer; import com.yourcompany.catcafepos.repository.CustomerRepository;
import org.springframework.stereotype.Service; import java.util.*;
@Service public class CustomerService {
  private final CustomerRepository repo; public CustomerService(CustomerRepository repo){this.repo=repo;}
  public List<Customer> all(){return repo.findAll();} public Customer get(Long id){return repo.findById(id).orElse(null);}
  public Customer save(Customer c){return repo.save(c);} public void delete(Long id){repo.deleteById(id);}
}
