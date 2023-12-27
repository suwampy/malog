package com.malog.member.presentation.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserLoginReq(
    @NotBlank
    @Email
    String email,

    @NotBlank
    String password
) {

}
