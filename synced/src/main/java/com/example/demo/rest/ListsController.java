package com.example.demo.rest;

import com.example.demo.dao.MyDAO;
import com.example.demo.entity.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is to allow calls from React. CORS errors are dumb
@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class ListsController {
    private final MyDAO myDAO;
    //Constructor Injection: this is telling the spring framework to wire up your
    //dependencies for the usersDAO.
    @Autowired
    public ListsController(@Qualifier("listIMPL") MyDAO myDAO) {
        this.myDAO = myDAO;
    }


    //http://localhost:8080/retrieveAllCollections
    @GetMapping("/retrieveAllCollections")
    public List<Object> findAll() {
        return myDAO.fetchAll();
    }


    @GetMapping("/findCollectionById/{Id}")
    public Object findById(@PathVariable int Id){
        return myDAO.fetchById(Id);
    }


    //http://localhost:8080/addCollection
    @PostMapping("/addCollection")
    public Lists addCollection(@RequestBody Lists cardCollection) {
        cardCollection.setId(0);
        myDAO.save(cardCollection);
        return cardCollection;
    }


    //http://localhost:8080/updateCollection
    @PutMapping("/updateCollection")
    public Lists updateCollection(@RequestBody Lists lists) {
        //this will execute an update instead of a create
        myDAO.save(lists);
        return lists;
    }


    //http://localhost:8080/deleteUser/1
    @DeleteMapping("/deleteCollection/{id}")
    public String deleteCollection(@PathVariable int id) {
        //Creating a tempUser to check to see if a user exists
        Lists cardCollection = (Lists) myDAO.fetchById(id);
        //This will throw an exception if the employee is null
        if(cardCollection == null) {
            return "User doesn't exist";
        }

        //This will execute the deleteByID.
        myDAO.deleteById(id);
        return "Deleted Collection id : " + id;
    }
}
