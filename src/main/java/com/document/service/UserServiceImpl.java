package com.document.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.document.dto.UserDto;
import com.document.entity.User;
import com.document.repository.UserRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // used by Spring Security
   
   

   // @Override
    public UserDto registerUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setGender(userDto.getGender());
        user.setDob(userDto.getDob());
        user.setRoles(userDto.getRoles() == null ? "ROLE_USER" : userDto.getRoles());

        User saved = userRepository.save(user);
        return toDto(saved);
    }

  //  @Override
    public UserDto authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UsernameNotFoundException("invalid user request !");
        }
        return toDto(user);
    }

  //  @Override
    public UserDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        return toDto(user);
    }

 //   @Override
    public UserDto updateUserProfile(Long userId, UserDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setName(dto.getName());
        user.setDob(dto.getDob());
        user.setGender(dto.getGender());
        // email & password update can be added if allowed

        User updated = userRepository.save(user);
        return toDto(updated);
    }

  //  @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

  //  @Override
    public List<UserDto> findMatches(Long userId) {
        User baseUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        // simple matching: opposite gender / different id
        return userRepository.findAll().stream()
                .filter(u -> !u.getId().equals(userId))
                .filter(u -> u.getGender().equalsIgnoreCase(baseUser.getGender()) == false)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setGender(user.getGender());
        dto.setDob(user.getDob());
        dto.setRoles(user.getRoles());
        // do not set password
        return dto;
    }
}
