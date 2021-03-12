package test;

import com.georgie.penjat.Puntuacio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

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
        String[] guessedWord = {"p", "o", "r", "c"};
        puntuacio.getParaulaSecretaDificultat(1);
        puntuacio.setParaula("porc");
        float points = 200f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }


    @Test
    void calculatePointsLevel1Extra() {
        String[] guessedWord = {"a", "r", "a", "n", "y", "a"};
        puntuacio.getParaulaSecretaDificultat(1);
        puntuacio.setParaula("aranya");
        float points = 210f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel1Wrong() {
        String[] guessedWord = {"p", null, "r", "c"};
        puntuacio.getParaulaSecretaDificultat(1);
        puntuacio.setParaula("porc");
        float points = 30f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel2() {
        String[] guessedWord = {"n", "e", "c", "t", "a", "r", "i", "n", "a"};
        puntuacio.getParaulaSecretaDificultat(2);
        puntuacio.setParaula("nectarina");
        float points = 400f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel2Extra() {
        String[] guessedWord = {"x", "i", "n", "x", "i", "l", "l", "a"};
        puntuacio.getParaulaSecretaDificultat(2);
        puntuacio.setParaula("xinxilla");
        float points = 410f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel2Wrong() {
        String[] guessedWord = {"n", null, "c", null, "a", null, "i", "n", "a"};
        puntuacio.getParaulaSecretaDificultat(2);
        puntuacio.setParaula("nectarina");
        float points = 120f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel3() {
        String[] guessedWord = {"e", "s", "c", "o", "m", "b", "r", "a", "r", "i", "e", "s"};
        puntuacio.getParaulaSecretaDificultat(3);
        puntuacio.setParaula("escombraries");
        float points = 600f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel3Extra() {
        String[] guessedWord = {"e", "s", "c", "o", "p", "i", "n", "y", "e", "s"};
        puntuacio.getParaulaSecretaDificultat(3);
        puntuacio.setParaula("escopinyes");
        float points = 610f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @Test
    void calculatePointsLevel3Wrong() {
        String[] guessedWord = {null, null, "c", "o", "m", "b", null, "a", null, "i", null, null};
        puntuacio.getParaulaSecretaDificultat(3);
        puntuacio.setParaula("escombraries");
        float points = 180f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void calculatePointsEmpty(int dif) {
        String[] guessedWord = {null, null, null, null};
        puntuacio.getParaulaSecretaDificultat(dif);
        puntuacio.setParaula("porc");
        float points = 0f;
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void calculatePointsNormal(int dif) {
        String[] guessedWord = {"p", "o", "r", "c"};
        puntuacio.getParaulaSecretaDificultat(dif);
        puntuacio.setParaula("porc");
        float points = switch (dif) {
            case 1 -> 200f;
            case 2 -> 400f;
            case 3 -> 600f;
            default -> 0f;
        };
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void calculatePointsExtra(int dif) {
        String[] guessedWord = {"a", "r", "a", "n", "y", "a"};
        puntuacio.getParaulaSecretaDificultat(dif);
        puntuacio.setParaula("aranya");
        float points = switch (dif) {
            case 1 -> 210f;
            case 2 -> 410f;
            case 3 -> 610f;
            default -> 0f;
        };
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void calculatePointsWrong(int dif) {
        String[] guessedWord = {"a", "r", "a", "n", null, "a"};
        puntuacio.getParaulaSecretaDificultat(dif);
        puntuacio.setParaula("aranya");
        float points = switch (dif) {
            case 1 -> 50f;
            case 2 -> 100f;
            case 3 -> 150f;
            default -> 0f;
        };
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void calculatePointsTime(int dif) {
        String[] guessedWord = {"p", "o", "r", "c"};
        puntuacio.getParaulaSecretaDificultat(dif);
        puntuacio.setParaula("porc");
        float points = switch (dif) {
            case 1 -> 193;
            case 2 -> 393;
            case 3 -> 593;
            default -> 0f;
        };
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(puntuacio.calcularPuntuacio(guessedWord, 1), points, "Nope");
    }
}

