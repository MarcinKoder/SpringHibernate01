package pl.coderslab.domain.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.domain.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }

    public Book update(Book book) {
        return entityManager.merge(book);
    }

    public Book find(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void remove(Long id) {
        Book book = find(id);
        if (book != null) {
            entityManager.remove(book);
        }
    }

    public void remove(Book book) {
        entityManager.remove(
                entityManager.contains(book)
                        ? book
                        : entityManager.merge(book));
    }

    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b").getResultList();
    }

    public List<Book> findAllByRating(Double rating) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.rating > :rating")
                .setParameter("rating", rating)
                .getResultList();
    }
    public List<Book> findPropositions(){
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.proposition = true").getResultList();
    }
}
