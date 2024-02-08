package com.example.hexagonalarchitecture.infraestructure.rest.controller;

import com.example.hexagonalarchitecture.application.usecases.UserService;
import com.example.hexagonalarchitecture.domain.model.dto.UserDto;
import com.example.hexagonalarchitecture.domain.model.dto.request.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable long id){
        return userService.getById(id);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PostMapping()
    public UserDto create(@RequestBody UserRequest taskRequest){
        return userService.createNew(taskRequest);
    }

    @PutMapping("/{id}")
    public UserDto userEdit(@RequestBody UserRequest taskRequest,
                               @PathVariable Long id){
        return userService.update(taskRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
    }
}
