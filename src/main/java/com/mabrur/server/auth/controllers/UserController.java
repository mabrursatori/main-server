package com.mabrur.server.auth.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.mabrur.server.auth.dto.JwtResponse;
import com.mabrur.server.auth.dto.LoginData;
import com.mabrur.server.auth.dto.MessageResponse;
import com.mabrur.server.auth.dto.SignupData;
import com.mabrur.server.auth.dto.UserData;
import com.mabrur.server.auth.entities.User;
import com.mabrur.server.auth.entities.UserRole;
import com.mabrur.server.auth.repos.UserRepo;
import com.mabrur.server.auth.services.UserService;
import com.mabrur.server.config.JwtUtils;
import com.mabrur.server.doa.dto.ResponseData;
import com.mabrur.server.utils.PasswordEncoder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<User>> register(@RequestBody UserData userData) {

        ResponseData<User> response = new ResponseData<>();
        User user = modelMapper.map(userData, User.class);
        response.setPayload(userService.registerUser(user));
        response.setStatus(true);
        response.getMessages().add("App User Saved !");
        return ResponseEntity.ok(response);

    }

    // public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginData loginRequest) {
    //     Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    //     String jwt = jwtUtils.generateJwtToken(authentication);

    //     User userDetails = (User) authentication.getPrincipal();
    //     List<String> roles = userDetails.getAuthorities().stream()
    //             .map(item -> item.getAuthority())
    //             .collect(Collectors.toList());
    //     return ResponseEntity.ok(new JwtResponse(jwt,
    //             userDetails.getId(),
    //             userDetails.getUsername()));
    // }

    // @PostMapping("/signup")
    // public ResponseEntity<?> registerUser(@Valid @RequestBody UserData signUpRequest) {

    //     if (userRepo.existsByUsername(signUpRequest.getUsername())) {
    //         return ResponseEntity
    //                 .badRequest()
    //                 .body(new MessageResponse("Error: Username is already taken!"));
    //     }
    //     // Create new user's account
    //     User user = new User(signUpRequest.getUsername(),
    //             encoder.encode(signUpRequest.getPassword()),
    //             signUpRequest.getRole());

    //     userRepo.save(user);
    //     return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    // }

}
