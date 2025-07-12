package thamiris.gracielle.gestao_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.security.Provider;

public interface ServiceRepository extends JpaRepository <Service, Long> {
}
