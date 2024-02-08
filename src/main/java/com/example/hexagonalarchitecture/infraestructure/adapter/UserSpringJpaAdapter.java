package com.example.hexagonalarchitecture.infraestructure.adapter;

import com.example.hexagonalarchitecture.domain.model.User;
import com.example.hexagonalarchitecture.domain.model.constant.UserConstant;
import com.example.hexagonalarchitecture.domain.port.UserPersistencePort;
import com.example.hexagonalarchitecture.infraestructure.adapter.entity.UserEntity;
import com.example.hexagonalarchitecture.infraestructure.adapter.exception.UserException;
import com.example.hexagonalarchitecture.infraestructure.adapter.mapper.UserDboMapper;
import com.example.hexagonalarchitecture.infraestructure.adapter.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserSpringJpaAdapter implements UserPersistencePort {
    private final UserRepository userRepository;
    private final UserDboMapper userDboMapper;

    public UserSpringJpaAdapter(UserRepository userRepository, UserDboMapper userDboMapper) {
        this.userRepository = userRepository;
        this.userDboMapper = userDboMapper;
    }


    /**
     * aqui se usa AWS DynamoDB
     * @param user
     * @return
     */
    @Override
    public User create(User user) {

        var userToSave = userDboMapper.toDbo(user);
        var userSaved = userRepository.save(userToSave);

        return userDboMapper.toDomain(userSaved);
    }

    @Override
    public User getById(Long id) {

        var optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new UserException(HttpStatus.NOT_FOUND,
                    String.format(UserConstant.TASK_NOT_FOUND_MESSAGE_ERROR, id));
        }

        return userDboMapper.toDomain(optionalUser.get());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userDboMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user, Long id) {
        var userToUpdate = findAndEnsureExists(id);
        userToUpdate.setId(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setCountry(user.getCountry());
        userToUpdate.setUpdatedAt(LocalDateTime.now());
//        var userToUpdate = userDboMapper.toDbo(user);
        var userUpdated = userRepository.save(userToUpdate);

        return userDboMapper.toDomain(userUpdated);
    }


    private UserEntity findAndEnsureExists(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

}
