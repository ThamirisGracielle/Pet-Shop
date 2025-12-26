package thamiris.gracielle.pet_shop.dataTransferObject;

import lombok.Data;

import java.util.List;

@Data
public class ClientDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private List<PetDto> pets;

}
