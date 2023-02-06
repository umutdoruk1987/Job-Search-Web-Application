package com.umutdoruk.hrms.dto.response;


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

        if (typeOfWork!=null)return new TypeOfWorkResponse(typeOfWork.getId(), typeOfWork.getName());
        else return new TypeOfWorkResponse();
    }
}
