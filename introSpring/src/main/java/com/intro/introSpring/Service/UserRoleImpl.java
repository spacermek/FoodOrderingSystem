package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.UserRole;
import com.intro.introSpring.Exceptions.ResourceNotFoundException;
import com.intro.introSpring.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole getById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user role with id ", id));
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole updateById(UserRole userRole, Long id) {
        return userRoleRepository.findById(id)
                .map(newUserRole -> {
                    newUserRole.setName(userRole.getName());
                    return userRoleRepository.save(newUserRole);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user role with id ", id));

    }

    @Override
    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }
}
