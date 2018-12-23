package szczepaniak.ppss.StackService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.HashSet;
import java.util.Set;
@Document("Masterpiece")
public class Masterpiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private  Set<Image> images;
    Set<Tag> tags= new HashSet<>();

}
