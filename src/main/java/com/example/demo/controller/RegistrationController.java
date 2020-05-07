package com.example.demo.controller;

import com.example.demo.exceptions.ValidationException;
import com.example.demo.model.Registration;
import com.example.demo.repository.MainRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
public class RegistrationController {
    @Autowired
    private MainRepository mainRepository;

    public RegistrationController(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = Registration.class)))
    })
    @PostMapping("/registration")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        if (mainRepository.existsByUsername(username)){
            throw new ValidationException("Username already existed");
        }
        String full_name = body.get("full_name");
        String email = body.get("email");
        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        mainRepository.save(new Registration(username, encodedPassword, full_name, email));
        return true;
    }
}