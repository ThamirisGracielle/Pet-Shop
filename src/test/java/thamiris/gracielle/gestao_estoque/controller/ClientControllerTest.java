package thamiris.gracielle.gestao_estoque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import thamiris.gracielle.gestao_estoque.model.Client;
import thamiris.gracielle.gestao_estoque.repository.ClientRepository;

import java.util.Properties;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    // @Mock: cria objeto falso controlado pelo Mockito.

    @Mock
    private ClientRepository clientRepository;

    // @InjectMocks: cria o objeto da classe que quer testar, e insere os mocks nele.

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createClient_simpleTest() {
        Client client = new Client();

        client.setNome("Thamiris");
        client.setEmail("thamiris@email.com");
        client.setTelefone("1234567890");

        /*
         "Quando o método save for chamado com qualquer objeto do tipo Client
          (any(Client.class)), retorne esse mesmo objeto client que criei."
        */

        when (clientRepository.save(any(Client.class))).thenReturn(client);

        ResponseEntity<String> response = clientController.createClient(client);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Cliente cadastrado!", response.getBody());

        verify(clientRepository, times(1)).save(client);



    }




    @Test
    void listAll() {
    }

    @Test
    void listById() {
    }

    @Test
    void updateClient() {
    }

    @Test
    void deleteClient() {
    }
}