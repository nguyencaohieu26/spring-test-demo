package com.example.sping.validation.demo.controller;

import com.example.sping.validation.demo.dto.UpdateUserRequest;
import com.example.sping.validation.demo.entity.User;
import com.example.sping.validation.demo.response.HTTPResponseObject;
import com.example.sping.validation.demo.response.HTTPResponseObjectBuilder;
import com.example.sping.validation.demo.service.TranslationService;
import com.example.sping.validation.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sping.validation.demo.constants.StringConstant.EMPTY_CHARACTER;
import static com.example.sping.validation.demo.constants.TranslationCode.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    //TODO Write unit test
    //TODO Finish CI/CD workflows
    //TODO How to run test java on Linux or Docker

    private final UserService userService;
    private final TranslationService translationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<HTTPResponseObject> GetUsers() {
        List<User> users = userService.findUsers();
        if (users.size() > 0) {
            return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                    .withMessage(EMPTY_CHARACTER).addData("users", userService.findUsers())
                    .build(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                .withMessage(translationService.getTranslation(NOT_FOUND_RESULT))
                .addData("users", userService.findUsers()).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    public ResponseEntity<HTTPResponseObject> getUser(@RequestParam String useID) {
        if (userService.getUser(useID) != null) {
            return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                    .withMessage(EMPTY_CHARACTER).addData("user",userService.getUser(useID)).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                .withMessage(translationService.getTranslation(NOT_FOUND_USER)).build(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HTTPResponseObject> CreateUser(@RequestBody @Valid User user) {
        userService.create(user);
        return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                .withMessage(translationService.getTranslation(CREATE_SUCCESS)).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<HTTPResponseObject> UpdateUser(@RequestBody @Valid UpdateUserRequest usr) {

        try{
            if (!userService.update(usr)){
                return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                        .withMessage(translationService.getTranslation(NOT_FOUND_USER)).build(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                    .withMessage(translationService.getTranslation(ex.getMessage())).build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                .withMessage(translationService.getTranslation(UPDATE_SUCCESS)).build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<HTTPResponseObject> DeleteUser(@RequestParam String Id) {
        if (!userService.delete(Id)){
            return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                    .withMessage(DELETE_SUCCESS).build(),HttpStatus.OK);
        }

        return new ResponseEntity<>(new HTTPResponseObjectBuilder()
                .withMessage(translationService.getTranslation(NOT_FOUND_USER)).build(),HttpStatus.BAD_REQUEST);
    }
}
