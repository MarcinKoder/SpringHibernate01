package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.dao.PublisherDao;
import pl.coderslab.domain.model.Publisher;

import java.util.Objects;

@Controller
@ResponseBody
@RequestMapping(value = "/publishers", produces = "text/plain;charset=UTF-8")
public class PublisherController {

    private PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @GetMapping("/find/{id:[1-9][0-9]*}")
    public String get(@PathVariable Long id) {
        Publisher publisher = publisherDao.find(id);
        if (Objects.isNull(publisher)) {
            return "Nie znaleziono wydawcy";
        }

        return publisher.toString();
    }

    @GetMapping("/update/{id:[1-9][0-9]*}")
    public String update(@PathVariable Long id, String name) {
        Publisher publisher = publisherDao.find(id);
        if (Objects.isNull(publisher)) {
            return "Nie znaleziono wydawcy";
        }

        publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @GetMapping("/delete/{id:[1-9][0-9]*}")
    public String delete(@PathVariable Long id) {
        Publisher publisher = publisherDao.find(id);
        if (Objects.isNull(publisher)) {
            return "Nie znaleziono wydawcy";
        }

        publisherDao.remove(id);
        return "Usunięto wydawcę o id: " + id;
    }

    @GetMapping("/create")
    public String create(@RequestParam String name) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherDao.save(publisher);
        return publisher.toString();
    }
}