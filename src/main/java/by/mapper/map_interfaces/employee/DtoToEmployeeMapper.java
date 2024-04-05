package by.mapper.map_interfaces.employee;

import by.database.entity.Employee;
import by.dto.employees_dto.FromDtoToEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToEmployeeMapper {
    DtoToEmployeeMapper INSTANCE = Mappers.getMapper(DtoToEmployeeMapper.class);

    Employee fromDto(FromDtoToEmployee employeeDto);
}
