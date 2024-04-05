package by.dto.supplier_dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FromSupplierDtoToBase {
    String name;
    String address;

    @Email
    String email;
    String phoneNumber;
}
