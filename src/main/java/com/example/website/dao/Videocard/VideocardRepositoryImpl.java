package com.example.website.dao.Videocard;

import com.example.website.entity.Videocard.Videocard;
import com.example.website.entity.Videocard.VideocardType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideocardRepositoryImpl implements VideocardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Videocard> findAll() {
        TypedQuery<Videocard> query = entityManager.createQuery("from Videocard", Videocard.class);
        return query.getResultList();
    }

    @Override
    public Videocard save(Videocard videocard) {
        if (videocard.getId() == null) {
            entityManager.persist(videocard); // для новых сущностей
        } else {
            videocard = entityManager.merge(videocard); // для обновления существующих
        }
        return videocard;
    }

    @Override
    public void delete(Integer id) {
        Videocard videocard = entityManager.find(Videocard.class, id);
        if (videocard != null) {
            entityManager.remove(videocard);
        }
    }

    @Override
    public List<Videocard> getVideocardByManufacturer(VideocardType manufacturer) {
        TypedQuery<Videocard> query = entityManager.createQuery(
                "from Videocard v where v.manufacturer = :manufacturer", Videocard.class);
        query.setParameter("manufacturer", manufacturer);
        return query.getResultList();
    }

    @Override
    public Videocard findById(Integer id) {
        return entityManager.find(Videocard.class, id);
    }
}
