package pl.coderslab.domain.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.domain.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class PublisherDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public Publisher update(Publisher publisher) {
        return entityManager.merge(publisher);
    }

    public Publisher find(Long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void remove(Long id) {
        Publisher publisher = find(id);
        if (publisher != null) {
            entityManager.remove(publisher);
        }
    }

    public void remove(Publisher publisher) {
        entityManager.remove(
                entityManager.contains(publisher)
                        ? publisher
                        : entityManager.merge(publisher));
    }

    public List<Publisher> findAll() {
        return entityManager.createQuery("SELECT p FROM Publisher p", Publisher.class).getResultList();
    }
}
