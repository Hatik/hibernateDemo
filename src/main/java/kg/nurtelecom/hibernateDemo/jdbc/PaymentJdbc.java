package kg.nurtelecom.hibernateDemo.jdbc;

import kg.nurtelecom.hibernateDemo.entities.Client;
import kg.nurtelecom.hibernateDemo.models.PaymentReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentJdbc extends BaseJdbc{
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    @Autowired
    DataSource dataSource;

    public List<PaymentReportModel> getAllPaymentsReport() {
        List<PaymentReportModel> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try{
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT p.*, c.name as CLIENT_NAME FROM Payment p left join client c on p.client_id = c.id");
            while(rs.next()) {
                result.add(PaymentReportModel.builder()
                        .id(rs.getLong("ID"))
                        .amount(rs.getLong("AMOUNT"))
                        .clientName(rs.getString("CLIENT_NAME"))
                        .build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, statement, connection);
        }
        return result;
    }
}
