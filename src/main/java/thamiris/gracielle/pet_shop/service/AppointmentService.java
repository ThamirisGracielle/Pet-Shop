package thamiris.gracielle.pet_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thamiris.gracielle.pet_shop.dataTransferObject.AppointmentDto;
import thamiris.gracielle.pet_shop.dataTransferObject.UpdateStatusDto;
import thamiris.gracielle.pet_shop.exception.BusinessException;
import thamiris.gracielle.pet_shop.exception.ResourceNotFoundException;
import thamiris.gracielle.pet_shop.model.*;
import thamiris.gracielle.pet_shop.model.enums.AppointmentStatus;
import thamiris.gracielle.pet_shop.repository.AppointmentRepository;
import thamiris.gracielle.pet_shop.repository.ClientRepository;
import thamiris.gracielle.pet_shop.repository.PetRepository;
import thamiris.gracielle.pet_shop.repository.PetShopServiceRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final PetRepository petRepository;
    private final PetShopServiceRepository serviceRepository;

    public Appointment create(AppointmentDto dto) {
        if (dto.getDataHora() == null || dto.getDataHora().isBefore(LocalDateTime.now())) {
            throw new BusinessException("A data ou hora do agendamento é inválida!");
        }

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + dto.getClientId()));

        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com ID: " + dto.getPetId()));

        PetShopService service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + dto.getServiceId()));

        if (!pet.getDono().getId().equals(client.getId())) {
            throw new BusinessException("O pet não pertence ao cliente informado");
        }

        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setPet(pet);
        appointment.setPetShopService(service);
        appointment.setDataHora(dto.getDataHora());
        appointment.setAppointmentStatus(AppointmentStatus.AGENDADO);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
    }

    public List<Appointment> findByPetId(Long petId) {
        return appointmentRepository.findByPetId(petId);
    }

    public List<Appointment> findByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return appointmentRepository.findByDataHoraBetween(startOfDay, endOfDay);
    }

    public Appointment cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));

        if (appointment.getAppointmentStatus() == AppointmentStatus.CANCELADO) {
            throw new BusinessException("Agendamento já está cancelado");
        }

        appointment.setAppointmentStatus(AppointmentStatus.CANCELADO);
        return appointmentRepository.save(appointment);
    }

    public Appointment updateStatus(Long id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));

        appointment.setAppointmentStatus(status);
        return appointmentRepository.save(appointment);
    }
}
