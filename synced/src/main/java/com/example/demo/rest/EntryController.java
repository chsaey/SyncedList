package com.example.demo.rest;

import com.example.demo.dao.MyDAO;
import com.example.demo.entity.Entries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is to allow calls from React
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class EntryController {
    private final MyDAO myDAO;

    @Autowired
    public EntryController(@Qualifier("entryIMPL") MyDAO myDAO) {
        this.myDAO = myDAO;
    }


    //http://localhost:8080/retrieveAllEntries
    @GetMapping("/retrieveAllEntries")
    public List<Object> findAll() {
        return myDAO.fetchAll();
    }

    //http://localhost:8080/addEntry
    @PostMapping("/addEntry")
    public Entries addEntry(@RequestBody Entries entry) {
        entry.setId(0);
        myDAO.save(entry);
        return entry;
    }


    //http://localhost:8080/updateEntry
    @PutMapping("/updateEntry")
    public Entries updateEntry(@RequestBody Entries entry) {
        //this will execute an update instead of a create
        myDAO.save(entry);
        return entry;
    }


    //http://localhost:8080/deleteEntry/1
    @DeleteMapping("/deleteEntry/{entryID}")
    public String deleteEntry(@PathVariable int entryID) {

        Entries entry = (Entries) myDAO.fetchById(entryID);


        if (entry == null) {
            throw new RuntimeException("Entry not found: " + entryID);
        }

        //This will execute the deleteByID.
        myDAO.deleteById(entryID);
        return "Deleted Entry id : " + entryID;
    }
}