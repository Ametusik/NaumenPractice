package ru.Amet_Kurtumerov.tgBot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Amet_Kurtumerov.tgBot.entity.Client;
import ru.Amet_Kurtumerov.tgBot.repository.ClientRepository;

@SpringBootTest
public class FillingTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void createTwoClients() {
        Client client1 = new Client();
        client1.setAddress("address1");
        client1.setFullName("fullName1");
        Client client2 = new Client();
        client2.setAddress("address2");
        client2.setFullName("fullName2");
    }
}
