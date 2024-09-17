package ru.neoflex.service;

import java.time.LocalDate;

public interface CalculatorByDaysService {
    int calculateVacationDays(int numberOfVacationDays, LocalDate startVacationDate);
}
