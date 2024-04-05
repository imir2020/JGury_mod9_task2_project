package by.database.repository;

import by.database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBy();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Product saveAndFlush(Product product);

    @Modifying
    @Query("""
            update Product p
            set p.priceForOne = :priceForOne
            where p.id = :id
            """)
    void updateProductPriceForOneById(Long priceForOne, Long id);

    void deleteById(Long id);
}
