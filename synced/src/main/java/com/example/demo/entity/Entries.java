package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


//Flashcard Entity
@Entity //This will let Java know that this is an entity that we are going to map to a database table.
@Table(name = "entries") //This is for the actual name of the database table we are mapping to the class.
// When using lazy loading, we can tell Jackson to ignore helpful garbage hibernate adds to classes
//Fixes the issues of serializing entities to/from the DB
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Entries {

    @Id //This will map the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This will auto increment your primary key
    @Column(name = "id") //This is mapping the primary key to the id column in the table.
    private int id;

    @Column(name = "entry")
    private String entry;

    @ManyToOne(fetch = FetchType.LAZY,  optional = false) // Many flashcards can belong to one set
    @OnDelete(action = OnDeleteAction.CASCADE) // One a set is deleted, cards of the set are also deleted
    @JoinColumn(name = "listID", nullable = false) // foreign key, column to join on
    @JsonIgnore// hide field from parser
    private Lists lists;

    @Column(name = "listID", updatable = false, insertable = false)
    private int listID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Lists getLists() {
        return lists;
    }

    public void setLists(Lists lists) {
        this.lists = lists;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

}