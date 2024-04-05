package by.database.repository;

import by.database.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale,Long > {
   List<Sale> findAllBy();

   Optional<Sale> findById(Long id);

   Sale save(Sale sale);

    @Modifying
    @Query("""
            update Sale s
            set s.count = :countSale
            where s.id = :id
            """)
    void update(Long countSale, Long id);

    void deleteById(Long id);
}
