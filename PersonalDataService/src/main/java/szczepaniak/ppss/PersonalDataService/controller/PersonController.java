package szczepaniak.ppss.PersonalDataService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import szczepaniak.ppss.PersonalDataService.model.Person;
import szczepaniak.ppss.PersonalDataService.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping()
    public Person create(@RequestBody Person person) {
       return personService.create(person);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        personService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Person getById(@PathVariable("id") String id) {
        return personService.getById(id);
    }
    @GetMapping()
    public List<Person> getPeople() {
        return personService.getAll();
    }
}
