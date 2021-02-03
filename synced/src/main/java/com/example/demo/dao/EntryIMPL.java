package com.example.demo.dao;

import com.example.demo.entity.Entries;
import com.example.demo.entity.Lists;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EntryIMPL implements  MyDAO{

    //Define field for entity manager
    /*The EntityManager API is used to create and remove persistent entity instances,
        to find entities by their primary key, and to query over entities. */
    private final EntityManager entityManager;

    //Set up constructor injection
    @Autowired
    public EntryIMPL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional //Defines the scope of a single database transaction.
    public List<Object> fetchAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Object> myQuery = currentSession.createQuery("from Entries");
        return myQuery.getResultList();
    }

    @Override
    @Transactional //Defines the scope of a single database transaction.
    public Object fetchById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Entries.class, theId);
    }

    @Override
    @Transactional //Defines the scope of a single database transaction.
    public void save(Object entry) {
        Session currentSession = entityManager.unwrap(Session.class);
        Entries temp = (Entries) entry;
        Query<Object> myQuery = currentSession.createQuery("from Lists where id like :i");
        myQuery.setParameter("i",temp.getListID());
        temp.setLists((Lists) myQuery.getResultList().get(0));
        currentSession.saveOrUpdate(temp);
    }

    @Override
    @Transactional //Defines the scope of a single database transaction.
    public void deleteById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Entries entry = currentSession.get(Entries.class, theId);
        currentSession.delete(entry);
    }
}
