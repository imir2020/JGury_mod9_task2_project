package by.mapper.map_interfaces.employee;

import by.database.entity.Employee;
import by.dto.employees_dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeToDtoMapper {
    EmployeeToDtoMapper INSTANCE = Mappers.getMapper(EmployeeToDtoMapper.class);

    EmployeeDto employeesToDto(Employee employee);
}
