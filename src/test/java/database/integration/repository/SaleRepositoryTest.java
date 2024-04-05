package database.integration.repository;

import annotation.IT;
import by.database.entity.Employee;
import by.database.entity.Product;
import by.database.entity.Sale;
import by.database.repository.EmployeeRepository;
import by.database.repository.ProductRepository;
import by.database.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@IT
@RequiredArgsConstructor
public class SaleRepositoryTest {

    private final SaleRepository salesRepository;
    private final ProductRepository productsRepository;
    private final EmployeeRepository employeesRepository;


    @Test
    public void findAll() {
        List<Sale> list = salesRepository.findAllBy();
        assertTrue(list.size() > 0);
        list.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;
        Optional<Sale> sales = salesRepository.findById(id);
        assertTrue(sales.isPresent());
    }

    @Test
    public void save() {
        Long employeeId = 4L;
        Long productId = 6L;
        Employee employee = employeesRepository.findById(employeeId).get();
        Product product = productsRepository.findById(productId).get();
        Sale sales = Sale.builder()
                .product(product)
                .count(4L)
                .employee(employee)
                .dateSales(LocalDate.now())
                .build();
        salesRepository.save(sales);
    }

    @Test
    public void update() {
        Long id = 1L;
        Long countSale = 15L;

        salesRepository.update(countSale,id);
        var sales = salesRepository.findById(id).get();
        assertEquals(countSale, sales.getCount());

    }

    @Test
    public void delete() {
        Long id = 2L;
        salesRepository.deleteById(id);
        Optional<Sale> sale = salesRepository.findById(id);
        assertTrue(sale.isEmpty());
    }
}
