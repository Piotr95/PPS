package szczepaniak.ppss.StackService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import szczepaniak.ppss.StackService.model.Image;
@Repository
public interface ImageRepository extends MongoRepository<Image,String> {
}
