package com.melardev.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.internal.constraintvalidators.bv.MaxValidatorForCharSequence;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForCharSequence;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.converter.HttpMessageConverter;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class User {

    private int id;

    @NotNull(message = "do you have a first name? I think so ...")
    // does not make sense to use it for Strings @NotEmpty instead
//    @Pattern(regexp = "\\w+")
    @Size(min = 2, max = 80)
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "\\w+")
    @Size(min = 2, max = 80)
    private String lastName;

    @AssertTrue(message = "You must agree with the terms of service to proceed")
    private boolean acceptTerms;

    @Email(message = "your email is invalid")
    @NotNull(message = "The email is a required field")
    private String email;

    @NotNull
    @Past(message = "Must be a valid date")
    //@DateTimeFormat(pattern ="dd->MM->yyyy")
    private Date birthDate;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }


}
