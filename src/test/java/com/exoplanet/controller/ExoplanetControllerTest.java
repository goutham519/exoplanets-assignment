package com.exoplanet.controller;

import com.exoplanet.controller.ExoplanetController;
import com.exoplanet.service.ExoplanetService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExoplanetControllerTest {

    @Mock
    private ExoplanetService exoplanetService;

    @InjectMocks
    private ExoplanetController exoplanetController;

    public ExoplanetControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExoplanetsWithReqDetails() {
        // Arrange
        Map<String, Object> planetList = new HashMap<>();
        when(exoplanetService.getExoplanetDetails()).thenReturn(planetList);

        // Act
        ResponseEntity<Map> response = exoplanetController.getExoplanetsWithReqDetails();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(planetList, response.getBody());

        // Verify that the service method was called once
        verify(exoplanetService, times(1)).getExoplanetDetails();
    }
}


