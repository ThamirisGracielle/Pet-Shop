package thamiris.gracielle.pet_shop.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import thamiris.gracielle.pet_shop.model.*;
import thamiris.gracielle.pet_shop.model.enums.Category;
import thamiris.gracielle.pet_shop.model.enums.Gender;
import thamiris.gracielle.pet_shop.model.enums.Size;
import thamiris.gracielle.pet_shop.model.enums.Species;
import thamiris.gracielle.pet_shop.repository.PetShopServiceRepository;

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
                    new PetShopService(null, "Banho", Species.GERAL,null, Category.HIGIENE_ESTETICA, Size.PEQUENO, 40.00),
                    new PetShopService(null, "Banho", Species.GERAL,null, Category.HIGIENE_ESTETICA, Size.MEDIO, 55.00),
                    new PetShopService(null, "Banho", Species.GERAL,null, Category.HIGIENE_ESTETICA, Size.GRANDE, 70.00),
                    new PetShopService(null, "Banho", Species.GERAL,null, Category.HIGIENE_ESTETICA, Size.GIGANTE, 90.00),
                    new PetShopService(null, "Tosa Higiênica", Species.GERAL,null, Category.HIGIENE_ESTETICA, Size.GERAL, 35.00),
                    new PetShopService(null, "Limpeza de Ouvido",  Species.GERAL,null, Category.HIGIENE_ESTETICA, Size.GERAL, 20.00),
                    new PetShopService(null, "Escovação de Dente", Species.GERAL, null,  Category.HIGIENE_ESTETICA, Size.GERAL, 90.00),
                    new PetShopService(null, "Escovação de Dente", Species.GERAL,null,  Category.HIGIENE_ESTETICA, Size.GERAL, 90.00),
                    new PetShopService(null, "Vacinação V10", Species.CACHORRO, null, Category.SAUDE, Size.GERAL, 90.00),
                    new PetShopService(null, "vacinação V4", Species.GATO,null,  Category.SAUDE, Size.GERAL, 85.00),
                    new PetShopService(null, "Antirrábica", Species.GERAL, null,  Category.SAUDE, Size.GERAL, 35.00),
                    new PetShopService(null, "Vermifugação", Species.GERAL,  null, Category.SAUDE, Size.GERAL, 85.00),
                    new PetShopService(null, "Consulta Veterinária", Species.GERAL,  null, Category.SAUDE, Size.GERAL, 120.00),
                    new PetShopService(null, "Castração", Species.GATO,  Gender.MACHO, Category.SAUDE, Size.GERAL, 180.00),
                    new PetShopService(null, "Castração", Species.GATO,  Gender.FEMEA, Category.SAUDE, Size.GERAL, 250.00),
                    new PetShopService(null, "Castração", Species.CACHORRO,  Gender.FEMEA, Category.SAUDE, Size.GERAL, 300.00),
                    new PetShopService(null, "Castração", Species.CACHORRO,  Gender.MACHO, Category.SAUDE, Size.GERAL, 450.00)

            );


            petShopServiceRepository.saveAll(servicosIniciais);
            System.out.println("Serviços iniciais cadastrados no banco.");
        }
    }
}
