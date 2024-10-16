package com.example.website.dao.User;

import com.example.website.entity.User.User;
import com.example.website.entity.Videocard.Videocard;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;

    @Override
    public User add(User user) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User v where v.username=:username", User.class);
        query.setParameter("username", username);
        return (User) query.getSingleResult();

    }
}
