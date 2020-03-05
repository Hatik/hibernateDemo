package kg.nurtelecom.hibernateDemo.bootstrap;

import kg.nurtelecom.hibernateDemo.entities.Client;
import kg.nurtelecom.hibernateDemo.entities.Payment;
import kg.nurtelecom.hibernateDemo.jdbc.ClientJdbc;
import kg.nurtelecom.hibernateDemo.repo.ClientRepo;
import kg.nurtelecom.hibernateDemo.repo.PaymentRepo;
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
    @Autowired
    PaymentRepo paymentRepo;

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

        Payment payment = Payment.builder()
                .amount(1000L)
                .client(Client.builder().id(1L).build())
                .build();
        Payment payment2 = Payment.builder()
                .amount(1000L)
                .client(erkinAgay)
                .build();
        paymentRepo.save(payment);
        paymentRepo.save(payment2);

        System.out.println(clientJdbc.getAllClient());
        System.out.println(clientJdbc.getAllClient());
        System.out.println(clientJdbc.getAllClientUsingDbPool());
        System.out.println(clientJdbc.getAllClientUsingDbPool());
        System.out.println(clientRepo.findAll());
        System.out.println(paymentRepo.findAll());
        Thread.currentThread().join();
    }
}
