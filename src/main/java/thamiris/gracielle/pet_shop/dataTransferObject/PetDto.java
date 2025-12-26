package thamiris.gracielle.pet_shop.dataTransferObject;

import lombok.Data;
import thamiris.gracielle.pet_shop.model.enums.Species;

import java.time.LocalDate;

@Data
public class PetDto {
    private Long id;
    private String nome;
    private Species especie;
    private String raca;
    private LocalDate idade;
    private Long donoId;

}
