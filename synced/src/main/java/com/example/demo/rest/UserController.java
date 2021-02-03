package com.example.demo.rest;

import com.example.demo.dao.MyDAO;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is to allow calls from React. CORS errors are dumb
@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class UserController {
    private final MyDAO myDAO;

    //Constructor Injection: this is telling the spring framework to wire up your
    //dependencies for the usersDAO.
    @Autowired
    public UserController(@Qualifier("userIMPL") MyDAO myDAO) {
        this.myDAO = myDAO;
    }

    //This is a GET request that will read a list of all the users.
    //http://localhost:8080/retrieveAllUsers
    @GetMapping("/retrieveAllUsers")
    public List<Object> findAll() {
        return myDAO.fetchAll();
    }

    //Get a user by their ID
    @GetMapping("/findUserById/{Id}")
    public Object findById(@PathVariable int Id){
        return myDAO.fetchById(Id);
    }

    //This is a POST request to add a new user.
    //http://localhost:8080/addUser
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        //also just in case they pass an id in JSON .... set id to o
        //this is to force a save of new item .... instead of update
        user.setId(0);

        //This will call the usersDqoImpl.save method to save a new user
        //through the usersDAO interface SPRING
        myDAO.save(user);
        return user;
    }

    //This is a PUT request to update an existing user.
    //http://localhost:8080/updateUser
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User updateUser) {
        //this will execute an update instead of a create
        myDAO.save(updateUser);
        return updateUser;
    }

    //This is a DELETE request to delete an existing user.
    //http://localhost:8080/deleteUser/1
    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable int userId) {
        //Creating a tempUser to check to see if a user exists
        User tempUser = (User) myDAO.fetchById(userId);

        //This will throw an exception if the employee is null
        if(tempUser == null) {
            return "User doesn't exist";
        }

        //This will execute the deleteByID.
        myDAO.deleteById(userId);
        return "Deleted user id : " + userId;
    }
}
