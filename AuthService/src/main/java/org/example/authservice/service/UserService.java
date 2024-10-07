package org.example.authservice.service;

import org.example.authservice.domain.dto.request.LoginDTO;
import org.example.authservice.domain.dto.request.UserRequest;
import org.example.authservice.domain.dto.response.JwtResponse;
import org.example.authservice.domain.dto.response.UserResponse;

import java.util.UUID;

public interface UserService {

     UserResponse save(UserRequest userRequest);
     UserResponse update(UUID id, UserRequest userRequest);
     void delete(UUID id);
     UserResponse findById(UUID id);
     JwtResponse login(LoginDTO loginDTO);

}


