package szczepaniak.ppss.PersonalDataService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import szczepaniak.ppss.PersonalDataService.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

}
