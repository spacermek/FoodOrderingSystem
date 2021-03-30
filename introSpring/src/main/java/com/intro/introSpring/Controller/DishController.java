package com.intro.introSpring.Controller;

import com.intro.introSpring.Entity.Dish;
import com.intro.introSpring.Service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public List<Dish> getAllDish() {
        return dishService.getAll();
    }

    @GetMapping("/{dishId}")
    public Dish getDishById(@PathVariable Long dishId) {
        return dishService.getById(dishId);
    }

    @PutMapping("/{dishId}")
    public Dish updateDishById(@RequestBody Dish dish, @PathVariable Long dishId) {
        return dishService.updateById(dish, dishId);
    }

    @PostMapping
    public Dish saveDish(@RequestBody Dish dish) {
        return dishService.save(dish);
    }

    @DeleteMapping("/{dishId}")
    public void deleteDishById(@PathVariable Long dishId) {
        dishService.deleteById(dishId);
    }
}
