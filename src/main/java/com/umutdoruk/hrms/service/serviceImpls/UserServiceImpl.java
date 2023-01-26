package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.UserRequest;
import com.umutdoruk.hrms.DTO.response.UserResponse;
import com.umutdoruk.hrms.entities.User;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.exception.AlreadyExistException;
import com.umutdoruk.hrms.repository.UserRepository;
import com.umutdoruk.hrms.service.services.CandidateService;
import com.umutdoruk.hrms.service.services.EmployerService;
import com.umutdoruk.hrms.service.services.UserService;
import com.umutdoruk.hrms.utilities.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CandidateService candidateService;
    private final EmployerService employerService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           CandidateService candidateService,
                           EmployerService employerService) {
        this.userRepository = userRepository;
        this.candidateService= candidateService;
        this.employerService = employerService;
    }

    @Override
    public void create(UserRequest userRequest) {

        User user = new User();

        if (userRequest == null)
            throw new NotFoundException("No User record found to update");
        else if (!Validators.userValidator(userRequest))
            throw new BadRequestException("User must be in the correct format and complete");
        else if (isEmailExist(userRequest.getEmail()))
            throw new AlreadyExistException("Email is already exist");
        else if (userRequest.getUsername() == null)
            user.setUsername(createUsernameIfNoPresent(userRequest));
        else if (isUsernameExist(userRequest.getUsername()))
            throw new AlreadyExistException("Username is already exist");

        // username uniq olsun. degistirilemesin. Ama email degistirilebilirsin.

        user.setPassword(userRequest.getPassword());
        user.setConfirmPassword(userRequest.getConfirmPassword());
        user.setEmail(userRequest.getEmail());
        user.setCreatedDate(LocalDate.now());
        user.setActive(userRequest.getActive());

        /*user.setRole();
        if(userRequest.getRoleName().equals("Employer")){
            employerService.create(null);
        }else candidateService.create(null);*/

        userRepository.save(user);
    }

    @Override
    public void update(UserRequest userRequest, Long id) {

        if (userRequest == null)
            throw new NotFoundException("No User record found to update");
        else if (!Validators.userValidator(userRequest))
            throw new BadRequestException("User must be in the correct format and complete");
        else if (isEmailExist(userRequest.getEmail()))
            throw new AlreadyExistException("User is already exist");

        User user = getUserById(id);
        user.setPassword(userRequest.getPassword());
        user.setConfirmPassword(userRequest.getConfirmPassword());
        user.setActive(userRequest.getActive());

        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User getUserById (Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User is not found"));
    }

    @Override
    public UserResponse getUserResponseById(Long id) {
        return UserResponse.of(getUserById(id));
    }

    @Override
    public UserResponse getUserResponseByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User is not found"));
    }

    @Override
    public Boolean isEmailExist(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        return user.isPresent();
    }

    @Override
    public Boolean isUsernameExist(String username){

        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent();
    }

    @Override
    public String createUsernameIfNoPresent(UserRequest userRequest) {
        String[] temp = userRequest.getEmail().split("@");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(temp[0]);

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }
}
