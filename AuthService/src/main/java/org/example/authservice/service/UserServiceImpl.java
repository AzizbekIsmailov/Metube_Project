package org.example.authservice.service;

import lombok.RequiredArgsConstructor;
import org.example.authservice.domain.dto.request.LoginDTO;
import org.example.authservice.domain.dto.request.UserRequest;
import org.example.authservice.domain.dto.response.JwtResponse;
import org.example.authservice.domain.dto.response.UserResponse;
import org.example.authservice.domain.entity.UserEntity;
import org.example.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse save(UserRequest userRequest) {
        UserEntity user = getUser(userRequest);

        userRepository.save(user);

        return getResponse(user);
    }

    private static UserResponse getResponse(UserEntity user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    private static UserEntity getUser(UserRequest userRequest) {
        UserEntity user = UserEntity.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .picturePath(userRequest.getPicturePath())
                .build();
        return user;
    }

    @Override
    public UserResponse update(UUID id, UserRequest userRequest) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(UUID id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        return getResponse(user);
    }

    @Override
    public JwtResponse login(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User with username " + loginDTO.getUsername() + " not found"));
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getUsername())){
            throw new RuntimeException("Incorrect password");
        }
        return new JwtResponse(jwtService.generateToken(user), jwtService.generateRefreshToken(user));
    }
}
