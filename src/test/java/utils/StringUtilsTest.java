package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class StringUtilsTest {

    //champs utilisable pour chaque méthode de test
    private final StringUtils stringUtils = new StringUtils();

    //Test for estEntier() method
    @Test
    @DisplayName("Retourne True si la chaine passée en paramètre est un entier")
    public void estEntierTest_When_String_Is_An_Integer(){
        String str = "66";
        boolean test = stringUtils.estEntier(str);
        assertThat(test).isTrue();
    }

    @Test
    @DisplayName("Retourne False si la chaine passée en paramètre est nulle")
    public void estEntierTest_When_String_Is_Null(){
        String nulle = null;
        boolean test2 = stringUtils.estEntier(nulle);
        assertThat(test2).isFalse();
    }

    //Test for nbOccurence() method
    @ParameterizedTest
    @CsvSource({
            " 'hello', 'l', 2 ",
            " 'noooon', 'o', 4 "
    })
    public void nbOccurrenceTest_With_Multiple_Values(String a, char b, int expectedResult){
        int ret = stringUtils.nbOccurrence(a, b);
        assertThat(ret).isEqualTo(expectedResult);
    }

}