package thamiris.gracielle.pet_shop.DTO;

import lombok.Data;
import thamiris.gracielle.pet_shop.model.Species;

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
