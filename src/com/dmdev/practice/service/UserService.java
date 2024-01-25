package com.dmdev.practice.service;

import com.dmdev.practice.dao.UserDao;
import com.dmdev.practice.dto.CreateUserDto;
import com.dmdev.practice.dto.UserDto;
import com.dmdev.practice.exception.ValidationException;
import com.dmdev.practice.mapper.CreateUserMapper;
import com.dmdev.practice.mapper.UserMapper;
import com.dmdev.practice.validator.CreateUserValidator;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator userValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        var validationResult = userValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);

        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);
        return userEntity.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
