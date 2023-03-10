package com.umutdoruk.jobSearch.service.user;

import com.umutdoruk.jobSearch.dto.candidate.CandidateRequest;
import com.umutdoruk.jobSearch.dto.employer.EmployerRequest;
import com.umutdoruk.jobSearch.dto.user.UserSignInRequest;
import com.umutdoruk.jobSearch.dto.user.UserSignupRequest;
import com.umutdoruk.jobSearch.dto.user.UserSignInResponse;
import com.umutdoruk.jobSearch.dto.user.UserSignupResponse;
import com.umutdoruk.jobSearch.entities.user.User;
import com.umutdoruk.jobSearch.enums.Role;
import com.umutdoruk.jobSearch.exception.BadRequestException;
import com.umutdoruk.jobSearch.exception.NotFoundException;
import com.umutdoruk.jobSearch.exception.AlreadyExistException;
import com.umutdoruk.jobSearch.repository.user.UserRepository;
import com.umutdoruk.jobSearch.security.utils.JwtUtils;
import com.umutdoruk.jobSearch.service.candidate.CandidateService;
import com.umutdoruk.jobSearch.service.candidate.ResumeService;
import com.umutdoruk.jobSearch.service.employer.EmployerService;
import com.umutdoruk.jobSearch.service.employer.JobAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public UserSignInResponse login(UserSignInRequest userSignInRequest) {

        User user = userRepository.findByUsername(userSignInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User is Not Found"));

        UserSignInResponse userSignInResponse = UserSignInResponse.of(user, null);
        userSignInResponse.setToken(jwtUtils.generateToken(user));

        return userSignInResponse;
    }

    @Override
    public UserSignupResponse create(UserSignupRequest userSignupRequest) {
        User user = userCreator(userSignupRequest);
        userRepository.save(user);
        createRoleWithRoleName(userSignupRequest);
        return UserSignupResponse.of(userRepository.findByUsername(user.getUsername()).get());
    }

    @Override
    public UserSignupResponse update(UserSignupRequest userSignupRequest) {
        User user = userUpdater(userSignupRequest);
        userRepository.save(user);
        return UserSignupResponse.of(userRepository.findByUsername(user.getUsername()).get());
    }

    @Override
    public void delete(/*Long id*/) {
        User user = userRepository.findByUsername(getUsernameFromAuthentication()).get();
        if (user.getRole().equals(Role.ROLE_CANDIDATE)) {
            candidateService.delete();
            userRepository.deleteById(user.getId());
        }
        if (user.getRole().equals(Role.ROLE_EMPLOYER)){
            employerService.delete();
            userRepository.deleteById(user.getId());
        }
    }

    @Override
    public User getUserById (Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User is not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new NotFoundException("User is not found"));
    }


    private UserSignupRequest userCreateValidator(UserSignupRequest userSignupRequest){
        if (userSignupRequest == null)
            throw new NotFoundException("No User record found to create");
        if (!isEmailFormatCorrect(userSignupRequest))
            throw new BadRequestException("User must have an email in the correct format");
        if (!isPasswordFormatCorrect(userSignupRequest))
            throw new BadRequestException("User must set a password with at least 8 digits and the same as the confirm password.");
        if (isEmailExist(userSignupRequest.getEmail()))
            throw new AlreadyExistException("This Email belongs to another user");
        if (userSignupRequest.getUsername() == null)
            userSignupRequest.setUsername(createUsernameIfNoPresent(userSignupRequest));
        if (userSignupRequest.getUsername().contains(" "))
            throw new BadRequestException("Username cannot contain spaces");
        if (isUsernameExist(userSignupRequest.getUsername().toLowerCase()))
            throw new AlreadyExistException("Username is already exist");
        if (userSignupRequest.getActive() == null)
            userSignupRequest.setActive(true);
        if (userSignupRequest.getRole()==null)
            userSignupRequest.setRole("ROLE_CANDIDATE");

        return userSignupRequest;
    }

    private User userCreator (UserSignupRequest userSignupRequest) {
        UserSignupRequest userSignupRequest1 = userCreateValidator(userSignupRequest);
        User user = new User();
        user.setPassword(encoder.encode(userSignupRequest1.getPassword()));
        user.setConfirmPassword(encoder.encode(userSignupRequest1.getConfirmPassword()));
        user.setEmail(userSignupRequest1.getEmail());
        user.setUsername(userSignupRequest1.getUsername().toLowerCase());
        user.setCreatedDate(LocalDate.now());
        user.setActive(userSignupRequest1.getActive());
        user.setRole(Role.findByName(userSignupRequest1.getRole()));
        return user;
    }

    private void userUpdateValidator(UserSignupRequest userSignupRequest){
        if (userSignupRequest.getEmail() != null) {
            if (!isEmailFormatCorrect(userSignupRequest))
                throw new BadRequestException("User must have an email in the correct format");
            if (isEmailExist(userSignupRequest.getEmail()))
                throw new AlreadyExistException("This Email belongs to another user");
        }if (userSignupRequest.getPassword() != null || userSignupRequest.getConfirmPassword() != null) {
            if (!isPasswordFormatCorrect(userSignupRequest))
                throw new BadRequestException("User must set a password with at least 8 digits and the same as the confirm password.");
        }if (userSignupRequest.getUsername() != null || userSignupRequest.getRole() != null){
            throw new BadRequestException("Username and Role cannot be changed afterwards");}
    }

    private User userUpdater (UserSignupRequest userSignupRequest){

        userUpdateValidator(userSignupRequest);

        User user = getUserByUsername(getUsernameFromAuthentication());
        //User user = getUserById(userSignupRequest.getUserId());
        if (userSignupRequest.getEmail()!=null)user.setEmail(userSignupRequest.getEmail());
        if (userSignupRequest.getPassword()!=null)user.setPassword(encoder.encode(userSignupRequest.getPassword()));
        if (userSignupRequest.getConfirmPassword()!=null)user.setConfirmPassword(encoder.encode(userSignupRequest.getConfirmPassword()));
        if (userSignupRequest.getActive()!=null)user.setActive(userSignupRequest.getActive());

        return user;
    }

    private void createRoleWithRoleName(UserSignupRequest userSignupRequest){
        User user = userRepository.findByEmail(userSignupRequest.getEmail()).get();
        CandidateRequest candidateRequest = null;
        EmployerRequest employerRequest = null;

        if (userSignupRequest.getRole().equals(Role.findByName("ROLE_CANDIDATE").toString())){
            candidateRequest = new CandidateRequest();
            candidateRequest.setUserId(user.getId());
            candidateService.create(candidateRequest);
        }
        if (userSignupRequest.getRole().equals(Role.findByName("ROLE_EMPLOYER").toString())){
            employerRequest= new EmployerRequest();
            employerRequest.setUserId(user.getId());
            employerService.create(employerRequest);
        }
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

        int id = new Random().nextInt(99999);

        NumberFormat formatter = new DecimalFormat("00000");
        String number = formatter.format(id);

        return temp[0] + number;
    }

    private Boolean isPasswordFormatCorrect(UserSignupRequest userSignupRequest){
        return  userSignupRequest.getPassword()!= null
                && userSignupRequest.getConfirmPassword()!=null
                && userSignupRequest.getPassword().equals(userSignupRequest.getConfirmPassword())
                && userSignupRequest.getPassword().length() >= 8
                && !userSignupRequest.getPassword().contains(" ");
    }

    private Boolean isEmailFormatCorrect(UserSignupRequest userSignupRequest) {

        return  userSignupRequest.getEmail()!=null
                &&  userSignupRequest.getEmail().contains("@")
                &&  !userSignupRequest.getEmail().contains(" ");
    }

    public static String getUsernameFromAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User is Not Found"));

        // ['ROLE_CANDIDATE', 'ROLE_EMPLOYER']
        String roleName = user.getRole().name();

        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        grantList.add(authority);

        return new org.springframework.security.core.userdetails.User( user.getUsername(),
                user.getPassword(),
                grantList);
    }

     /*@Override
    public UserSignupResponse getUserResponseById(Long id) {
        return UserSignupResponse.of(getUserById(id));
    }

    @Override
    public UserSignupResponse getUserResponseByEmail(String email) {
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User is not found"));

        return UserSignupResponse.of(user);
    }*/
}
