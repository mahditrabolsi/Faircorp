package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.AdressSearchService;
import com.emse.spring.faircorp.dto.ApiGouvAdressDto;
import com.emse.spring.faircorp.dto.ApiGouvResponseDto;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/adress")
@Transactional
public class AdressController {
    private final AdressSearchService adressSearchService;

    public AdressController(AdressSearchService adressSearchService) {
        this.adressSearchService = adressSearchService;
    }
    @GetMapping
    public List<ApiGouvAdressDto> getAdress(@RequestParam List<String> adress){
        return adressSearchService.getAdress(adress);
    }
}
