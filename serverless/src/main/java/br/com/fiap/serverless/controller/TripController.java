package br.com.fiap.serverless.controller;

//import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.serverless.dto.CreateTripDTO;
import br.com.fiap.serverless.service.TripService;

@RestController
@RequestMapping("trips")
public class TripController {

    @Autowired
    TripService tripService;

    @GetMapping
    public @ResponseBody ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(tripService.findAll());
    }

    // Mesmo URI de findAll
    // - Verificar como fazer
    // public @ResponseBody ResponseEntity<?> findByPeriod(@RequestParam LocalDate start, @RequestParam LocalDate end) {
    //     return ResponseEntity.ok().body(tripService.findByPeriod(id));
    // }

    @GetMapping("{id}")
    public @ResponseBody ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(tripService.findById(id));
    }

    // @GetMapping("{country}")
    // public @ResponseBody ResponseEntity<?> findByCountry(@PathVariable String country) {
    //     return ResponseEntity.ok().body(tripService.findByCountry(country));
    // }

    // Mesma URI de findByCountry
    // @GetMapping("/{country}")
    // public @ResponseBody ResponseEntity<?> findByCity(@PathVariable String country, @RequestParam String city) {
    //     return ResponseEntity.ok().body(tripService.findByCity(country, city));
    // }

    @PostMapping
    public @ResponseBody ResponseEntity<?> create(@RequestBody CreateTripDTO createTripDTO) {
        return ResponseEntity.ok().body(tripService.saveTrip(createTripDTO));
    }

    @PutMapping("{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody CreateTripDTO createTripDTO) {
        return ResponseEntity.ok().body(tripService.updateTrip(id, createTripDTO));
    }

    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(tripService.deleteTrip(id));
    }
    
}
