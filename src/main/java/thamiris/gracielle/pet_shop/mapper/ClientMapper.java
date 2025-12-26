package thamiris.gracielle.pet_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import thamiris.gracielle.pet_shop.dataTransferObject.ClientDto;
import thamiris.gracielle.pet_shop.model.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "pets", ignore = true)
    ClientDto toDto(Client client) ;
    
    Client toEntity (ClientDto clientDTO);

}
