package thamiris.gracielle.gestao_estoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thamiris.gracielle.gestao_estoque.model.Client;
import thamiris.gracielle.gestao_estoque.repository.ClientRepository;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    private ClientRepository clientRepository;


    @GetMapping
    public List<Client> listAll(){
        return  clientRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id){
        return clientRepository.findById(id)
                .map(client -> ResponseEntity.ok(client))
                .orElse(ResponseEntity.notFound().build());
    }

}
