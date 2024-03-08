package utils;

import org.sqlite.SQLiteDataSource;

public class DataSourceUtil {

    public static SQLiteDataSource getDataSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:C:\\Users\\Sabin\\IdeaProjects\\a4-sabin1603\\CarRentals.sqlite");
        return dataSource;
    }
}
