package com.example.sping.validation.demo.entity;

import com.example.sping.validation.demo.utils.annotations.EmailUnique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/** Annotation @Document to set the collection name that will be used by the model. If the collection doesn't exist, MongoDB will create it.
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "users")
public class User {

    public enum Gender{
        MALE,FEMALE
    }

    @Id
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @EmailUnique
    private String email;

    @NotEmpty(message = "Phone number is mandatory")
    //@Pattern(regexp = "",message = "Phone number wrong format")
    private String phone;

    @NotEmpty(message = "Date birth is mandatory")
    @Pattern(regexp = "(((20[012]\\d|19\\d\\d)|(1\\d|2[0123]))-((0[0-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01])))|(((0[1-9])|([12][0-9])|(3[01]))-((0[0-9])|(1[012]))-((20[012]\\d|19\\d\\d)|(1\\d|2[0123])))|(((20[012]\\d|19\\d\\d)|(1\\d|2[0123]))\\/((0[0-9])|(1[012]))\\/((0[1-9])|([12][0-9])|(3[01])))|(((0[0-9])|(1[012]))\\/((0[1-9])|([12][0-9])|(3[01]))\\/((20[012]\\d|19\\d\\d)|(1\\d|2[0123])))|(((0[1-9])|([12][0-9])|(3[01]))\\/((0[0-9])|(1[012]))\\/((20[012]\\d|19\\d\\d)|(1\\d|2[0123])))|(((0[1-9])|([12][0-9])|(3[01]))\\.((0[0-9])|(1[012]))\\.((20[012]\\d|19\\d\\d)|(1\\d|2[0123])))|(((20[012]\\d|19\\d\\d)|(1\\d|2[0123]))\\.((0[0-9])|(1[012]))\\.((0[1-9])|([12][0-9])|(3[01])))",message = "Birth Date wrong format")
    private String dateBirth;

    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime updateTime;
}