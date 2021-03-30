package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.User;
import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    User save(User user);
    User updateById(User user, Long id);
    void deleteById(Long id);
}
