package thamiris.gracielle.gestao_estoque.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import thamiris.gracielle.gestao_estoque.model.*;
import thamiris.gracielle.gestao_estoque.repository.PetShopServiceRepository;

import java.util.List;

@Component
public class ServiceInitializer implements CommandLineRunner {

    private final PetShopServiceRepository petShopServiceRepository;

    public ServiceInitializer(PetShopServiceRepository petShopServiceRepository) {
        this.petShopServiceRepository = petShopServiceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Só cadastra se não existir nenhum serviço no banco
        if (petShopServiceRepository.count()==0) {
            List<PetShopService> servicosIniciais = List.of(
                    new PetShopService(null, "Banho", Especie.GERAL,null, Categoria.HIGIENE_ESTETICA, Porte.PEQUENO, 40.00),
                    new PetShopService(null, "Banho", Especie.GERAL,null, Categoria.HIGIENE_ESTETICA, Porte.MEDIO, 55.00),
                    new PetShopService(null, "Banho",Especie.GERAL,null, Categoria.HIGIENE_ESTETICA, Porte.GRANDE, 70.00),
                    new PetShopService(null, "Banho", Especie.GERAL,null, Categoria.HIGIENE_ESTETICA, Porte.GIGANTE, 90.00),
                    new PetShopService(null, "Tosa Higiênica", Especie.GERAL,null, Categoria.HIGIENE_ESTETICA, Porte.GERAL, 35.00),
                    new PetShopService(null, "Limpeza de Ouvido",  Especie.GERAL,null, Categoria.HIGIENE_ESTETICA, Porte.GERAL, 20.00),
                    new PetShopService(null, "Escovação de Dente",Especie.GERAL, null,  Categoria.HIGIENE_ESTETICA, Porte.GERAL, 90.00),
                    new PetShopService(null, "Escovação de Dente",Especie.GERAL,null,  Categoria.HIGIENE_ESTETICA, Porte.GERAL, 90.00),
                    new PetShopService(null, "Vacinação V10",Especie.CACHORRO, null, Categoria.SAUDE, Porte.GERAL, 90.00),
                    new PetShopService(null, "vacinação V4",Especie.GATO,null,  Categoria.SAUDE, Porte.GERAL, 85.00),
                    new PetShopService(null, "Antirrábica",Especie.GERAL, null,  Categoria.SAUDE, Porte.GERAL, 35.00),
                    new PetShopService(null, "Vermifugação",Especie.GERAL,  null,Categoria.SAUDE, Porte.GERAL, 85.00),
                    new PetShopService(null, "Consulta Veterinária",Especie.GERAL,  null,Categoria.SAUDE, Porte.GERAL, 120.00),
                    new PetShopService(null, "Castração",Especie.GATO,  Gender.MACHO,Categoria.SAUDE, Porte.GERAL, 180.00),
                    new PetShopService(null, "Castração",Especie.GATO,  Gender.FEMEA,Categoria.SAUDE, Porte.GERAL, 250.00),
                    new PetShopService(null, "Castração",Especie.CACHORRO,  Gender.FEMEA,Categoria.SAUDE, Porte.GERAL, 300.00),
                    new PetShopService(null, "Castração",Especie.CACHORRO,  Gender.MACHO,Categoria.SAUDE, Porte.GERAL, 450.00)

            );


            petShopServiceRepository.saveAll(servicosIniciais);
            System.out.println("Serviços iniciais cadastrados no banco.");
        }
    }
}
