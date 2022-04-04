package com.fbc.batchidservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IdCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
//    private boolean match;
    @ManyToOne
    @JoinColumn(
            name = "batch_id",
            referencedColumnName = "batchId"
    )
    private Batch batch;
}
