package app.database.servise;

import app.database.bl.SessionUtil;
import app.database.dao.File3DAO;
import app.database.entities.File3;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class File3Service extends SessionUtil implements File3DAO {
    @Override
    public void add(File3 file) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(file);

        //close session with a transaction
        closeTransactionSesstion();
    }

    @Override
    public List<File3> getAll() throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM FILE3";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(File3.class);
        List<File3> list = query.list();

        //close session with a transaction
        closeTransactionSesstion();

        return list;
    }

    @Override
    public File3 getById(String id) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        String sql = "SELECT * FROM FILE3 WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(File3.class);
        query.setParameter("id", id);

        File3 file= (File3) query.getSingleResult();

        //close session with a transaction
        closeTransactionSesstion();

        return file;
    }

    @Override
    public void update(File3 file) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.update(file);

        //close session with a transaction
        closeTransactionSesstion();
    }

    @Override
    public void remove(File3 file) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.remove(file);

        //close session with a transaction
        closeTransactionSesstion();
    }
}
