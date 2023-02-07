package com.umutdoruk.jobSearch.enums;

import com.umutdoruk.jobSearch.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {
    ROLE_CANDIDATE,
    ROLE_EMPLOYER;

    public static Role findByName (String roleName){

        return Arrays.stream(Role.values())
                .filter(role-> role.name().equals(roleName))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Role is not found. Available roles are : "
                        + Arrays.toString(Role.values())));
    }
}

