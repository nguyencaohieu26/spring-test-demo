package com.example.sping.validation.demo.service;

import com.example.sping.validation.demo.dto.UpdateUserRequest;
import com.example.sping.validation.demo.entity.User;
import com.example.sping.validation.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import static com.example.sping.validation.demo.constants.TranslationCode.UPDATE_FAIL_EMAIL_EXIST;


@Service
@AllArgsConstructor
public class UserService {

     private final UserRepository userRepository;

    public void create(User user) {
        userRepository.insert(user);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public boolean delete(String id) {
        if (getUser(id)  == null){
            return getUser(id) == null;
        }

        userRepository.deleteById(id);
        return false;
    }

    public boolean update(UpdateUserRequest usr) throws Exception {
        if (userRepository.findById(usr.getId()).isEmpty()){
            return false;
        }
        User usrFound = userRepository.findById(usr.getId()).get();
        if (!usrFound.getEmail().equals(usr.getEmail()) &&  userRepository.findUserByEmail(usr.getEmail()).isPresent()){
            throw new Exception(UPDATE_FAIL_EMAIL_EXIST);
        }
        usrFound.setName(usr.getName());
        usrFound.setPhone(usr.getPhone());
        usrFound.setEmail(usr.getEmail());
        usrFound.setGender(usr.getGender());
        usrFound.setDateBirth(usr.getDateBirth());
        usrFound.setUpdateTime(LocalDateTime.now());
        userRepository.save(usrFound);

        return userRepository.findById(usr.getId()).isPresent();
    }

    public User getUser(String useID) {
        if (userRepository.findById(useID).isPresent()){
            return userRepository.findById(useID).get();
        }

        return null;
    }
}
