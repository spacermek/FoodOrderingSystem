package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getAll();
    UserRole getById(Long id);
    UserRole save(UserRole userRole);
    UserRole updateById(UserRole userRole, Long id);
    void deleteById(Long id);
}
