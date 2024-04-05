package database.integration.repository;

import annotation.IT;
import by.database.entity.UserStatus;
import by.database.entity.User;
import by.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@IT
@RequiredArgsConstructor
public class UserRepositoryTest {

    Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);
    private final UserRepository userRepository;


    @Test
    public void findAll() {
        List<User> list = userRepository.findAllBy();
        assertTrue(list.size() > 0);
        list.forEach(System.out::println);
        log.info("List of users find in method findAll(): {}", list);

    }

    @Test
    public void findById() {
        Long userId = 1L;
        Optional<User> user = userRepository.findById(userId);
        assertTrue(user.isPresent());
        log.info("Object  was find in method findById(): {}", user);
    }

    @Test
    public void save() {
        User user = User.builder()
                .name("Vadim")
                .birthday(LocalDate.parse("1984-08-12"))
                .password("344")
                .status(UserStatus.ADMIN)
                .build();
        User userAfterSave = userRepository.save(user);
        assertEquals(user, userAfterSave);
        log.info("Object was saved in method save(): {}", user);
    }

    @Test
    public void update() {
        Long userId = 1L;
        UserStatus updateStatus = UserStatus.ADMIN;
        userRepository.updateStatus(updateStatus, userId);

        User userAfterUpdate = userRepository.findById(userId).get();
        assertEquals(updateStatus, userAfterUpdate.getStatus());
        log.info("Status of user was updated in method update(): {}", updateStatus);

    }

    @Test
    public void delete() {
        Long userId = 2L;
        userRepository.deleteById(userId);

        Optional<User> user = userRepository.findById(userId);
        assertTrue(user.isEmpty());
    }

    @Test
    public void findByPassword() {
        String password = "111";
        User user = userRepository.findByPassword(password).get();
        assertNotNull(user);
        log.info("Object was find by your password in method findByPassword(): {}", user);
    }

    /**
     * Вывести всех пользователей с заданным статусом
     */
    @Test
    public void findUsersWithChooseStatus() {
//        List<User> users = userRepository.findByStatus(UserStatus.ADMIN);
//        assertThat(users).hasSize(4);
//        users.forEach(System.out::println);
    }

    /**
     * Вывести всех пользователей отсортированных по фамилии
     */
    @Test
    public void findAllUsersSortedByName() {
//        List<User> users = userRepository.findUsersByNameOrderByName();
//        assertThat(users).hasSize(5);
//        users.forEach(System.out::println);
    }

    /**
     * Вывести всех пользователей с датой рождения меньше указанной
     * использовать пагинацию
     */
    @Test
    public void findAllUsersByBirthday() {
//        List<User> users = userRepository
//                .findAllUsersByBirthdayLessThanOrderByBirthdayDesc(LocalDate.parse("1995-01-11"));
//        assertThat(users).hasSize(3);
//        users.forEach(System.out::println);
    }
}
