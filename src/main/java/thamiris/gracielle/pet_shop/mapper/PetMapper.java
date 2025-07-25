package thamiris.gracielle.pet_shop.mapper;

import org.mapstruct.Mapper;
import thamiris.gracielle.pet_shop.DTO.PetDTO;
import thamiris.gracielle.pet_shop.model.Pet;

@Mapper(componentModel = "spring")
public interface PetMapper {
    PetDTO toDTO(Pet pet);
    Pet toEntity(PetDTO petDTO);
}