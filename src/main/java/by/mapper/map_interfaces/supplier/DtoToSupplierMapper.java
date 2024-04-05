package by.mapper.map_interfaces.supplier;


import by.database.entity.Supplier;
import by.dto.supplier_dto.FromSupplierDtoToBase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToSupplierMapper {

    DtoToSupplierMapper INSTANCE = Mappers.getMapper(DtoToSupplierMapper.class);
    Supplier mapFrom(FromSupplierDtoToBase fromSupplierDtoToBase);

}
