package pl.coderslab.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.domain.dao.BookDao;
import pl.coderslab.domain.model.Book;

public class BookConverter implements Converter<String, Book> {
    private BookDao bookDao;

    @Autowired
    public void setAuthorDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book convert(String s) {
        return bookDao.find(Long.parseLong(s));
    }
}
