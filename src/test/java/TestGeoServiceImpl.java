


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestGeoServiceImpl {

    static GeoService geoService;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Tests started");
        geoService = new GeoServiceImpl();
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

    @ParameterizedTest
    @CsvSource({"'127.0.0.1','172.0.32.11','96.44.183.149','172.12.25.33','96.55.78.35','4.45.34.6'"})
    public void byIpTest(String Loc, String Mosc, String New, String ipMosc, String ipNew, String Null) {
        String expected1 = GeoServiceImpl.LOCALHOST;
        Assertions.assertEquals(expected1, Loc);

        String expected2 = GeoServiceImpl.MOSCOW_IP;
        Assertions.assertEquals(expected2, Mosc);

        String expected3 = GeoServiceImpl.NEW_YORK_IP;
        Assertions.assertEquals(expected3, New);

        boolean expected4 = ipMosc.startsWith("172.");
        Assertions.assertTrue(expected4, ipMosc);

        boolean expected5 = ipNew.startsWith("96.");
        Assertions.assertTrue(expected5, ipNew);

        Assertions.assertNull(geoService.byIp(Null));
    }
    @Test
    void LocationByCoordinates() {
        Assertions.assertThrows(RuntimeException.class,
                () -> geoService.byCoordinates(32.21D, 43.24D)
                );
    }












}
