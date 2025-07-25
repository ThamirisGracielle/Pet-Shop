package thamiris.gracielle.pet_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thamiris.gracielle.pet_shop.model.PetShopService;

public interface PetShopServiceRepository extends JpaRepository <PetShopService, Long> {
}
