package by.database.repository;

import by.database.entity.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllBy();

    List<Employee> findAllByOrderByNameAsc(PageRequest pageRequest);

    @Query("""
            select e.phoneNumber from Employee e 
            """)
    List<String> findPhoneNumbersBy(PageRequest pageRequest);

    Optional<Employee> findById(Long id);

    Employee save(Employee employee);

    Employee saveAndFlush(Employee employee);

    @Modifying
    @Query("""
            update Employee e 
            set e.address = :employeeAddress
            where e.id = :id
            """)
    void updateAddress(String employeeAddress, Long id);

    void deleteById(Long id);

    @Query("""
            select e from Employee e
            where not e.rank.rankName = :greid
            order by e.dateBirth
            """)
    List<Employee> findAllEmployeesLessManagers(Greid greid);

    @Query("""
            select e.phoneNumber from Employee e
            where e.id = :id
            """)
    String findPhoneNumberById(Long id);


}

