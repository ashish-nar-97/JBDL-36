package com.example.wallet.ewallet;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {


    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;  // username

    @NotBlank
    private String password;

    // NotBlank : null + length() > 0
    @NotBlank
    private String email;

    @NotNull
    UserIdentifier userIdentifier;

    @NotBlank
    private String identifierValue;

    private String dob;

    private String country;

    public User to(){
        return User.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .country(country)
                .dob(dob)
                .userIdentifier(userIdentifier)
                .identifierValue(identifierValue)
                .build();
    }
}
