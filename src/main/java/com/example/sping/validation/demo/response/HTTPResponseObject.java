package com.example.sping.validation.demo.response;

import lombok.*;

import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HTTPResponseObject {
    private String message;

    private HashMap<String,Object> body;

}


