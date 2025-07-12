package thamiris.gracielle.gestao_estoque.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import thamiris.gracielle.gestao_estoque.model.Categoria;
import thamiris.gracielle.gestao_estoque.model.PetShopService;
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
                    new PetShopService(null, "Banho Pequeno", 40.00, Categoria.HIGIENE_ESTETICA)
            );


            petShopServiceRepository.saveAll(servicosIniciais);
            System.out.println("Serviços iniciais cadastrados no banco.");
        }
    }
}
