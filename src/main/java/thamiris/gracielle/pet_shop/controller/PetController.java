package thamiris.gracielle.pet_shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thamiris.gracielle.pet_shop.dataTransferObject.PetDto;
import thamiris.gracielle.pet_shop.service.PetService;

import java.util.List;

/**
 * Controller responsável por gerenciar operações de pets
 */
@Tag(name = "Pets", description = "Endpoints para gerenciamento de pets")
@RequiredArgsConstructor
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    /**
     * Cadastra um novo pet
     * @param dto Dados do pet
     * @return Pet cadastrado
     */
    @Operation(summary = "Criar pet", description = "Cadastra um novo pet no sistema")
    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody @Valid PetDto dto) {
        PetDto created = petService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Lista todos os pets
     * @return Lista de pets
     */
    @Operation(summary = "Listar pets", description = "Retorna todos os pets cadastrados")
    @GetMapping
    public ResponseEntity<List<PetDto>> listAll() {
        return ResponseEntity.ok(petService.findAll());
    }

    /**
     * Busca pet por ID
     * @param id ID do pet
     * @return Pet encontrado
     */
    @Operation(summary = "Buscar pet", description = "Busca um pet específico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PetDto> listById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.findById(id));
    }

    /**
     * Lista pets de um cliente específico
     * @param id ID do cliente
     * @return Lista de pets do cliente
     */
    @Operation(summary = "Listar pets por cliente", description = "Retorna todos os pets de um cliente específico")
    @GetMapping("/dono/{id}")
    public ResponseEntity<List<PetDto>> listByClient(@PathVariable Long id) {
        return ResponseEntity.ok(petService.findByClientId(id));
    }

    /**
     * Atualiza dados do pet
     * @param id ID do pet
     * @param dto Novos dados do pet
     * @return Pet atualizado
     */
    @Operation(summary = "Atualizar pet", description = "Atualiza os dados de um pet existente")
    @PutMapping("/{id}")
    public ResponseEntity<PetDto> updatePet(@PathVariable Long id, @RequestBody @Valid PetDto dto) {
        PetDto updated = petService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Remove um pet
     * @param id ID do pet
     * @return Resposta sem conteúdo
     */
    @Operation(summary = "Deletar pet", description = "Remove um pet do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
