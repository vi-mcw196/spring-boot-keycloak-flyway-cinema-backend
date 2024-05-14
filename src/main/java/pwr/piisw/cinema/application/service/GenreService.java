package pwr.piisw.cinema.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.piisw.cinema.application.entity.Genre;
import pwr.piisw.cinema.application.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    public List<Genre> findByNameIgnoreCase(String name) {
        return genreRepository.findByNameIgnoreCase(name);
    }

    public Long countByName(String name) {
        return genreRepository.countByName(name);
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteById(Integer id) {
        genreRepository.deleteById(id);
    }
}