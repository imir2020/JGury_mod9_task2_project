package by.dto.product_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;
import org.springframework.validation.annotation.Validated;


@Value
@Builder
@Validated
public class FromProductDtoToBase {

    @PositiveOrZero
    Long count;

    @NotBlank
    @NotNull
    String name;

    @PositiveOrZero
    Long priceForOne;

    @PositiveOrZero
    Long categoryId;

    @PositiveOrZero
    Long supplierId;
}
