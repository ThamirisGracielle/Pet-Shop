package thamiris.gracielle.pet_shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thamiris.gracielle.pet_shop.model.Pet;
import thamiris.gracielle.pet_shop.repository.ClientRepository;
import thamiris.gracielle.pet_shop.repository.PetRepository;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClientRepository clientRepository;

   @PostMapping
   public ResponseEntity<String> createPet (@RequestBody @Valid Pet pet){
       petRepository.save(pet);
       return ResponseEntity.ok("Pet cadastrado com sucesso!");
   }

    @GetMapping
    public List<Pet> listAll(){
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id){
         return  petRepository.findById(id)
                 .map(pet -> ResponseEntity.ok(pet))
                 .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dono/{id}")
    public ResponseEntity<List<Pet>> listByClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    List<Pet> pets = petRepository.findByDono(client);
                    return ResponseEntity.ok(pets);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updatePet (@PathVariable Long id, @RequestBody @Valid Pet pet){
       petRepository.findById(id)
               .map(pet1 -> {
                   pet.setNome(pet.getNome());
                   pet.setEspecie(pet.getEspecie());
                   pet.setRaca(pet.getRaca());
                   pet.setIdade(pet.getIdade());
                   pet.setDono(pet.getDono());

                   petRepository.save(pet);
                   return ResponseEntity.ok("Pet atualizado com sucesso");
               });
       return ResponseEntity.status(404).body("Pet nao encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet (@PathVariable Long id){
       return petRepository
               .findById(id)
               .map( pet -> { petRepository.delete(pet);
                   return ResponseEntity.ok("Exclusão realizada com sucesso");
               })
               .orElseThrow(() -> new RuntimeException("Não foi possivel excluir"));
    }
}
