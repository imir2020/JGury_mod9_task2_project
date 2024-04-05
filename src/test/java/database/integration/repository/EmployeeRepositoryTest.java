package database.integration.repository;


import annotation.IT;
import by.database.entity.Employee;
import by.database.entity.Rank;
import by.database.repository.EmployeeRepository;
import by.database.repository.Greid;
import by.database.repository.RankRepository;
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
public class EmployeeRepositoryTest {
    Logger log = LoggerFactory.getLogger(EmployeeRepositoryTest.class);

    private final EmployeeRepository employeesRepository;
    private final RankRepository ranksRepository;


    @Test
    public void findAll() {
        List<Employee> employees = employeesRepository.findAllBy();
        assertTrue(employees.size() > 0);
        employees.forEach(System.out::println);

        log.info("Result list from method findAll(): {}", employees);
    }

    @Test
    public void findById() {
        Long id = 6L;
        Optional<Employee> employee = employeesRepository.findById(id);
        assertTrue(employee.isPresent());
        log.info("Object employees from method findById(): {}", employee.get());
    }

    @Test
    public void save() {
        Long id = 2L;
        Rank rank = ranksRepository.findById(id).get();
        Employee employee = Employee.builder()
                .lastName("Artamonov")
                .name("Alex")
                .middleName("Sadyikov")
                .dateBirth(LocalDate.parse("1980-03-29"))
                .phoneNumber("8-654-333-98-11")
                .address("Torsk, veteranov 54,dom 45,corp 11, kv 67")
                .build();
        employee.setRanks(rank);
        employeesRepository.save(employee);
        log.info("Object from method save() is saved: {}", employee);
    }

    @Test
    public void update() {
        Long id = 8L;
        String newAddress = "Astana, veteranov 453,dom 45,corp 2, kv 23";
        employeesRepository.updateAddress(newAddress, id);
        Employee updateEmployee = employeesRepository.findById(id).get();
        assertEquals(newAddress, updateEmployee.getAddress());
        log.info("Object address from method update() is updated: {}", newAddress);
    }

    @Test
    public void delete() {
        Long id = 11L;
        employeesRepository.deleteById(id);
        Optional<Employee> employee = employeesRepository.findById(id);
        assertTrue(employee.isEmpty());

        log.warn("Object was deleted in method delete()");
    }

    /**
     * Вывести всех работников без учёта менеджеров.
     * Отсортировать по дню рождения
     */
    @Test
    public void findAllEmployeesLessManagers() {
//        List<Employee> list = employeesRepository.findAllEmployeesLessManagers(Greid.MANAGER);
//        list.forEach(System.out::println);
//        assertThat(list).hasSize(7);
    }

    /**
     * Найти телефон работника по id
     */
    @Test
    public void findEmployeesPhoneNumberById() {
        Long employeeId = 2L;

        var phoneNumber = employeesRepository.findPhoneNumberById(employeeId);
        assertNotNull(phoneNumber);
        System.out.println(phoneNumber);
    }
}
