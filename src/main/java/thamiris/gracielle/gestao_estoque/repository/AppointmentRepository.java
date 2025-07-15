package thamiris.gracielle.gestao_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thamiris.gracielle.gestao_estoque.model.Appointment;
import thamiris.gracielle.gestao_estoque.model.Client;
import thamiris.gracielle.gestao_estoque.model.Pet;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPetId(Long petId);
    List<Appointment> findByDate(LocalDateTime start, LocalDateTime end);
}
