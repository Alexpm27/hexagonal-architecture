package com.example.hexagonalarchitecture.domain.port;


import com.example.hexagonalarchitecture.domain.model.User;

import java.util.List;

public interface UserPersistencePort {

    User create(User user);
    User getById(Long id);
    List<User> getAll();
    void deleteById(Long id);
    User update(User user, Long id);

}
