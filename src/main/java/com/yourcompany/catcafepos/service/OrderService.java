package com.yourcompany.catcafepos.service;
import com.yourcompany.catcafepos.model.*; import com.yourcompany.catcafepos.repository.*;
import org.springframework.stereotype.Service; import java.time.*; import java.time.format.DateTimeFormatter;
import java.util.*; import java.util.stream.Collectors;
@Service public class OrderService {
  private final OrderRepository orderRepo; private final ProductRepository productRepo;
  public OrderService(OrderRepository orderRepo, ProductRepository productRepo){ this.orderRepo=orderRepo; this.productRepo=productRepo; }
  public List<Order> all(){return orderRepo.findAll();} public Order get(Long id){return orderRepo.findById(id).orElse(null);}
  public Order save(Order order){
    double total=0; for(var it: order.getItems()){ var p=productRepo.findById(it.getProduct().getId()).orElseThrow();
      it.setProduct(p); if(it.getPrice()<=0) it.setPrice(p.getPrice());
      if(p.getStock()<it.getQuantity()) throw new RuntimeException("Stock not enough for "+p.getName());
      p.setStock(p.getStock()-it.getQuantity()); productRepo.save(p);
      total += it.getPrice()*it.getQuantity(); }
    order.setTotalAmount(total); if(order.getOrderDate()==null) order.setOrderDate(LocalDateTime.now());
    if(order.getReceiptNo()==null || order.getReceiptNo().isBlank()){ order.setReceiptNo("R"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))); }
    return orderRepo.save(order);
  }
  public List<Order> between(LocalDate s, LocalDate e){ return orderRepo.findByOrderDateBetween(s.atStartOfDay(), e.atTime(LocalTime.MAX)); }
  public Map<String,Object> statsToday(){ var t=LocalDate.now(); var list=between(t,t);
    double sum=list.stream().mapToDouble(Order::getTotalAmount).sum();
    return Map.of("totalSales",sum,"totalOrders",list.size(),
      "paymentCash", list.stream().filter(o->"cash".equalsIgnoreCase(o.getPaymentMethod())).count(),
      "paymentQr", list.stream().filter(o->"qr".equalsIgnoreCase(o.getPaymentMethod())).count()); }
  public Map<String,Object> daily7(){ var end=LocalDate.now(); var start=end.minusDays(6);
    var list=between(start,end); var labels=new ArrayList<String>(); var data=new ArrayList<Double>();
    for(int i=0;i<7;i++){ var d=start.plusDays(i); labels.add(d.toString());
      double s=list.stream().filter(o->o.getOrderDate().toLocalDate().equals(d)).mapToDouble(Order::getTotalAmount).sum(); data.add(s); }
    return Map.of("labels", labels, "data", data); }
  public Map<String,Object> hourly(){ var t=LocalDate.now(); var list=between(t,t); int[] buckets=new int[10];
    for(var o: list){ int h=o.getOrderDate().getHour(); if(h>=9 && h<=18){ buckets[h-9]++; } }
    var labels=new ArrayList<String>(); for(int h=9; h<=18; h++) labels.add(String.format("%02d:00",h));
    var boxed=new ArrayList<Integer>(); for(int v: buckets) boxed.add(v);
    return Map.of("labels", labels, "data", boxed); }
  public List<Map<String,Object>> topProducts(int limit){
    Map<Long,Integer> qty=new HashMap<>(); for(var o: orderRepo.findAll()){ for(var it: o.getItems()){ qty.merge(it.getProduct().getId(), it.getQuantity(), Integer::sum);}}
    return qty.entrySet().stream().sorted((a,b)->Integer.compare(b.getValue(), a.getValue())).limit(limit)
      .map(e->{ var p=productRepo.findById(e.getKey()).orElseThrow(); return Map.<String,Object>of("name",p.getName(),"emoji",p.getEmoji(),"quantity",e.getValue()); })
      .collect(Collectors.toList()); }

      public Map<String, Object> monthlySales() {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
        LocalDate endOfYear = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());
        List<Order> yearOrders = between(startOfYear, endOfYear);
    
        Map<String, Double> salesByMonth = new LinkedHashMap<>();
        String[] monthNames = {"ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค."};
        for (String month : monthNames) {
            salesByMonth.put(month, 0.0);
        }
    
        for (Order order : yearOrders) {
            int monthIndex = order.getOrderDate().getMonthValue() - 1;
            String monthName = monthNames[monthIndex];
            double currentSales = salesByMonth.get(monthName);
            salesByMonth.put(monthName, currentSales + order.getTotalAmount());
        }
    
        return Map.of("labels", new ArrayList<>(salesByMonth.keySet()), "data", new ArrayList<>(salesByMonth.values()));
    }  
}
