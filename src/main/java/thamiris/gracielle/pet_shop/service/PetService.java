package thamiris.gracielle.pet_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thamiris.gracielle.pet_shop.dataTransferObject.PetDto;
import thamiris.gracielle.pet_shop.exception.BusinessException;
import thamiris.gracielle.pet_shop.exception.ResourceNotFoundException;
import thamiris.gracielle.pet_shop.mapper.PetMapper;
import thamiris.gracielle.pet_shop.model.Client;
import thamiris.gracielle.pet_shop.model.Pet;
import thamiris.gracielle.pet_shop.repository.ClientRepository;
import thamiris.gracielle.pet_shop.repository.PetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final ClientRepository clientRepository;
    private final PetMapper petMapper;

    public PetDto create(PetDto dto) {
        Client client = clientRepository.findById(dto.getDonoId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + dto.getDonoId()));
        
        Pet pet = petMapper.toEntity(dto);
        pet.setDono(client);
        Pet saved = petRepository.save(pet);
        return petMapper.toDTO(saved);
    }

    public List<PetDto> findAll() {
        return petRepository.findAll()
                .stream()
                .map(petMapper::toDTO)
                .toList();
    }

    public PetDto findById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com ID: " + id));
        return petMapper.toDTO(pet);
    }

    public List<PetDto> findByClientId(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + clientId));
        
        return petRepository.findByDono(client)
                .stream()
                .map(petMapper::toDTO)
                .toList();
    }

    public PetDto update(Long id, PetDto dto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com ID: " + id));
        
        if (dto.getDonoId() != null && !dto.getDonoId().equals(pet.getDono().getId())) {
            Client newClient = clientRepository.findById(dto.getDonoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + dto.getDonoId()));
            pet.setDono(newClient);
        }
        
        pet.setNome(dto.getNome());
        pet.setEspecie(dto.getEspecie().toString());
        pet.setRaca(dto.getRaca());
        pet.setIdade(dto.getIdade());
        
        Pet updated = petRepository.save(pet);
        return petMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com ID: " + id));
        petRepository.delete(pet);
    }
}
