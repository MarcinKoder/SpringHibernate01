package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.dao.BookDao;
import pl.coderslab.domain.dao.PublisherDao;
import pl.coderslab.domain.model.Book;
import pl.coderslab.domain.model.Publisher;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/publishers/form", produces = "text/plain;charset=UTF-8")
public class PublisherFormController {

    private PublisherDao publisherDao;
    private BookDao bookDao;

    public PublisherFormController(PublisherDao publisherDao, BookDao bookDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
    }

    @GetMapping
    public String prepareListOfPublishers(Model model) {
        model.addAttribute("publishers", publisherDao.findAll());
        return "all-publishers";
    }

    @GetMapping("/add")
    public String prepareAddPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher-form";
    }

    @PostMapping("/add")
    public String savePublisher(@Valid Publisher publisher, BindingResult result) {
        if (result.hasErrors()) {
            return "publisher-form";
        }
        if (publisher.getId() == null) {
            publisherDao.save(publisher);
        }
        return "redirect:/publishers/form";
    }

    @GetMapping("/update/{id:[1-9][0-9]*}")
    public String prepareBookToEdit(@PathVariable Long id, Model model) {
        Publisher publisher = publisherDao.find(id);
        if (publisher == null) {
            return "Nie znaleziono książki";
        }
        model.addAttribute("publisher", publisher);
        return "publisher-form";
    }

    @PostMapping("/update/{id:[1-9][0-9]*}")
    public String editPublisher(@Valid Publisher publisher, BindingResult result) {
        if (result.hasErrors()) {
            return "publisher-form";
        }
        if (publisher.getId() != null) {
            publisherDao.update(publisher);
        }
        return "redirect:/publishers/form";
    }

    @GetMapping("/confirmDelete/{id:[1-9][0-9]*}")
    public String deletePublisher(@PathVariable Long id, Model model) {
        Publisher publisher = publisherDao.find(id);
        model.addAttribute("publisher", publisher);
        return "delete-publisher-confirmation";
    }

    @GetMapping("/delete/{id:[1-9][0-9]*}")
    public String confirmDeletingPublisher(@PathVariable Long id) {
        Publisher publisher = publisherDao.find(id);
        List<Book> books = publisher.getBooks();
        for (Book book : books) {
            book.setPublisher(null);
            bookDao.update(book);
        }
        publisherDao.remove(publisher);
        return "redirect:/publishers/form";
    }
}
