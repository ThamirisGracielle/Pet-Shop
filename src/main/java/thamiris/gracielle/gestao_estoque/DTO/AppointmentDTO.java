package thamiris.gracielle.gestao_estoque.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {

    private Long clientId;
    private Long petId;
    private Long serviceId;
    private LocalDateTime dataHora;
    private String appointmentStatus;

}
