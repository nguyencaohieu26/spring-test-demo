package com.example.sping.validation.demo.dto;

import com.example.sping.validation.demo.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotEmpty(message = "Id is mandatory")
    private String Id;

    @NotEmpty(message = "Id is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Phone number is mandatory")
    //@Pattern(regexp = "",message = "Phone number wrong format")
    private String phone;

    @NotNull(message = "Gender is mandatory")
    private User.Gender gender;

    @NotEmpty(message = "Date birth is mandatory")
    @Pattern(regexp = "(((20[012]\\d|19\\d\\d)|(1\\d|2[0123]))-((0[0-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01])))|(((0[1-9])|([12][0-9])|(3[01]))-((0[0-9])|(1[012]))-((20[012]\\d|19\\d\\d)|(1\\d|2[0123])))|(((20[012]\\d|19\\d\\d)|(1\\d|2[0123]))\\/((0[0-9])|(1[012]))\\/((0[1-9])|([12][0-9])|(3[01])))|(((0[0-9])|(1[012]))\\/((0[1-9])|([12][0-9])|(3[01]))\\/((20[012]\\d|19\\d\\d)|(1\\d|2[0123])))|(((0[1-9])|([12][0-9])|(3[01]))\\/((0[0-9])|(1[012]))\\/((20[012]\\d|19\\d\\d)|(1\\d|2[0123])))|(((0[1-9])|([12][0-9])|(3[01]))\\.((0[0-9])|(1[012]))\\.((20[012]\\d|19\\d\\d)|(1\\d|2[0123])))|(((20[012]\\d|19\\d\\d)|(1\\d|2[0123]))\\.((0[0-9])|(1[012]))\\.((0[1-9])|([12][0-9])|(3[01])))",message = "Birth Date wrong format")
    private String dateBirth;
}
