package br.com.fiap.serverless.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.serverless.dto.CreateTripDTO;
import br.com.fiap.serverless.entity.Trip;

@Service
public class TripServiceImpl implements TripService {

    @Override
    public Trip saveTrip(CreateTripDTO createTripDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Trip updateTrip(Long id, CreateTripDTO createTripDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Trip> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Trip> findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteTrip(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Trip> findTripsByAssociate(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
