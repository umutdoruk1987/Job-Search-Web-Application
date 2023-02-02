package com.umutdoruk.hrms.service.serviceImpls;

import com.umutdoruk.hrms.DTO.request.UserSignupRequest;
import com.umutdoruk.hrms.DTO.response.UserSignupResponse;
import com.umutdoruk.hrms.entities.User;
import com.umutdoruk.hrms.exception.BadRequestException;
import com.umutdoruk.hrms.exception.NotFoundException;
import com.umutdoruk.hrms.exception.AlreadyExistException;
import com.umutdoruk.hrms.repository.UserRepository;
import com.umutdoruk.hrms.service.services.UserService;
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

        userRepository.save(userCreator(userSignupRequest));
    }

    @Override
    public void update(UserSignupRequest userSignupRequest) {

        userRepository.save(userUpdater(userSignupRequest));
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

    private UserSignupRequest userCreateValidator(UserSignupRequest userSignupRequest){

        if (userSignupRequest == null)
            throw new NotFoundException("No User record found to create");
        else if (!isEmailFormatCorrect(userSignupRequest))
            throw new BadRequestException("User must have an email in the correct format");
        else if (!isPasswordFormatCorrect(userSignupRequest))
            throw new BadRequestException("User must set a password with at least 8 digits and the same as the confirm password.");
        else if (isEmailExist(userSignupRequest.getEmail()))
            throw new AlreadyExistException("This Email belongs to another user");
        else if (userSignupRequest.getUsername() == null)
            userSignupRequest.setUsername(createUsernameIfNoPresent(userSignupRequest));
        else if (isUsernameExist(userSignupRequest.getUsername()))
            throw new AlreadyExistException("Username is already exist");

        return userSignupRequest;
    }

    private User userCreator (UserSignupRequest userSignupRequest) {
        UserSignupRequest userSignupRequest1 = userCreateValidator(userSignupRequest);
        if (userSignupRequest.getActive() == null)
            userSignupRequest.setActive(Boolean.TRUE);

        if (userSignupRequest.getRoleName()==null)
            userSignupRequest.setRoleName("Candidate");

        User user = new User();
        user.setPassword(userSignupRequest1.getPassword());
        user.setConfirmPassword(userSignupRequest1.getConfirmPassword());
        user.setEmail(userSignupRequest1.getEmail());
        user.setUsername(userSignupRequest1.getUsername());
        user.setCreatedDate(LocalDate.now());
        user.setActive(userSignupRequest1.getActive());
        user.setRoleName(userSignupRequest1.getRoleName());
        return user;
    }

    private void userUpdateValidator(UserSignupRequest userSignupRequest){
        if (userSignupRequest.getEmail() != null) {
            if (!isEmailFormatCorrect(userSignupRequest))
                throw new BadRequestException("User must have an email in the correct format");
            else if (isEmailExist(userSignupRequest.getEmail()))
                throw new AlreadyExistException("This Email belongs to another user");
        }if (userSignupRequest.getPassword() != null || userSignupRequest.getConfirmPassword() != null) {
            if (!isPasswordFormatCorrect(userSignupRequest))
                throw new BadRequestException("User must set a password with at least 8 digits and the same as the confirm password.");
        }if (userSignupRequest.getUsername() != null || userSignupRequest.getRoleName() != null){
            throw new BadRequestException("Username and Role cannot be changed afterwards");}
    }

    private User userUpdater (UserSignupRequest userSignupRequest){

        userUpdateValidator(userSignupRequest);

        User user = getUserById(userSignupRequest.getUserId());
        if (userSignupRequest.getEmail()!=null)user.setEmail(userSignupRequest.getEmail());
        if (userSignupRequest.getPassword()!=null)user.setPassword(userSignupRequest.getPassword());
        if (userSignupRequest.getConfirmPassword()!=null)user.setConfirmPassword(userSignupRequest.getConfirmPassword());
        if (userSignupRequest.getActive()!=null)user.setActive(userSignupRequest.getActive());

        return user;
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
        return  userSignupRequest.getPassword()!= null
                && userSignupRequest.getConfirmPassword()!=null
                && userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword())
                && userSignupRequest.getPassword().length() >= 8;
    }

    private Boolean isEmailFormatCorrect(UserSignupRequest userSignupRequest) {

        return  userSignupRequest.getEmail()!=null
                &&  userSignupRequest.getEmail().contains("@")
                &&  !userSignupRequest.getEmail().contains(" ");
    }

}
