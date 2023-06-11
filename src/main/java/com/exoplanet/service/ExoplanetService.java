package com.exoplanet.service;

import com.exoplanet.model.Exoplanet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExoplanetService {

    private HttpClient client = HttpClient.newHttpClient();
    private static final String API_URL = "https://gist.githubusercontent.com/joelbirchler/66cf8045fcbb6515557347c05d789b4a/raw/9a196385b44d4288431eef74896c0512bad3defe/exoplanets";

    public Map getExoplanetDetails(){

        try {
            List<Exoplanet> planetList = getExoplanetList();
            int count = (int) planetList.stream()
                    .filter(planet -> planet.getTypeFlag() == 3)
                    .count();
            Exoplanet temp = planetList.stream()
                    .max(Comparator.comparingInt(Exoplanet::getHostStarTempK)).get();
            Map<String, Map<String, Integer>> discoveryTimeline = getDiscoveryTimeline(planetList);
            return buildResponseMap(count,temp.getPlanetIdentifier(),discoveryTimeline);
        }
        catch (Exception e){
            Map response = new HashMap<>();
            response.put("Message","Exception while getting Exoplanet details");
            return  response;
        }

    }

    private Map buildResponseMap(int orphanPlanets, String planetIdentifier, Map<String, Map<String, Integer>> discoveryTimeline) {
        Map response = new HashMap<>();
        response.put("Total Orphan Planets", orphanPlanets);
        response.put("Planet with hottest star", planetIdentifier);
        response.put("Discovery Timeline", discoveryTimeline);
        return response;
    }

    private Map<String, Map<String, Integer>> getDiscoveryTimeline(List<Exoplanet> planetList){
        Map<String, Map<String, Integer>> timeline = new HashMap<>();
        for (int i = 0; i < planetList.size(); i++) {
            Exoplanet planet = planetList.get(i);
            String discoveryYear = planet.getDiscoveryYear();
            discoveryYear = (discoveryYear.isEmpty()) ? "No Year" : discoveryYear;
            String sizeGroup = getSizeGroup(planet.getRadiusJpt());
            timeline.computeIfAbsent(discoveryYear, k -> new HashMap<>());
            timeline.get(discoveryYear).merge(sizeGroup, 1, Integer::sum);

        }
        return timeline;
    }


    private String getSizeGroup(double radius) {
        if (radius < 1) {
            return "small";
        } else if (radius < 2) {
            return "medium";
        } else {
            return "large";
        }
    }

    private List<Exoplanet> getExoplanetList() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper =
                new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Exoplanet> planetList = mapper.readValue(response.body(), new TypeReference<>() {
        });
        return planetList;
    }


}
