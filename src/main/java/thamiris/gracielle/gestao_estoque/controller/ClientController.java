package thamiris.gracielle.gestao_estoque.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thamiris.gracielle.gestao_estoque.model.Client;
import thamiris.gracielle.gestao_estoque.repository.ClientRepository;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class ClientController {

    private ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody @Valid Client client) {
        clientRepository.save(client);
        return ResponseEntity.ok("Cliente cadastrado!");
    }


    @GetMapping
    public List<Client> listAll() {
        return clientRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(client -> ResponseEntity.ok(client))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody @Valid Client client) {
        clientRepository.findById(id)
                .map(client1 -> {
                    client.setNome(client.getNome());
                    client.setTelefone(client.getTelefone());
                    client.setEmail(client.getEmail());

                    clientRepository.save(client);
                    return ResponseEntity.ok("Cliente atualizado com sucesso");
                });
        return ResponseEntity.status(404).body("Cliente não encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id){
            return clientRepository
                    .findById(id)
                    .map(client -> { clientRepository.delete(client);
                        return ResponseEntity.ok("Exclusao realizada com sucesso");
                    })
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}