package by.http.controller;

import by.database.entity.Rank;
import by.database.repository.Greid;
import by.dto.employees_dto.FromDtoToEmployee;
import by.service.EmployeeService;
import by.service.RankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final RankService rankService;

    @GetMapping
    public String findAll(Model model) {
        var result = employeeService.findAll();
        model.addAttribute("employees", result);
        model.addAttribute("ranks", rankService.findAll());

        return "employee/employees";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return employeeService.findById(id)
                .map(employee -> {
                    model.addAttribute("employee", employee);
                    model.addAttribute("greids", Greid.values());
                    model.addAttribute("ranks", rankService.findAll());

                    return "employee/employee";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, FromDtoToEmployee fromDtoToEmployee) {
        model.addAttribute("employee", fromDtoToEmployee);
        model.addAttribute("ranks", rankService.findAll());
        return "employee/employee_save";
    }




    @PostMapping
    public String create(FromDtoToEmployee fromDtoToEmployee,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("employee", fromDtoToEmployee);
            return "redirect:/employees/registration";
        }

        var result = employeeService.save(fromDtoToEmployee);
        return "redirect:/employees/" + result.getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, FromDtoToEmployee fromDtoToEmployee) {
        log.info("Enter in method update(), EmployeeController class: {}", fromDtoToEmployee);

        return employeeService.update(id, fromDtoToEmployee)
                .map(redirect -> "redirect:/employees/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!employeeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/employees";
    }
}
