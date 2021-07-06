import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    private static Connection CONFIG = null;

    private JdbcConfig(){}

    public static Connection getConnection(){
        if(CONFIG == null) {
            try {
                Class.forName(JDBC_DRIVER);
                CONFIG = DriverManager.getConnection(DB_URL, "root", "");
                return CONFIG;

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return CONFIG;
    }
}
