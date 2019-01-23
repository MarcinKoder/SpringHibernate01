package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.annotations.ValidationGroupProposition;
import pl.coderslab.domain.dao.AuthorDao;
import pl.coderslab.domain.dao.BookDao;
import pl.coderslab.domain.dao.PublisherDao;
import pl.coderslab.domain.model.Author;
import pl.coderslab.domain.model.Book;
import pl.coderslab.domain.model.Publisher;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/propositions")
public class PropositionController {
    private BookDao bookDao;
    private PublisherDao publisherDao;
    private AuthorDao authorDao;

    public PropositionController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers() {
        return publisherDao.findAll();
    }
    @ModelAttribute("authors")
    public List<Author> authors() {
        return authorDao.findAll();
    }

    @GetMapping
    public String prepareListOfPropositions(Model model) {
        model.addAttribute("books", bookDao.findPropositions());
        return "all-books";
    }

    @GetMapping("/add")
    public String prepareAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/add")
    public String saveBook(@Validated({ValidationGroupProposition.class}) Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "book-form";
        }
        if (book.getId() == null) {
            book.setProposition(true);
            bookDao.save(book);
        }
        return "redirect:/propositions";
    }

    @GetMapping("/update/{id:[1-9][0-9]*}")
    public String prepareBookToEdit(@PathVariable Long id, Model model) {
        Book book = bookDao.find(id);
        if (book == null) {
            return "Nie znaleziono książki";
        }
        model.addAttribute("book", book);
        return "book-form";
    }

    @PostMapping("/update/{id:[1-9][0-9]*}")
    public String editBook(@Validated({ValidationGroupProposition.class})Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "book-form";
        }
        if (book.getId()!=null) {bookDao.update(book);}
        return "redirect:/propositions";
    }

    @GetMapping("/confirmDelete/{id:[1-9][0-9]*}")
    public String deleteBook(@PathVariable Long id, Model model) {
        Book book = bookDao.find(id);
        model.addAttribute("book", book);
        return "delete-book-confirmation";
    }
    @GetMapping("/delete/{id:[1-9][0-9]*}")
    public String confirmDeletingBook(@ModelAttribute Book book){
        bookDao.remove(book);
        return "redirect:/propositions";
    }
}
