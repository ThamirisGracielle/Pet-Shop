package thamiris.gracielle.pet_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thamiris.gracielle.pet_shop.DTO.PetDTO;
import thamiris.gracielle.pet_shop.model.Pet;

@Mapper(componentModel = "spring")
public interface PetMapper {
    @Mapping(source = "dono.id", target = "donoId")
    PetDTO toDTO(Pet pet);
    
    @Mapping(source = "donoId", target = "dono.id")
    Pet toEntity(PetDTO petDTO);
}