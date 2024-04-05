package by.mapper.map_interfaces.supplier;

import by.database.entity.Supplier;
import by.dto.supplier_dto.SupplierDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SupplierToDtoMapper {

    SupplierToDtoMapper INSTANCE = Mappers.getMapper(SupplierToDtoMapper.class);

    SupplierDto mapFrom(Supplier supplier);
}
