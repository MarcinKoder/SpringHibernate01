package pl.coderslab.domain.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.domain.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Author author) {
        entityManager.persist(author);
    }

    public Author update(Author author) {
        return entityManager.merge(author);
    }

    public Author find(Long id) {
        return entityManager.find(Author.class, id);
    }

    public void remove(Long id) {
        Author author = find(id);
        if (author != null) {
            entityManager.remove(author);
        }
    }

    public void remove(Author author) {
        entityManager.remove(
                entityManager.contains(author)
                        ? author
                        : entityManager.merge(author));
    }

    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a").getResultList();
    }
}
