import java.sql.*;

public class Database {
    Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./cache");
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS CACHE(ARGUMENT INT PRIMARY KEY, RESULT INT NOT NULL)");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void save(int argument, int result) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO CACHE VALUES(?, ?)")) {
            statement.setInt(1, argument);
            statement.setInt(2, result);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    int load(int argument) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT RESULT FROM CACHE WHERE ARGUMENT = ?")) {
            statement.setInt(1, argument);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
