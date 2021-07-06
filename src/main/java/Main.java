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
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int age = resultSet.getInt("age");
                String city = resultSet.getString("city");
                User user = new User(id, username, age, city);
                System.out.println(user.toString());
            }
        }
            catch(SQLException e){
                e.printStackTrace();
            }

    }
}
