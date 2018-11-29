import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static task01.Utils.concatenateWords;

public class ConcatenateWordsTests {
    private static final Class EXCEPTION_CLASS = NullPointerException.class;
    private static final List<String> NON_LATIN_SYMBOLS_PATTERN = Collections.singletonList("\\W*");
    private static final List<String> ONLY_LATIN_SYMBOLS_PATTERN = Collections.singletonList("\\w*");

    @ParameterizedTest
    @CsvSource({" , Not null second", "Not null first , ", " , "})
    void testConcatenateWordsWithNull(String first, String second) {
        assertAll(() -> assertThrows(EXCEPTION_CLASS, () -> concatenateWords(first, second)));
    }

    @Test
    void testConcatenateWordsIsOK() {
        assertAll(
                () -> assertEquals("HelloWorld", concatenateWords("Hello", "World")),
                () -> assertEquals("FirstSecond", concatenateWords("First", "Second"))
        );
    }

    @Test
    void testConcatenateWordsWithEmptyString() {
        assertAll(
                () -> assertEquals("World", concatenateWords("", "World")),
                () -> assertEquals("First", concatenateWords("First", "")),
                () -> assertEquals("", concatenateWords("", ""))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "АЯЯЯфвывыаыацау,ааыфвывавыдла",
            "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛЛДЖЭЯЧСМИТЬБЮ, я",
            "А ,йцукенгшщзхъфывапролджэячсмитьбю",
            "\u0441, \u0506"
    })
    void testIfWordsIsNonLatin(String first, String second) {
        assertAll(() -> assertLinesMatch(NON_LATIN_SYMBOLS_PATTERN, asList(concatenateWords(first, second))));
    }

    @Test
    public void testIfWordsIsLatin() {
        assertAll(
                () -> assertLinesMatch(ONLY_LATIN_SYMBOLS_PATTERN, asList(concatenateWords("ASSASFVBTBR", "fewfwfrqbkvbvb"))),
                () -> assertLinesMatch(ONLY_LATIN_SYMBOLS_PATTERN, asList(concatenateWords("AQQWCWerw113sdsdfewwwfewf77_", ""))),
                () -> assertLinesMatch(ONLY_LATIN_SYMBOLS_PATTERN, asList(concatenateWords("", "asfsfdsfd998_456")))
        );
    }
}
