package by.database.repository;

import by.database.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findAllBy();

    Optional<Supplier> findById(Long id);

    Supplier save(Supplier supplier);

    Supplier saveAndFlush(Supplier supplier);


    @Modifying
    @Query("""
            update Supplier s
            set s.email = :email
            where s.id = :id
            """)
    void updateEmail(String email, Long id);

    void deleteById(Long id);

    /**
     * Найти и вывести отсортированный список всех телефонных номеров
     */
    @Query("""
            select s.phoneNumber from Supplier s 
            order by s.phoneNumber
            """)
    List<String> listPhoneNumbers();

    /**
     * Вывести все отсортированные email и телефонные номера
     */
    @Query("""
            select s.email,s.phoneNumber from Supplier s
            order by s.email asc
            """)
    List<Object[]> listEmailAndPhoneNumber();
}
