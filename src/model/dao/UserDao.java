package model.dao;

import model.entities.User;

import java.util.List;

public interface UserDao {

    List<String> readAllUsers();
    void importToDB(List<String> users);
    List<String> findAll();

}
