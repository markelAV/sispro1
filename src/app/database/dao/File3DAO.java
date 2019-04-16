package app.database.dao;


import app.database.entities.File3;

import java.sql.SQLException;
import java.util.List;

public interface File3DAO {

    //create
    void add(File3 file) throws SQLException;

    //read
    List<File3> getAll() throws SQLException;

    File3 getById(String id) throws SQLException;

    //update
    void update(File3 file) throws SQLException;

    //delete
    void remove(File3 file) throws SQLException;
}
