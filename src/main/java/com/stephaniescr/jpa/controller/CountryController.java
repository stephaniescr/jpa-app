package com.stephaniescr.jpa.controller;

import com.stephaniescr.jpa.exception.ResourceNotFoundException;
import com.stephaniescr.jpa.model.Country;
import com.stephaniescr.jpa.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/country")
    public Page<Country> getAllCountries(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @PostMapping("/country")
    public Country createCountry(@Valid @RequestBody Country country) {
        return countryRepository.save(country);
    }

    @PutMapping("/country/{countryId}")
    public Country updateCountry(@PathVariable Long countryId, @Valid @RequestBody Country countryRequest) {
        return countryRepository.findById(countryId).map(country -> {
            country.setName(countryRequest.getName());
            return countryRepository.save(country);
        }).orElseThrow(() -> new ResourceNotFoundException("CountryId " + countryId + " not found"));
    }


    @DeleteMapping("/country/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long countryId) {
        return countryRepository.findById(countryId).map(country -> {
            countryRepository.delete(country);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CountryId " + countryId + " not found"));
    }

}