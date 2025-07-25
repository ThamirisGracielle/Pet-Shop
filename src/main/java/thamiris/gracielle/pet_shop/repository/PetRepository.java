package thamiris.gracielle.pet_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thamiris.gracielle.pet_shop.model.Client;
import thamiris.gracielle.pet_shop.model.Pet;

import java.util.List;

public interface PetRepository  extends JpaRepository <Pet,Long> {

    List<Pet> findByDono(Client dono);
}
