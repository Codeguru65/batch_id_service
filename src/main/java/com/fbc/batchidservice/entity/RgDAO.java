package com.fbc.batchidservice.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RgDAO {

    private String lastRefreshedTime;
    private String dateOfBirth;
    private String sex;
    private String source;
    private String surname;
    private String firstName;
    private String status;
    private String birthPlace;
    private String personNo;
    private String dateOfDeath;


}
