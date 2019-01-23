package pl.coderslab.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.dao.AuthorDao;
import pl.coderslab.domain.model.Author;

@Controller
@RequestMapping(value = "/authors", produces = "text/plain;charset=UTF-8")
public class AuthorController {

    private AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping("/find/{id:[1-9][0-9]*}")
    @ResponseBody
    public String get(@PathVariable Long id) {
        Author author = authorDao.find(id);
        if (author == null) {
            return "Nie znaleziono autora";
        }
        return author.toString();
    }

    @GetMapping("/update/{id:[1-9][0-9]*}")
    @ResponseBody
    public String update(@PathVariable Long id, String firstName, String lastName) {
        Author author = authorDao.find(id);
        if (author == null) {
            return "Nie znalezion autora";
        }

        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.update(author);
        return author.toString();
    }

    @GetMapping("/delete/{id:[1-9][0-9]*}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        Author author = authorDao.find(id);
        if (author == null) {
            return "Nie znaleziono autora";
        }

        authorDao.remove(id);
        return "Usunięto autora o id " + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String create(@RequestParam String firstName,@RequestParam String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.save(author);
        return author.toString();
    }
}
