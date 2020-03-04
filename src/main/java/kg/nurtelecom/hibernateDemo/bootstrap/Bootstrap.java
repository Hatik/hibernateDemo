package kg.nurtelecom.hibernateDemo.bootstrap;

import kg.nurtelecom.hibernateDemo.entities.Client;
import kg.nurtelecom.hibernateDemo.jdbc.ClientJdbc;
import kg.nurtelecom.hibernateDemo.repo.ClientRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bootstrap implements CommandLineRunner {
    @Autowired
    ClientJdbc clientJdbc;
    @Autowired
    ClientRepo clientRepo;
    @Override
    public void run(String... args) throws Exception {
        Client erkinAgay = Client.builder()
                .name("Erkin")
                .balance(1000L)
                .build();
        Client baike = Client.builder()
                .name("Baike")
                .balance(null)
                .build();
        clientRepo.save(erkinAgay);
        clientRepo.save(baike);

        System.out.println(clientJdbc.getAllClient());
        System.out.println(clientRepo.findAll());

    }
}
