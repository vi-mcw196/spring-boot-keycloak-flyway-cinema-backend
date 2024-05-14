package pwr.piisw.cinema.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.piisw.cinema.application.entity.Genre;
import pwr.piisw.cinema.application.repository.GenreRepository;
import pwr.piisw.cinema.exception.CinemaException;
import pwr.piisw.cinema.exception.CinemaExceptionType;

import java.util.List;

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

    public Genre findById(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new CinemaException(CinemaExceptionType.GENRE_NOT_FOUND));
    }

    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    public List<Genre> findByNameIgnoreCase(String name) {
        return genreRepository.findByNameIgnoreCase(name);
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteById(Integer id) {
        genreRepository.deleteById(id);
    }

    public Genre updateGenre(Integer id, Genre genreDetails) {
        Genre genre = findById(id);
        genre.setName(genreDetails.getName());
        return genreRepository.save(genre);
    }
}