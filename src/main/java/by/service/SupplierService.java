package by.service;

import by.database.repository.SupplierRepository;
import by.dto.supplier_dto.FromSupplierDtoToBase;
import by.dto.supplier_dto.SupplierDto;
import by.mapper.classes.suppliers.DtoToSupplier;
import by.mapper.classes.suppliers.SupplierToDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupplierService {
    private final SupplierRepository suppliersRepository;
    private final SupplierToDto supplierToDto;
    private final DtoToSupplier dtoToSupplier;

    public List<SupplierDto> findAll() {
        log.info("Attempt to extract SupplierDto collection in method findAll()");
        return suppliersRepository.findAll()
                .stream()
                .map(supplierToDto::mapFrom)
                .collect(Collectors.toList());
    }

    public Optional<SupplierDto> findById(Long id) {
        log.info("Attempt to extract SupplierDto object in method findById()");
        return suppliersRepository.findById(id).map(supplierToDto::mapFrom);
    }

    @Transactional
    public SupplierDto save(FromSupplierDtoToBase fromSupplierDtoToBase) {
        log.info("Attempt to save fromSupplierDtoToBase object in method save()");
        return Optional.of(fromSupplierDtoToBase)
                .map(dtoToSupplier::mapFrom)
                .map(suppliersRepository::save)
                .map(supplierToDto::mapFrom)
                .orElseThrow();
    }

    @Transactional
    public void updateEmail(String email, Long id) {
        suppliersRepository.updateEmail(email, id);
        log.info("Attempt to update email by id for object Supplier in method updateEmail()");

    }

    @Transactional
    public Optional<SupplierDto> update(Long id, FromSupplierDtoToBase fromSupplierDtoToBase) {
        log.info("Attempt to update email by id for object Supplier in method update()");
        return suppliersRepository.findById(id)
                .map(supplier -> dtoToSupplier.mapFrom(fromSupplierDtoToBase))
                .map(suppliersRepository::saveAndFlush)
                .map(supplierToDto::mapFrom);
    }


    public boolean delete(Long id) {
        log.info("Attempt to delete Supplier object by your id, in method delete()");
        return suppliersRepository.findById(id)
                .map(supplier -> {
                    suppliersRepository.deleteById(supplier.getId());
                    suppliersRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public List<String> listPhoneNumbers() {
        log.info("Attempt to find  phone numbers collection, in method listPhoneNumbers()");
        return suppliersRepository.listPhoneNumbers();
    }

    public List<Object[]> listEmailAndPhoneNumber() {
        log.info("Attempt to find list of arrays emails and phone numbers in method listEmailAndPhoneNumber()");
        return suppliersRepository.listEmailAndPhoneNumber();
    }
}
