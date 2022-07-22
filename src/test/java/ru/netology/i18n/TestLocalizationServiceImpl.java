package ru.netology.i18n;

import org.junit.jupiter.api.*;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.BRAZIL;
import static ru.netology.entity.Country.RUSSIA;


public class TestLocalizationServiceImpl {

    @BeforeAll
    public static void initSuite() {
        System.out.println("Tests started");
    }
    @BeforeEach
    public void initTest() {
        System.out.println("Starting new Test");
    }
    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete");
    }
    @AfterAll
    public static void completeSuit() {
        System.out.println("Tests complete");
    }
    @Test
    void localization() {
        LocalizationServiceImpl loc = new LocalizationServiceImpl();
        Assertions.assertEquals("Добро пожаловать", loc.locale(RUSSIA));
        Assertions.assertEquals("Welcome", loc.locale(BRAZIL));
    }
}
