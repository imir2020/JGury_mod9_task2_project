package by.dto.employees_dto;

import by.database.entity.Rank;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;


@Value
@Builder
public class FromDtoToEmployee {
    String lastName;
    String name;
    String middleName;
    LocalDate dateBirth;
    String phoneNumber;
    String address;
    Rank rank;
}
