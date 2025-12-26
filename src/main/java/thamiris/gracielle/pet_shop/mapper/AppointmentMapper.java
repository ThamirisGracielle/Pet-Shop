package thamiris.gracielle.pet_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thamiris.gracielle.pet_shop.dataTransferObject.AppointmentDto;
import thamiris.gracielle.pet_shop.model.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "id", target = "appointmentId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "pet.id", target = "petId")
    @Mapping(source = "petShopService.id", target = "serviceId")
    AppointmentDto toDTO(Appointment appointment);

    @Mapping(source = "appointmentId", target = "id")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "petId", target = "pet.id")
    @Mapping(source = "serviceId", target = "petShopService.id")
    Appointment toEntity(AppointmentDto appointmentDTO);
}