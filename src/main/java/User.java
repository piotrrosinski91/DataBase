import lombok.*;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {
    private int id;
    private String username;
    private int age;
    private String city;

}
