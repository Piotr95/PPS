package szczepaniak.ppss.PersonalDataService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Document(collection = "people")
@Data
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private UUID userID;

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;

    private Gender gender;

    private Set<String> interests;


}
