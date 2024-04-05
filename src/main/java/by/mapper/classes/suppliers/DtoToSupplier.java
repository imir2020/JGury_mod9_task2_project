package by.mapper.classes.suppliers;


import by.database.entity.Supplier;
import by.dto.supplier_dto.FromSupplierDtoToBase;
import by.mapper.map_interfaces.supplier.DtoToSupplierMapper;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DtoToSupplier {

    public Supplier mapFrom(FromSupplierDtoToBase fromSupplierDtoToBase){
        return DtoToSupplierMapper.INSTANCE.mapFrom(fromSupplierDtoToBase);
    }

}
