package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Cafe;
import com.intro.introSpring.Enum.Role;
import com.intro.introSpring.Exceptions.ResourceNotFoundException;
import com.intro.introSpring.Exceptions.RoleException;
import com.intro.introSpring.Repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CafeServiceImpl implements CafeService {
    private final CafeRepository cafeRepository;
    private final UserService userService;

    @Autowired
    public CafeServiceImpl(CafeRepository cafeRepository, UserService userService) {
        this.cafeRepository = cafeRepository;
        this.userService = userService;
    }

    @Override
    public List<Cafe> getAll() {
        return cafeRepository.findAll();
    }

    @Override
    public Cafe getById(Long id) {
        return cafeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find cafe with id ", id));
    }

    @Override
    public Cafe save(Cafe cafe) {
        if (userService.getById(cafe.getUserSupplierId().getId()).getUserRole().getName().equals(Role.Supplier))
        return cafeRepository.save(cafe);

        else throw new RoleException("Incorrect role!");
    }

    @Override
    public Cafe updateById(Cafe cafe, Long id) {
        return cafeRepository.findById(id)
                .map(newCafe -> {
                    newCafe.setName(cafe.getName());
                    newCafe.setUserSupplierId(cafe.getUserSupplierId());
                    return cafeRepository.save(newCafe);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find cafe with id ", id));
    }

    @Override
    public void deleteById(Long id) {
        cafeRepository.deleteById(id);
    }
}
