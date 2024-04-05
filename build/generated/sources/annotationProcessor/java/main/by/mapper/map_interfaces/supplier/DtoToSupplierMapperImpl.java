package by.mapper.map_interfaces.supplier;

import by.database.entity.Supplier;
import by.dto.supplier_dto.FromSupplierDtoToBase;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T19:10:53+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DtoToSupplierMapperImpl implements DtoToSupplierMapper {

    @Override
    public Supplier mapFrom(FromSupplierDtoToBase fromSupplierDtoToBase) {
        if ( fromSupplierDtoToBase == null ) {
            return null;
        }

        Supplier.SupplierBuilder supplier = Supplier.builder();

        supplier.name( fromSupplierDtoToBase.getName() );
        supplier.address( fromSupplierDtoToBase.getAddress() );
        supplier.email( fromSupplierDtoToBase.getEmail() );
        supplier.phoneNumber( fromSupplierDtoToBase.getPhoneNumber() );

        return supplier.build();
    }
}
