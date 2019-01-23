package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.domain.model.Book;
import pl.coderslab.domain.model.Publisher;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.util.Formatter;
import java.util.Set;

@Controller
@RequestMapping(value = "/books/validate", produces = "text/plain;charset=UTF-8")
public class ValidationController {

    private Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping
    @ResponseBody
    public String validate() {
        Book book = new Book();
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        StringBuilder result = new StringBuilder();
        for (ConstraintViolation<Book> constraint : constraints) {
            String message = constraint.getMessage();
            Path propertyPath = constraint.getPropertyPath();
            Object invalidValue = constraint.getInvalidValue();
            Formatter formatter = new Formatter();
            formatter.format("Dla pola %s naruszono wymaganie wartością %s, treść błędu: %s", propertyPath, invalidValue, message);
            result.append(formatter).append("\n");
        }
        return result.toString();
    }

    @GetMapping("/form")
    public String validateBook(Model model){
        Book book = new Book();
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        model.addAttribute("errors",constraints);
        return "errors";
    }
}
