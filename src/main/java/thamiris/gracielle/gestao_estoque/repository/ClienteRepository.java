package thamiris.gracielle.gestao_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thamiris.gracielle.gestao_estoque.model.Client;

public interface ClienteRepository extends JpaRepository <Client, Long> {
}
