package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.entities.User;
import com.umutdoruk.hrms.repository.UserRepository;
import com.umutdoruk.hrms.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
