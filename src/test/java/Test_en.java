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

public class Test_en {
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
    @ValueSource(strings = {"96.0.32.11", "96.0.32.1"})
    public void sendMessageWithAmericanIp_isReturnWelcome(String ipAddress) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(new Location("New York", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome ");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        Assertions.assertEquals("Welcome ", messageSender.send(headers));
    }

}
