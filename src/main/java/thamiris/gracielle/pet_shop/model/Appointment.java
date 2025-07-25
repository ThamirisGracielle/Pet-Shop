package thamiris.gracielle.pet_shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Cliente é obrigatório")
    @ManyToOne
    private Client client;

    @NotNull(message = "Pet é obrigatório")
    @ManyToOne
    private Pet pet;

    @NotNull(message = "Serviço é obrigatório")
    @ManyToOne
    private PetShopService petShopService;

    @NotNull(message = "Data e hora são obrigatórias")
    @Future(message = "A data e hora devem estar no futuro")
    private LocalDateTime dataHora;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus = AppointmentStatus.AGENDADO;
}