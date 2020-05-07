package com.example.demo.controller;

import com.example.demo.model.UsrToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.config.JwtToken;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.repository.UsrTokenRepository;
import com.example.demo.service.JwtUserDetailsService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthorizationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsrTokenRepository usrTokenRepository;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = JwtResponse.class)))
    })

    @PostMapping("/authorization")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        val userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        val token = jwtToken.generateToken(userDetails);
        val userId = userRepository.findByUsername(authenticationRequest.getUsername()).id;
        usrTokenRepository.save(new UsrToken(userId, token));
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/checktoken")
    public String endpoint()
    {
        String checkToken = "OK";
        return checkToken;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
