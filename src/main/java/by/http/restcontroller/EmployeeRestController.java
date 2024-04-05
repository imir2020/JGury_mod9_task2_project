package by.http.restcontroller;

import by.dto.employees_dto.EmployeeDto;
import by.dto.employees_dto.FromDtoToEmployee;
import by.service.EmployeeService;
import by.service.RankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private final EmployeeService employeeService;
    private final RankService rankService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        var result = employeeService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable("id") Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> create(@Validated @RequestBody FromDtoToEmployee fromDtoToEmployee) {
        var result = employeeService.save(fromDtoToEmployee);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable("id") Long id,
                                              @Validated @RequestBody FromDtoToEmployee fromDtoToEmployee) {
        log.info("Enter in method update(), EmployeeController class: {}", fromDtoToEmployee);
        return employeeService.update(id, fromDtoToEmployee)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (!employeeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
