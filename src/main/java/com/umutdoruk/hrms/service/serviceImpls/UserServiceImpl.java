package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.UserSignupRequest;
import com.umutdoruk.hrms.DTO.response.UserSignupResponse;
import com.umutdoruk.hrms.entities.User;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.exception.AlreadyExistException;
import com.umutdoruk.hrms.repository.UserRepository;
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

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(UserSignupRequest userSignupRequest) {

        User user = new User();

        if (userSignupRequest == null)
            throw new NotFoundException("No User record found to create");
        else if (!Validators.userValidator(userSignupRequest))
            throw new BadRequestException("User must be in the correct format and complete");
        else if (isEmailExist(userSignupRequest.getEmail()))
            throw new AlreadyExistException("Email is already exist");
        else if (userSignupRequest.getUsername() == null)
            user.setUsername(createUsernameIfNoPresent(userSignupRequest));
        else if (isUsernameExist(userSignupRequest.getUsername()))
            throw new AlreadyExistException("Username is already exist");

        user.setPassword(userSignupRequest.getPassword());
        user.setConfirmPassword(userSignupRequest.getConfirmPassword());
        user.setEmail(userSignupRequest.getEmail());
        user.setCreatedDate(LocalDate.now());
        user.setActive(userSignupRequest.getActive());
        user.setRoleName(userSignupRequest.getRoleName());

        userRepository.save(user);
    }

    @Override
    public void update(UserSignupRequest userSignupRequest) {

        if (userSignupRequest == null)
            throw new NotFoundException("No User record found to update");
        else if (!isPasswordFormatCorrect(userSignupRequest))
            throw new BadRequestException("Password and confirm password must be same");
        else if(userSignupRequest.getUsername()!=null || userSignupRequest.getRoleName()!= null)
                throw new BadRequestException("Username and Role cannot be changed afterwards");
        else if (userSignupRequest.getEmail()!=null) {
            if (!Validators.userValidator(userSignupRequest))
                throw new BadRequestException("User must be in the correct format and complete");
            else if (isEmailExist(userSignupRequest.getEmail()))
                throw new AlreadyExistException("Email is already exist");
        }

        User user = getUserById(userSignupRequest.getUserId());
        user.setPassword(userSignupRequest.getPassword());
        user.setConfirmPassword(userSignupRequest.getConfirmPassword());
        user.setEmail(userSignupRequest.getEmail());
        if (userSignupRequest.getActive()!=null)user.setActive(userSignupRequest.getActive());

        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
       /* if (!(userDeteilService.getUser.getId == id))
            throw new BadRequestException("you can only delete your own user");*/
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById (Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User is not found"));
    }

    @Override
    public UserSignupResponse getUserResponseById(Long id) {
        return UserSignupResponse.of(getUserById(id));
    }

    @Override
    public UserSignupResponse getUserResponseByEmail(String email) {
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User is not found"));

        return UserSignupResponse.of(user);
    }

    private Boolean isEmailExist(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        return user.isPresent();
    }

    private Boolean isUsernameExist(String username){

        Optional<User> user = userRepository.findByUsername(username);

        return user.isPresent();
    }

    private String createUsernameIfNoPresent(UserSignupRequest userSignupRequest) {
        String[] temp = userSignupRequest.getEmail().split("@");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(temp[0]);

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }

    private Boolean isPasswordFormatCorrect(UserSignupRequest userSignupRequest){
        return ((userSignupRequest.getPassword()!= null && userSignupRequest.getConfirmPassword()==null)
                ||(userSignupRequest.getPassword()==null && userSignupRequest.getConfirmPassword()!=null)
                ||(userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword())));
    }
}
