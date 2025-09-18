package com.yourcompany.catcafepos.controller;
import com.yourcompany.catcafepos.model.Order; import com.yourcompany.catcafepos.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat; import org.springframework.web.bind.annotation.*; import java.time.LocalDate; import java.util.*;
@RestController @RequestMapping("/api/orders")
public class OrderController {
  private final OrderService service; public OrderController(OrderService s){this.service=s;}
  @GetMapping public List<Order> all(){return service.all();}
  @GetMapping("/{id}") public Order get(@PathVariable Long id){return service.get(id);}
  @PostMapping public Order create(@RequestBody Order o){return service.save(o);}
  @GetMapping("/report") public Map<String,Object> report(@RequestParam("start") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam("end") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate end){
    var list=service.between(start,end); double total=list.stream().mapToDouble(Order::getTotalAmount).sum(); return Map.of("orders",list,"totalSales",total);}
  @GetMapping("/stats/today") public Map<String,Object> statsToday(){return service.statsToday();}
  @GetMapping("/chart/daily7") public Map<String,Object> daily7(){return service.daily7();}
  @GetMapping("/chart/hourly") public Map<String,Object> hourly(){return service.hourly();}
  @GetMapping("/top-products") public List<Map<String,Object>> topProducts(@RequestParam(defaultValue="5") int limit){return service.topProducts(limit);}
  @GetMapping("/chart/monthly")
public Map<String, Object> monthlySales() {
    return service.monthlySales(); // คุณจะต้องสร้าง method นี้ใน OrderService ด้วย
}
}
