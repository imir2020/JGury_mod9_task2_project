package database.integration.service;

import annotation.IT;
import by.database.entity.Rank;
import by.database.repository.RankRepository;
import by.dto.employees_dto.FromDtoToEmployee;
import by.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class EmployeeServiceIT {
    private final EmployeeService employeeService;
    private final RankRepository rankRepository;

    @Test
    public void findAll() {
        var actualResultList = employeeService.findAll();
        assertTrue(actualResultList.size() > 0);
        actualResultList.forEach(System.out::println);
    }

    @Test
    public void findAllByOrderByNameAsc(){
        Integer page = 0;
        Integer sizePage = 2;
        var actualResultList = employeeService.findAllByOrderByName(0,2);
        assertTrue(actualResultList.size() > 0);
        actualResultList.forEach(System.out::println);
    }
    @Test
    public void findPhoneNumbersByOrderByPhoneNumberAsc(){
        Integer page = 1;
        Integer sizePage = 4;
        var actualResultList = employeeService.findAllPhoneNumbers(page,sizePage);
        assertTrue(actualResultList.size() > 0);
        actualResultList.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 2L;
        var actual = employeeService.findById(id);
        assertTrue(actual.isPresent());
    }

    @Test
    public void save() {
        Rank rank = rankRepository.findById(2L).get();
        FromDtoToEmployee employee = FromDtoToEmployee.builder()
                .lastName("Artamonov")
                .name("Alex")
                .middleName("Sadyikov")
                .dateBirth(LocalDate.parse("1980-03-29"))
                .phoneNumber("8-654-333-98-11")
                .address("Torsk, veteranov 54,dom 45,corp 11, kv 67")
                .rank(rank)
                .build();
        employeeService.save(employee);
    }

    @Test
    public void updateAddress() {
        Long id = 2L;
        String address = "Torsk, veteranov 3421,dom 45,corp 11, kv 67";
        employeeService.updateAddress(address, id);
    }

    @Test
    public void delete() {
//        Long id = 1L;
//        employeeService.delete(id);
//
//       var expected = employeeService.findById(id);
//        assertTrue(expected.isEmpty());
    }

    @Test
    public void findPhoneNumberById(){
        Long id = 5L;
       var phoneNumber =  employeeService.findPhoneNumberById(id);
       assertTrue(phoneNumber.isPresent());
       assertNotEquals(phoneNumber.get(),"");
    }
}
