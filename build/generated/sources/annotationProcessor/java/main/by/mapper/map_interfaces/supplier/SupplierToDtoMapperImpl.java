package by.mapper.map_interfaces.supplier;

import by.database.entity.Supplier;
import by.dto.supplier_dto.SupplierDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T19:10:53+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class SupplierToDtoMapperImpl implements SupplierToDtoMapper {

    @Override
    public SupplierDto mapFrom(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDto.SupplierDtoBuilder supplierDto = SupplierDto.builder();

        supplierDto.id( supplier.getId() );
        supplierDto.name( supplier.getName() );
        supplierDto.address( supplier.getAddress() );
        supplierDto.email( supplier.getEmail() );
        supplierDto.phoneNumber( supplier.getPhoneNumber() );

        return supplierDto.build();
    }
}
