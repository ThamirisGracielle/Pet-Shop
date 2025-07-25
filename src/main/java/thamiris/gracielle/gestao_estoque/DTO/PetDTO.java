package thamiris.gracielle.gestao_estoque.DTO;

import lombok.Data;
import thamiris.gracielle.gestao_estoque.model.Species;

import java.time.LocalDate;

@Data
public class PetDTO {
    private Long id;
    private String nome;
    private Species especie;
    private String raca;
    private LocalDate idade;
    private Long donoId;

}
