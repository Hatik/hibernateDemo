package kg.nurtelecom.hibernateDemo.repo;

import kg.nurtelecom.hibernateDemo.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
