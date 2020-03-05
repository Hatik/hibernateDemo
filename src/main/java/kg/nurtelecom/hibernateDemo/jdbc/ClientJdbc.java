package kg.nurtelecom.hibernateDemo.jdbc;

import kg.nurtelecom.hibernateDemo.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientJdbc extends BaseJdbc {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    @Autowired
    DataSource dataSource;

    public List<Client> getAllClient() {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            Long before = System.currentTimeMillis();
            connection = DriverManager.getConnection(url, username, password);
            Long after = System.currentTimeMillis();
            System.err.println("Connection took: " + (after-before));
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM CLIENT");
            while(rs.next()) {
                clients.add(Client.builder()
                        .id(rs.getLong("ID"))
                        .balance(rs.getLong("BALANCE"))
                        .name(rs.getString("NAME"))
                        .build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, statement, connection);
        }
        return clients;
    }

    public List<Client> getAllClientUsingDbPool() {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            Long before = System.currentTimeMillis();
            connection = dataSource.getConnection();
            Long after = System.currentTimeMillis();
            System.err.println("Connection in pool took: " + (after-before));
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM CLIENT");
            while(rs.next()) {
                clients.add(Client.builder()
                        .id(rs.getLong("ID"))
                        .balance(rs.getLong("BALANCE"))
                        .name(rs.getString("NAME"))
                        .build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, statement, connection);
        }
        return clients;
    }

}
