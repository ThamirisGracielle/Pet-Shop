package thamiris.gracielle.pet_shop.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {

    private Long AppointmentId;
    private Long clientId;
    private Long petId;
    private Long serviceId;
    private LocalDateTime dataHora;
    private String appointmentStatus;

}
