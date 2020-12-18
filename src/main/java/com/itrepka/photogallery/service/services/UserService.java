package com.itrepka.photogallery.service.services;

import com.itrepka.photogallery.model.Role;
import com.itrepka.photogallery.model.User;
import com.itrepka.photogallery.repository.UserRepository;
import com.itrepka.photogallery.service.dto.CreateGalleryDto;
import com.itrepka.photogallery.service.dto.CreateUserDto;
import com.itrepka.photogallery.service.dto.UpdateUserDto;
import com.itrepka.photogallery.service.dto.UserDto;
import com.itrepka.photogallery.service.exception.GalleryInvalidDataException;
import com.itrepka.photogallery.service.exception.UserAlreadyExistsException;
import com.itrepka.photogallery.service.exception.UserInvalidDataException;
import com.itrepka.photogallery.service.exception.UserNotFoundException;
import com.itrepka.photogallery.service.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDtoMapper userDtoMapper;
    @Autowired
    private GalleryService galleryService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> userDtoMapper.toDto(user))
                .collect(Collectors.toList());
    }

    public UserDto getUserById(int id) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(user -> userDtoMapper.toDto(user))
                .orElseThrow(() -> new UserNotFoundException("Not found user with id = " + id));
    }

    @Transactional
    public UserDto addNewUser(CreateUserDto createUserDto) throws UserInvalidDataException, UserAlreadyExistsException, UserNotFoundException, GalleryInvalidDataException {
        validateCreatingUser(createUserDto);

        User user = userDtoMapper.toModel(createUserDto);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user.setRole(Role.USER);
        user.setCreatedAt(OffsetDateTime.now());
        User savedUser = userRepository.save(user);
        //new gallery for user
        CreateGalleryDto createGalleryDto = new CreateGalleryDto(user.getLogin() + "-gallery", savedUser.getUserId());
        galleryService.addNewGallery(createGalleryDto);

        return userDtoMapper.toDto(savedUser);
    }

    @Transactional
    public UserDto updateUser(int id, UpdateUserDto updateUserDto) throws UserInvalidDataException, UserNotFoundException {
        validateUpdatingUser(updateUserDto);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not found user with id = " + id));

        user.setPassword(updateUserDto.getPassword()); //todo encoder
        user.setUpdatedAt(OffsetDateTime.now());
        User updatedUser = userRepository.save(user);

        return userDtoMapper.toDto(updatedUser);
    }

    @Transactional
    public UserDto deleteUserById(int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not found user with id = " + id));

        userRepository.deleteById(id);

        return userDtoMapper.toDto(user);
    }

    private void validateUpdatingUser(UpdateUserDto updateUserDto) throws UserInvalidDataException {
        if (updateUserDto.getPassword() == null || updateUserDto.getPassword().isEmpty()) {
            throw new UserInvalidDataException("Password may not be empty!");
        }
    }


    private void validateCreatingUser(CreateUserDto createUserDto) throws UserInvalidDataException, UserAlreadyExistsException {
        String password = createUserDto.getPassword();
        String login = createUserDto.getLogin();

        if (login == null || login.isEmpty() || password == null || password.isEmpty()){
            throw new UserInvalidDataException("Invalid login or password");
        }

        if (getAllUsers().stream().anyMatch(userDto -> userDto.getLogin().equalsIgnoreCase(login))){
            throw new UserAlreadyExistsException("Already exists user with login: " + login);
        }
    }
}
