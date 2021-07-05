import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = JdbcConfig.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from users");

            while(resultSet.next()){
                String nameOfQuestion = resultSet.getString("username");
                System.out.println(nameOfQuestion);
            }
        }
            catch(SQLException e){
                e.printStackTrace();
            }

    }
}
