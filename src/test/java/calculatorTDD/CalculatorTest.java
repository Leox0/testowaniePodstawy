package calculatorTDD;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.geom.Rectangle2D;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @ParameterizedTest
    @CsvSource({  //podane paramety w csv //mogą byc też enum, tablice etc. // @MethodSource - najwięcej możliwości, możliwość mieszania typów itp
            "1,1,2",
            "3,5,8",
            "6,6,5" //błędne założenie
    })
    void shouldAddTwoNumbers(Double a, Double b, Double expectedResult) {
        //given

        //when
        Double result = calculator.add(a, b);

        //then
        assertEquals(expectedResult, result);

    }

    @Test
    void shouldSubtractTwoNumbers() {
        Double a = 5.0;
        Double b = 2.0;
        Double expectedResult = 3.0;

        //when
        Double result = calculator.subtract(a, b);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldPowTheNumber() {
        Double a = 5.0;
        Integer b = 2;
        Double expectedResult = 25.0;

        //when
        Double result = calculator.pow(a, b);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldSqrtTheNumber() {
        Double a = 9.0;
        Double expectedResult = 3.0;

        //when
        Double result = calculator.sqrt(a);

        //then
        assertEquals(expectedResult, result);
    }
}