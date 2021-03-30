package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Dish;
import com.intro.introSpring.Exceptions.ResourceNotFoundException;
import com.intro.introSpring.Repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish getById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find dish with id ", id));
    }

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish updateById(Dish dish, Long id) {
        return dishRepository.findById(id)
                .map(newDish -> {
                    newDish.setName(dish.getName());
                    newDish.setQuantity(dish.getQuantity());
                    newDish.setCafeId(dish.getCafeId());
                    newDish.setPrice(dish.getPrice());
                    return dishRepository.save(newDish);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find dish with id ", id));
    }

    @Override
    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }
}
