package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.dao.AuthorDao;
import pl.coderslab.domain.dao.BookDao;
import pl.coderslab.domain.model.Author;
import pl.coderslab.domain.model.Book;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/authors/form", produces = "text/plain;charset=UTF-8")
public class AuthorFormController {

    private AuthorDao authorDao;
    private BookDao bookDao;

    public AuthorFormController(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }

    @ModelAttribute("books")
    public List<Book> books() {
        return bookDao.findAll();
    }

    @GetMapping
    public String prepareListOfAuthors(Model model) {
        model.addAttribute("authors", authorDao.findAll());
        return "all-authors";
    }

    @GetMapping("/add")
    public String prepareAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    @PostMapping("/add")
    public String saveAuthor(@Valid Author author, BindingResult result) {
        if (result.hasErrors()){
            return "author-form";
        }
        if (author.getId() == null) {
            authorDao.save(author);
        }
        return "redirect:/authors/form";
    }

    @GetMapping("/update/{id:[1-9][0-9]*}")
    public String prepareAuthorToEdit(@PathVariable Long id, Model model) {
        Author author = authorDao.find(id);
        if (author == null) {
            return "Nie znaleziono książki";
        }
        model.addAttribute("author", author);
        return "author-form";
    }

    @PostMapping("/update/{id:[1-9][0-9]*}")
    public String editAuthor(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "author-form";
        }
        if (author.getId() != null) {
            authorDao.update(author);
        }
        return "redirect:/authors/form";
    }

    @GetMapping("/confirmDelete/{id:[1-9][0-9]*}")
    public String deleteAuthor(@PathVariable Long id, Model model) {
        Author author = authorDao.find(id);
        model.addAttribute("author", author);
        return "delete-author-confirmation";
    }

    @GetMapping("/delete/{id:[1-9][0-9]*}")
    public String confirmDeletingAuthor(@PathVariable Long id) {
        Author author = authorDao.find(id);
        List<Book> books = author.getBooks();
        for (Book book : books) {
            book.getAuthors().remove(author);
            bookDao.update(book);
        }
        authorDao.remove(author);
        return "redirect:/authors/form";
    }
}