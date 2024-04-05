package by.service;


import by.database.entity.User;
import by.database.repository.UserRepository;
import by.dto.user_dto.FromDtoToUser;
import by.dto.user_dto.UserDto;
import by.mapper.classes.users.DtoToUser;
import lombok.RequiredArgsConstructor;
import by.mapper.classes.users.UserToDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final DtoToUser dtoToUser;
    private final UserToDto userToDto;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public Optional<UserDto> findById(Long id) {
        log.info("Attempt to extract UserDto object in method findById()");
        return userRepository.findById(id).map(userToDto::mapFrom);
    }

    public Optional<UserDto> login(String password) {
        Optional<UserDto> result = userRepository.findByPassword(password)
                .map(userToDto::mapFrom);
        if (result.isEmpty()) {
            log.warn("The password is not exist!");
        } else {
            log.info("The User with name {} was login", result.get().getName());
        }
        return result;
    }

    @Transactional
    public UserDto save(FromDtoToUser fromDtoToUser) {
        log.info("Attempt to save fromUserDtoToBase object in method save()");
        return Optional.of(fromDtoToUser)
                .map(dto->{
                    uploadImage(dto.getImage());
                    return dtoToUser.mapFrom(dto);
                })
                .map(userRepository::save)
                .map(userToDto::mapFrom)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserDto> update(Long id, FromDtoToUser fromDtoToUser){
        return userRepository.findById(id)
                .map(entity -> {
                    uploadImage(fromDtoToUser.getImage());
                    return dtoToUser.mapFrom(fromDtoToUser);
                })
                .map(userRepository::saveAndFlush)
                .map(userToDto::mapFrom);
    }
    public Optional<byte[]> findAvatar(Long id){
        return userRepository.findById(id)
                .map(User::getImage)
                .filter(StringUtils::hasText)
                .flatMap(imageService::get);
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("Attempt to delete User object by your id, in method delete()");

    }

    public void deleteByPassword(String password) {
        var user = userRepository.findByPassword(password).get();
        userRepository.deleteById(user.getId());
        log.info("Attempt to delete User object by your password, in method delete()");

    }
}
