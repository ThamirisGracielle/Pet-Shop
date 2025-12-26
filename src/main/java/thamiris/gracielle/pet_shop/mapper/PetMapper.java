package thamiris.gracielle.pet_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thamiris.gracielle.pet_shop.dataTransferObject.PetDto;
import thamiris.gracielle.pet_shop.model.Pet;

@Mapper(componentModel = "spring")
public interface PetMapper {
    @Mapping(source = "dono.id", target = "donoId")
    PetDto toDTO(Pet pet);
    
    @Mapping(source = "donoId", target = "dono.id")
    Pet toEntity(PetDto petDTO);
}