package web.repository;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Transactional
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}