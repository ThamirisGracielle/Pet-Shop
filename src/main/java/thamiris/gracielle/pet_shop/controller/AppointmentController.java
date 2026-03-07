package thamiris.gracielle.pet_shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thamiris.gracielle.pet_shop.dataTransferObject.AppointmentDto;
import thamiris.gracielle.pet_shop.model.Appointment;
import thamiris.gracielle.pet_shop.model.enums.AppointmentStatus;
import thamiris.gracielle.pet_shop.service.AppointmentService;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller responsável por gerenciar agendamentos
 */
@Tag(name = "Agendamentos", description = "Endpoints para gerenciamento de agendamentos")
@RequiredArgsConstructor
@RestController
@RequestMapping("/agendamento")
public class AppointmentController {

    private final AppointmentService appointmentService;

    /**
     * Cria um novo agendamento
     * @param dto Dados do agendamento
     * @return Agendamento criado
     */
    @Operation(summary = "Criar agendamento", description = "Cria um novo agendamento para um pet")
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody @Valid AppointmentDto dto) {
        Appointment created = appointmentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Lista todos os agendamentos
     * @return Lista de agendamentos
     */
    @Operation(summary = "Listar agendamentos", description = "Retorna todos os agendamentos")
    @GetMapping
    public ResponseEntity<List<Appointment>> listAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    /**
     * Busca agendamento por ID
     * @param id ID do agendamento
     * @return Agendamento encontrado
     */
    @Operation(summary = "Buscar agendamento", description = "Busca um agendamento específico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> listById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    /**
     * Lista agendamentos de um pet específico
     * @param id ID do pet
     * @return Lista de agendamentos do pet
     */
    @Operation(summary = "Listar agendamentos por pet", description = "Retorna todos os agendamentos de um pet")
    @GetMapping("/pet/{id}")
    public ResponseEntity<List<Appointment>> listByPet(@PathVariable Long id) {
        List<Appointment> appointments = appointmentService.findByPetId(id);
        return ResponseEntity.ok(appointments);
    }

    /**
     * Lista agendamentos de uma data específica
     * @param date Data para buscar agendamentos
     * @return Lista de agendamentos da data
     */
    @Operation(summary = "Listar agendamentos por data", description = "Retorna todos os agendamentos de uma data específica")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Appointment>> listByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Appointment> appointments = appointmentService.findByDate(date);
        return ResponseEntity.ok(appointments);
    }

    /**
     * Cancela um agendamento
     * @param id ID do agendamento
     * @return Agendamento cancelado
     */
    @Operation(summary = "Cancelar agendamento", description = "Cancela um agendamento existente")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id) {
        Appointment cancelled = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(cancelled);
    }

    /**
     * Atualiza o status de um agendamento
     * @param id ID do agendamento
     * @param status Novo status
     * @return Agendamento atualizado
     */
    @Operation(summary = "Atualizar status", description = "Atualiza o status de um agendamento")
    @PutMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(@PathVariable Long id, @RequestBody AppointmentStatus status) {
        Appointment updated = appointmentService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}