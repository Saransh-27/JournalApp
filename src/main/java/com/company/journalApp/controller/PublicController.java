package com.company.journalApp.controller;

import com.company.journalApp.dto.LoginDTO;
import com.company.journalApp.dto.SignupDTO;
import com.company.journalApp.dto.UserDTO;
import com.company.journalApp.mapper.UserMapper;
import com.company.journalApp.service.UserDetailsServiceImpl;
import com.company.journalApp.service.UserService;
import com.company.journalApp.utilis.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
@Tag(name = "Public APIs", description = "Create users and login")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(
            summary = "Health check",
            description = "Check project is running or not "
    )
    @GetMapping("/health-check")
    public String healthCheck() {
        log.info("Health is ok !");
        return "Ok";
    }

    @Operation(
            summary = "sign-up",
            description = "Create new user or sign-up"
    )
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDTO dto) {
        userService.saveNewUser(UserMapper.toEntity(
                UserDTO.builder()
                        .userName(dto.getUserName())
                        .password(dto.getPassword())
                        .email(dto.getEmail())
                        .build()
        ));
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }


    @Operation(
            summary = "login",
            description = "Login to get JWT token"
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}