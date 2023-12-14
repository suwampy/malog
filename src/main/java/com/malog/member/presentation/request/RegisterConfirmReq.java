package com.malog.member.presentation.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterConfirmReq(@NotBlank String token, @NotBlank @Email String email) {

}
