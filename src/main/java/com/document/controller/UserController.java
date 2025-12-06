package com.document.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.document.dto.AuthRequest;
import com.document.dto.UserDto;
import com.document.service.JwtService;
import com.document.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserServiceImpl userService,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String authenticationAndToken(@RequestBody AuthRequest auth) throws AuthenticationException {
    	Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
    	if(authentication.isAuthenticated()) return jwtService.generateToken(auth.getEmail());
    	else {
    		throw new UsernameNotFoundException("Not found");
    	}
    	
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto saved = userService.registerUser(userDto);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateUserProfile(@RequestParam Long userId,
                                                     @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUserProfile(userId, userDto));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/matches")
    public ResponseEntity<List<UserDto>> findMatches(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.findMatches(userId));
    }
}
