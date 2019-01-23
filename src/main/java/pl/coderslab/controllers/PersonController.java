package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.dao.PersonDao;
import pl.coderslab.domain.model.Person;
import pl.coderslab.domain.model.PersonDetails;

import java.util.Objects;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "/persons", produces = "text/plain;charset=UTF-8")
public class PersonController {

    private PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping("/find/{id:[1-9][0-9]*}")
    public String get(@PathVariable Long id) {
        Person person = personDao.find(id);
        if (Objects.isNull(person)) {
            return "Nie znaleziono osoby";
        }

        return person.toString().concat(", details: ").concat(person.getDetails().toString());
    }

    @GetMapping("/update/{id:[1-9][0-9]*}")
    public String update(@PathVariable Long id,
                         @RequestParam(required = false) String fN,
                         @RequestParam(required = false) String lN,
                         @RequestParam(required = false) String sN,
                         @RequestParam(required = false) String s,
                         @RequestParam(required = false) String c) {
        Person person = personDao.find(id);
        if (Objects.isNull(person)) {
            return "Nie znaleziono osoby";
        }

        PersonDetails details = person.getDetails();
        Optional.ofNullable(fN).ifPresent(details::setFirstName);
        Optional.ofNullable(lN).ifPresent(details::setLastName);
        Optional.ofNullable(sN).ifPresent(details::setStreetNumber);
        Optional.ofNullable(s).ifPresent(details::setStreet);
        Optional.ofNullable(c).ifPresent(details::setCity);
        personDao.update(person);

        return person.toString().concat(", details: ").concat(person.getDetails().toString());
    }

    @GetMapping("/delete/{id:[1-9][0-9]*}")
    public String delete(@PathVariable Long id) {
        Person person = personDao.find(id);
        if (Objects.isNull(person)) {
            return "Nie znaleziono osoby";
        }

        personDao.remove(id);
        return "Usunięto osobę o id " + id;
    }

    @GetMapping("/create")
    public String create(String login, String email, String password,
                         @RequestParam(required = false) String fN,
                         @RequestParam(required = false) String lN,
                         @RequestParam(required = false) String sN,
                         @RequestParam(required = false) String s,
                         @RequestParam(required = false) String c) {
        Person person = new Person();
        person.setLogin(login);
        person.setPassword(password);
        person.setEmail(email);
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName(fN);
        personDetails.setLastName(lN);
        personDetails.setStreet(s);
        personDetails.setStreetNumber(sN);
        personDetails.setCity(c);

        person.addDetails(personDetails);
        personDao.save(person);

        return person.toString().concat(", details: ").concat(person.getDetails().toString());
    }
}