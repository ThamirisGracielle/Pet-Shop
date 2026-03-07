package thamiris.gracielle.pet_shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thamiris.gracielle.pet_shop.model.PetShopService;
import thamiris.gracielle.pet_shop.service.PetShopServiceService;

import java.util.List;

/**
 * Controller responsável por gerenciar serviços do pet shop
 */
@Tag(name = "Serviços", description = "Endpoints para gerenciamento de serviços")
@RequiredArgsConstructor
@RestController
@RequestMapping("/servicos")
public class PetShoServiceController {

    private final PetShopServiceService petShopServiceService;

    /**
     * Cadastra um novo serviço
     * @param petShopService Dados do serviço
     * @return Serviço cadastrado
     */
    @Operation(summary = "Criar serviço", description = "Cadastra um novo serviço no pet shop")
    @PostMapping
    public ResponseEntity<PetShopService> createPetShopService(@RequestBody @Valid PetShopService petShopService) {
        PetShopService created = petShopServiceService.create(petShopService);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Lista todos os serviços
     * @return Lista de serviços
     */
    @Operation(summary = "Listar serviços", description = "Retorna todos os serviços disponíveis")
    @GetMapping
    public ResponseEntity<List<PetShopService>> listAll() {
        return ResponseEntity.ok(petShopServiceService.findAll());
    }

    /**
     * Busca serviço por ID
     * @param id ID do serviço
     * @return Serviço encontrado
     */
    @Operation(summary = "Buscar serviço", description = "Busca um serviço específico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PetShopService> findById(@PathVariable Long id) {
        return ResponseEntity.ok(petShopServiceService.findById(id));
    }

    /**
     * Atualiza dados do serviço
     * @param id ID do serviço
     * @param petShopService Novos dados do serviço
     * @return Serviço atualizado
     */
    @Operation(summary = "Atualizar serviço", description = "Atualiza os dados de um serviço existente")
    @PutMapping("/{id}")
    public ResponseEntity<PetShopService> updatePetShopService(@PathVariable Long id, @RequestBody @Valid PetShopService petShopService) {
        PetShopService updated = petShopServiceService.update(id, petShopService);
        return ResponseEntity.ok(updated);
    }

    /**
     * Remove um serviço
     * @param id ID do serviço
     * @return Resposta sem conteúdo
     */
    @Operation(summary = "Deletar serviço", description = "Remove um serviço do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetShopService(@PathVariable Long id) {
        petShopServiceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
