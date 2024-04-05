package by.service;


import by.database.entity.Employee;
import by.database.repository.EmployeeRepository;
import by.dto.employees_dto.EmployeeDto;
import by.dto.employees_dto.FromDtoToEmployee;
import by.mapper.classes.employees.DtoToEmployee;
import by.mapper.classes.employees.EmployeeToDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {
    private final EmployeeRepository employeesRepository;
    private final EmployeeToDto employeeToDto;
    private final DtoToEmployee dtoToEmployee;


    public List<EmployeeDto> findAll() {
        var resultList = employeesRepository.findAll().stream()
                .map(employeeToDto::mapFrom)
                .collect(Collectors.toList());
        log.info("Attempt to extract EmployeeDto collection in method findAll()");
        return resultList;

    }

    public List<EmployeeDto> findAllByOrderByName(Integer page, Integer sizePage) {
        var dynamicSort = Sort.sort(Employee.class);
        var sort = dynamicSort.by(Employee::getId).ascending();
        var pageable = PageRequest.of(page, sizePage, sort);

        var resultList = employeesRepository.findAllByOrderByNameAsc(pageable)
                .stream()
                .map(employeeToDto::mapFrom)
                .collect(Collectors.toList());
        log.info("Attempt to extract EmployeeDto page in method findAllByOrderByName(): {}" + resultList);
        return resultList;
    }

    public List<String> findAllPhoneNumbers(Integer page, Integer sizePage) {
        var dynamicSort = Sort.sort(Employee.class);
        var sort = dynamicSort.by(Employee::getId).ascending();
        var pageable = PageRequest.of(page, sizePage, sort);
        var resultList = employeesRepository.findPhoneNumbersBy(pageable);
        log.info("""
                         Attempt to extract PhoneNumbers collection in the form of page in method findAllByOrderByName(): {}
                         """ + resultList);
        return resultList;

    }

    public Optional<EmployeeDto> findById(Long id) {
        log.info("Attempt to extract EmployeeDto object in method findById()");
        return employeesRepository.findById(id).map(employeeToDto::mapFrom);
    }

    @Transactional
    public EmployeeDto save(FromDtoToEmployee fromDtoToEmployee) {
        log.info("Attempt to save toBaseEmployeeDto object in method save()");
        return Optional.of(fromDtoToEmployee)
                .map(dtoToEmployee::mapFrom)
                .map(employeesRepository::save)
                .map(employeeToDto::mapFrom)
                .orElseThrow();
    }

    @Transactional
    public void updateAddress(String address, Long id) {
        employeesRepository.updateAddress(address, id);
        log.info("Attempt to update address in Employee object by your id, in method updateAddress()");
    }


    @Transactional
    public Optional<EmployeeDto> update(Long id, FromDtoToEmployee fromDtoToEmployee) {
        log.info("Attempt to update  in Employee object by your id, in method update()");
        return employeesRepository.findById(id)
                .map(employee -> dtoToEmployee.mapFrom(fromDtoToEmployee))
                .map(employee -> {
                    employee.setId(id);
                    return employee;
                })
                .map(employeesRepository::saveAndFlush)
                .map(employeeToDto::mapFrom);
    }

    public boolean delete(Long id) {
        log.info("Attempt to delete Employee object in method delete()");
        return employeesRepository.findById(id)
                .map(category -> {
                    employeesRepository.deleteById(category.getId());
                    employeesRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public Optional<String> findPhoneNumberById(Long id) {
        var employee = employeesRepository.findPhoneNumberById(id);
        return Optional.ofNullable(employee);

    }
}
