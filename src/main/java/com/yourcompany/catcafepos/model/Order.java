package com.yourcompany.catcafepos.model;
import jakarta.persistence.*; import java.time.LocalDateTime; import java.util.*;
@Entity @Table(name="orders")
public class Order {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne private Customer customer;
  private LocalDateTime orderDate = LocalDateTime.now();
  private double totalAmount; private String paymentMethod;
  @Column(unique=true) private String receiptNo;
  @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true) @JoinColumn(name="order_id")
  private List<OrderItem> items = new ArrayList<>();
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public Customer getCustomer(){return customer;} public void setCustomer(Customer v){this.customer=v;}
  public LocalDateTime getOrderDate(){return orderDate;} public void setOrderDate(LocalDateTime v){this.orderDate=v;}
  public double getTotalAmount(){return totalAmount;} public void setTotalAmount(double v){this.totalAmount=v;}
  public String getPaymentMethod(){return paymentMethod;} public void setPaymentMethod(String v){this.paymentMethod=v;}
  public String getReceiptNo(){return receiptNo;} public void setReceiptNo(String v){this.receiptNo=v;}
  public List<OrderItem> getItems(){return items;} public void setItems(List<OrderItem> v){this.items=v;}
}
