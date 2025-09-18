package com.yourcompany.catcafepos.controller;
import com.yourcompany.catcafepos.model.StoreSetting; import com.yourcompany.catcafepos.service.StoreSettingService;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/settings")
public class SettingController {
  private final StoreSettingService service; public SettingController(StoreSettingService s){this.service=s;}
  @GetMapping public StoreSetting get(){return service.get();}
  @PutMapping public StoreSetting save(@RequestBody StoreSetting s){return service.save(s);}
}
