package pl.coderslab.domain.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.domain.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Component
@Transactional
public class PersonDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Person person) {
        entityManager.persist(person);
    }

    public Person update(Person person) {
        return entityManager.merge(person);
    }

    public Person find(Long id) {
        return entityManager.find(Person.class, id);
    }

    public void remove(Long id) {
        Person person = find(id);
        if (person != null) {
            entityManager.remove(person);
        }
    }

    public void remove(Person person) {
        entityManager.remove(
                entityManager.contains(person)
                        ? person
                        : entityManager.merge(person));
    }

    public List<Person> findAll() {
        return entityManager.createQuery("SELECT p FROM Person p").getResultList();
    }
}
