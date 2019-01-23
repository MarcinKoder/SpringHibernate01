package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.dao.AuthorDao;
import pl.coderslab.domain.dao.BookDao;
import pl.coderslab.domain.dao.PublisherDao;
import pl.coderslab.domain.model.Author;
import pl.coderslab.domain.model.Book;
import pl.coderslab.domain.model.Publisher;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/books", produces = "text/plain;charset=UTF-8")
public class BookController {

    private BookDao bookDao;
    private PublisherDao publisherDao;
    private AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping("/find/{id:[1-9][0-9]*}")
    @ResponseBody
    public String get(@PathVariable Long id) {
        Book book = bookDao.find(id);
        if (book == null) {
            return "Nie znaleziono";
        }

        return book.toString();
    }

    @GetMapping("/update/{id:[1-9][0-9]*}")
    @ResponseBody
    public String update(@PathVariable Long id, String title, String description) {
        Book book = bookDao.find(id);
        if (book == null) {
            return "Nie znaleziono";
        }

        book.setTitle(title);
        book.setDescription(description);
        bookDao.update(book);
        return book.toString();
    }

    @GetMapping("/delete/{id:[1-9][0-9]*}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        Book book = bookDao.find(id);
        if (book == null) {
            return "Nie znaleziono";
        }

        bookDao.remove(book);
        return "Usunięto książkę o id: " + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String create(@RequestParam String title, @RequestParam(required = false) String description,
                         @RequestParam(required = false) Double rating,
                         @RequestParam Long publisherId,
                         @RequestParam(name = "authorId") List<Long> authorIds) {
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setRating(rating);

        Publisher publisher = publisherDao.find(publisherId);
        if (publisher == null) {
            return "Nie podano wydawcy";
        }
        book.addPublisher(publisher);

        if (authorIds.isEmpty()) {
            return "Nie podano autorów";
        }

        List<Author> authors = authorIds
                .stream()
                .map(authorDao::find)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (authors.size() != authorIds.size()) {
            return "Nie poprawna lista autorów";
        }

        authors.forEach(book::addAuthor);
        bookDao.save(book);
        return book.toString();
    }
}
