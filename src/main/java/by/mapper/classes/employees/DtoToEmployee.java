package by.mapper.classes.employees;


import by.database.entity.Employee;
import by.dto.employees_dto.FromDtoToEmployee;
import by.mapper.map_interfaces.employee.DtoToEmployeeMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@NoArgsConstructor
public class DtoToEmployee {

    public Employee mapFrom(FromDtoToEmployee employeesDto) {
        log.info("Object of EmployeesDto type will be converted to Employee type");
        return DtoToEmployeeMapper.INSTANCE.fromDto(employeesDto);
    }
}
