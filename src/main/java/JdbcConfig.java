import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    private static Connection CONFIG;

    private JdbcConfig(){}

    public static Connection getConnection(){
        if(CONFIG == null) {
            try {
                Class.forName(JDBC_DRIVER);
                Connection connection = DriverManager.getConnection(DB_URL, "root", "");
                return connection;

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return CONFIG;
    }
}
