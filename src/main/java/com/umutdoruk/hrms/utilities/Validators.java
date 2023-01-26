package com.umutdoruk.hrms.utilities;

import com.umutdoruk.hrms.DTO.request.CandidateRequest;
import com.umutdoruk.hrms.DTO.request.EmployerRequest;
import com.umutdoruk.hrms.DTO.request.UserRequest;

public class Validators {

    public static boolean candidateValidator(CandidateRequest candidateRequest) {
            return !candidateRequest.getFirstName().isEmpty()
                && !candidateRequest.getLastName().isEmpty()
                && candidateRequest.getTelephoneNumber().length() == 11;
    }

    public static boolean employerValidator(EmployerRequest employerRequest){
        return  !employerRequest.getWebsite().isEmpty()
                && !employerRequest.getCompanyName().isEmpty()
                && employerRequest.getCompanyTelephoneNumber().length()==11;
    }

    public static boolean userValidator(UserRequest userRequest) {
        return  userRequest.getEmail().contains("@")
                && !userRequest.getEmail().contains(" ")
                && userRequest.getPassword().length() >= 8
                && userRequest.getPassword().equals(userRequest.getConfirmPassword());
    }

}
