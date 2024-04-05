package database.integration.service;


import annotation.IT;
import by.dto.supplier_dto.FromSupplierDtoToBase;
import by.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class SupplierServiceIT {
    private final SupplierService supplierService;


    @Test
    public void findAll() {
        var resultList = supplierService.findAll();
        assertTrue(resultList.size() > 0);
        resultList.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 3L;
        var supplier = supplierService.findById(id);
        assertTrue(supplier.isPresent());
    }

    @Test
    public void save() {
        FromSupplierDtoToBase dtoToBaseSupplier = FromSupplierDtoToBase.builder()
                .name("Tails&&Beaks")
                .address("On the village for grandfather")
                .email("tail@gmail.com")
                .phoneNumber("2-222-333-44-55")
                .build();
        supplierService.save(dtoToBaseSupplier);
    }

    @Test
    public void updateEmail() {
        Long id = 2L;
        String email = "tail222@gmail.com";
        supplierService.updateEmail(email,id);
        var supplier = supplierService.findById(id);
        assertTrue(supplier.isPresent());
        assertEquals(email,supplier.get().getEmail());
    }

    @Test
    public void update(){
        Long id = 5L;
        FromSupplierDtoToBase dtoToBase = FromSupplierDtoToBase.builder()
                .address("NovoSibirsk, d23")
                .email("tail222@gmail.com")
                .name("Gills&Fins")
                .phoneNumber("8(345)-123-23-34")
                .build();
        var result = supplierService.update(id,dtoToBase);
        assertTrue(result.isPresent());
        assertEquals(dtoToBase.getAddress(),result.get().getAddress());
        assertEquals(dtoToBase.getEmail() , result.get().getEmail());
        assertEquals(dtoToBase.getName() , result.get().getName());
        assertEquals(dtoToBase.getPhoneNumber() , result.get().getPhoneNumber());
    }

    @Test
    public void delete() {
//        Long id = 1L;
//        supplierService.delete(id);
//        var actual = supplierService.findById(id);
//        assertTrue(actual.isEmpty());
    }

    @Test
    public void listPhoneNumbers(){
        var list = supplierService.listPhoneNumbers();
        assertTrue(list.size()>0);
        list.forEach(System.out::println);
    }

    @Test
    public void listEmailAndPhoneNumber(){
        var list = supplierService.listEmailAndPhoneNumber();
        assertTrue(list.size()>0);
        list.forEach(System.out::println);
    }
}
