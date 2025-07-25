package thamiris.gracielle.pet_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thamiris.gracielle.pet_shop.DTO.AppointmentDTO;
import thamiris.gracielle.pet_shop.model.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "pet.id", target = "petId")
    AppointmentDTO toDTO(Appointment appointment);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "petId", target = "pet.id")
    Appointment toEntity(AppointmentDTO appointmentDTO);
}