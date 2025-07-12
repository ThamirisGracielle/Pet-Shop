package thamiris.gracielle.gestao_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thamiris.gracielle.gestao_estoque.model.Client;
import thamiris.gracielle.gestao_estoque.model.Pet;

import java.util.List;

public interface PetRepository  extends JpaRepository <Pet,Long> {

    List<Pet> findByDono(Client dono);
}
