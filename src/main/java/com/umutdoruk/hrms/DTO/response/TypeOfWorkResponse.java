package com.umutdoruk.hrms.DTO.response;


import com.umutdoruk.hrms.entities.TypeOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOfWorkResponse {

    private Long technologyId;
    private String technologyName;

    public static TypeOfWorkResponse of(TypeOfWork typeOfWork){
        return new TypeOfWorkResponse(typeOfWork.getId(), typeOfWork.getName());
    }
}
