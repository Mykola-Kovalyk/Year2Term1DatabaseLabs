package com.pavelchak.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    private Integer id;
    private String surname;
    private String name;
    private String city;
    private String email;
}
