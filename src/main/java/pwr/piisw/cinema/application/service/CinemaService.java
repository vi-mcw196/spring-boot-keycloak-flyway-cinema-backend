package pwr.piisw.cinema.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.piisw.cinema.application.entity.Cinema;
import pwr.piisw.cinema.application.repository.CinemaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    @Autowired
    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public Optional<Cinema> findById(Integer id) {
        return cinemaRepository.findById(id);
    }

    public List<Cinema> findByCity(String city) {
        return cinemaRepository.findByCity(city);
    }

    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    public void deleteById(Integer id) {
        cinemaRepository.deleteById(id);
    }
}