

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;



public class Test_rus {
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

    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "172.12.25.33"})
    public void sendMessageWithRusIp (String ip) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать ");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Assertions.assertEquals("Добро пожаловать ", messageSender.send(headers));

    }



}
