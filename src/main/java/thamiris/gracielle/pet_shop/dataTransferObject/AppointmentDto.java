package thamiris.gracielle.pet_shop.dataTransferObject;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDto {

    private Long AppointmentId;
    private Long clientId;
    private Long petId;
    private Long serviceId;
    private LocalDateTime dataHora;
    private String appointmentStatus;

}
