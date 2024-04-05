package by.mapper.map_interfaces.employee;

import by.database.entity.Employee;
import by.dto.employees_dto.EmployeeDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T19:10:53+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class EmployeeToDtoMapperImpl implements EmployeeToDtoMapper {

    @Override
    public EmployeeDto employeesToDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto.EmployeeDtoBuilder employeeDto = EmployeeDto.builder();

        employeeDto.id( employee.getId() );
        employeeDto.lastName( employee.getLastName() );
        employeeDto.name( employee.getName() );
        employeeDto.middleName( employee.getMiddleName() );
        employeeDto.dateBirth( employee.getDateBirth() );
        employeeDto.phoneNumber( employee.getPhoneNumber() );
        employeeDto.address( employee.getAddress() );
        employeeDto.rank( employee.getRank() );

        return employeeDto.build();
    }
}
