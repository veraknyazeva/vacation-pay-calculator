package ru.neoflex.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.service.CalculatorByDaysService;
import ru.neoflex.service.VacationPayCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
public class VacationPayCalculatorController {
    private final VacationPayCalculatorService calculatorService;
    private final CalculatorByDaysService calculatorByDaysService;


    public VacationPayCalculatorController(VacationPayCalculatorService calculatorService, CalculatorByDaysService calculatorByDaysService) {
        this.calculatorService = calculatorService;
        this.calculatorByDaysService = calculatorByDaysService;
    }

    @GetMapping("/calculate")
    public BigDecimal getVacationPayAmount(@RequestParam("averageSalary") double averageSalary,
                                           @RequestParam("numberOfVacationDays") int numberOfVacationDays,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startVacationDate) {
        if (startVacationDate.isPresent()) {
            numberOfVacationDays = calculatorByDaysService.calculateVacationDays(numberOfVacationDays, startVacationDate.get());
        }
        return calculatorService.calculateTheAmountOfVacationPay(averageSalary, numberOfVacationDays);
    }
}
