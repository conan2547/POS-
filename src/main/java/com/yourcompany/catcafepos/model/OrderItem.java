package com.yourcompany.catcafepos.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*;
@Entity
public class OrderItem {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) private Product product;
  @Min(1) private int quantity; @Min(0) private double price;
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public Product getProduct(){return product;} public void setProduct(Product v){this.product=v;}
  public int getQuantity(){return quantity;} public void setQuantity(int v){this.quantity=v;}
  public double getPrice(){return price;} public void setPrice(double v){this.price=v;}
}
