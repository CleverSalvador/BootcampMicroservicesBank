package com.clientservice.app.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class NaturalPerson {

    private String firstName;
    private String lastName;
    private Date dateBirth;
    
}
