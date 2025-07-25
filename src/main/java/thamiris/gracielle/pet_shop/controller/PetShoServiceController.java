package thamiris.gracielle.pet_shop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thamiris.gracielle.pet_shop.model.PetShopService;
import thamiris.gracielle.pet_shop.repository.PetShopServiceRepository;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class PetShoServiceController {

    @Autowired
    PetShopServiceRepository petShopServiceRepository;


    @PostMapping
    public ResponseEntity<String> createPetShopService (@RequestBody @Valid PetShopService petShopService){
        petShopServiceRepository.save(petShopService);
        return ResponseEntity.ok().body("Serviço criado com sucesso!");
    }

    @GetMapping
    public List<PetShopService> listALL(){
        return petShopServiceRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePetShopService(@PathVariable Long id, @RequestBody @Valid PetShopService petShopService){
        petShopServiceRepository.findById(id)
                .map(petShopService1 ->{

                    petShopService.setNome(petShopService.getNome());
                    petShopService.setSpecies(petShopService.getSpecies());
                    petShopService.setGender(petShopService.getGender());
                    petShopService.setCategory(petShopService.getCategory());
                    petShopService.setSize(petShopService.getSize());
                    petShopService.setPreco(petShopService.getPreco());

                    petShopServiceRepository.save(petShopService);
                    return ResponseEntity.ok(" Serviço atualizado com sucesso");

                });
        return  ResponseEntity.status(404).body("Serviço não encontrado!");
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletePetShoService(@PathVariable Long id){
        return petShopServiceRepository.findById(id)
                .map(petShopService -> { petShopServiceRepository.delete(petShopService );
                    return ResponseEntity.ok("Servico deletado com sucesso!");
                })
                .orElseThrow(() -> new  RuntimeException("Não foi possivel excluir"));
    }

}
