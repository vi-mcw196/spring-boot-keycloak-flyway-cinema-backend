package pwr.piisw.cinema.application.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.Genre;
import pwr.piisw.cinema.application.service.GenreService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genres")
@SecurityRequirement(name = "Keycloak")
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
        Optional<Genre> genre = genreService.findById(id);
        return genre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public List<Genre> getGenresByName(@PathVariable String name) {
        return genreService.findByName(name);
    }

    @GetMapping("/name-ignore-case/{name}")
    public List<Genre> getGenresByNameIgnoreCase(@PathVariable String name) {
        return genreService.findByNameIgnoreCase(name);
    }

    @GetMapping("/count/{name}")
    public Long countGenresByName(@PathVariable String name) {
        return genreService.countByName(name);
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Integer id, @RequestBody Genre genreDetails) {
        Optional<Genre> genreOptional = genreService.findById(id);
        if (genreOptional.isPresent()) {
            Genre genre = genreOptional.get();
            genre.setName(genreDetails.getName());
            return ResponseEntity.ok(genreService.save(genre));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id) {
        Optional<Genre> genre = genreService.findById(id);
        if (genre.isPresent()) {
            genreService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}