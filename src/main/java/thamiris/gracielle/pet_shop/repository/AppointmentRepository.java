package thamiris.gracielle.pet_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thamiris.gracielle.pet_shop.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPetId(Long petId);
    List<Appointment> findByDataHoraBetween(LocalDateTime start, LocalDateTime end);

}
