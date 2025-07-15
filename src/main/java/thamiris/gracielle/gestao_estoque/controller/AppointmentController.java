package thamiris.gracielle.gestao_estoque.controller;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thamiris.gracielle.gestao_estoque.model.Appointment;
import thamiris.gracielle.gestao_estoque.model.AppointmentStatus;
import thamiris.gracielle.gestao_estoque.model.Pet;
import thamiris.gracielle.gestao_estoque.repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AppointmentController {


    private AppointmentRepository appointmentRepository;

    @PutMapping
    public ResponseEntity<String> createAppointment(@RequestBody @Valid Appointment appointment) {
        appointmentRepository.save(appointment);
        return ResponseEntity.ok("Agendamento criado com sucesso!");
    }

    @GetMapping()
    public List<Appointment> ListALL() {
        return appointmentRepository.findAll();
    }

    @GetMapping("/{ID}")
    public ResponseEntity<String> listById(@PathVariable Long id) {
        return appointmentRepository.findById(id)
                .map(Appointment -> ResponseEntity.ok("Agendamento localizado"))
                .orElseThrow(() -> new RuntimeException("Agendamento não localizado"));
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<List<Appointment>> listByPet(@PathVariable Long id) {
        List<Appointment> appointments = appointmentRepository.findByPetId(id);
        if (appointments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointments);
    }


    @GetMapping("/date/{date}")
    public ResponseEntity<List<Appointment>> listByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<Appointment> appointments = appointmentRepository.findByDate(startOfDay, endOfDay);

        if (appointments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    if (appointment.getAppointmentStatus() == AppointmentStatus.CANCELADO) {
                        return ResponseEntity.badRequest().body(appointment); // já cancelado
                    }
                    appointment.setAppointmentStatus(AppointmentStatus.CANCELADO);
                    appointmentRepository.save(appointment);
                    return ResponseEntity.ok(appointment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(@PathVariable Long id, @RequestBody AppointmentStatus status) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setAppointmentStatus(status);
                    appointmentRepository.save(appointment);
                    return ResponseEntity.ok(appointment);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}