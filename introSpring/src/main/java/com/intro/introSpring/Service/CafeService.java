package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Cafe;

import java.util.List;

public interface CafeService {
    List<Cafe> getAll();
    Cafe getById(Long id);
    Cafe save(Cafe cafe);
    Cafe updateById(Cafe cafe, Long id);
    void deleteById(Long id);
}
