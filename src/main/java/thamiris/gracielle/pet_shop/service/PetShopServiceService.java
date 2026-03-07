package thamiris.gracielle.pet_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thamiris.gracielle.pet_shop.exception.ResourceNotFoundException;
import thamiris.gracielle.pet_shop.model.PetShopService;
import thamiris.gracielle.pet_shop.repository.PetShopServiceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetShopServiceService {

    private final PetShopServiceRepository petShopServiceRepository;

    public PetShopService create(PetShopService petShopService) {
        return petShopServiceRepository.save(petShopService);
    }

    public List<PetShopService> findAll() {
        return petShopServiceRepository.findAll();
    }

    public PetShopService findById(Long id) {
        return petShopServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + id));
    }

    public PetShopService update(Long id, PetShopService petShopService) {
        PetShopService existing = petShopServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + id));
        
        existing.setNome(petShopService.getNome());
        existing.setSpecies(petShopService.getSpecies());
        existing.setGender(petShopService.getGender());
        existing.setCategory(petShopService.getCategory());
        existing.setSize(petShopService.getSize());
        existing.setPreco(petShopService.getPreco());
        
        return petShopServiceRepository.save(existing);
    }

    public void delete(Long id) {
        PetShopService petShopService = petShopServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + id));
        petShopServiceRepository.delete(petShopService);
    }
}
