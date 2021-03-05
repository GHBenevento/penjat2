package test;

import com.georgie.penjat.Puntuacio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuntuacioTest {

    private Puntuacio puntuacio;
    private String word;
    private String[] words;
    private Boolean Exists = false;

    @BeforeEach
    void reiniciar() {
        this.puntuacio = new Puntuacio();
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
            if (word == words[i]) {
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
            if (word == words[i]) {
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
            if (word == words[i]) {
                Exists = true;
            }
        }
        assertTrue(Exists);
    }

    @Test
    void wrongValue(){
        puntuacio.getParaulaSecretaDificultat(4);
        assertEquals(puntuacio.getIntents(), 0, "It's wrong");
    }

}

