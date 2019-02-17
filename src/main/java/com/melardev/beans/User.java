package com.melardev.beans;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    // These validation annotations are available after adding hibernate-validation in pom.xml file
    private long id;
    @Size(min = 5, max = 20, message = "user.name.size")
    private String name;

    @Size(min = 6, max = 15, message = "{user.password.size}")
    @Pattern(regexp = "\\S+", message = "{user.password.pattern}")
    private String password;

    @NotEmpty(message = "{user.email.empty}")
    @Email(message = "{user.email.valid}")
    private String emailAddress;
}
