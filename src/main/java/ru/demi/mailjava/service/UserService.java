package ru.demi.mailjava.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.demi.mailjava.DBException;
import ru.demi.mailjava.dao.UserDAO;
import ru.demi.mailjava.model.User;

/**
 * @author demi
 * @date   22.01.16
 */
public class UserService {

    private final SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void singUp(String login, String password) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDAO dao = new UserDAO(session);
            dao.insertUser(login, password);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public boolean isSingIn(String login, String password) throws DBException {
        User user = getUserByLogin(login);
        return user != null && user.getPassword().equals(password);
    }

    public User getUserByLogin(String login) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UserDAO dao = new UserDAO(session);
            User user = dao.getUserByLogin(login);
            session.close();
            return user;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
}
