package com.company.journalApp.service;

import com.company.journalApp.api.response.WeatherResponse;
import com.company.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("Weather_of_"+ city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else {
            String template = appCache.APP_CACHE.get("weather_api");
            if (template == null) {
                throw new RuntimeException("weather_api not found in APP_CACHE");
            }
            String finalapi = template.replace("<city>", city).replace("<apikey>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null){
                redisService.set("Weather_of_" + city, body, 300l);
            }
            return body;
        }

    }

}
