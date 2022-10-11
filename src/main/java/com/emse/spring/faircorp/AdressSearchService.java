package com.emse.spring.faircorp;

import com.emse.spring.faircorp.dto.ApiGouvAdressDto;
import com.emse.spring.faircorp.dto.ApiGouvFeatureDto;
import com.emse.spring.faircorp.dto.ApiGouvResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdressSearchService {

    private final RestTemplate restTemplate;

    public AdressSearchService(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.rootUri("https://api-adresse.data.gouv.fr").build();
    }
    public List<ApiGouvAdressDto> getAdress(List<String> params){
        String uri = UriComponentsBuilder.fromUriString("/search")
                .queryParam("q", String.join("+", params))
                .queryParam("limit", 15)
                .build().toUriString();
        return restTemplate.getForObject(uri, ApiGouvResponseDto.class).getFeatures().stream()
                .map(ApiGouvFeatureDto::getProperties).toList();
    }
}
