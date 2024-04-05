package by.http.restcontroller;

import by.dto.supplier_dto.FromSupplierDtoToBase;
import by.dto.supplier_dto.SupplierDto;
import by.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/suppliers")
public class SupplierRestController {
    private final SupplierService suppliersService;

    @GetMapping
    public ResponseEntity<List<SupplierDto>> findAll() {
        var result = suppliersService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> findById(@PathVariable("id") Long id) {
        return suppliersService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplierDto> create(@Validated @RequestBody FromSupplierDtoToBase fromSupplierDtoToBase) {
        var result = suppliersService.save(fromSupplierDtoToBase);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> update(@PathVariable("id") Long id,
                                              @Validated @RequestBody FromSupplierDtoToBase fromSupplierDtoToBase) {
        return suppliersService.update(id, fromSupplierDtoToBase)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (!suppliersService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
