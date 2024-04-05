package by.dto.supplier_dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SupplierDto {
    Long id;
    String name;
    String address;
    String email;
    String phoneNumber;
}
