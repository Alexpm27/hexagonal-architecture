package com.example.hexagonalarchitecture.application.usecases;


import com.example.hexagonalarchitecture.domain.model.dto.UserDto;
import com.example.hexagonalarchitecture.domain.model.dto.request.UserRequest;

import java.util.List;

public interface UserService {
    UserDto createNew(UserRequest request);
    UserDto getById(Long id);
    List<UserDto> getAll();
    void deleteById(Long id);
    UserDto update(UserRequest request, Long id);
}
