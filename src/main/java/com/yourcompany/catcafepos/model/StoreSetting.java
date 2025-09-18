package com.yourcompany.catcafepos.model;
import jakarta.persistence.*;
@Entity
public class StoreSetting {
  @Id private Long id = 1L;
  private String shopName;
  @Column(length=1000) private String address;
  private String phone; private String taxId; private String promptpayId;
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getShopName(){return shopName;} public void setShopName(String v){this.shopName=v;}
  public String getAddress(){return address;} public void setAddress(String v){this.address=v;}
  public String getPhone(){return phone;} public void setPhone(String v){this.phone=v;}
  public String getTaxId(){return taxId;} public void setTaxId(String v){this.taxId=v;}
  public String getPromptpayId(){return promptpayId;} public void setPromptpayId(String v){this.promptpayId=v;}
}
