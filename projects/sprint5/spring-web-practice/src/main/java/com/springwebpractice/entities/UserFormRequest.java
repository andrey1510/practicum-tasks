package com.springwebpractice.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public class UserFormRequest {

    @Length(min = 2)
    private String name;

    @Min(value = 1)
    @Max(value = 100)
    private int age;

}
