package thamiris.gracielle.gestao_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import thamiris.gracielle.gestao_estoque.model.PetShopService;

public interface PetShopServiceRepository extends JpaRepository <PetShopService, Long> {
}
