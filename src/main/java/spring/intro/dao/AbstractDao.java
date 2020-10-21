package spring.intro.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {

    private SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T add(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add entity: " + t, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    public Optional<T> getById(Long id, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
            Root<T> root = query.from(clazz);
            query.select(root).where(criteriaBuilder.equal(root.get("id"), id));
            return Optional.of(session.createQuery(query).getSingleResult());
        }
    }

    public List<T> getAll(Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
            Root<T> root = query.from(clazz);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
}
