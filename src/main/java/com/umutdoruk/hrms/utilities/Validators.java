package com.umutdoruk.hrms.utilities;

import com.umutdoruk.hrms.entities.Candidate;
import com.umutdoruk.hrms.entities.Employer;
import com.umutdoruk.hrms.entities.User;

import java.util.Random;

public class Validators {

    public static boolean candidateValidator(Candidate candidate) {

            return userValidator(candidate)
                && !candidate.getFirstName().isEmpty()
                && !candidate.getLastName().isEmpty()
                && candidate.getTelephoneNumber().length() == 11;
    }

    public static boolean employerValidator(Employer employer){

        return userValidator(employer)
                && !employer.getWebsite().isEmpty()
                && !employer.getCompanyName().isEmpty()
                && employer.getCompanyTelephoneNumber().length()==11;
    }

    public static boolean userValidator(User user) {
        return
                user.getEmail().contains("@")
                && !user.getEmail().contains(" ")
                && user.getPassword().length() >= 8
                && user.getPassword().equals(user.getConfirmPassword());
    }

    public static String createUsernameIfNoPresent(User user) {
        String[] temp = user.getEmail().split("@");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(temp[0]);

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }


}
