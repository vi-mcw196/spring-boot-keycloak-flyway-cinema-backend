package pwr.piisw.cinema.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.piisw.cinema.application.entity.Ticket;
import pwr.piisw.cinema.application.service.TicketService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@SecurityRequirement(name = "Keycloak")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ticket>> findByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(ticketService.findByUserId(userId));
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<List<Ticket>> findByShowId(@PathVariable Integer showId) {
        return ResponseEntity.ok(ticketService.findByShowId(showId));
    }

    @GetMapping("/email")
    public ResponseEntity<List<Ticket>> findByUserEmail(@RequestParam String email) {
        return ResponseEntity.ok(ticketService.findByUserEmail(email));
    }

    @GetMapping("/dateRange")
    public ResponseEntity<List<Ticket>> findTicketsByBookingDateRange(@RequestParam Timestamp startDate, @RequestParam Timestamp endDate) {
        return ResponseEntity.ok(ticketService.findTicketsByBookingDateRange(startDate, endDate));
    }

    @GetMapping("/cinema/{cinemaId}")
    public ResponseEntity<List<Ticket>> findByCinemaId(@PathVariable Integer cinemaId) {
        return ResponseEntity.ok(ticketService.findByCinemaId(cinemaId));
    }

    @PostMapping
    public ResponseEntity<Ticket> save(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.save(ticket), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        ticketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{ticketId}/pay")
    public ResponseEntity<byte[]> payForTicket(@PathVariable Integer ticketId) {
        byte[] qrCode = ticketService.payForTicket(ticketId);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket_qr.png");
        return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
    }

    @PostMapping("/validateQR")
    public ResponseEntity<Boolean> validateQRCode(@RequestBody String qrContent) {
        boolean isValid = ticketService.validateQRCode(qrContent);
        return ResponseEntity.ok(isValid);
    }
}
