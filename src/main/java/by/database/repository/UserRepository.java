package by.database.repository;

import by.database.entity.UserStatus;
import by.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllBy();

    Optional<User> findUserById(Long id);

    User save(User user);

    @Modifying
    @Query("""
            update User u
            set u.status = :status
            where u.id = :id
            """)
    void updateStatus(UserStatus status, Long id);

    void deleteById(Long id);

    /**
     * Найти пользователя по паролю
     */

    Optional<User> findByPassword(String password);

    /**
     * Вывести всех пользователей с заданным статусом
     */
    @Query("""
            select u from User u 
            where u.status =:status
            """)
    List<User> findByStatus(UserStatus status);

    /**
     * Вывести всех пользователей отсортированных по имени
     */
    @Query("""
            select u from User u 
            order by u.name
            """)
    List<User> findUsersByNameOrderByName();

    /**
     * Вывести всех пользователей с датой рождения меньше указанной,
     * и отсортированные по убыванию дат рождения.
     */

    @Query("""
            select u from User u 
            where u.birthday <: birthday
            order by u.birthday desc 
            """)
    List<User> findAllUsersByBirthdayLessThanOrderByBirthdayDesc(LocalDate birthDay);
}
