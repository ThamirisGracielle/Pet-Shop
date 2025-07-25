package thamiris.gracielle.pet_shop.mapper;

import org.mapstruct.Mapper;
import thamiris.gracielle.pet_shop.DTO.ClientDTO;
import thamiris.gracielle.pet_shop.model.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDto(Client client) ;
    Client toEntity (ClientDTO clientDTO);

}
