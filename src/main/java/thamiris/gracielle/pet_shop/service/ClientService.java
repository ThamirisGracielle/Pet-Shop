package thamiris.gracielle.pet_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thamiris.gracielle.pet_shop.dataTransferObject.ClientDto;
import thamiris.gracielle.pet_shop.exception.ResourceNotFoundException;
import thamiris.gracielle.pet_shop.mapper.ClientMapper;
import thamiris.gracielle.pet_shop.model.Client;
import thamiris.gracielle.pet_shop.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDto create(ClientDto dto) {
        Client client = clientMapper.toEntity(dto);
        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }

    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        return clientMapper.toDto(client);
    }

    public ClientDto update(Long id, ClientDto dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        
        client.setNome(dto.getNome());
        client.setEmail(dto.getEmail());
        client.setTelefone(dto.getTelefone());
        
        Client updated = clientRepository.save(client);
        return clientMapper.toDto(updated);
    }

    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        clientRepository.delete(client);
    }
}
