package database.integration.repository;

import annotation.IT;
import by.database.entity.Supplier;
import by.database.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@IT
@RequiredArgsConstructor
public class SupplierRepositoryTest {
    private final Logger log = LoggerFactory.getLogger(SupplierRepositoryTest.class);
    private final SupplierRepository suppliersRepository;


    @Test
    public void findAll() {
        List<Supplier> list = suppliersRepository.findAllBy();
        assertTrue(list.size() > 0);
        assertThat(list).hasSize(5);
        log.info("List from method findAll(): {}", list);
    }

    @Test
    public void findById() {
        Long supplierId = 3L;
        Optional<Supplier> supplier = suppliersRepository.findById(supplierId);
        assertTrue(supplier.isPresent());
        log.info("Object from method findById(): {}", supplier);
    }

    @Test
    public void save() {
        Supplier supplier = Supplier.builder()
                .name("Tails&&Beaks")
                .address("On the village for grandfather")
                .email("tail@gmail.com")
                .phoneNumber("2-222-333-44-55")
                .build();
        Supplier supplierAfterSave = suppliersRepository.save(supplier);
        assertEquals(supplierAfterSave, supplier);
        log.info("Object was saved in method save(): {}", supplierAfterSave);
    }

    @Test
    public void update() {
        Long supplierId = 3L;
        String email = "qqq@f.to";

        suppliersRepository.updateEmail(email, supplierId);
        Supplier supplierAfterUpdate = suppliersRepository.findById(supplierId).get();
        assertEquals(email, supplierAfterUpdate.getEmail());
        log.info("Email was updated in method update(): {}", email);
    }

    @Test
    public void delete() {
        Long supplierId = 1L;

        suppliersRepository.deleteById(supplierId);
        Optional<Supplier> supplier = suppliersRepository.findById(supplierId);
        assertTrue(supplier.isEmpty());
        log.warn("Object was deleted in method delete()");
    }

    @Test
    public void listPhoneNumbers() {
        List<String> results = suppliersRepository.listPhoneNumbers();
        assertThat(results).hasSize(5);
        results.forEach(System.out::println);
    }

    @Test
    public void listEmailAndPhoneNumbers() {
        List<Object[]> results = suppliersRepository.listEmailAndPhoneNumber();
        List<String> emailList = results.stream().map(r -> (String) r[0]).toList();
        assertThat(emailList).contains("horn@gmail.com", "beak@gmail.com");
        emailList.forEach(System.out::println);

        List<String> phoneNumbersList = results.stream().map(r -> (String) r[1]).toList();
        assertThat(phoneNumbersList).contains("8-988-342-65-98", "1-111-111-11-99", "8-455-876-23-21");
        phoneNumbersList.forEach(System.out::println);
    }
}
