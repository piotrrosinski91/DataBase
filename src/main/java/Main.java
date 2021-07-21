import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
    private static Connection connection = JdbcConfig.getConnection();
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * from users");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int age = resultSet.getInt("age");
                String city = resultSet.getString("city");
                User user = new User(id, username, age, city);
                System.out.println(user.toString());
            }

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

            while (resultsetAfterUserAdd.next()) {
                int id = resultsetAfterUserAdd.getInt("id");
                String username = resultsetAfterUserAdd.getString("username");
                int age = resultsetAfterUserAdd.getInt("age");
                String city = resultsetAfterUserAdd.getString("city");
                User user = new User(id, username, age, city);
                System.out.println(user.toString());
            }
            findYoungestUser();

            findYoungestUser();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findYoungestUser() throws SQLException {
        ArrayList<Integer> listOfAges = new ArrayList<>();
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from users");
        ResultSet resultSet1 = statement1.executeQuery("SELECT * from users");

        while (resultSet.next()) {
            listOfAges.add(resultSet.getInt("age"));
        }
        int youngestUSer = listOfAges.stream()
                .min(Integer::compareTo)
                .get();

        while (resultSet1.next()) {
            if (youngestUSer == resultSet1.getInt("age")) {
                System.out.println("\nThe Youngest user:");
                User user = new User(resultSet1.getInt("id"), resultSet1.getString("username"),
                                     resultSet1.getInt("age"), resultSet1.getString("city"));

                System.out.println(user.toString());
            }
        }

    }
}



