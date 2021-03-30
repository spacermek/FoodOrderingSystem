package com.intro.introSpring.Controller;
import com.intro.introSpring.Entity.UserRole;
import com.intro.introSpring.Service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public List<UserRole> getAllUserRoles() {
        return userRoleService.getAll();
    }

    @GetMapping("/{userRoleId}")
    public UserRole getUserRoleById(@PathVariable Long userRoleId) {
        return userRoleService.getById(userRoleId);
    }

    @PostMapping
    public UserRole saveUserRole(@RequestBody UserRole userRole) {
        return userRoleService.save(userRole);
    }

    @PutMapping("/{userRoleId}")
    public UserRole updateUserRole(@RequestBody UserRole userRole, @PathVariable Long userRoleId) {
        return userRoleService.updateById(userRole, userRoleId);
    }

    @DeleteMapping("/{userRoleId}")
    public void deleteUserRole(@PathVariable Long userRoleId) {
        userRoleService.deleteById(userRoleId);
    }
}
