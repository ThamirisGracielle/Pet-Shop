package thamiris.gracielle.gestao_estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "Espécie é obrigatório")
    private String especie;

    @NotBlank(message = "Raça é obrigatório")
    private String raca;

    @NotBlank(message = "Nome do dono é obrigatório")
    private Client dono;



}
