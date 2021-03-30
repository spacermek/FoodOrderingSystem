package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.User;
import com.intro.introSpring.Exceptions.ResourceNotFoundException;
import com.intro.introSpring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with id ", id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateById(User user, Long id) {
        return userRepository.findById(id)
                .map(newUser -> {
                    newUser.setPhoneNumber(user.getPhoneNumber());
                    newUser.setGender(user.getGender());
                    newUser.setCodeWord(user.getCodeWord());
                    newUser.setUserRole(user.getUserRole());
                    return userRepository.save(newUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find user with id ", id));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
