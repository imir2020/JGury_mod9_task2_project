package by.mapper.map_interfaces.product;

import by.database.entity.Category;
import by.database.entity.Product;
import by.database.entity.Supplier;
import by.dto.product_dto.FromProductDtoToBase;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-05T19:10:53+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DtoToProductMapperImpl implements DtoToProductMapper {

    @Override
    public Product mapFrom(FromProductDtoToBase fromProductDtoToBase) {
        if ( fromProductDtoToBase == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.category( fromProductDtoToBaseToCategory( fromProductDtoToBase ) );
        product.supplier( fromProductDtoToBaseToSupplier( fromProductDtoToBase ) );
        product.name( fromProductDtoToBase.getName() );
        product.count( fromProductDtoToBase.getCount() );
        product.priceForOne( fromProductDtoToBase.getPriceForOne() );

        return product.build();
    }

    protected Category fromProductDtoToBaseToCategory(FromProductDtoToBase fromProductDtoToBase) {
        if ( fromProductDtoToBase == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( fromProductDtoToBase.getCategoryId() );

        return category.build();
    }

    protected Supplier fromProductDtoToBaseToSupplier(FromProductDtoToBase fromProductDtoToBase) {
        if ( fromProductDtoToBase == null ) {
            return null;
        }

        Supplier.SupplierBuilder supplier = Supplier.builder();

        supplier.id( fromProductDtoToBase.getSupplierId() );

        return supplier.build();
    }
}
