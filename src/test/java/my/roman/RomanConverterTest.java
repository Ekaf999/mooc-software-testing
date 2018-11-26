package my.roman;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RomanConverterTest {
    private static final Logger LOGGER = Logger.getLogger( RomanConverterTest.class.getName() );
    private RomanConverter romanConverter = new RomanConverter();

    @BeforeAll
    static void beforeAll() {
        try {
            // This block configures the logger with handler and formatter
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
            String date = dateFormat.format(new Date());
            FileHandler fh = new FileHandler("log/roman2arabicTest" + date + ".log");
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    @BeforeEach
    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        String methodName = testInfo.getTestMethod().get().getName();
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int totalRepetitions = repetitionInfo.getTotalRepetitions();
        LOGGER.info(String.format("About to execute repetition %d of %d for %s", currentRepetition,
                totalRepetitions, methodName));
    }
*/
    @DisplayName("Valid single digit roman numbers")
    @ParameterizedTest
    @CsvSource({"I, 1", "V, 5", "X, 10", "L, 50", "C, 100", "D, 500", "M, 1000"})
    void validSingleDigitTest(String roman, Integer expected, TestInfo testInfo) {
        Integer result = romanConverter.roman2arabic(roman);
        LOGGER.info(String.format(testInfo.getDisplayName() +" Roman = %s, result = %d, expected result = %d", roman, result, expected));
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource({"IV, 4", "IIV, 3", "IX, 9", "IIIL, 47", "XC, 90", "IID, 498", "CCM, 800"})
    @DisplayName("Valid substractions because substractor is smaller than substractee and and substractor digits are the same:")
    void validSubstractionTest(String roman, Integer expected) {
        Integer result = romanConverter.roman2arabic(roman);
        LOGGER.info(String.format("Roman = %s, result = %d, expected result = %d", roman, result, expected));
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource({"IVX", "IIVC", "IXC", "IIILM", "CXD", "DIIM","CCXM", "MDCVIC"})
    @DisplayName("Invalid substractions because substractors are not the same.")
    void invalidSubstractionTest(String roman) {
        Integer result = romanConverter.roman2arabic(roman);
        Integer expected = null;
        LOGGER.info(String.format("Roman = %s, result = %d, expected result = %d", roman, result, expected));
        assertNull(result);
    }

    @ParameterizedTest
    @CsvSource({"MDCCCXLVI, 1846", "MDCCLXXVI, 1776", "MCMLIV, 1954", "MMXIV, 2014", "MMXVIII, 2018", "XXIX, 29",
                "IIXX, 18", "XIIX, 18", "IIII, 4", "XXXXI, 41", "CCCCM, 600", "IXI, 10", "ICI, 100"})
    @DisplayName("Valid, several digit roman numbers.")
    void validSeveralDigitsTest(String roman, Integer expected) {
        Integer result = romanConverter.roman2arabic(roman);
        LOGGER.info(String.format("Roman = %s, result = %d, expected result = %d", roman, result, expected));
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource({"XS", "V I",  "A"})
    @DisplayName("Invalid romans because of an invalid digit.")
    void invalidDigitTest(String roman) {
        Integer result = romanConverter.roman2arabic(roman);
        Integer expected = null;
        LOGGER.info(String.format("Roman = %s, result = %d, expected result = %d", roman, result, expected));
        assertNull(result);
    }

    @ParameterizedTest
    @CsvSource({"'', 0"})
    @DisplayName("The input is an empty string.")
    void emptyInputTest(String roman, Integer expected) {
        Integer result = romanConverter.roman2arabic(roman);
        LOGGER.info(String.format("Roman = %s, result = %d, expected result = %d", roman, result, expected));
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource({" , "})
    @DisplayName("null input.")
    void nullInputTest(String roman, Integer expected) {
        Integer result = romanConverter.roman2arabic(roman);
        LOGGER.info(String.format("Roman = %s, result = %d, expected result = %d", roman, result, expected));
        assertNull(result);
    }
}
