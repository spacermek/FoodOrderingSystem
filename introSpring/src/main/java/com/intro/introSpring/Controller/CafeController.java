package com.intro.introSpring.Controller;

import com.intro.introSpring.Entity.Cafe;
import com.intro.introSpring.Service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cafe")
public class CafeController {
    private final CafeService cafeService;

    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping
    public List<Cafe> getAllCafes() {
        return cafeService.getAll();
    }

    @GetMapping("/{cafeId}")
    public Cafe getCafeById(@PathVariable Long cafeId) {
        return cafeService.getById(cafeId);
    }

    @PostMapping
    public Cafe saveCafe(@RequestBody Cafe cafe) {
        return cafeService.save(cafe);
    }

    @PutMapping("/{cafeId}")
    public Cafe updateCafeById(@RequestBody Cafe cafe, @PathVariable Long cafeId) {
        return cafeService.updateById(cafe, cafeId);
    }

    @DeleteMapping("/{cafeId}")
    public void deleteCafeById(@PathVariable Long cafeId){
        cafeService.deleteById(cafeId);
    }
}
