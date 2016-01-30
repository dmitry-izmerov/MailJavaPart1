package ru.demi.mailjava.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ru.demi.mailjava.model.User;

/**
 * @author demi
 * @date 30.01.16
 */
public class UserDAO {

    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public long insertUser(String login, String password) throws HibernateException {
        return (Long) session.save(new User(login, password));
    }

    public User getUserByLogin(String login) {
        Criteria criteria = session.createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }
}
