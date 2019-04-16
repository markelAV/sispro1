package app.database.dao;


import app.database.entities.File7;

import java.sql.SQLException;
import java.util.List;

public interface File7DAO {

    //create
    void add(File7 file) throws SQLException;

    //read
    List<File7> getAll() throws SQLException;

    File7 getById(String id) throws SQLException;

    //update
    void update(File7 file) throws SQLException;

    //delete
    void remove(File7 file) throws SQLException;
}
