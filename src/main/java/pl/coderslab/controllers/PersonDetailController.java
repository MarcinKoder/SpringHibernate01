package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.dao.PersonDao;
import pl.coderslab.domain.dto.PersonDTO;
import pl.coderslab.domain.model.Person;
import pl.coderslab.domain.model.PersonDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/persondetails/form")
public class PersonDetailController {


    @ModelAttribute("hobbies")
    public Collection<String> hobbies(){
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Java");
        hobbies.add("HTML");
        hobbies.add("Jakies głupoty");
        return hobbies;
    }

    @ModelAttribute("countries")
    public Collection<String> countries() {
        List<String> countries = new ArrayList<>();
        countries.add("Polska");
        countries.add("Niemcy");
        countries.add("Rosja");
        return countries;
    }

    @ModelAttribute("skills")
    public Collection<String> skills() {
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("PHP");
        skills.add("Ruby");
        return skills;
    }


    @GetMapping("/addDetails")
    public String prepareAddForm(Model model) {
        model.addAttribute("personDTO", new PersonDTO());
        return "/add-person-detail-form";
    }

    @PostMapping("/addDetails")
    @ResponseBody
    public String savePersonDetails(PersonDTO personDTO) {
        return personDTO.toString();

    }
}
