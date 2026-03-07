package thamiris.gracielle.pet_shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thamiris.gracielle.pet_shop.dataTransferObject.ClientDto;
import thamiris.gracielle.pet_shop.service.ClientService;

import java.util.List;

/**
 * Controller responsável por gerenciar operações de clientes
 */
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClientController {

    private final ClientService clientService;

    /**
     * Cadastra um novo cliente
     * @param dto Dados do cliente
     * @return Cliente cadastrado
     */
    @Operation(summary = "Criar cliente", description = "Cadastra um novo cliente no sistema")
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto dto) {
        ClientDto created = clientService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Lista todos os clientes
     * @return Lista de clientes
     */
    @Operation(summary = "Listar clientes", description = "Retorna todos os clientes cadastrados")
    @GetMapping
    public ResponseEntity<List<ClientDto>> listAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    /**
     * Busca cliente por ID
     * @param id ID do cliente
     * @return Cliente encontrado
     */
    @Operation(summary = "Buscar cliente", description = "Busca um cliente específico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> listById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    /**
     * Atualiza dados do cliente
     * @param id ID do cliente
     * @param dto Novos dados do cliente
     * @return Cliente atualizado
     */
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody @Valid ClientDto dto) {
        ClientDto updated = clientService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Remove um cliente
     * @param id ID do cliente
     * @return Resposta sem conteúdo
     */
    @Operation(summary = "Deletar cliente", description = "Remove um cliente do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}