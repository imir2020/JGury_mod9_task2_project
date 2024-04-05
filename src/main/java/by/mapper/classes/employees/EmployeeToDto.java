package by.mapper.classes.employees;

import by.database.entity.Employee;
import by.dto.employees_dto.EmployeeDto;
import by.mapper.map_interfaces.employee.EmployeeToDtoMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@NoArgsConstructor
public class EmployeeToDto {

    public EmployeeDto mapFrom(Employee employee) {
        log.info("Object of Employee type will be converted to EmployeesDto type");
        return EmployeeToDtoMapper.INSTANCE.employeesToDto(employee);
    }

}
