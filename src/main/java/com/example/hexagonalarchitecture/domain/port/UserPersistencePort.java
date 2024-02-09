package com.example.hexagonalarchitecture.domain.port;


import com.example.hexagonalarchitecture.domain.model.User;

import java.util.List;

public interface UserPersistencePort {

    User create(User user);
    User getById(Long id);
    User getByEmail(String email);
    List<User> getAll();
    void deleteById(Long id);
    String verify(String code);
    User update(User user, Long id);
}
