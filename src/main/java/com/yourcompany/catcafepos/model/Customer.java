package com.yourcompany.catcafepos.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*;
@Entity
public class Customer {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @NotBlank @Size(max=150) private String name;
  @Size(max=30) private String phone; @Email @Size(max=150) private String email;
  @Column(length=1000) private String address;
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getName(){return name;} public void setName(String v){this.name=v;}
  public String getPhone(){return phone;} public void setPhone(String v){this.phone=v;}
  public String getEmail(){return email;} public void setEmail(String v){this.email=v;}
  public String getAddress(){return address;} public void setAddress(String v){this.address=v;}
}
