import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static java.time.Duration.ofNanos;
import static org.apache.commons.math3.util.CombinatoricsUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static task01.Utils.computeFactorial;

public class ComputeFactorialTests {
    private static int randomNumber;

    @BeforeEach
    void initVariables() {
        randomNumber = new Random().nextInt(20);
    }

    @RepeatedTest(100)
    void testComputeFactorial() {
        assertEquals(factorial(randomNumber), computeFactorial(randomNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -20, -10, -103})
    void testComputeNegativeFactorial(int number) {
        assertThrows(IllegalArgumentException.class, () -> computeFactorial(number));
    }

    @Test
    void testComputeFactorialExceptionMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> computeFactorial(-234));
        assertEquals("Number can't be less than zero", exception.getMessage());
    }

    @Test
    void testComputeFactorialForZeroArgument() {
        assertEquals(1, computeFactorial(0));
    }

    @RepeatedTest(1000)
    @Disabled
    void testComputeFactorialWithTimeout() {
        assertTimeout(ofNanos(1), () -> computeFactorial(randomNumber));
    }
}
