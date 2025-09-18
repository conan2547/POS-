package com.yourcompany.catcafepos.service;
import com.yourcompany.catcafepos.model.StoreSetting; import com.yourcompany.catcafepos.repository.StoreSettingRepository;
import org.springframework.stereotype.Service;
@Service public class StoreSettingService {
  private final StoreSettingRepository repo; public StoreSettingService(StoreSettingRepository repo){this.repo=repo;}
  public StoreSetting get(){ return repo.findById(1L).orElseGet(()->{ var s=new StoreSetting();
    s.setShopName("🐱 Cat Café & Milk Tea"); s.setAddress("123/45 ... กรุงเทพฯ 10110"); s.setPhone("02-123-4567");
    s.setTaxId("1234567890123"); s.setPromptpayId(""); return repo.save(s); }); }
  public StoreSetting save(StoreSetting s){ s.setId(1L); return repo.save(s); }
}
