package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Dish;

import java.util.List;

public interface DishService {
    List<Dish> getAll();
    Dish getById(Long id);
    Dish save(Dish food);
    Dish updateById(Dish Dish, Long id);
    void deleteById(Long id);
}
