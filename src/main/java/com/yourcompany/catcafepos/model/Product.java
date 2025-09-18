package com.yourcompany.catcafepos.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*;
@Entity
public class Product {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @NotBlank @Size(max=32) @Column(unique=true) private String code;
  @NotBlank @Size(max=150) private String name;
  @Min(0) private double price; @Min(0) private int stock;
  @Size(max=50) private String category; @Size(max=8) private String emoji;
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getCode(){return code;} public void setCode(String v){this.code=v;}
  public String getName(){return name;} public void setName(String v){this.name=v;}
  public double getPrice(){return price;} public void setPrice(double v){this.price=v;}
  public int getStock(){return stock;} public void setStock(int v){this.stock=v;}
  public String getCategory(){return category;} public void setCategory(String v){this.category=v;}
  public String getEmoji(){return emoji;} public void setEmoji(String v){this.emoji=v;}
}
