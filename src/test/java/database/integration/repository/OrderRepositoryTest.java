package database.integration.repository;

import annotation.IT;
import by.database.entity.Order;
import by.database.entity.Supplier;
import by.database.repository.OrderRepository;
import by.database.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class OrderRepositoryTest {
    Logger log = LoggerFactory.getLogger(OrderRepositoryTest.class);

    private final OrderRepository orderRepository;
    private final SupplierRepository suppliersRepository;


    @Test
    public void findAllBy() {
        List<Order> list = orderRepository.findAllBy();
        assertTrue(list.size() > 0);
        log.info("List of orders is: {}", list);
    }

    @Test
    public void findById() {
        Long orderId = 1L;
        Order order = orderRepository.findById(orderId).get();
        assertNotNull(order);
        log.info("The order by id is: {}", order);
    }

    @Test
    public void save() {
        Supplier supplier = Supplier.builder()
                .name("Tails&&Beaks")
                .address("On the village for grandfather")
                .email("tail@gmail.com")
                .phoneNumber("0-222-999-99-99")
                .build();
        suppliersRepository.save(supplier);
        Order order = Order.builder()
                .supplier(supplier)
                .nameProduct("Шоколад")
                .countProduct(2000L)
                .priceProduct(50L)
                .dateOrder(LocalDate.now())
                .build();
        Order orderAfterSave = orderRepository.save(order);
        assertEquals(order, orderAfterSave);
    }

    @Test
    public void update() {
        Long orderId = 1L;
        String productName = "chocolate";
        orderRepository.update(productName,orderId);
        var result = orderRepository.findById(orderId).get();
        assertEquals(productName,result.getNameProduct());

    }

    @Test
    public void delete() {
        Long orderId = 2L;
        assertThrows(NoSuchElementException.class, () -> {
            orderRepository.deleteById(orderId);
             orderRepository.findById(orderId).get();
        });
    }
}
