package szczepaniak.ppss.StackService.model;

//import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

import java.util.UUID;

@Document("Image")
@Data
public class Image  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private UUID ownerID;

    String imageURL;

    Resolution resolution;
    Money


}
