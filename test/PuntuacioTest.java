package test;

import com.georgie.penjat.Puntuacio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuntuacioTest {

    private Puntuacio puntuacio;
    private String word;
    private String[] words;
    private Boolean Exists;

    @BeforeEach
    void reiniciar() {
        this.puntuacio = new Puntuacio();
        Exists = false;
    }


    @Test
    void wrongValue() {
        puntuacio.getParaulaSecretaDificultat(0);
        assertEquals(puntuacio.getIntents(), 0, "It's wrong");
    }

    @Test
    void tryLevelOneTries() {
        puntuacio.getParaulaSecretaDificultat(1);
        assertEquals(puntuacio.getIntents(), 5, "It's wrong");
    }

    @Test
    void tryLevelOneWord() {
        word = puntuacio.getParaulaSecretaDificultat(1);
        words = puntuacio.getParaules1();
        for (int i = 0; i < words.length; i++) {
            if (word.equals(words[i])) {
                Exists = true;
            }
        }
        assertTrue(Exists);
    }

    @Test
    void tryLevelTwoTries() {
        puntuacio.getParaulaSecretaDificultat(2);
        assertEquals(puntuacio.getIntents(), 4, "It's wrong");
    }

    @Test
    void tryLevelTwoWord() {
        word = puntuacio.getParaulaSecretaDificultat(2);
        words = puntuacio.getParaules2();
        for (int i = 0; i < words.length; i++) {
            if (word.equals(words[i])) {
                Exists = true;
            }
        }
        assertTrue(Exists);
    }

    @Test
    void tryLevelThreeTries() {
        puntuacio.getParaulaSecretaDificultat(3);
        assertEquals(puntuacio.getIntents(), 3, "It's wrong");
    }

    @Test
    void tryLevelThreeWord() {
        word = puntuacio.getParaulaSecretaDificultat(3);
        words = puntuacio.getParaules3();
        for (int i = 0; i < words.length; i++) {
            if (word.equals(words[i])) {
                Exists = true;
            }
        }
        assertTrue(Exists);
    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void tryDifficultiesTries(int dif) {
        puntuacio.getParaulaSecretaDificultat(dif);
        int tries = switch (dif) {
            case 1 -> 5;
            case 2 -> 4;
            case 3 -> 3;
            default -> 0;
        };
        assertEquals(tries, puntuacio.getIntents(), "Wrong");
    }


    @Test
    void calculatePointsLevel1() {
        String[] guessedWord = {"a", "r", "a", "n", "y", "a"};
        puntuacio.getParaulaSecretaDificultat(1);
        float points = 60f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel1Wrong() {
        String[] guessedWord = {"p", null, "r", "c"};
        puntuacio.getParaulaSecretaDificultat(1);
        float points = 30f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel2() {
        String[] guessedWord = {"x", "i", "n", "x", "i", "l", "l", "a"};
        puntuacio.getParaulaSecretaDificultat(2);
        float points = 160f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel2Wrong() {
        String[] guessedWord = {"n", null, "c", null, "a", null, "i", "n", "a"};
        puntuacio.getParaulaSecretaDificultat(2);
        float points = 120f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel3() {
        String[] guessedWord = {"e", "s", "c", "o", "p", "i", "n", "y", "e", "s"};
        puntuacio.getParaulaSecretaDificultat(3);
        float points = 300f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel3Wrong() {
        String[] guessedWord = {null, null, "c", "o", "m", "b", null, "a", null, "i", null, null};
        puntuacio.getParaulaSecretaDificultat(3);
        float points = 180f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void calculatePointsEmpty(int dif) {
        String[] guessedWord = {null, null, null, null};
        puntuacio.getParaulaSecretaDificultat(dif);
        float points = 0f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

}

