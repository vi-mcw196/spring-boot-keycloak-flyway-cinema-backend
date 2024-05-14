package pwr.piisw.cinema.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.Genre;
import pwr.piisw.cinema.application.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Integer id) {
        Genre genre = genreService.findById(id);
        return ResponseEntity.ok(genre);
    }

    @GetMapping("/name/{name}")
    public List<Genre> getGenresByName(@PathVariable String name) {
        return genreService.findByName(name);
    }

    @GetMapping("/name-ignore-case/{name}")
    public List<Genre> getGenresByNameIgnoreCase(@PathVariable String name) {
        return genreService.findByNameIgnoreCase(name);
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Integer id, @RequestBody Genre genreDetails) {
        Genre updatedGenre = genreService.updateGenre(id, genreDetails);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id) {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}