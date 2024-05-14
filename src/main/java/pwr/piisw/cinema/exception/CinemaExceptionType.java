package pwr.piisw.cinema.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CinemaExceptionType {
    INTERNAL("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_AUTHORIZED("Not logged in", HttpStatus.FORBIDDEN),
    GENRE_NOT_FOUND("Genre not found", HttpStatus.NOT_FOUND),
    MOVIE_NOT_FOUND("Movie not found", HttpStatus.NOT_FOUND),
    SHOWTIME_NOT_FOUND("Showtime not found", HttpStatus.NOT_FOUND),
    SEAT_NOT_AVAILABLE("Seat not available", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String description;
    private final HttpStatus status;

    CinemaExceptionType(String description, HttpStatus status) {
        this.code = this.name();
        this.description = description;
        this.status = status;
    }
}