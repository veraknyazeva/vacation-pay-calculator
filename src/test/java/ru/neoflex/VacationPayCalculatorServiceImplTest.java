package ru.neoflex;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.neoflex.exception.NegativeNumber;
import ru.neoflex.service.impl.VacationPayCalculatorServiceImpl;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VacationPayCalculatorServiceImpl.class})
public class VacationPayCalculatorServiceImplTest {
    private static final double AVERAGE_SALARY = 620000;
    private static final int NUMBER_OF_VACATION_DAYS = 12;
    private static final int NUMBER_OF_VACATION_DAYS_NEGATIVE = -12;

    private static final BigDecimal EXPECTED = BigDecimal.valueOf(18409.56);


    @Autowired
    VacationPayCalculatorServiceImpl calculatorService;

    @Test
    public void calculate_the_amount_of_vacation_pay() {
        BigDecimal sum = calculatorService.calculateTheAmountOfVacationPay(AVERAGE_SALARY, NUMBER_OF_VACATION_DAYS);
        assertThat(sum).isEqualTo(EXPECTED);
    }

    @Test
    public void calculate_the_amount_of_vacation_pay_negative() {
        NegativeNumber negativeNumber = assertThrows(NegativeNumber.class,
                () -> calculatorService.calculateTheAmountOfVacationPay(AVERAGE_SALARY, NUMBER_OF_VACATION_DAYS_NEGATIVE));

        Assertions.assertThat(negativeNumber).isNotNull();
        Assertions.assertThat(negativeNumber.getMessage()).isEqualTo("Вы ввели отрицательное или нулевое число, проверьте данные");
    }
}
