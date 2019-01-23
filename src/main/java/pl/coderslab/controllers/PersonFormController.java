package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.dao.PersonDao;
import pl.coderslab.domain.model.Person;
import pl.coderslab.domain.model.PersonDetails;

@Controller
@RequestMapping("/persons/form")
public class PersonFormController {

    private PersonDao personDao;

    public PersonFormController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping("/add")
    public String prepareAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "/add-person-form";
    }

    @PostMapping("/add")
    @ResponseBody
    public String savePerson(Person person) {
        person.setDetails(new PersonDetails());
        personDao.save(person);
        return person.toString();
    }
}
