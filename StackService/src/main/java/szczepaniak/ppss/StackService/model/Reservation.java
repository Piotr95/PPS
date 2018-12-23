package szczepaniak.ppss.StackService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Data
@Document("reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private  String masterpiece_id ;
    Image image;
    LocalDate reservation_start;
    LocalDate reservation_end;
}
