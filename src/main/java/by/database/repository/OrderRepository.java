package by.database.repository;

import by.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllBy();

    Optional<Order> findById(Long id);

    Order save(Order order);

    @Modifying
    @Query("""
            update Order o
            set o.nameProduct = :nameProduct
            where o.id = :id
            """)
    void  update(String nameProduct, Long id);

    void deleteById(Long id);
}
