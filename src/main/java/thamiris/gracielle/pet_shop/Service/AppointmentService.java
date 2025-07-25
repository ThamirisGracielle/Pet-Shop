package thamiris.gracielle.pet_shop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thamiris.gracielle.pet_shop.DTO.AppointmentDTO;
import thamiris.gracielle.pet_shop.DTO.UpdateStatusDTO;
import thamiris.gracielle.pet_shop.model.*;
import thamiris.gracielle.pet_shop.repository.AppointmentRepository;
import thamiris.gracielle.pet_shop.repository.ClientRepository;
import thamiris.gracielle.pet_shop.repository.PetRepository;
import thamiris.gracielle.pet_shop.repository.PetShopServiceRepository;

import java.time.LocalDateTime;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetShopServiceRepository serviceRepository;

    // ✅ Método de criar/atualizar agendamento
    public Appointment createUpdateAppointmente(AppointmentDTO dto) {
        if (dto.getDataHora() == null || dto.getDataHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("A data ou hora do agendamento inválido!");
        }

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet não encontrado!"));

        PetShopService service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        if (!pet.getDono().getId().equals(client.getId())) {
            throw new RuntimeException("O pet não pertence ao cliente");
        }

        Appointment appointment;

        if (dto.getAppointmentId() != null) {
            appointment = appointmentRepository.findById(dto.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com ID: "
                            + dto.getAppointmentId()));
        } else {
            appointment = new Appointment();
        }

        appointment.setClient(client);
        appointment.setPet(pet);
        appointment.setPetShopService(service);
        appointment.setDataHora(dto.getDataHora());

        try {
            AppointmentStatus status = AppointmentStatus.valueOf(dto.getAppointmentStatus());
            appointment.setAppointmentStatus(status);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status de agendamento inválido!");
        }

        return appointmentRepository.save(appointment);
    }

    public Appointment updateStatus(Long id, UpdateStatusDTO dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        try {
            AppointmentStatus status = AppointmentStatus.valueOf(dto.getStatus());
            appointment.setAppointmentStatus(status);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status inválido: " + dto.getStatus());
        }

        return appointmentRepository.save(appointment);
    }
}
