package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.User;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.repository.UserRepository;
import com.umutdoruk.hrms.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User is not found"));
    }

    @Override
    public Boolean isEmailExist(String email) {

        Optional<?> user = userRepository.findByEmail(email);

        return user.isPresent();
    }
}
