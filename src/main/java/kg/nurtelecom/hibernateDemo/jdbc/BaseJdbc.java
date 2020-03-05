package kg.nurtelecom.hibernateDemo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class BaseJdbc {
    protected void close(ResultSet rs, Statement statement, Connection connection){
        closeAutoCloseable(rs);
        closeAutoCloseable(statement);
        closeAutoCloseable(connection);
    }

    protected void closeAutoCloseable(AutoCloseable resource){
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
