package szczepaniak.ppss.PersonalDataService.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import szczepaniak.ppss.PersonalDataService.infrastructure.exeption.ResourceNotFoundException;
import szczepaniak.ppss.PersonalDataService.model.Person;
import szczepaniak.ppss.PersonalDataService.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames={"People"})
public class PersonService {
    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @CacheEvict( value = "Person created",allEntries = true )
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @CachePut(value = "Person Updated")
    public Person update(Person person) {
        if (personRepository.existsById(person.getId())) {
            return personRepository.save(person);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @CacheEvict(allEntries = true)
    public void delete(String id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }
    @Cacheable( value = "Person getby ID" )
    public Person getById(String id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(ResourceNotFoundException::new);
    }
    @Cacheable(value = "GetPeople")
    public List<Person> getAll() {
        Iterable<Person> people = personRepository.findAll();
        return  Lists.newArrayList(people);
    }

}
