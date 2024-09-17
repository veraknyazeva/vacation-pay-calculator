package ru.neoflex.service;

import java.math.BigDecimal;

public interface VacationPayCalculatorService {
    BigDecimal calculateTheAmountOfVacationPay(double averageSalary, int numberOfVacationDays);
}
