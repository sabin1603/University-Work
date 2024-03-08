package repository;

import domain.Identifiable;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
public abstract class DatabaseRepository<ID, T extends Identifiable<ID>> extends GenericRepository<ID, T> {

    protected final String URL = "jdbc:sqlite:CarRentals.sqlite";

    protected String tableName;

    protected Connection conn = null;

    public DatabaseRepository(String tableName){this.tableName = tableName;}

    public abstract void getData();

    public void openConnection() throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(URL);
        if (conn == null || conn.isClosed())
            conn = dataSource.getConnection();
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }
}
