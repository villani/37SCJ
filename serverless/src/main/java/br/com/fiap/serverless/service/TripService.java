package br.com.fiap.serverless.service;

import java.util.List;
import java.util.Optional;

import br.com.fiap.serverless.dto.CreateTripDTO;
import br.com.fiap.serverless.entity.Trip;

public interface TripService {

    Trip saveTrip(CreateTripDTO createTripDTO);
    Trip updateTrip(Long id,CreateTripDTO createTripDTO);
    Iterable<Trip> findAll();
    Optional<Trip> findById(Long id);
    String deleteTrip(Long id);
    List<Trip> findTripsByAssociate(Long id);
    
}
