import lombok.RequiredArgsConstructor;

import java.sql.*;

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
            System.out.println();

            User userIntoDB = new User("Kamila", 29, "Katowice");


            System.out.println();
            System.out.println();
            System.out.println();


            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into users(username, age, city) VALUES(?, ?, ?)");
            preparedStatement.setString(1, userIntoDB.getUsername());
            preparedStatement.setInt(2, userIntoDB.getAge());
            preparedStatement.setString(3, userIntoDB.getCity());
            preparedStatement.execute();

            Statement statementAfterUserAdd = connection.createStatement();
            ResultSet resultsetAfterUserAdd = statementAfterUserAdd.executeQuery("SELECT * from users");



            while(resultsetAfterUserAdd.next()){
                int id = resultsetAfterUserAdd.getInt("id");
                String username = resultsetAfterUserAdd.getString("username");
                int age = resultsetAfterUserAdd.getInt("age");
                String city = resultsetAfterUserAdd.getString("city");
                User user = new User(id, username, age, city);
                System.out.println(user.toString());
            }


        }
            catch(SQLException e){
                e.printStackTrace();
            }

    }
}
