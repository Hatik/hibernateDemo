package kg.nurtelecom.hibernateDemo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfig extends HikariConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver.class-name}")
    private String driverClassName;




    @Bean
    public DataSource dataSource() {
        this.setJdbcUrl(url);
        this.setUsername(username);
        this.setPassword(password);
        this.setDriverClassName(driverClassName);
        return new HikariDataSource(this);
    }
}
