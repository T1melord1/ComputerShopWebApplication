package com.example.website.dao;

import com.example.website.entity.Videocard;
import com.example.website.entity.VideocardType;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VideocardRepositoryImpl implements VideocardRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Videocard> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Videocard", Videocard.class).getResultList();
    }

    @Override
    public Videocard save(Videocard videocard) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(videocard);
        return videocard;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Videocard v where v.id=:videocardId");
        query.setParameter("videocardId", id);
        query.executeUpdate();
    }

    @Override
    public List<Videocard> getVideocardByManufacturer(VideocardType manufacturer) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Videocard v where v.manufacturer=:manufacturer",Videocard.class);
        query.setParameter("manufacturer", manufacturer);
        List<Videocard> videocards = query.getResultList();
        return videocards;
    }

    @Override
    public Videocard findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Videocard v where v.id=:videocardId");
        query.setParameter("videocardId", id);
        return (Videocard) query.getSingleResult();
    }

}
