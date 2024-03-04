package com.esisba.tp_auth.API;

import com.esisba.tp_auth.entities.User;
import com.esisba.tp_auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class Controller {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (userRepository.findUserByAddress(user.getAddress()) != null) {
            return "Email address is already in use";
        }else {
            user.setPasswordHash(user.getPassword());
            userRepository.save(user);
            return "User Created Successfully";
        }
    }

    @GetMapping("/get")
    public User getUserByAddressAndPassword(@RequestBody AuthenticationRequest authenticationRequest) {
        String address = authenticationRequest.getAddress();
        String password = authenticationRequest.getPassword();

        User user = userRepository.findUserByAddress(address);

        if (user != null && user.verifyPassword(password)) {
            return user;
        } else {
            return null; // Handle invalid credentials
        }
    }
}