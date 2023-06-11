package com.exoplanet.service;

import com.exoplanet.model.Exoplanet;
import com.exoplanet.service.ExoplanetService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExoplanetServiceTest {

    private static final String API_URL = "https://gist.githubusercontent.com/joelbirchler/66cf8045fcbb6515557347c05d789b4a/raw/9a196385b44d4288431eef74896c0512bad3defe/exoplanets";

    @Mock
    private HttpClient client;

    @InjectMocks
    private ExoplanetService exoplanetService;

    public ExoplanetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExoplanetDetails() throws IOException, InterruptedException {
        // Arrange
        List<Exoplanet> planetList = new ArrayList<>();
        Exoplanet exoplanet1 = new Exoplanet();
        exoplanet1.setTypeFlag(3);
        exoplanet1.setHostStarTempK(5000);
        exoplanet1.setDiscoveryYear("2022");
        exoplanet1.setRadiusJpt(1.5);

        Exoplanet exoplanet2 = new Exoplanet();
        exoplanet2.setTypeFlag(2);
        exoplanet2.setHostStarTempK(4000);
        exoplanet2.setDiscoveryYear("2022");
        exoplanet2.setRadiusJpt(1.5);

        planetList.add(exoplanet1);
        planetList.add(exoplanet2);

        HttpResponse<String> responseMock = mock(HttpResponse.class);
        when(responseMock.statusCode()).thenReturn(200);
        ObjectMapper mapper =
                new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        when(responseMock.body()).thenReturn(mapper.writeValueAsString(planetList));

        when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(responseMock);

        // Act
        Map<String, Object> result = exoplanetService.getExoplanetDetails();

        // Assert
        int orphanPlanets = 1;
        String planetIdentifier = exoplanet1.getPlanetIdentifier();
        Map<String, Map<String, Integer>> expectedTimeline = new HashMap<>();
        Map<String, Integer> sizeGroupCount = new HashMap<>();
        sizeGroupCount.put("medium", 2);
        expectedTimeline.put("2022", sizeGroupCount);

        assertEquals(orphanPlanets, result.get("Total Orphan Planets"));
        assertEquals(planetIdentifier, result.get("Planet with hottest star"));
        assertEquals(expectedTimeline, result.get("Discovery Timeline"));
    }
}
