package ru.neoflex.service.impl;

import org.springframework.stereotype.Service;
import ru.neoflex.exception.NegativeNumber;
import ru.neoflex.service.VacationPayCalculatorService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class VacationPayCalculatorServiceImpl implements VacationPayCalculatorService {
    private final double AVERAGE_NUMBER_OF_DAYS_IN_A_MONTH = 29.3;
    private final int NUMBER_OF_MONTHS = 12;
    private final double PERSONAL_INCOME_TAX_PERCENTAGE = 0.13;

    @Override
    public BigDecimal calculateTheAmountOfVacationPay(double averageSalary, int numberOfVacationDays) {
        if (averageSalary <= 0 || numberOfVacationDays <= 0) {
            throw new NegativeNumber("Вы ввели отрицательное или нулевое число, проверьте данные");
        }
        double averageDailyEarnings = averageSalary / (NUMBER_OF_MONTHS * AVERAGE_NUMBER_OF_DAYS_IN_A_MONTH);
        double vacationPay = averageDailyEarnings * numberOfVacationDays;
        double personalIncomeTax = PERSONAL_INCOME_TAX_PERCENTAGE * vacationPay;
        double result = vacationPay - personalIncomeTax;
        BigDecimal response = BigDecimal.valueOf(result);
        return response.setScale(2, RoundingMode.HALF_UP);
    }
}