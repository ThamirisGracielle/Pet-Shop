package thamiris.gracielle.gestao_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thamiris.gracielle.gestao_estoque.model.Pet;

public interface PetRepository  extends JpaRepository <Pet,Long> {
}
