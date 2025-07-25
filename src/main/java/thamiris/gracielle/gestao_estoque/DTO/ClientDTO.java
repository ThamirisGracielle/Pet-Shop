package thamiris.gracielle.gestao_estoque.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private List<PetDTO> pets;

}
