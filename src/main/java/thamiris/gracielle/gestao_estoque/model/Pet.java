package thamiris.gracielle.gestao_estoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

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

    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate idade;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "Dono é obrigatório")
    private Client dono;



}
