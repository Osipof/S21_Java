package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    NumberWorker numberWorker;

    @BeforeEach
    void beforeEachMethod() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 11, 991, 701, 151, 181, 95219, 11329, 10091})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 8, 180, 999, 1024, 1025, 1026, 1027, 1028, 91239, 12319, 19011})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 0, -1, -10, -1111})
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void checkDigitSum(int x, int y) {
        Assertions.assertEquals(numberWorker.digitsSum(x), y);
    }
}
