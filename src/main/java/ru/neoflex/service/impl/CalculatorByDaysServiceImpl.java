package ru.neoflex.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.neoflex.service.CalculatorByDaysService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CalculatorByDaysServiceImpl implements CalculatorByDaysService {

    private List<LocalDate> holidays;

    @Override
    public int calculateVacationDays(int numberOfVacationDays, LocalDate startVacationDate) {
        for (LocalDate day = startVacationDate; day.isBefore(startVacationDate.plusDays(numberOfVacationDays)); day = day.plusDays(1)) {
            if (holidays.contains(day)) {
                numberOfVacationDays -= 1;
            }
        }
        return numberOfVacationDays;
    }

    @Value("${service.const.listOfHolidays}")
    public void setHolidays(List<String> holidays) {
        this.holidays = new ArrayList<>();
        for (String date : holidays) {
            this.holidays.add(LocalDate.parse(date));
        }
    }
}
