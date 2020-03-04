package kg.nurtelecom.hibernateDemo.repo;

import kg.nurtelecom.hibernateDemo.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}
