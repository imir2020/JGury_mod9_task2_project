package by.mapper.classes.suppliers;


import by.database.entity.Supplier;
import by.dto.supplier_dto.SupplierDto;
import by.mapper.map_interfaces.supplier.SupplierToDtoMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@NoArgsConstructor
public class SupplierToDto {
    public SupplierDto mapFrom(Supplier supplier){
        log.info("Object of Supplier type will be converted to SupplierDto type");
        return SupplierToDtoMapper.INSTANCE.mapFrom(supplier);
    }

}
